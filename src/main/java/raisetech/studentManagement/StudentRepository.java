package raisetech.studentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentCourse();

  @Select("SELECT * FROM students")
  List<Student> searchStudent();
}







