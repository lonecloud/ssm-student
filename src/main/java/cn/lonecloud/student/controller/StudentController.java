package cn.lonecloud.student.controller;

import cn.lonecloud.student.common.CommonController;
import cn.lonecloud.student.exception.BusinessException;
import cn.lonecloud.student.pojo.Student;
import cn.lonecloud.student.service.IStudentService;
import cn.lonecloud.student.vo.PageListVO;
import cn.lonecloud.student.vo.R;
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

    /**
     * 显示所有的学生
     * @return
     */
    @GetMapping("/list")
    public String list(){
        return "list";
    }

    /**
     * 获取数据
     * @param offset 开始
     * @param limit 限制条件
     * @return
     */
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

    /**
     * 查询某个学生
     * @param id
     * @return
     */
    @GetMapping("select/{id}")
    @ResponseBody
    public R<Student> selectOne(@PathVariable("id") String id){
        return studentService.selectOne(id);
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R<Student> add(Student student){
        return studentService.add(student);
    }

    /**
     * 更新学生
     * @param student
     * @param id
     * @return
     */
    @PostMapping("/update/{id}")
    @ResponseBody
    public R update(Student student,@PathVariable("id") Integer id){
        student.setId(id);
        return studentService.update(student);
    }

    /**
     * 删除某个学生
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id")String id){
        return studentService.delete(id);
    }

}
