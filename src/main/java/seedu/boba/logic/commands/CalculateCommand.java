package seedu.boba.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.CalculationParser;
import seedu.boba.model.BobaBotModel;


/**
 * A build-in calculator for bobaBot for cashiers to do basic arithmetic
 * calculation including +, -, /, -, with precedence enabled
 * e.g. calc 3*(1+4)/2
 */
public class CalculateCommand extends Command {

    /**
     * Command word for calculator.
     */
    public static final String COMMAND_WORD = "calc";

    /**
     * Message for calculator.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Calculate an arithmetic expression"
            + "Example: " + COMMAND_WORD + "3 * (1 + 2.4)";

    /**
     * Logger to record all the calculations performed
     * for future checking of bugs
     */
    private static Logger logger = Logger.getLogger("Calculation_History");

    private final String expression;

    /**
     * Constructor for calculator.
     * @param exp The arithmetic expression to be computed
     */
    public CalculateCommand(String exp) {
        this.expression = exp;
    }
    @Override
    public CommandResult execute(BobaBotModel bobaBotModel) throws CommandException {

        requireNonNull(bobaBotModel);
        String calcResult =
                CalculationParser.parseCalculation(this.expression);
        String feedback = "Calculator:\n";
        feedback += this.expression + " = " + calcResult;
        logger.log(Level.INFO, feedback);
        return new CommandResult(feedback);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalculateCommand // instanceof handles nulls
                && expression.equals(((CalculateCommand) other).expression)); // state check
    }

    /**
     * Class for reading and understanding the arithmetic expression.
     * Adapted from: https://www.daniweb.com/programming/software-development/
     *      threads/442690/java-expression-parser-calculator
     * with modifications
     */
    private static class ArithmeticExpressionReader {
        private static final int HIGH_PRECEDENCE = 2;
        private static final int LOW_PRECEDENCE = 0;

        // All operators set with their precedence
        private static final Map<String, Integer> OPERATORS = new HashMap<>();

        static {
            // Map<"token", precedence>
            OPERATORS.put("+", LOW_PRECEDENCE);
            OPERATORS.put("-", LOW_PRECEDENCE);
            OPERATORS.put("*", HIGH_PRECEDENCE);
            OPERATORS.put("/", HIGH_PRECEDENCE);
        }

        // Test if token is an operator
        private static boolean isOperator(String token) {
            return OPERATORS.containsKey(token);
        }

        // Compare precedence of operators.
        private static int comparePrecedence(String token1, String token2) {
            if (!isOperator(token1) || !isOperator(token2)) {
                throw new IllegalArgumentException("Invalid tokens: " + token1
                        + " " + token2);
            }
            return OPERATORS.get(token1) - OPERATORS.get(token2);
        }

        // Convert infix expression format into Reverse Polish notation
        public static String[] expressionToRpn(String[] inputTokens) {
            // Reverse Polish notation
            ArrayList<String> rpn = new ArrayList<>();
            Stack<String> stack = new Stack<>();

            // For each token
            for (String token : inputTokens) {
                if (isOperator(token)) {
                    // If token is an operator.
                    // While stack not empty AND stack top element
                    // is an operator and have higher precedence
                    while (!stack.empty()
                            && isOperator(stack.peek())
                            && comparePrecedence(token, stack.peek()) <= 0) {
                        rpn.add(stack.pop());
                    }
                    // Push the new operator on the stack
                    stack.push(token);
                } else if (token.equals("(")) {
                    // If token is a left parenthesis
                    stack.push(token);
                } else if (token.equals(")")) {
                    // If token is a right parenthesis
                    while (!stack.empty() && !stack.peek().equals("(")) {
                        rpn.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    // If token is a number
                    rpn.add(token);
                }
            }
            while (!stack.empty()) {
                rpn.add(stack.pop());
            }

            assert stack.empty() : "Stack is not empty at the end";

            String[] rpnStrArr = new String[rpn.size()];
            return rpn.toArray(rpnStrArr);
        }

        public static double rpnToDouble(String[] tokens) {
            Stack<String> stack = new Stack<>();

            for (String token : tokens) {
                // If the token is a value, push it onto the stack
                if (!isOperator(token)) {
                    stack.push(token);
                } else {
                    // Token is an operator: pop top two entries
                    Double d2 = Double.valueOf(stack.pop());
                    Double d1 = Double.valueOf(stack.pop());

                    // Calculate the result
                    Double result = token.compareTo("*") == 0
                        ? d1 * d2
                        : token.compareTo("/") == 0
                        ? d1 / d2
                        : token.compareTo("+") == 0
                        ? d1 + d2
                        : d1 - d2;
                    // Push result onto stack
                    stack.push(String.valueOf(result));
                }
            }

            return Double.parseDouble(stack.pop());
        }

        /**
         * Main logic (method) for the calculation reader.
         * @param userInput Cashier's input in String
         * @return The result of calculation, rounded to 2 d.p.
         */
        public static String parseCalculation(String userInput) {
            String regex = "((?<=[(|)|\\+|\\*|\\-|/])|(?=[(|)|\\+|\\*|\\-|/]))";
            String resultStr;
            // System.out.println(userInput);
            assert OPERATORS.containsKey("+") : "Don't have addition";
            assert OPERATORS.containsKey("-") : "Don't have subtraction";
            assert OPERATORS.containsKey("*") : "Don't have multiplication";
            assert OPERATORS.containsKey("/") : "Don't have division";
            try {
                String[] input = userInput.split(regex);
                String[] output = expressionToRpn(input);
                System.out.println(" ");
                // Feed the rpn string to rpntoDouble to give result
                Double result = rpnToDouble(output);
                resultStr = String.format("%.2f", result);
            } catch (NumberFormatException | EmptyStackException nfe) {
                resultStr = "INVALID EXPRESSION";
            }
            return resultStr;
        }
    }
}
