package cn.lonecloud.student.util;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lonecloud
 * @version v1.0
 * @date 2017/10/21
 */
public class ExcelUtils {

    private static final String XLS="xls";
    private static final String XLSX="xlsx";

    /**
     * 导出数据
     * @param file
     * @return
     */
    public static List<String[]> readExcelContent(File  file) {
        List<String[]>  content = Lists.newArrayList();
        String str = "";
        POIFSFileSystem fs=null;
        HSSFWorkbook wb=null;
        HSSFSheet sheet=null;
        HSSFRow row=null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(file));
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 0; i < rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            String[] rowContents=new String[colNum];
            while (row!=null&&j < colNum) {
                rowContents[j]=getCellFormatValue(row.getCell(j));
                j++;
            }
            content.add(rowContents);
        }
        return content;
    }

    /**
     * 获取cell值
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:
                case HSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        //cellvalue = cell.getDateCellValue().toLocaleString();

                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRING
                case HSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 导出Excel
     * @param dataList 数据
     * @param filePath 文件路径
     * @param columnNames 列名字
     * @param excludeFiledName 排除的数据值名
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     */
    public static File exportExcel(List dataList, String filePath,String[] columnNames,
                                   String... excludeFiledName) throws IOException, IllegalAccessException {
        File file=new File(filePath);
        if (!file.exists()){
            file.createNewFile();
        }
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet();
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //设置标题信息
        for (int i = 0; i < columnNames.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < dataList.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            Object obj = dataList.get(i);
            if (obj!=null){
                Class<?> clazz = obj.getClass();
                Field[] declaredFields = clazz.getDeclaredFields();
                int j=0;
                for (Field field:declaredFields) {
                    if (Arrays.asList(excludeFiledName).stream().equals(field.getName())){
                        continue;
                    }
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    row.createCell(j++).setCellValue(value!=null?value.toString():"-");
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();
        return file;
    }
}
