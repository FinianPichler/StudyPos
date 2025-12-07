package com.example.study.domain.persistanceTest;


import com.example.study.domain.*;
import com.example.study.domain.converter.StudentStatusConverterTest;
import com.example.study.persistance.CourseRepo;
import com.example.study.persistance.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;



import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class StudentRepoTest {

    @Autowired
     StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    private Student student1;
    private Student student2;
    private Course course1;
    private Course course2;

    @BeforeEach
    public void setUp(){
        studentRepo.deleteAll();
        courseRepo.deleteAll();

        student1 = new Student();
        student1.setFirstName("Max");
        student1.setLastName("Stay");
        student1.setBirthDate(LocalDate.of(2000,1, 15));
        student1.setEmail(new Email("max@address.com"));
        student1.setAddress(new Address("Straße 23","1020","Wien"));
        student1.setStudentStatus(StudentStatus.ACTIVE);

        student2 = new Student();
        student2.setFirstName("Fin");
        student2.setLastName("Red");
        student2.setBirthDate(LocalDate.of(2001,3, 15));
        student2.setEmail(new Email("fin@address.com"));
        student2.setAddress(new Address("neu straße 20","1080","Wien"));
        student2.setStudentStatus(StudentStatus.ACTIVE);


        course1 = new Course();
        course1.setCourseName("Java");
        course1.setCredits(5);
        course1.setMaxStudents(30);
        course1.setCourseStatus(CourseStatus.RUNNING);

        course2 = new Course();
        course2.setCourseName("Python");
        course2.setCredits(5);
        course2.setMaxStudents(30);
        course2.setCourseStatus(CourseStatus.RUNNING);
    }

    @Test
    public void can_save_and_find_student(){
        Student savedStudent = studentRepo.save(student1);

        assertNotNull(savedStudent.getStudentId());

        Optional<Student> studentOptional = studentRepo.findById(savedStudent.getStudentId());

        assertTrue(studentOptional.isPresent());
        assertEquals("Max", studentOptional.get().getFirstName());
        assertEquals("Stay", studentOptional.get().getLastName());
    }
}
