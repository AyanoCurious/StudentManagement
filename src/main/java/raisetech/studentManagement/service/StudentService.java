package raisetech.studentManagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;


@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudents(int age) {
    //年齢が30代の人のみを抽出
    //抽出したリストをコントローラーに渡す
    List<Student> allStudents = repository.findAllStudents();
    List<Student> studentsThirties = new ArrayList<>();
    for (Student student : allStudents) {
      if (student.getAge() >= age && student.getAge() <= age + 9) {
        studentsThirties.add(student);
      }
    }
    return studentsThirties;
  }

  public List<StudentsCourses> searchStudentsCourses() {
    List<StudentsCourses> allCourses = repository.searchStudentCourse();
    List<StudentsCourses> javaCourses = new ArrayList<>();
    //絞り込み検索で「Javaコース」のコース情報のみを抽出
    //抽出したリストをコントローラーに渡す
    for (StudentsCourses course : allCourses) {
      if ("Java Programming".equals(course.getCourseName())) {
        javaCourses.add(course);
      }
    }
    return javaCourses;
  }

  // 受講生を検索するメソッド
  public List<Student> searchStudentList() {
    return repository.findAllStudents();
  }

  //全てのコース情報を取得するメソッド
  public List<StudentsCourses> findAllStudentsCourses() {
    return repository.searchStudentCourse();
  }

  //新規登録
  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());//受講生のデータををDBに登録
    //TODO:コース情報登録も行う
    for (StudentsCourses studentsCourse : studentDetail.getStudentsCourses()) {
      studentsCourse.setStudentId(studentDetail.getStudent().getId());//学生IDをコース情報に登録
      studentsCourse.setCourseStartAt(LocalDateTime.now());//開始日を現在に設定
      studentsCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));//終了日を1年後に設定
      repository.registerStudentsCourses(studentsCourse);//コース情報をDBに登録
    }
  }

  //更新
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
  }

  public StudentDetail searchStudentById(int id) {
    Student student = repository.findStudentById(id);
    List<StudentsCourses> studentCourses = repository.searchStudentCourseByStudentId(student.getId());
    StudentDetail studentDetail = new StudentDetail();//実体を持たせる
    studentDetail.setStudent(student);//newがあるから使える
    studentDetail.setStudentsCourses(studentCourses);
    return studentDetail;
  }
}

