package raisetech.studentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
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
      List<Student> allStudents = repository.searchStudent();
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

    public List<Student> searchStudentList() {
      return repository.searchStudent();
    }

    public List<StudentsCourses> findAllStudentsCourses() {
      return repository.searchStudentCourse();
    }
  }


