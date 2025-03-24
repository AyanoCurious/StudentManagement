//このPJの主役のクラス
package raisetech.studentManagement.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.studentManagement.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;


@Controller
public class StudentController {

  private StudentService service; // serviceというフィールド変数のこと
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
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentsCourses();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));
    return "studentList";
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();//StudentDetailの空のオブジェクトを作成
    studentDetail.setStudentsCourses(
        Arrays.asList(new StudentsCourses()));//空のコース情報をセット　model:Thymeleafにデータを渡す役割
    model.addAttribute("studentDetail", studentDetail);//thymeleafに渡す
    return "registerStudent";//registerStudent.htmlを表示
  }

  @PostMapping("/updateStudent") //受講生のIDが入る
  public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {  //入力してもしエラーがある場合
      return "updateStudent"; //updateStudent.htmlを表示
    }
    service.updateStudent(studentDetail);//ここでDBに保存
    return "redirect:/studentsList"; //登録完了後、/studentsListにリダイレクト
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {  //入力してもしエラーがある場合
      return "registerStudent"; //registerStudent.htmlを表示
    }
    service.registerStudent(studentDetail);//ここでDBに保存
    return "redirect:/studentsList"; //登録完了後、/studentsListにリダイレクト
  }

  // StudentController クラス
  @GetMapping("/student/{id}")
  public String getStudent(@PathVariable int id, Model model) {
    StudentDetail studentDetail = service.searchStudentById(id); // serviceから詳細情報を取得
    model.addAttribute("studentDetail", studentDetail); // モデルに追加してThymeleafに渡す
    return "updateStudent";
  }
}


