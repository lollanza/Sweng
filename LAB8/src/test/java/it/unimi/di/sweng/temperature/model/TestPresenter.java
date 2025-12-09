package it.unimi.di.sweng.temperature.model;

import it.unimi.di.sweng.temperature.presenter.MyPresenter;
import it.unimi.di.sweng.temperature.presenter.Presenter;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import it.unimi.di.sweng.temperature.view.View;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TestPresenter {
    @Test
    void testSetTemperatura() {
        Model mockModel = mock();
        ScaleStrategy strategy = mock();
        View view = mock();

        when(strategy.convertToCelsius(423.32)).thenReturn(9876.4);
        Presenter SUT = new MyPresenter(mockModel, strategy, view);
        SUT.action("423.32");

        verify(mockModel).setTemp(9876.4);

    }

    @Test
    void testUpdate() {
        ObservableModel mockModel = mock();
        when(mockModel.getState()).thenReturn(14.1414);
        ScaleStrategy strategy = mock();
        View view = mock();
        when(strategy.convertFromCelsius(14.1414)).thenReturn(976.4);
        MyPresenter SUT = new MyPresenter(mockModel, strategy,view);

        SUT.update(mockModel, 43.23);
        verify(view).setValue("976.40");
    }
}
