package cn.lonecloud.student.service.impl;

import cn.lonecloud.student.dao.StudentMapper;
import cn.lonecloud.student.pojo.Student;
import cn.lonecloud.student.service.IStudentService;
import cn.lonecloud.student.vo.PageListVO;
import cn.lonecloud.student.vo.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lonecloud
 * @version v1.0
 * @date 上午9:27 2017/10/27
 */
@Service
public class StudentServiceImpl implements IStudentService {

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
        }else{
            return R.error("参数传递错误");
        }
        return R.success();
    }
}
