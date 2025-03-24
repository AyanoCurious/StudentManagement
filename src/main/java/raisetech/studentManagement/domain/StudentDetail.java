//受講生の詳細情報（学生情報＋コース情報）」を持つクラス
package raisetech.studentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

@Getter //(Lombok) 自動的にgetStudent() と getStudentsCourses() を生成する
@Setter //(Lombok) 自動的にsetStudent() と setStudentsCourses() を生成する

public class StudentDetail { //受講生の基本情報

  private Student student; //Studentクラスのオブジェクトをフィールドとして持つ（Studentクラスのデータそのもの）
  private List<StudentsCourses> studentsCourses;//受講生コースの情報のリスト

}