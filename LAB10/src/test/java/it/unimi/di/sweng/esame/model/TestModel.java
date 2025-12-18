package it.unimi.di.sweng.esame.model;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestModel {
    @Test
    void testInsertSuccess() {
        Lesson lesson = new Lesson("Math", DayOfWeek.MONDAY, LocalTime.of(8,30),3);
        IState SUT = new State();

        SUT.insertLesson(lesson);

        assertThat(SUT.getLessons()).containsExactly(lesson);
    }

    @Test
    void testInsertSovrapposizione() {
        /*
        Lesson lesson = new Lesson("Math", DayOfWeek.MONDAY, LocalTime.of(8,30),3);

        Lesson lesson2 = new Lesson("History", DayOfWeek.MONDAY, LocalTime.of(10,30),3);
         */

        Lesson lesson = mock();
        Lesson lesson2 = mock();
        when(lesson.overlaps(lesson2)).thenReturn(true);
        when(lesson2.overlaps(lesson)).thenReturn(true);

        IState SUT = new State();

        SUT.insertLesson(lesson);

        assertThatThrownBy(()-> SUT.insertLesson(lesson2)).isInstanceOf(IllegalArgumentException.class).hasMessage("Overlapping time error");
    }
}
