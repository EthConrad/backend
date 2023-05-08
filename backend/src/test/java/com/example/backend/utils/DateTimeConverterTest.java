package com.example.backend.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeConverterTest {

    //Tests the happy path scenario's where the dates match one of the expected formats
    @CsvSource({"2022-12-10, 2022-12-10T00:00", "2022-12-10 08:20, 2022-12-10T08:20",
            "2022/12/10, 2022-12-10T00:00", "2022/12/10 08:20, 2022-12-10T08:20",
            "10/12/2022, 2022-12-10T00:00", "10/12/2022 08:20, 2022-12-10T08:20",
            "10-12-2022, 2022-12-10T00:00", "10-12-2022 08:20, 2022-12-10T08:20",})
    @ParameterizedTest
    void convertDateTime_success(String input, String output) {
        assertEquals(output, DateTimeConverter.convertDateTime(input).toString());
    }

    //Tests invalid scenarios such as a date in US format, invalid characters, time before date
    //If these were desired formats they can easily be added to dateFormatList in the converter
    @CsvSource({"2022-22-10", "2022-2?-10", "10:00 2022-12-10"})
    @ParameterizedTest
    void convertDateTime_fail(String input) {
        assertThrows(IllegalArgumentException.class, () -> DateTimeConverter.convertDateTime(input));
    }
}
