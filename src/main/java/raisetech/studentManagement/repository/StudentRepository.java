package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentCourse();


  @Select("SELECT * FROM students WHERE age >= 30 AND age <= 39")
  List<Student> searchStudentThirties();

  @Select("SELECT * FROM students_courses WHERE course_name = 'Java Programming'")
  List<StudentsCourses> searchStudentCourseJava();

}