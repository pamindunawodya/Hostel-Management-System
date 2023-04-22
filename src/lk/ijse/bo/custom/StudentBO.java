package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;
import org.hibernate.Session;


import java.util.List;

public interface StudentBO extends SuperBo {
    List<String> getAllStudents();

   boolean addStudent(StudentDTO dto)  ;

   boolean updateStudent(StudentDTO dto) ;

   List<Student> existStudent(String id) ;

   boolean deleteCustomer(String id) ;

   String generateNewCustomerID() ;

    List<StudentDTO> searchStudentByText(String text);

     StudentDTO findByPk(String pk) ;
}
