package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.Lesson;
import it.unimi.di.sweng.esame.view.OutputView;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyOutputPresenterTest {
    @Test
    void testUpdate() {

        OutputView view = mock();
        when(view.size()).thenReturn(4);
        Observer <List<Lesson>> SUT = new MyOutputPresenter(view,DayOfWeek.MONDAY);

        List<Lesson> l = new ArrayList<>();
        Lesson first = new Lesson("Math", DayOfWeek.MONDAY, LocalTime.of(8, 30), 2);
        Lesson second = new Lesson("Hist", DayOfWeek.MONDAY, LocalTime.of(10, 30), 1);
        l.add(second);
        l.add(first);
        l.add(new Lesson("Math", DayOfWeek.FRIDAY, LocalTime.of(10,30),2));

        SUT.update(mock(),l);
        verify(view).set(0,"08:30-10:30 Math");
        verify(view).set(1,"10:30-11:30 Hist");
    }
}