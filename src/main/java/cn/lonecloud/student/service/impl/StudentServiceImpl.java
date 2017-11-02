package cn.lonecloud.student.service.impl;

import cn.lonecloud.student.dao.StudentMapper;
import cn.lonecloud.student.pojo.Student;
import cn.lonecloud.student.service.IStudentService;
import cn.lonecloud.student.util.ExcelUtils;
import cn.lonecloud.student.util.RequestUtils;
import cn.lonecloud.student.vo.PageListVO;
import cn.lonecloud.student.vo.R;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author lonecloud
 * @version v1.0
 * @date 上午9:27 2017/10/27
 */
@Service
public class StudentServiceImpl implements IStudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    StudentMapper studentMapper;

    @Override
    public PageListVO<Student> listByPage(int offset, int limit) {
        List<Student> studentList = studentMapper.selectByPage(offset, limit);
        int count = studentMapper.selectCount();
        return new PageListVO<Student>(count, studentList);
    }

    @Override
    public R<Student> selectOne(String id) {
        if (StringUtils.isBlank(id)) {
            return R.error("id不能为空");
        }
        Student student = studentMapper.selectByPrimaryKey(Integer.valueOf(id));
        return R.success(student);
    }

    @Override
    public R delete(String id) {
        if (StringUtils.isBlank(id)) {
            return R.error("id不能为空");
        }
        int i = studentMapper.deleteByPrimaryKey(Integer.valueOf(id));
        return R.success();
    }

    @Override
    public R<Student> add(Student student) {

        int insert = studentMapper.insert(student);
        if (insert == 0) {
            return R.error("添加失败");
        } else {
            return R.success();
        }
    }

    @Override
    public R update(Student student) {
        if (student != null & student.getId() != null) {
            studentMapper.updateByPrimaryKeySelective(student);
        } else {
            return R.error("参数传递错误");
        }
        return R.success();
    }

    @Override
    public R importStudentExcel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        String realFileName = UUID.randomUUID().toString() + "." + fileType;
        String filePath = RequestUtils.getRequest().getServletContext().getRealPath("/WEB-INF");
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File tempFile = new File(filePath, realFileName);
        try {
            file.transferTo(tempFile);
            //处理Excel数据
            List<String[]> readExcelContent = ExcelUtils.readExcelContent(tempFile);
            //构建数据
            List<Student> studentList = buildStudentList(readExcelContent);
            int count = studentMapper.insertBatch(studentList);
            return R.success();
        } catch (IOException e) {
            logger.error(e.getMessage(),e.toString());
            return R.error("导入失败");
        }
    }

    @Override
    public File exportStudent() {
        List<Student> studentList = studentMapper.seletAll();
        try {
            File file = ExcelUtils.exportExcel(studentList, RequestUtils.getRequest().getSession().getServletContext().getRealPath(File.separator) +
                            UUID.randomUUID().toString() + ".xls",
                    new String[]{"id", "名字", "年龄", "学科", "创建时间"}, "updateTime");
            return file;
        } catch (IOException e) {
            logger.error(e.getMessage(),e.toString());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e.toString());
        }
        return null;
    }

    @Override
    public PageListVO<Student> searchByPage(String search, int offset, int limit) {
        if (search != null && !search.isEmpty()) {
            search="%"+search+"%";
            List<Student> studentList = studentMapper.searchByPage(search, offset, limit);
            int count = studentMapper.selectSearchCount(search, offset, limit);
            return new PageListVO<Student>(count, studentList);
        }else {
            return this.listByPage(offset,limit);
        }
    }

    private List<Student> buildStudentList(List<String[]> readExcelContent) {

        List<Student> studentList = Lists.newArrayList();
        readExcelContent.remove(0);//删除第一行标示
        readExcelContent.stream().forEach((items) -> {
//            不设置id
            Student student = new Student();
            //student.setId(Integer.valueOf(items[0]));
            student.setName(items[1]);
            //判断年龄为数字;
            int age=0;
            if(items[2]!=null&&NumberUtils.isNumber(items[2])){
                age=(int)Double.parseDouble(items[2]);
            }
            student.setAge(age);
            student.setMajor(items[3]);
            student.setCreateTime(new Date());
            studentList.add(student);
        });
        return studentList;
    }
}
