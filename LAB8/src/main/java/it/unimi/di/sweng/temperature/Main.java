package it.unimi.di.sweng.temperature;


import it.unimi.di.sweng.temperature.model.ObservableModel;
import it.unimi.di.sweng.temperature.presenter.CelsiusScaleStrategy;
import it.unimi.di.sweng.temperature.presenter.FahrenheitScaleStrategy;
import it.unimi.di.sweng.temperature.presenter.MyPresenter;
import it.unimi.di.sweng.temperature.presenter.ScaleStrategy;
import it.unimi.di.sweng.temperature.view.MyTextView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Temperature 2025");

    MyTextView celsiusField = new MyTextView("Celsius");
    MyTextView celsiusField2 = new MyTextView("Celsius2");
    MyTextView fahrenheitField = new MyTextView("Fahrenheit");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(celsiusField, 1, 0);
    gridPane.add(fahrenheitField, 1, 1);
    gridPane.add(celsiusField2, 1, 2);

    //TODO: aggiungere creazione e collegamento degli oggetti
      ObservableModel model = new ObservableModel();
      MyPresenter presenter1 = new MyPresenter(model, CelsiusScaleStrategy.INSTANCE, celsiusField);
      model.addObserver(presenter1);

      MyPresenter presenter2 = new MyPresenter(model, CelsiusScaleStrategy.INSTANCE, celsiusField2);

      MyPresenter presenter3 = new MyPresenter(model, FahrenheitScaleStrategy.INSTANCE, fahrenheitField);
      model.addObserver(presenter3);
      model.addObserver(presenter2);

    Scene scene = new Scene(gridPane);
    stage.setScene(scene);
    stage.show();
  }

}
