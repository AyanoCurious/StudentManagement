package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;


@Mapper
public interface StudentRepository {

  //全ての生徒情報
  @Select("SELECT * FROM students")
  List<Student> findAllStudents();

  // 学生IDを使ってコース情報を取得する
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> getCoursesByStudentId(int studentId);


  @Select("SELECT * FROM students WHERE age >= 30 AND age <= 39")
  List<Student> searchStudentThirties();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentCourse();

  @Insert("INSERT INTO students (full_name, furigana, nickname, email, location, gender, age, remark, is_delete, marks) VALUES (#{fullName}, #{furigana}, #{nickname}, #{email}, #{location}, #{gender}, #{age}, #{remark}, #{isDeleted}, #{marks})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  //コース情報
  @Insert("INSERT INTO students_courses (student_id, course_name, course_start_at, course_end_at) "
      + "VALUES (#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentCourse);

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(int id); // 受講生IDで検索するメソッド

  @Update(
      "UPDATE students SET full_name = #{fullName}, furigana = #{furigana}, nickname = #{nickname}, "
          + "email = #{email}, location = #{location}, gender = #{gender}, age = #{age}, remark = #{remark}, "
          + "is_delete = #{isDeleted}, marks = #{marks} WHERE id = #{id}")
  void updateStudent(Student student);

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findStudentById(int id);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> searchStudentCourseByStudentId(int studentId);
}
