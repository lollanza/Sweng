package it.unimi.di.sweng.esame;

import it.unimi.di.sweng.esame.model.Model;
import it.unimi.di.sweng.esame.presenter.MyInputPresenter;
import it.unimi.di.sweng.esame.presenter.MyOutputPresenter;
import it.unimi.di.sweng.esame.presenter.MyOutputPresenterMaths;
import it.unimi.di.sweng.esame.view.DisplayView;
import it.unimi.di.sweng.esame.view.InputLezione;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.DayOfWeek;


public class Main extends Application {

    public static final int NUM_LINES = 4;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Timetable");

        InputLezione input = new InputLezione();

        DisplayView display1 = new DisplayView(NUM_LINES, "Monday");
        DisplayView display2 = new DisplayView(NUM_LINES, "Friday");
        DisplayView display3 = new DisplayView(NUM_LINES, "Maths");

        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.add(input, 0, 3);
        GridPane.setColumnSpan(input, GridPane.REMAINING);

        gridPane.add(display1, 1, 0);
        gridPane.add(display2, 2, 0);

        gridPane.add(display3, 0, 2);
        GridPane.setColumnSpan(display3, GridPane.REMAINING);

        //TODO
        Model model = new Model();

        var inputPresenter = new MyInputPresenter(input,model);
        var p1 = new MyOutputPresenter(display1, DayOfWeek.MONDAY);
        var p2 = new MyOutputPresenter(display2, DayOfWeek.FRIDAY);
        var p3 = new MyOutputPresenterMaths(display3);

        model.addObserver(p1);
        model.addObserver(p2);
        model.addObserver(p3);

        model.notifyObservers();

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
