import it.unimi.di.sweng.slalom.model.Model;
import it.unimi.di.sweng.slalom.model.Skier;
import it.unimi.di.sweng.slalom.presenters.InputPresenter;
import it.unimi.di.sweng.slalom.views.NextSkierView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TestInputPresenter {

    @ParameterizedTest
    @CsvSource(textBlock = """
      -20, Tempo deve essere positivo
      76.5, Il tempo della singola manche deve essere inferiore ai 60 secondi
      pippo, Il tempo deve essere un numero
      """)
    void testInputErrors(String input, String expected){
        NextSkierView view = mock();
        Model model = mock();
        InputPresenter SUT = new InputPresenter(model, view);

        SUT.action("Nome", input);
        verify(view).showError(expected);
    }

    @Test
    void testInputOK(){
        NextSkierView view = mock();
        Model model = mock();
        InputPresenter SUT = new InputPresenter(model, view);

        SUT.action("Pippo", "53.11");
        verify(model).addSecondRuntime(new Skier("Pippo", 53.11));
    }
}
