package com.example.study.domain;

import com.example.study.domain.coverter.StudentStatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @NotNull
    @Column(name =("email") ,nullable = false, unique = true)
    private Email email;

    @Embedded
    private Address address;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "student_status", nullable = false)
    private StudentStatus studentStatus;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(
                    name = "student_id",
                    nullable = false,
                    foreignKey = @ForeignKey(name = "fk_student_courses_student")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id",
                    foreignKey = @ForeignKey(name = "fk_student_courses_course")
            )
    )
    private List<Course> courses = new ArrayList<>();

    public void enrollInCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this);
    }

    public void unenrollInCourse(Course course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
    }

}
