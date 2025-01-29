package raisetech.studentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentsThirties")
  public List<Student> getStudentsThirties() {
    return service.searchStudents();

  }

  @GetMapping("/studentsOnlyJavaCourses")
  public List<StudentsCourses> getStudentsOnlyJavaCourses() {
    return service.searchStudentsCourses();
  }
}