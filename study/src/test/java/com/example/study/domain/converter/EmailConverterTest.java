package com.example.study.domain.converter;

import com.example.study.domain.Email;
import com.example.study.domain.coverter.EmailConverter;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmailConverterTest {

    private final EmailConverter emailConverter = new EmailConverter();

    @ParameterizedTest
    @MethodSource("emailData")
    public void can_convert_to_db_and_back(Email email, String expectedEmail) {

        String dbValue = emailConverter.convertToDatabaseColumn(email);
        assertEquals(expectedEmail, dbValue);

        Email backEmail = emailConverter.convertToEntityAttribute(dbValue);
        assertEquals(email, backEmail);
    }

    private static Stream<Arguments> emailData() {
        return Stream.of(
                Arguments.of(new Email("test@address.com"), "test@address.com"),
                Arguments.of(new Email("john@htl.com"),"john@htl.com")
        );
    }

    @Test
    public void converts_null_email_to_null_string() {
        assertNull(emailConverter.convertToEntityAttribute(null));
    }

    @Test
    public void converts_null_string_to_null_email() {
        assertNull(emailConverter.convertToEntityAttribute(null));
    }

    @Test
    public void converts_blank_string_to_null_email() {
        assertNull(emailConverter.convertToEntityAttribute(""));
        assertNull(emailConverter.convertToEntityAttribute("   "));
    }

    @Test
    public void gives_an_error_the_at_is_missing(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Email("no at in email address is invalid"));

        assertEquals("Invalid email address", exception.getMessage());

    }

    @Test
    public void throws_exeption_if_email_is_empty() {
        assertThrows(IllegalArgumentException.class, () -> new Email(""));
    }

    @Test
    public void accepts_valid_email_with_at_symbol() {
        assertDoesNotThrow(() -> new Email("correct@address.com"));
    }
}
