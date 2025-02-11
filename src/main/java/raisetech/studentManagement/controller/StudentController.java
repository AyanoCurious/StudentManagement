package raisetech.studentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;


  @RestController
  public class StudentController {

    private StudentService service;
    private StudentConverter converter;

    @Autowired
    public StudentController(StudentService service, StudentConverter converter) {
      this.service = service;
      this.converter = converter;
    }

    @GetMapping("/studentsThirties")
    public List<StudentDetail> getStudentsThirties(int age) {
      List<Student> students = service.searchStudents(age);
      List<StudentsCourses> studentsCourses = service.searchStudentsCourses();

      return converter.convertStudentDetails(students, studentsCourses);
    }

    @GetMapping("/studentsOnlyJavaCourses")
    public List<StudentsCourses> getStudentsOnlyJavaCourses() {
      return service.searchStudentsCourses();
    }

    @GetMapping("/studentsList")
    public List<StudentDetail> getStudentList() {
      List<Student> students = service.searchStudentList();
      List<StudentsCourses> studentsCourses = service.searchStudentsCourses();

      return converter.convertStudentDetails(students, studentsCourses);
    }
  }


