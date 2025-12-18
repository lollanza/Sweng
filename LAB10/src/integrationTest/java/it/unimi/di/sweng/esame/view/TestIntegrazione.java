package it.unimi.di.sweng.esame.view;


import it.unimi.di.sweng.esame.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
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


import javax.imageio.ImageIO;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

@ExtendWith(ApplicationExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestIntegrazione {

  private static final boolean HEADLESS = false;
  private InputLezione input;
  private DisplayView[] display;
  private Label errorMessage;
  private Stage stage;


  @BeforeAll
  public static void setupSpec() {
    if (HEADLESS) System.setProperty("testfx.headless", "true");
  }

  @Start
  public void start(Stage primaryStage) {

    Main m = new Main();
    m.start(primaryStage);

    stage = primaryStage;
    GridPane gp = (GridPane) primaryStage.getScene().getRoot();
    ObservableList<Node> view = gp.getChildren();

    input = (InputLezione) view.get(0);
    display = new DisplayView[3];
    for (int i = 0; i < 3; i++) {
      display[i] = (DisplayView) view.get(i+1);
    }
    errorMessage = FieldSupport.EXTRACTION.fieldValue("error", Label.class, input);
  }


  @Test
  public void testStart(FxRobot robot) {
    assertThat(display[0].get(0)).isEqualTo("");
    assertThat(display[1].get(0)).isEqualTo("");
    assertThat(display[2].get(0)).isEqualTo("");
  }

  @Test
  public void firstMondayView(FxRobot robot) {
    inserisci("History,MONDAY", "11:30,3", robot);

    verifyThat(errorMessage, hasText(""));
    assertThat(display[0].get(0)).startsWith("11:30-14:30").endsWith("History");

  }

  @Test
  public void firstMathsView(FxRobot robot) {
    inserisci("Maths,FRIDAY", "9:30,2", robot);

    verifyThat(errorMessage, hasText(""));
    assertThat(display[2].get(0)).startsWith("FRIDAY").endsWith("09:30-11:30");

  }
  @Test
  public void firstFridayView(FxRobot robot) {
    inserisci("History,FRIDAY", "9:30,1", robot);

    verifyThat(errorMessage, hasText(""));
    assertThat(display[1].get(0)).startsWith("09:30-10:30").endsWith("History");

  }


  @Test
  public void TestFirstViewOrder(FxRobot robot) {
    inserisci("Maths,MONDAY", "10:30,1", robot);
    inserisci("History,MONDAY", "8:30,2", robot);
    inserisci("Maths,FRIDAY", "14:30,1", robot);

    verifyThat(errorMessage, hasText(""));
    assertThat(display[0].get(0)).startsWith("08:30-10:30").endsWith("History");
    assertThat(display[0].get(1)).startsWith("10:30-11:30").endsWith("Maths");
    assertThat(display[0].get(2)).isEqualTo("");
  }

  @Test
  public void TestThirdViewOrder(FxRobot robot) {
    inserisci("Maths,MONDAY", "10:30,1", robot);
    inserisci("History,MONDAY", "8:30,2", robot);
    inserisci("Maths,FRIDAY", "8:30,1", robot);
    inserisci("Maths,TUESDAY", "14:30,3", robot);
    inserisci("Maths,TUESDAY", "8:30,3", robot);

    verifyThat(errorMessage, hasText(""));
    assertThat(display[2].get(0)).startsWith("MONDAY").endsWith("10:30-11:30");
    assertThat(display[2].get(1)).startsWith("TUESDAY").endsWith("08:30-11:30");
    assertThat(display[2].get(2)).startsWith("TUESDAY").endsWith("14:30-17:30");
    assertThat(display[2].get(3)).startsWith("FRIDAY").endsWith("08:30-09:30");
  }

  @Test
  public void overlapLessonError(FxRobot robot) {
    inserisci("Maths,MONDAY", "10:30,1", robot);
    inserisci("History,MONDAY", "8:30,2", robot);
    inserisci("Maths,MONDAY", "9:30,2", robot);


    verifyThat(errorMessage, hasText("Overlapping time error"));
  }



  @ParameterizedTest()
  @CsvSource(delimiter = '|', textBlock = """
      Maths,MONDAY | 9:30,-1 | Invalid duration
      Maths,MONDAY | 9:30,5  | Invalid duration
      Maths,MONDAY | 7:30,2  | Invalid time
      Maths,MONDAY | 17:30,2  | Invalid time
      Maths,LUNEDI | 17:30,2  | Invalid day
      Maths,FRIDAY | 25:30,2  | Invalid time format
      """
  )
  public void testInserisciError(String t1, String t2, String expectedError, FxRobot robot) {
    inserisci(t1, t2, robot);
    System.out.println(errorMessage);
    verifyThat(errorMessage, hasText(expectedError));
  }


  // TEST UTILITY FUNCTIONS
  private void inserisci( @NotNull String f1, @NotNull String f2, @NotNull FxRobot robot) {
    robot.doubleClickOn(input.text1);
    robot.doubleClickOn(input.text1);
    robot.write(f1, 0);
    robot.doubleClickOn(input.text2);
    robot.doubleClickOn(input.text2);
    robot.write(f2, 0);
    robot.clickOn(input.button);
  }


}
