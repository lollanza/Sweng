package it.unimi.di.sweng.esame.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LessonTest {

    @ParameterizedTest
    @CsvSource(delimiter = '|', textBlock = """
            8:30 | 2 | 9:30 | 1 | true
            8:30 | 2 | 11:30 | 1 | false
            11:30 | 2 | 9:30 | 1 | false
            11:30 |2|9:30|3|true
            8:30 | 2 | 10:30 |2| false
            """)
    void testOverlaps(String startA, int durationA, String startB, int durationB, boolean expected) {
        LocalTime start1 = LocalTime.parse(startA, DateTimeFormatter.ofPattern("H:mm"));
        LocalTime start2 = LocalTime.parse(startB, DateTimeFormatter.ofPattern("H:mm"));
        Lesson a = new Lesson("Math", DayOfWeek.MONDAY, start1,durationA);
        Lesson b = new Lesson("Math", DayOfWeek.MONDAY, start2,durationB);
        assertThat(a.overlaps(b)).isEqualTo(expected);

    }
}