package it.unimi.di.sweng.lab11;

import it.unimi.di.sweng.lab11.model.Giocattolo;
import it.unimi.di.sweng.lab11.model.Model;
import it.unimi.di.sweng.lab11.model.Observable;
import it.unimi.di.sweng.lab11.presenter.*;
import it.unimi.di.sweng.lab11.view.DisplayView;
import it.unimi.di.sweng.lab11.view.InputSanta;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Comparator;

public class Main extends Application {
  //TODO da completare

  public static final int MAXTOYS = 3;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Santa is coming to...");

    InputSanta input = new InputSanta();
    DisplayView displayCity1 = new DisplayView(MAXTOYS, "Milano");
    DisplayView displayCity2 = new DisplayView(MAXTOYS, "Roma");
    DisplayView displayToy1 = new DisplayView(MAXTOYS, "Peluche");
    DisplayView displayToy2 = new DisplayView(MAXTOYS, "PS5");

    GridPane gridPane = new GridPane();
    gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    gridPane.setPadding(new Insets(10, 10, 10, 10));

    gridPane.add(input, 0, 0);
    GridPane.setColumnSpan(input, GridPane.REMAINING);

    gridPane.add(displayCity1, 1, 1);
    gridPane.add(displayCity2, 2, 1);
    gridPane.add(displayToy1, 3, 1);
    gridPane.add(displayToy2, 4, 1);


    // TODO da completare creando modello e presenter e collegandoli opportunamente
      Model m = new Model();
      var i = new MyInputPresenter(input,m);
      var p1 = new MyOutputPresenter(displayCity1,
              g -> !g.city().equals("Milano"),
              Comparator.comparing(Giocattolo::nome, String.CASE_INSENSITIVE_ORDER),
              Giocattolo::formatNome
              );
      var p2 = new MyOutputPresenter(displayCity2,
              g -> !g.city().equals("Roma"),
              Comparator.comparing(Giocattolo::nome, String.CASE_INSENSITIVE_ORDER),
              Giocattolo::formatNome);
      var p3 = new MyOutputPresenter(displayToy1,
              g->!g.nome().equals("Peluche"),
              Comparator.comparing(Giocattolo::quantita).reversed().thenComparing(Giocattolo::city),
              Giocattolo::formatCity
              );
      var p4 = new MyOutputPresenter(displayToy2,
              g->!g.nome().equals("PS5"),
              Comparator.comparing(Giocattolo::quantita).reversed().thenComparing(Giocattolo::city),
              Giocattolo::formatCity
      );
      m.addObserver(p1);
      m.addObserver(p2);
      m.addObserver(p3);
      m.addObserver(p4);
      m.notifyObservers();


    //HINT: utile dopo aver definito model per inizializzare viste
    //model.notifyObservers();

    Scene scene = new Scene(gridPane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
