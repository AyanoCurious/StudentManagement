package raisetech.studentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM student WHERE name = #{name}")
  Student searchByName(String name);

  @Insert("INSERT INTO student (name, age) VALUES (#{name}, #{age})")
  void registerStudent(Student student);

  @Update("UPDATE student SET age = #{age} WHERE name = #{name}")
  void update(Student student);

  @Delete("DELETE FROM student WHERE name = #{name}")
  void delete(Student student);

  @Select("SELECT * FROM student")
  List<Student> findAll();
}

