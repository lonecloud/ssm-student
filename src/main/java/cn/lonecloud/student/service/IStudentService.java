package cn.lonecloud.student.service;

import cn.lonecloud.student.pojo.Student;
import cn.lonecloud.student.vo.PageListVO;
import cn.lonecloud.student.vo.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author lonecloud
 * @version v1.0
 * @date 上午9:27 2017/10/27
 */
public interface IStudentService {


    PageListVO<Student> listByPage(int offset, int limit);

    R<Student> selectOne(String id);

    R delete(String id);

    R<Student> add(Student student);

    R update(Student student);

    R importStudentExcel(MultipartFile file);

    File exportStudent();

    PageListVO<Student> searchByPage(String search, int offset, int limit);
}
