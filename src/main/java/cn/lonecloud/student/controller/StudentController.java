package cn.lonecloud.student.controller;

import cn.lonecloud.student.common.CommonController;
import cn.lonecloud.student.exception.BusinessException;
import cn.lonecloud.student.pojo.Student;
import cn.lonecloud.student.service.IStudentService;
import cn.lonecloud.student.vo.PageListVO;
import cn.lonecloud.student.vo.R;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lonecloud
 * @version v1.0
 * @date 下午11:41 2017/10/26
 */
@Controller
@RequestMapping("/student")
public class StudentController extends CommonController{

    @Autowired
    IStudentService studentService;


    @GetMapping("/list")
    public String list(){
        return "list";
    }

    @GetMapping("/loadListData")
    @ResponseBody
    public Object loadListData(@RequestParam(value = "offset",defaultValue = "offset",required = false) int offset,
                       @RequestParam(value = "limit",defaultValue = "10",required = false) int limit){
        try {
            PageListVO<Student> pageListVO=studentService.listByPage(offset,limit);
            return pageListVO;
        }catch (Exception e){
            if (e instanceof BusinessException){
                logger.debug(e.getMessage());
            }
            logger.error(e.getStackTrace().toString(),e.getMessage());
        }
        return new PageListVO<>(0);
    }
    @GetMapping("select/{id}")
    @ResponseBody
    public R<Student> selectOne(@PathVariable("id") String id){
        return studentService.selectOne(id);
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id")String id){
        return studentService.delete(id);
    }

}
