package com.example.study.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


public class AddressTest {

    @Test
    public void creates_valid_address() {
        Address address =new Address("Spengergasse 20", "1050", "Wien");

        assertEquals("Spengergasse 20", address.street());
        assertEquals("1050", address.zipCode());
        assertEquals("Wien", address.city());
    }

    @Test
    public void throws_exception_when_street_is_blank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Address("", "1050", "Wien"));
        assertTrue(exception.getMessage().contains("Street must not be blank"));
    }

    @Test
    public void throws_exception_when_zipcode_is_blank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Address("Spengergasse", "", "Wien"));
        assertTrue(exception.getMessage().contains("Zip code must not be blank"));
    }

    @Test
    public void throws_exception_when_city_is_blank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Address("Spengergasse", "1050", ""));
        assertTrue(exception.getMessage().contains("City must not be blank"));
    }

    @Test
    public void two_of_the_same_addresses() {
        Address address1 = new Address("Spengergasse 20", "1050", "Wien");
        Address address2 = new Address("Spengergasse 20", "1050", "Wien");
        assertEquals(address1, address2);
    }

    @Test
    public void allowes_address_to_be_null() {
        assertDoesNotThrow(() -> new Address(null, null,null));
    }
}
