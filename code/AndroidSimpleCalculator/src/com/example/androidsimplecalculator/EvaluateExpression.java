package com.example.androidsimplecalculator;
//******************************************************************
//  EvaluateExpression an String expression given to the constructor
//  and returns the answer as a String by the getAnswer() method.
//******************************************************************

import java.util.*;

public class EvaluateExpression {

  String answer = "";
  
  /** Evaluate an double expression */
  public EvaluateExpression(String expression) {
    // Create operandStack to store operands
    Stack<Double> operandStack = new Stack<Double>();
  
    // Create operatorStack to store operators and ( and )
    Stack<Character> operatorStack = new Stack<Character>();
    
    // if the expression is empty, then answer = 0 and return
    try{
    if (expression.trim().equals("")) {
        answer = "0";
        return;
    }
  
    // Insert blanks around (, ), +, -, /, and *
    expression = insertBlanks(expression);

    // Extract operands and operators
    String[] tokens = expression.split(" ");
    // Phase 1: Scan tokens
    for (String token: tokens) {
      token = token.trim();
      if (token.length() == 0) // Blank space
        continue; // Back to extract the next token
      else if (isOperator(token.charAt(0))&& token.length()==1) {
          // Process all +, -, *, / in the top of the operator stack 
          while (!operatorStack.isEmpty() && 
                   isOperator(operatorStack.peek())&&   matchPrec(token.charAt(0),operatorStack.peek()))
              processAnOperator(operandStack, operatorStack);

          // Push the +, -, *, or / operator double the operator stack
          operatorStack.push(token.charAt(0));
      }
      else if (token.charAt(0) == '(') {
        operatorStack.push('('); // Push '(' to stack
      }
      else if (token.charAt(0) == ')') {
        // Process all the operators in the stack until seeing '('
        while (operatorStack.peek() != '(') {
          processAnOperator(operandStack, operatorStack);
        }
        operatorStack.pop(); // Pop the '(' symbol from the stack
      }
      else { // An operand scanned
        // Push an operand to the stack
        operandStack.push(Double.valueOf(token));
      }
    }

    // Phase 2: process all the remaining operators in the stack 
    while (!operatorStack.isEmpty()) {
      processAnOperator(operandStack, operatorStack);
    }
   

    // Return the result
    answer = "" + operandStack.pop();
 
  }
  catch(EmptyStackException e){
      answer = "Invalid Equation";
  }
  catch(ArithmeticException e){
      answer = "Can't divide by zero";
  }
  catch(Exception e){
      answer = "Improper Equation";
  }
  }
  
  /** Return the answer of the evaluation as a String */
  public String getAnswer()
  {
      return answer;
  }

  /** Process one operator: Take an operator from operatorStack and
   *  apply it on the operands in the operandStack */
  public void processAnOperator(
      Stack<Double> operandStack, Stack<Character> operatorStack) {
    char op = operatorStack.pop();
    double op1 = operandStack.pop();
    double op2 = operandStack.pop();
    if (op == '+') 
      operandStack.push(op2 + op1);
    else if (op == '-') 
      operandStack.push(op2 - op1);
    else if (op == '*') 
      operandStack.push(op2 * op1);
    else if (op == '/') 
      operandStack.push(op2 / op1);
  }
  
  public boolean isOperator(char val) {
    return (val == '+' || val == '-' || val == '*' || val == '/');
  }
  
  public String insertBlanks(String s) {
    String result = "";
    s = s.replaceAll("[-][-]", "+");
    s = s.replaceAll("[+][-]", "-");
    
    for (int i = 0; i < s.length(); i++) {      
      if (s.charAt(i) == '(' || s.charAt(i) == ')' || 
          s.charAt(i) == '+' || (s.charAt(i) == '-'&& i!=0) ||
          s.charAt(i) == '*' || s.charAt(i) == '/')
        result += " "+ s.charAt(i) + " ";
      else
        result += s.charAt(i);
    }
    result = result.replaceAll("[*]  - ", "* -");
    result = result.replaceAll("[/]  - ", "/ -");
    
    return result;
  }

  public boolean matchPrec(char tok1, char tok2) {
    return (tok1 == '+' || tok1 == '-' || tok2 == '*' || tok2 == '/');
  }
}
