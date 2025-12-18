package it.unimi.di.sweng.esame.presenter;

import it.unimi.di.sweng.esame.model.IState;
import it.unimi.di.sweng.esame.model.Lesson;
import it.unimi.di.sweng.esame.view.InputView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.mockito.Mockito.*;

class MyInputPresenterTest {
    @ParameterizedTest
    @CsvSource(delimiter = '|',textBlock = """
            Maths,MONDAY | 7:30,2 | Invalid time
            Maths,MONDAY | 19:30,2 | Invalid time
            Maths,MONDAY | 29:30   ,  3 | Invalid time format
            Maths,MONDAY | a,2 | Invalid time format
            Maths,MONDAY | 8:30,2,2  | Invalid second input format
            Maths,MONDAY | 8:30 | Invalid second input format
            Maths,SATURDAY | 8:30,2 | Invalid day
            Maths,LUNEDI | 8:30,2 | Invalid day
            Maths, | 8:30,2 | Invalid first input format
            Maths | 8:30,2 | Invalid first input format
            """)
    void testInputErrors(String text1, String text2, String expectedErrorMessage) {

        InputView view = mock();
        InputPresenter SUT = new MyInputPresenter(view, mock());

        SUT.action(text1,text2);

        verify(view).showError(expectedErrorMessage);
    }

    @Test
    void testInputValid() {
        InputView view = mock();
        IState model = mock();

        InputPresenter SUT = new MyInputPresenter(view,model);
        SUT.action("Maths,MONDAY", "8:30,2");

        verify(view).showSuccess();
        verify(model).insertLesson(new Lesson("Maths", DayOfWeek.MONDAY, LocalTime.of(8,30),2));

    }


    @Test
    void testInputModelFails() {
        InputView view = mock();
        IState model = mock();
        doThrow(new IllegalArgumentException("prova messaggio errore")).when(model).insertLesson(any(Lesson.class));
        InputPresenter SUT = new MyInputPresenter(view,model);
        SUT.action("Maths,MONDAY", "8:30,2");

        verify(view).showError("prova messaggio errore");
        verify(model).insertLesson(new Lesson("Maths", DayOfWeek.MONDAY, LocalTime.of(8,30),2));
        verifyNoMoreInteractions(view,model);
    }
}