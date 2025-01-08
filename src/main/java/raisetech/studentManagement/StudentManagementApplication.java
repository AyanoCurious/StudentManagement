package raisetech.studentManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  @Autowired
  private StudentRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }

  @GetMapping("/studentInfo")
  public String getStudentInfo(@RequestParam String name) {
    Student student = repository.searchByName(name);
    if (student != null) {
      return student.getName() + " " + student.getAge() + "歳";
    } else {
      return "Student not found.";
    }
  }

  @PostMapping("/students")
  public String createStudentInfo(@RequestParam String name, @RequestParam int age) {
    Student student = new Student(name, age);
    repository.registerStudent(student);
    return "Added: " + name + " " + age + " years old.";
  }

  @GetMapping("/allStudents")
  public List<Student> getAllStudents() {
    return repository.findAll();
  }

  @PutMapping("/studentInfo")
  public String updateStudentInfo(@RequestParam String name, @RequestParam int age) {
    Student student = repository.searchByName(name);
    if (student != null) {
      student.setAge(age);
      repository.update(student);
      return "Updated: " + name + " " + age + " years old.";
    } else {
      return "Student not found.";
    }
  }

  @DeleteMapping("/studentInfo")
  public String deleteStudentInfo(@RequestParam String name) {
    Student student = repository.searchByName(name);
    if (student != null) {
      repository.delete(student); // deleteメソッドをStudentRepositoryに追加
      return "Deleted: " + name;
    } else {
      return "Student not found.";
    }
  }
}



