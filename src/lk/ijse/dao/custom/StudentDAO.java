package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDAO  extends CrudDAO<Student> {

 List<Student> searchRoomByText(String text);
 public Student findByPk(String pk, Session session);


}