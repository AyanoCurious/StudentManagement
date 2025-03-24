package raisetech.studentManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.repository.StudentRepository;

@SpringBootApplication

public class StudentManagementApplication {


  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }
}
/*課題が難しい
1.springイニシャライザー　第2章まで戻る
2.授業と復習同時進行
3.復習すると気づきを得られる
4.web検索する ⇒ chatGPT*/