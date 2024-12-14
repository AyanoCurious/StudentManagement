package raisetech.studentManagement;

import java.util.HashMap;
import java.util.Map;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

  private Map<String, String> students = new HashMap<>();

  {

  }

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);


  }

  @GetMapping("/studentInfo")
  public String getStudentInfo(@RequestParam String name) {
    String age = students.get(name);
    return age != null ? name + " is " + age + " years old." : "Student not found.";
  }

  @PostMapping("/studentInfo")
  public String setStudentInfo(@RequestParam String name, @RequestParam String age) {
    students.put(name, age);
    return "Added: " + name + " " + age + " years old.";

  }

  @GetMapping("/allStudents")
  public Map<String, String> getAllStudents() {
    return students;

  }
}









