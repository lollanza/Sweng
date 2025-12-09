package it.unimi.di.sweng.temperature.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.*;

import it.unimi.di.sweng.temperature.Observable;
import it.unimi.di.sweng.temperature.Observer;
import org.junit.jupiter.api.Test;

public class TestModel {


  @Test

  public void testSetterGetter() {
    Model model = new TemperatureModel();
    //inizializzazione
    assertThat(model.getTemp()).isCloseTo(0, within(0.01));
    //setter
    model.setTemp(42.42);
    //getter
    assertThat(model.getTemp()).isCloseTo(42.42, within(0.01));
  }

    @Test
    void testObservableModel() {
        Observer<Double> observer1 = mock();
        Observer<Double> observer2 = mock();

        ObservableModel SUT = new ObservableModel();

        SUT.addObserver(observer1);
        SUT.addObserver(observer2);

        SUT.setTemp(1212.323);

        verify(observer1).update(SUT, 1212.32);
        verify(observer2).update(SUT, 1212.32);
    }

    @Test
    void testObservableModel2() {
        Observer<Double> observer1 = mock();
        Observer<Double> observer2 = mock();

        ObservableModel SUT = new ObservableModel();

        SUT.addObserver(observer1);
        SUT.addObserver(observer2);

        SUT.setTemp(1212.323);
        SUT.setTemp(12.33);
        SUT.setTemp(12.33333);


        verify(observer1,times(2)).update(eq(SUT), anyDouble());
        verify(observer2, times(2)).update(eq(SUT), anyDouble());
    }
}
