package hw6;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/*
 * Course: CS 2302
 * Section: 2
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Homework 6
 */

public class EvaluateExpression {
  private boolean DEBUG = false;
  private int debugLevel = 6;

  String answer = "";

  /** Evaluate an integer expression */
  public EvaluateExpression(String expression) {
    // Create stack1 for tokens. This will be the first thing we tokenize into
    Stack<String> stack1 = new Stack<String>();

    // Since stack2 should only be numbers to be added, go ahead and just make it out of BigDecimals
    Stack<BigDecimal> stack2 = new Stack<BigDecimal>();

    // if the expression is empty, then answer = 0 and return
    if (expression.trim().equals("")) {
      answer = "0";
      return;
    }

    // Insert blanks around (, ), +, -, /, and *
    expression = insertBlanks(expression);

    // Extract operands and operators
    String[] tokens = expression.split(" ");

    // Execute Phase 0 on token array turning it into stack1
    executePhase0(tokens, stack1);

    // Execute Phase 1 on the two stacks
    // If we get an exception, check to see if the answer's been set to something (for cases where 
    // the exception constructor does not allow passing a string on). If it is, simply return and 
    // let the program show what the answer's been set to. For exceptions where we can send a message,
    // set the answer to that message and return
    try {
      executePhase1(stack1, stack2);
    } catch (EmptyStackException e) {
      e.printStackTrace();
      if(! answer.contentEquals("")) { return; }
    } catch (ArithmeticException e) {
      e.printStackTrace();
      answer = e.getMessage();
      return;
    } catch (Exception e) {
      e.printStackTrace();
      answer = e.getMessage();
      return;
    }

    // Move on to Phase 2 to add up everything in stack2
    try {
      executePhase2(stack2);
    } catch (EmptyStackException e) {
      e.printStackTrace();
      if(! answer.contentEquals("")) { return; }
    } catch (ArithmeticException e) {
      e.printStackTrace();
      answer = e.getMessage();
      return;
    } catch (Exception e) {
      e.printStackTrace();
      answer = e.getMessage();
      return;
    }

    // Phase 3: return the result
    debugInfo("PHASE 3: returning an answer of " + stack2.peek());
    answer = "" + stack2.pop();
  }

  // Process tokens from stack1 and populate stack2 with operands to be added together
  private void executePhase1(Stack<String> stack1, Stack<BigDecimal> stack2) 
      throws EmptyStackException, ArithmeticException, Exception {
    while(!stack1.empty()) {
      String token = stack1.pop();
      debugInfo("PHASE 1: BEGINNING OF PROCESSING just popped off token '" + token + "'", 5);
      debugInfo("PHASE 1: BEGINNING OF PROCESSING => Contents of stack1: " + stack1, 4);
      debugInfo("PHASE 1: BEGINNING OF PROCESSING => Contents of stack2: " + stack2, 4);

      if (isOperator(token)) {
        debugInfo("PHASE 1: OPERATION => token '" + token + "' is an operator", 3);
        // Check to see if stack2 is empty.  If it is, we've got an equation of the
        // form: '2 +'; '2 -'; '2 /'; or '2 *'
        if(stack2.isEmpty()) {
          EmptyStackException problem = new EmptyStackException();
          answer = "Improper Equation";
          throw problem;
        }
        // Check to see if next token is a '-' sign
        if(!stack1.isEmpty() && stack1.peek().contentEquals("-")){
          debugInfo("PHASE 1: OPERATION => MINUS CHECKING => token '" + token + "' is a leading minus sign. ", 3);
          stack1.pop(); // Pop off '-'
          // If stack1 has something more on it that isn't a number,  we want to negate whatever's on 
          // stack2 (which is the operand we just put on there)
          if(!stack1.isEmpty() && tokenIsNotANumber(stack1.peek())) {
            debugInfo("PHASE 1: OPERATION => MINUS CHECKING => stack1 isn't empty and '" + stack1.peek() + "' is not a number");
            if(!stack2.isEmpty()) {
              debugInfo("PHASE 1: OPERATION => MINUS CHECKING => stack2 isn't empty. Negating top DB Operand there: '" + stack2.peek() + "'");
              negateTopBDOperand(stack2);
              debugInfo("PHASE 1: OPERATION => MINUS CHECKING => Negated top of stack2 is '" + stack2.peek() + "'. Continuing now");
              continue;
            } else {
              throw new Exception("Invalid Equation");
            }
          }
          // Now that we know it's a number, negate it
          debugInfo("PHASE 1: OPERATION => MINUS CHECKING => Negating top DB Operand of stack2: '" + stack2.peek() + "'");
          negateTopBDOperand(stack2);
          debugInfo("PHASE 1: OPERATION => MINUS CHECKING => Negated top of stack2 is '" + stack2.peek() + "'.");
        }
        // Check to see if the next operator is a ')'. If so, consolidate that down into a single term
        if (!stack1.isEmpty() && stack1.peek().contentEquals(")")) {
          debugInfo("PHASE 1: Found an upcoming ')'. Instigating Parenception", 2);
          String nextToken = handleParenthesisExpression(stack1, stack1.pop());
          debugInfo("PHASE 1: Got a result of '" + nextToken + "' from Parenception. Pushing that back onto stack1", 2);
          validateAndPushToStringStack(nextToken, stack1);
        }
        // Now that we've done our work with '-' and ')', let's process stuff
        // '+' is simplest. Just push it onto stack
        if (token.contentEquals("+")) {
          debugInfo("PHASE 1: Found a '" + token + "'", 2);
          debugInfo("PHASE 1: PLUS => Pushing top operand of stack 1, '" + stack1.peek() + "', onto stack2", 4);
          //          stack2.push(new BigDecimal(stack1.pop()));
          validateAndPushToBDStack(stack1.pop(), stack2);
          // For the rest, handle them in their own methods
        } else if (token.contentEquals("-")) {
          debugInfo("PHASE 1: Found a '" + token + "'", 2);
          doSubtraction(stack1, stack2);
        } else if (token.contentEquals("*")) {
          debugInfo("PHASE 1: Found a '" + token + "'", 2);
          doMultiplication(stack1, stack2);
        } else if (token.contentEquals("/")) {
          debugInfo("PHASE 1: Found a '" + token + "'", 2);
          doDivision(stack1, stack2);
        } else {
          debugInfo("PHASE 1: You should never be here with token '" + token + "'", 2);
          throw new Exception("Invalid Equation");
        }
      } else if (token.contentEquals(")")) {
        debugInfo("PHASE 1: Found a '" + token + "'. Instigating Parenception", 2);
        BigDecimal bd = new BigDecimal(handleParenthesisExpression(stack1, token));
        debugInfo("PHASE 1: Got a result of '" + bd + "' from Parenception. Pushing that onto stack2", 2);
        //        stack2.push(bd);
        validateAndPushToBDStack(bd, stack2);
      } else if (token.contentEquals("(")) {
        debugInfo("PHASE 1: We've found a bare '" + token + "' which is bad");
        // We should never see one of these in the wild. If we do, throw an exception
        throw new Exception("Improper Equation");
      } else {
        debugInfo("PHASE 1: Found '" + token + "' which should be an operand", 2);
        // This is a bare operand.  Convert it to BigDecimal and throw it onto stack2
        if(tokenIsNotANumber(token)) {
          debugInfo("PHASE 1: Apparently the token '" + token + "' is not a number. DANGER WILL ROBINSON!!! Trowing 'Invalid Equation' Exception");
          throw new Exception("Invalid Equation");
        } else {
          //          stack2.push(new BigDecimal(token));
          validateAndPushToBDStack(token, stack2);
        }
      }
      debugInfo("PHASE 1: Done processing token '" + token + "'", 2);
      debugInfo("PHASE 1: END OF PROCESSING => Contents of stack1: " + stack1, 4);
      debugInfo("PHASE 1: END OF PROCESSING => Contents of stack2: " + stack2, 4);
    }
  }


  // Perform validation prior 
  private void validateAndPushToStringStack(String nextToken,
      Stack<String> stack1) throws Exception {
    if(tokenIsNotANumber(nextToken)) {
      throw new Exception("Invalid Equation");
    } else {
      stack1.push(nextToken);
    }
  }

  // String version to do validation and pushing to BigDecimal stack
  private void validateAndPushToBDStack(String nextToken,
      Stack<BigDecimal> stack2) throws Exception {
    if(tokenIsNotANumber(nextToken)) {
      throw new Exception("Invalid Equation");
    } else {
      stack2.push(new BigDecimal(nextToken));
    }
  }

  // String version to do validation and pushing to BigDecimal stack
  private void validateAndPushToBDStack(BigDecimal nextToken,
      Stack<BigDecimal> stack2) throws Exception {
    if(tokenIsNotANumber(nextToken.toString())) {
      throw new Exception("Invalid Equation");
    } else {
      stack2.push(nextToken);
    }
  }

  // Handle subtraction operations
  private void doSubtraction(Stack<String> stack1, Stack<BigDecimal> stack2) throws Exception {
    debugInfo("*** doSubtraction: ENTERing function(" + stack1 + "," + stack2 + ")");
    // We want to turn this into a '+', by negating the top operand, then putting it on stack2
    debugInfo("PHASE 1: MINUS => Negating top operand of stack2: " + stack2.peek(), 4);
    negateTopBDOperand(stack2);
    // Check to see if we have a next token.
    if(!stack1.isEmpty()) {
      // If we do, is our next token an operator. If it is, we just return
      if(isOperator(stack1.peek())){
        debugInfo("*** doSubtraction: EXITing function(" + stack1 + "," + stack2 + ") BECAUSE stack1.peek() showed me an operator");
        return;
        // If our next token is not an operator, put that onto stack2
      } else {
        debugInfo("*** doSubtraction() => Top operand is now " + stack1.peek() + ". Pushing that onto stack2", 4);
        //        stack2.push(new BigDecimal(stack1.pop()));
        validateAndPushToBDStack(stack1.pop(), stack2);
      }
    }
    debugInfo("*** doSubtraction: EXITing function(" + stack1 + "," + stack2 + ") NORMALLY");
  }

  // Handle multiplication operations
  private void doMultiplication(Stack<String> stack1, Stack<BigDecimal> stack2) throws Exception {
    // For multiplication and division, we follow the same pattern. Grab top operands from both stacks, operate
    // on them, then push them to stack2
    // Handle special case where the next thing is actually a parenthesis
    // Check to make sure stack isn't empty, because if it is bad stuff could happen
    debugInfo("PHASE 1: MULTIPLYING => Checking for PARENCEPTION!", 3);
    if(!stack1.empty() && stack1.peek().contentEquals(")")) {
      debugInfo("PHASE 1: MULTIPLYING => Next operator is a '('. Initiating PARENCEPTION!", 3);
      // Get fancy here and use the stack1.pop() as the String argument. Take results
      // and push it back onto stack1 so we can pop it next
      stack1.push(handleParenthesisExpression(stack1, stack1.pop()));
    }
    BigDecimal bd1 = new BigDecimal(stack1.pop());
    BigDecimal bd2 = stack2.pop();
    debugInfo("PHASE 1: MULTIPLYING => equation: " + bd1 + "*" + bd2, 4);
    bd1 = bd1.multiply(bd2);
    debugInfo("PHASE 1: MULTIPLYING => result: " + bd1, 4);
    //    stack2.push(bd1);
    validateAndPushToBDStack(bd1, stack2);
  }

  // Handle division operations

  private void doDivision(Stack<String> stack1, Stack<BigDecimal> stack2) throws Exception {
    // Do the same as above, but with division
    if(!stack1.empty() && stack1.peek().contentEquals(")")) {
      debugInfo("PHASE 1: Next operator is a '('. Initiating PARENCEPTION!", 3);
      stack1.push(handleParenthesisExpression(stack1, stack1.pop()));
    }
    BigDecimal bd1 = new BigDecimal(stack1.pop());
    BigDecimal bd2 = stack2.pop();
    // Check to make sure we're not trying to divide by zero. If we are, throw an exception
    if(bd2.equals(new BigDecimal(0))) {
      ArithmeticException problem = new ArithmeticException("Can't divide by zero");
      throw problem;
    }
    // bd1.divide(bd2, RoundingMode.HALF_UP) => bd1 / bd2
    debugInfo("PHASE 1: DIVIDING => equation: " + bd1 + "/" + bd2, 4);
    // Keep up to 20 decimals of precision (per samplecode13.txt) and use typical rounding
    // where x < .5 gets rounded down and everything else is rounded up
    bd1 = bd1.divide(bd2, 20, RoundingMode.HALF_UP).stripTrailingZeros();
    debugInfo("PHASE 1: DIVIDING => result: " + bd1, 4);
    //    stack2.push(bd1);
    validateAndPushToBDStack(bd1, stack2);
  }

  // Scan token array and put contents into stack1

  // Phase 0: Method to scan token array and put them onto specified stack
  private void executePhase0(String[] tokens, Stack<String> stack1) {
    // Phase 0: Scan tokens and put them onto stack1
    for (String token: tokens) {
      token = token.trim();
      if (token.length() == 0) { // Blank space 
        continue; // Back to extract the next token
      }
      debugInfo("PHASE 0: Pushing '" + token + "' onto stack1", 6);
      stack1.push(token);
    }    
  }

  // Phase 2: Add everything in stack2 together

  // Work through stack 2 adding all values and leaving that final value as the contents
  // of stack2
  private void executePhase2(Stack<BigDecimal> stack2) throws Exception {
    debugInfo("PHASE 2: Beginning execution of phase 2");
    while (stack2.size() > 1) {
      debugInfo("PHASE 2: stack2 contains " + stack2.size() + " items", 5);
      // pull off two items, add them, push result back on the stack.
      BigDecimal bd1 = stack2.pop();
      BigDecimal bd2 = stack2.pop();
      debugInfo("PHASE 2: Adding together " + bd1 + " and " + bd2, 4);
      bd1 = bd1.add(bd2);
      debugInfo("PHASE 2: Result is " + bd1 + " which is going back on the stack", 4);
      //      stack2.push(bd1);
      validateAndPushToBDStack(bd1, stack2);
    }
  }

  // Method to pull off a parenthesized expression from the stack, recursively solve it,
  // and return the answer for that expression.

  // Use this to extract out Expressions contained within parantheses and perform recursion
  // to solve them. Then return that answer to the caller
  private String handleParenthesisExpression(Stack<String> stack, String expr) throws Exception {
    int levelCount = 1; // Keep track of how many levels we're down the rabbit hole. We start at 1
    // since we're encountering the first ')'
    debugInfo("*** handleParenthesisExpression(): PARENTHESIS => expression so far: '" + expr + "'", 5);
    String nTok;
    while(levelCount > 0) {
      if(stack.isEmpty()) {
        throw new Exception("Improper Equation");
      }
      nTok = stack.pop();
      // Check to see if this is a '(' or ')' and modify levelCount appropriately
      if(nTok.contentEquals(")")) { levelCount++; }
      if(nTok.contentEquals("(")) { levelCount--; }
      // Then prepend nTok to expr
      expr = nTok + expr;
      debugInfo("*** handleParenthesisExpression(): PARENTHESIS => levelCount: " + levelCount + "; expression so far: '" + expr + "'", 5);
    }
    debugInfo("*** handleParenthesisExpression(): PARENTHESIS => FINAL expression: '" + expr + "'", 4);
    // Now we've got a fully parenthesized expression inside 'expr'. Lop off the beginning and ending parens, then
    // create a new EvaluateExpression object, run it through the recursive ringer and get back the result and put
    // it back on top of stack1
    expr = expr.substring(1, expr.length()-1);
    debugInfo("*** handleParenthesisExpression(): PARENTHESIS => PROCESSED expression: '" + expr + "'", 4);
    EvaluateExpression expRecursion = new EvaluateExpression(expr);
    return expRecursion.getAnswer();
  }

  // If they don't pass in a level, just set it to 1

  // Utility method to print debugging information
  private void debugInfo(String msg) {
    this.debugInfo(msg, 1);
  }

  // Utility method to print debugging information. Explicitly sets debug level of message

  // Print out debug info if debug level is greater than the threshold
  private void debugInfo(String msg, int dLvl) {
    if (DEBUG && dLvl <= debugLevel) {
      System.out.println("DEBUG: [" + msg 
          + "] "
          );
    }
  }

  // A utility method to negate the top operand on a String stack

  // This does the same thing, but for a BigDecimal stack

  // Method to negate the top operand of a BigDecimal stack
  private void negateTopBDOperand(Stack<BigDecimal> stack) {
    debugInfo("*** ENTERing function negateTopBDOperand(" + stack + ")");
    BigDecimal bd = stack.pop() ; // Make temporary BigDecimal variable and make it equal to what we popped.
    bd = bd.negate();                   // make bd = -bd
    stack.push(bd);                     // Put it back on the stack
    debugInfo("*** EXITing function negateTopBDOperand(" + stack + ")");
  }

  // A checker to see if the specified token is not a number

  // Method to determine if a String token is a number via regular expression
  private boolean tokenIsNotANumber(String token) {    
    if(token.matches("-?[0-9]+\\.?[0-9]*")) {
      debugInfo("**** FUNCTION tokenIsNotANumber(): Token '" + token + "' is a number", 7);
      return false;
    }
    debugInfo("**** FUNCTION tokenIsNotANumber(): Token '" + token + "' is NOT a number", 7);
    return true;
  }

  /** Return the answer */
  public String getAnswer()
  {
    debugInfo("Returning answer of " + answer);
    debugInfo("=========================================================");
    return answer;
  }

  // See if a token is a mathematical operator. Only needs to process '+', '-', '*' and '/' 
  // due to the algorithm using recursion to process '(' and ')'

  // Determine if the token is a mathematical operator
  public boolean isOperator(String token) {
    boolean foo = (token.contentEquals("+") || token.contentEquals("-") 
        || token.contentEquals("*") || token.contentEquals("/"));
    debugInfo("*** Function isOperator(): Returning '" + foo + "' for token '" + token + "'", 5);
    return foo;
  }

  // A utility method used to add blank spaces around the mathematical operators

  // Insert blank strings around mathematical operators and parentheses
  public String insertBlanks(String s) {
    String result = "";
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == ')' || 
          s.charAt(i) == '+' || s.charAt(i) == '-' ||
          s.charAt(i) == '*' || s.charAt(i) == '/')
        result += " " + s.charAt(i) + " ";
      else
        result += s.charAt(i);
    }
    return result;
  }

}
