package raisetech.studentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;
  private String fullName;
  private String furigana;
  private String nickname;
  private String email;
  private String location;
  private String gender;
  private int age;
  private String remark;
  private boolean isDeleted;
  private String marks;
}


