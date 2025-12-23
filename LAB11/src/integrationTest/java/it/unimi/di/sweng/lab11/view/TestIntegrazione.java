package it.unimi.di.sweng.lab11.view;


import it.unimi.di.sweng.lab11.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.assertj.core.util.introspection.FieldSupport;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {

  private InputSanta input;
  private DisplayView[] display;
  private Label errorMessage;

  private static final boolean HEADLESS = false;



  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(Stage primaryStage) {

    Main m = new Main();
    m.start(primaryStage);


    GridPane gp = (GridPane) primaryStage.getScene().getRoot();
    ObservableList<Node> view = gp.getChildren();

    input = (InputSanta) view.get(0);
    display = new DisplayView[4];
    for (int i = 0; i < 4; i++) {
      display[i] = (DisplayView) view.get(i+1);
    }
    errorMessage = FieldSupport.EXTRACTION.fieldValue("error", Label.class, input);
  }


  @Test
  public void testStart(FxRobot robot) {
    // FIG 1
    assertThat(display[0].get(0)).isEqualTo("");
    assertThat(display[1].get(0)).isEqualTo("");
  }

  @Test
  public void testAddPeluche(FxRobot robot) {
    addToysForCity("Peluche:8", "Bresso", robot);
    assertThat(display[2].get(0)).startsWith("Bresso").endsWith("8");
  }

  @Test
  public void testAddPelucheToMilano(FxRobot robot) {
    addToysForCity("Peluche:8", "Milano", robot);
    assertThat(display[0].get(0)).startsWith("Peluche").endsWith("8");
    assertThat(display[2].get(0)).startsWith("Milano").endsWith("8");
  }

  @Test
  public void testSortPresentationCity(FxRobot robot) {
    addToysForCity("PS5:7", "Milano", robot);
    addToysForCity("Trenino:18", "Milano", robot);
    addToysForCity("Peluche:8", "Milano", robot);

    assertThat(display[0].get(0)).startsWith("Peluche").endsWith("8");
    assertThat(display[0].get(1)).startsWith("PS5").endsWith("7");
    assertThat(display[0].get(2)).startsWith("Trenino").endsWith("18");
  }
  @Test
  public void testSortPresentationToy(FxRobot robot) {
    addToysForCity("Peluche:7", "Milano", robot);
    addToysForCity("Peluche:18", "Bologna", robot);
    addToysForCity("Peluche:8", "Roma", robot);
    addToysForCity("Peluche:8", "Genova", robot);

    assertThat(display[2].get(0)).startsWith("Bologna").endsWith("18");
    assertThat(display[2].get(1)).startsWith("Genova").endsWith("8");
    assertThat(display[2].get(2)).startsWith("Roma").endsWith("8");

  }

  @ParameterizedTest()
  @CsvSource(delimiter='|', textBlock = """
    Peluche:6    | ''       | City and toy must not be blank
    Peluche:otto | Milano   | The number of toys must be a positive integer
    Peluche 7    | Milano   | The input must be in the form toy:num
    """
  )
  public void testAddError(String t1, String t2, String expectedEror, FxRobot robot) {
    addToysForCity(t1, t2, robot);
    verifyThat(errorMessage, hasText(expectedEror));
  }

  // TEST UTILITY FUNCTIONS
  private void addToysForCity(String f1, String f2, @NotNull FxRobot robot) {
    robot.doubleClickOn(input.toy);
    robot.write(f1, 0);
    robot.doubleClickOn(input.city);
    robot.write(f2, 0);
    robot.doubleClickOn(input.addButton);
  }
}
