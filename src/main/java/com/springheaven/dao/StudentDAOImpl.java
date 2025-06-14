package com.springheaven.dao;

import com.springheaven.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDAOImpl implements StudentDAO{



    @PersistenceContext(unitName = "mysqldb")
    EntityManager entityManager;

    @Transactional
    public void SaveStudent(Student student) {


        System.out.println("i am going to save the student object");
        entityManager.persist(student);
    }
}
