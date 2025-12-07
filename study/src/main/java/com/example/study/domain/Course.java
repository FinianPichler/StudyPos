package com.example.study.domain;

import com.example.study.domain.coverter.CourseStatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotNull
    @Column(name = "course_name", nullable = false)
    private String courseName;
    @NotNull
    @Column(nullable = false)
    private Integer credits;
    private Integer maxStudents;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "course_status", nullable = false)
    private CourseStatus courseStatus;

    @ManyToMany(
            mappedBy = "courses",
            fetch = FetchType.LAZY
    )
    private List<Student> students = new ArrayList<>();
}
