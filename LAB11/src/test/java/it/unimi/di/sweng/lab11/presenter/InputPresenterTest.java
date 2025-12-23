package it.unimi.di.sweng.lab11.presenter;

import it.unimi.di.sweng.lab11.model.Giocattolo;
import it.unimi.di.sweng.lab11.model.IState;
import it.unimi.di.sweng.lab11.view.InputView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class InputPresenterTest {


    @ParameterizedTest
    @CsvSource(delimiter = '|', emptyValue = "", nullValues = "NULL", textBlock = """
            Peluche:1010 | Milano | Quantità consegnata troppo elevata!
            PS5:-1 | Milano | Quantità consegnata negativa!
            Trenino:0 | Firenze | Quantità consegnata nulla!
            Peluche:6 | '' | City and toy must not be blank
              :7  | Firenze | City and toy must not be blank
            Peluche:otto | Milano | The number of toys must be a positive integer
            Peluche 7 | Milano | The input must be in the form toy:num
            """)
    void testErrors(String text1,String text2,String m) {
        InputView view = mock();
        IState stato = mock();
        InputPresenter SUT = new MyInputPresenter(view, stato);
        SUT.action(text1,text2);
        verify(view).showError(m);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|',textBlock = """
            Troppi giochi in citta | Peluche:99 | Milano
            Già consegnato in questa città | Peluche:27 | Milano
            """)
    void testInputErratiStato(String errore, String text1, String text2) {
        IState stato = mock();
        InputView view = mock();
        InputPresenter SUT = new MyInputPresenter(view,stato);

        doThrow(new IllegalArgumentException(errore))
                .when(stato).inserimentoGioco(any(Giocattolo.class));
        SUT.action(text1,text2);
        verify(view).showError(errore);
    }

    @Test
    void testSuccess() {
        IState stato = mock();
        InputView view = mock();

        InputPresenter SUT = new MyInputPresenter(view,stato);

        SUT.action("Peluche:8","Milano");
        verify(view).showSuccess();
        verify(stato).inserimentoGioco(new Giocattolo("Peluche",8,"Milano"));

    }
}