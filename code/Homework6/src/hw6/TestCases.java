package hw6;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCases {
  private EvaluateExpression ex;
  
  @Test
  public void test() {
//    fail("Not yet implemented");
    ex = new EvaluateExpression("1 + 1");
    assertEquals("2", ex.getAnswer());
  }
  
  @Test
  public void testSimplePrecedence() {
    ex = new EvaluateExpression("1 + 2 * 3 - 6 / 2");
    assertEquals("4", ex.getAnswer());
  }

  @Test
  public void testSimplePrecedenceWithEasierToReadDebugging() {
    ex = new EvaluateExpression("1 + 2 * 3 - 4 / 2");
    assertEquals("5", ex.getAnswer());
  }

  @Test
  public void testParentheses() {
    ex = new EvaluateExpression("2 * (1 + 1)");
    assertEquals("4", ex.getAnswer());
  }
  
  @Test
  public void testDoubleParentheses() {
    ex = new EvaluateExpression("2 * (5 * (1 + 1))");
    assertEquals("20", ex.getAnswer());
  }

  @Test
  public void testMultipleParentheses() {
    ex = new EvaluateExpression("(2 + 2) * (5 * (1 + 1))");
    assertEquals("40", ex.getAnswer());
  }

  @Test
  public void testLeadingParentheses() {
    ex = new EvaluateExpression("(1 + 2) * 5");
    assertEquals("15", ex.getAnswer());
  }

  @Test
  public void testSubtractingNegativeNumbers() {
    ex = new EvaluateExpression("2 - - 2");
    assertEquals("4", ex.getAnswer());
  }

  @Test
  public void testMultiplyingNegativeNumbers() {
    ex = new EvaluateExpression("2 * - 2");
    assertEquals("-4", ex.getAnswer());
  }

  @Test
  public void testAddingNegativeNumbers() {
    ex = new EvaluateExpression("2 + - 2");
    assertEquals("0", ex.getAnswer());
  }

  @Test
  public void testDividingNegativeNumbers() {
    ex = new EvaluateExpression("2 / - 2");
    assertEquals("-1", ex.getAnswer());
  }

  @Test
  public void testLeadingMinusSign() {
    ex = new EvaluateExpression("- 2 * (5 * (1 + 1))");
    assertEquals("-20", ex.getAnswer());
  }

  @Test
  public void testLeadingMinusSignInFrontOfParens() {
    ex = new EvaluateExpression("2 * - (5 * (1 + 1))");
    assertEquals("-20", ex.getAnswer());
  }
  
  @Test
  public void testExcepionThrowing() {
    ex = new EvaluateExpression("2 +");
    assertEquals("Improper Equation", ex.getAnswer());
  }
  
  @Test
  public void testDivideByZero() {
    ex = new EvaluateExpression("1 / 0");
    assertEquals("Can't divide by zero", ex.getAnswer());
  }

  @Test
  public void testLettersBeginningEquation() {
    ex = new EvaluateExpression("a + 5");
    assertEquals("Invalid Equation", ex.getAnswer());
  }
  
  @Test
  public void testLettersInEquation() {
    ex = new EvaluateExpression("4 + a + 5");
    assertEquals("Invalid Equation", ex.getAnswer());
  }

  @Test
  public void testLettersEndingEquation() {
    ex = new EvaluateExpression("5 + a");
    assertEquals("Invalid Equation", ex.getAnswer());
  }
  
  @Test
  public void testMismatchedOpenParensInEquation() {
    ex = new EvaluateExpression("2 + (5");
    assertEquals("Improper Equation", ex.getAnswer());
  }

  @Test
  public void testMismatchedClosingParensInEquation() {
    ex = new EvaluateExpression("2 + 5)");
    assertEquals("Improper Equation", ex.getAnswer());
  }

}
