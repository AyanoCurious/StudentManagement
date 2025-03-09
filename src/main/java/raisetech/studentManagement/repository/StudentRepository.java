package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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

  @Insert("INSERT INTO students (full_name, furigana, nickname, email, location, gender, age, remark, is_delete, marks) VALUES (#{fullName}, #{furigana}, #{nickname}, #{email}, #{location}, #{gender}, #{age}, #{remark}, #{isDeleted}, #{marks})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

//コース情報
  /*@Insert("INSERT INTO students_courses (id, student_id, course_name, course_start_at, course_end_at) VALUES (#{id}, #{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  void insertStudentCourse(StudentsCourses studentCourse);*/
}