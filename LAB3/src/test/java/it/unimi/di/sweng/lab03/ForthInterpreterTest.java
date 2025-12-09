package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Timeout(2)
public class ForthInterpreterTest {
  private Interpreter interpreter;

  @BeforeEach
  public void setUp() {
    interpreter = new ForthInterpreter();
  }


  @Test
  public void testInputVuoto() {
    interpreter.input("");
    assertThat(interpreter.toString()).isEqualTo("<- Top");
  }

  @ParameterizedTest
  @CsvSource({
          "1, 1 <- Top",
          "1 2, 1 2 <- Top",
          "'1\n2', 1 2 <- Top",
          "'1   2 \n3', 1 2 3 <- Top"
  })
  public void testInputNumerico(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 +, 3 <- Top",
          "1 2 + 5 +, 8 <- Top",
  })
  public void testSomma(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2+, Token error 2+",
          "1 2 +5 +, Token error +5",
  })
  public void testSommaConEccezione(String program, String output) {
    assertThatThrownBy(() -> interpreter.input(program))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(output);
  }

  @Test
  public void testUnderFlow() {
            assertThatThrownBy(() -> interpreter.input("1 +"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Stack underflow");
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 *, 2 <- Top",
          "1 2 * 5 *, 10 <- Top",
  })
  public void testProddo(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 -, 1 <- Top",
          "6 3 /, 2 <- Top",
  })
  public void testOperandiOppostiAQuelliGiaTestati(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

    @ParameterizedTest
    @CsvSource({
            "1 2 dup, 1 2 2 <- Top",
            "6 3 5 dup, 6 3 5 5 <- Top",
    })
    public void testComandoAvanzatoDup(String program, String output) {
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 swap, 2 1 <- Top",
            "6 3 5 swap, 6 5 3 <- Top",
    })
    public void testComandoAvanzatoSwap(String program, String output) {
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 drop, 1 <- Top",
            "6 3 5 drop, 6 3 <- Top",
    })
    public void testComandoAvanzatoDrop(String program, String output) {
        interpreter.input(program);
        assertThat(interpreter.toString()).isEqualTo(output);
    }

    @Test
    public void testComandiAvanzatiCombinati() {
        interpreter.input("1 2 + 3 * 4 dup 5 + drop swap");
        assertThat(interpreter.toString()).isEqualTo("4 9 <- Top");
    }

    @Test
    public void testEccezioneComandiAvanzatiCombinati() {
        assertThatThrownBy(() -> interpreter.input("1 2 + 3 * drop swap"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}