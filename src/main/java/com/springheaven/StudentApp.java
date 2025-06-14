package com.springheaven;

import com.springheaven.config.AppConfig;
import com.springheaven.dao.StudentDAO;
import com.springheaven.dao.StudentDAOImpl;
import com.springheaven.entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class StudentApp
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Student student= new Student();
        student.setId(1);
        student.setStudentName("PraveenRaj");
        student.setAddr("33 BlaasBalgStraat");
        student.setMobile("+32497354080");

       // StudentDAO studentDAO=new StudentDAOImpl();

        //lets create an object using spring

        AnnotationConfigApplicationContext container= new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO studentDao = container.getBean("studentDAO", StudentDAO.class);
        studentDao.SaveStudent(student);




    }
}
