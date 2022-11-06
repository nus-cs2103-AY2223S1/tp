package seedu.boba.logic.parser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Class for reading and understanding the arithmetic expression.
 * Solution below dapted from: https://www.daniweb.com/programming/
 *      software-development/threads/442690/java-expression-parser-calculator
 */
public class CalculationParser {

    private static final HashMap<String, Integer> OPERATORS_PRECEDENCES = new HashMap<>();

    private static final int HIGH_PRECEDENCE = 1;
    private static final int LOW_PRECEDENCE = 0;
    static {
        OPERATORS_PRECEDENCES.put("+", LOW_PRECEDENCE);
        OPERATORS_PRECEDENCES.put("-", LOW_PRECEDENCE);
        OPERATORS_PRECEDENCES.put("*", HIGH_PRECEDENCE);
        OPERATORS_PRECEDENCES.put("/", HIGH_PRECEDENCE);
    }
    private static boolean isOperator(String token) {
        return OPERATORS_PRECEDENCES.containsKey(token);
    }

    /**
     * Compare precedence of operators.
     * @param token1 first token
     * @param token2 second token
     * @return Positive number if the first token have higher precedence, vice versa
     */
    private static int comparePrecedence(String token1, String token2) {
        if (!isOperator(token1)) {
            throw new IllegalArgumentException("Invalid token: " + token1);
        }
        if (!isOperator(token2)) {
            throw new IllegalArgumentException("Invalid token: " + token2);
        }
        return OPERATORS_PRECEDENCES.get(token1) - OPERATORS_PRECEDENCES.get(token2);
    }

    /**
     * Perform basic +, -, *, /, calculation
     * @param l Left operand
     * @param r Right operand
     * @param operator Operator
     * @return Result of calculation in string
     */
    private static String calculateBasic(String l, String r, String operator) {
        double left = Double.parseDouble(l);
        double right = Double.parseDouble(r);
        double result;
        switch(operator) {
        case "+":
            result = left + right;
            break;
        case "-":
            result = left - right;
            break;
        case "*":
            result = left * right;
            break;
        case "/":
            result = left / right;
            break;
        default:
            throw new IllegalArgumentException("Not basic operator");
        }
        return String.valueOf(result);
    }

    private static boolean haveHigherOperator(Stack<String> stack, String currToken) {
        return !stack.empty()
                && isOperator(stack.peek())
                && comparePrecedence(stack.peek(), currToken) > 0;
    }
    private static boolean isLeftParenthesis(String token) {
        return token.equals("(");
    }
    private static boolean isRightParenthesis(String token) {
        return token.equals(")");
    }

    /**
     * Convert input tokens into Reverse Polish notation (RPN),
     * this will reorder to tokens so that it's easier for program to understand.
     * @param inputTokens Tokens in the expression
     * @return Converted RPN
     */
    public static ArrayList<String> tokensToRpn(String[] inputTokens) {
        Stack<String> tokenStack = new Stack<>();
        // Result RPN
        ArrayList<String> rpn = new ArrayList<>();

        for (String token : inputTokens) {
            if (isOperator(token)) {
                // If there are higher precedence operators in the stack, add them to RPN
                // before this operator, so they are computed first
                while (haveHigherOperator(tokenStack, token)) {
                    rpn.add(tokenStack.pop());
                }
                // Push current token on the stack
                tokenStack.push(token);
            } else if (isLeftParenthesis(token)) {
                tokenStack.push(token);
            } else if (isRightParenthesis(token)) {
                // Add all tokens between the two parenthesis into RPN
                while (!tokenStack.empty() && !isLeftParenthesis(tokenStack.peek())) {
                    rpn.add(tokenStack.pop());
                }
                // Remove the left parenthesis from stack
                tokenStack.pop();
            } else {
                // Token is number
                rpn.add(token);
            }
        }
        // Add all the rest of tokens in stack to RPN
        while (!tokenStack.empty()) {
            rpn.add(tokenStack.pop());
        }
        assert tokenStack.empty() : "Token stack is not empty at the end";
        return rpn;
    }

    /**
     * Do calculation, turn RPN into result in type double
     * @param rpn RPN
     * @return Calculation result
     */
    public static double calculateResultFromRpn(ArrayList<String> rpn) {
        Stack<String> tokenStack = new Stack<>();

        for (String token : rpn) {
            // If token is operator, get top two number from stack for calculation
            if (isOperator(token)) {
                String rightOperand = tokenStack.pop();
                String leftOperand = tokenStack.pop();
                String result = calculateBasic(leftOperand, rightOperand, token);
                tokenStack.push(result);
            } else {
                // Token is number
                tokenStack.push(token);
            }
        }

        // The last number on stack is would be the final result
        double finalResult = Double.parseDouble(tokenStack.pop());
        return finalResult;
    }

    /**
     * Main logic (method) for the calculation reader.
     * @param userInput Cashier's input in String
     * @return The result of calculation, rounded to 2 d.p.
     */
    public static String parseCalculation(String userInput) {
        // RegEx to identify the tokens, a single number will not be split
        System.out.println(userInput);
        String regex = "(?<=[(|)|\\+|\\*|\\-|/])|(?=[(|)|\\+|\\*|\\-|/])";
        String resultStr;
        // System.out.println(userInput);
        assert OPERATORS_PRECEDENCES.containsKey("+") : "Don't have addition";
        assert OPERATORS_PRECEDENCES.containsKey("-") : "Don't have subtraction";
        assert OPERATORS_PRECEDENCES.containsKey("*") : "Don't have multiplication";
        assert OPERATORS_PRECEDENCES.containsKey("/") : "Don't have division";

        // Split the arithmetic expression string into tokens
        String[] inputTokens = userInput.split(regex);
        // Filter out the the empty spaces
        inputTokens = Arrays.stream(inputTokens).filter(s -> !s.trim().equals("")).toArray(String[]::new);
        System.out.println(Arrays.toString(inputTokens));
        // Convert tokens into RPN
        ArrayList<String> rpn = tokensToRpn(inputTokens);
        // Feed the rpn string to rpntoDouble to give result
        Double finalResult = calculateResultFromRpn(rpn);
        // Round result to 2 d.p.
        DecimalFormat df = new DecimalFormat("#.##");
        resultStr = df.format(finalResult);

        return resultStr;
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof CalculationParser;
    }
}
