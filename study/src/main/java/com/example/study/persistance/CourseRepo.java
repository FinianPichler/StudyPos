package com.example.study.persistance;

import com.example.study.domain.Course;
import com.example.study.domain.CourseStatus;
import com.example.study.projection.CourseInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByCourseStatus(CourseStatus courseStatus);

    List<Course> findByCourseNameContainingIgnoreCase(String courseName);


    List<Course> findByCreditsGreaterThanEqual(Integer credits);

    long countByCourseStatus(CourseStatus courseStatus);

    List<CourseInfoProjection> findProjectionByCourseStatus(@Param("status") CourseStatus courseStatus);

    @Query("select c from Course c where size(c.students) < c.maxStudents")
    List<Course> findCoursesWithFreeSeats();

    @Query("select c from Course c join c.students s where s.studentId = :studentId")
    List<Course> findCoursesByStudentId(@Param("studentId") Long studentId);

    @Query("select c from Course c where size(c.students) = 0")
    List<Course> findCoursesWithNoStudents();
}
