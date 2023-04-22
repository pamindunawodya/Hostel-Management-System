package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StudentBOimpl implements StudentBO {
    private Session session;
    private Transaction transaction;


    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<String> getAllStudents() {
        List<String> stringList = new ArrayList<>();
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        try{
            stringList =  studentDAO.getAll(session);
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
        }finally {
            session.close();
        }
        return stringList;
    }

    @Override
    public boolean addStudent(StudentDTO dto) {
        return studentDAO.add(new Student(dto.getId(),dto.getName(), dto.getAddress(), dto.getContact(), dto.getGender()));

    }

    @Override
    public boolean updateStudent(StudentDTO dto) {
        System.out.println(studentDAO.update(new Student(dto.getId(),dto.getName(), dto.getAddress(), dto.getContact(), dto.getGender())));
        return studentDAO.update(new Student(dto.getId(),dto.getName(), dto.getAddress(), dto.getContact(), dto.getGender()));

    }

    @Override
    public List<Student> existStudent(String id) {
       return  null;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return studentDAO.delete(id);

    }

    @Override
    public String generateNewCustomerID() {
        return null;
    }

    @Override
    public List<StudentDTO> searchStudentByText(String text) {
        List<StudentDTO> studentList = new ArrayList<>();
        for(Student student:studentDAO.searchRoomByText(text)){
               studentList.add(new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getGender()));
        }
        return studentList;
    }

    @Override
    public StudentDTO findByPk(String pk) {
        StudentDTO studentDTO = null;
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        try{
           // System.out.println(studentDAO.findByPk(pk,session));

            //conver Student to StudentDTO
            Student  student = studentDAO.findByPk(pk,session);
            studentDTO = new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getGender());
            return studentDTO;

        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return  studentDTO;
        }finally {
            session.close();
        }
    }
    }
