package com.example.study.persistance;

import com.example.study.domain.Email;
import com.example.study.domain.Student;
import com.example.study.domain.StudentStatus;
import com.example.study.projection.StudentNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    List<Student> findByLastName(String lastName);

    List<Student> findByStudentStatus(StudentStatus studentStatus);

    Optional<Student> findByEmail(Email email);

    List<StudentNameProjection>  findProjectionByLastName(String lastName);

    Optional<StudentNameProjection> findProjectionByEmail(Email email);

    List<Student> findByLastNameContainingIgnoreCase(String lastName);

    long countByStudentStatus(StudentStatus studentStatus);

    boolean existsByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.lastName =:lastName")
    List<Student> findStudentsByLastName(@Param("lastName") String lastName);

    @Query("Select s from Student s Join s.courses c where c.courseName = :courseName")
    List<Student> findStudentsEnrolledInCourse(@Param("courseName") String courseName);

    @Query("select s from Student s where size(s.courses) > :minCourses")
    List<Student>  findStudentsWithMoreThanNCourses(@Param("minCourses") int minCourses);
}
