package com.example.study.domain.converter;

import com.example.study.domain.CourseStatus;
import com.example.study.domain.coverter.CourseStatusConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CourseStatusConverterTest {

    private final CourseStatusConverter statusConverter = new CourseStatusConverter();

    @ParameterizedTest
    @MethodSource("statusData")
    public void can_convert_to_db_and_back(CourseStatus status, String expectedStatus){
        String dbStatus = statusConverter.convertToDatabaseColumn(status);
        assertEquals(expectedStatus, dbStatus);

        String backStatus = statusConverter.convertToDatabaseColumn(status);
        assertEquals(expectedStatus, backStatus);
    }

    private static Stream<Arguments> statusData() {
        return Stream.of(
                Arguments.of(CourseStatus.PLANNED,("P")),
                Arguments.of(CourseStatus.RUNNING,("R")),
                Arguments.of(CourseStatus.COMPLETED,("C"))
        );
    }

    @Test
    public void converts_null_status_to_null_string(){
        assertNull(statusConverter.convertToDatabaseColumn(null));
    }

    @Test
    public void converts_null_string_to_null_status(){
        assertNull(statusConverter.convertToEntityAttribute(null));
    }

    @Test
    public void throws_exception_when_status_is_unknown(){
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> statusConverter.convertToEntityAttribute("A"));
        assertTrue (exception.getMessage().contains("Invalid course status code:"));
    }
}

