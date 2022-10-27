package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.EmptyStackException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CalculateCommand extends Command {

    public static final String COMMAND_WORD = "calc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public String expression;

    public CalculateCommand(String expression) {
        this.expression = expression;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        String calcResult = ExpressionParser.parseCalculation(this.expression);
        return new CommandResult(calcResult);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalculateCommand // instanceof handles nulls
                && expression.equals(((CalculateCommand) other).expression)); // state check
    }

    private static class ExpressionParser {
        // Associativity constants for operators
        private static final int LEFT_ASSOC = 0;
        private static final int RIGHT_ASSOC = 1;

        // Operators
        private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();

        static {
            // Map<"token", []{precendence, associativity}>
            OPERATORS.put("+", new int[]{0, LEFT_ASSOC});
            OPERATORS.put("-", new int[]{0, LEFT_ASSOC});
            OPERATORS.put("*", new int[]{5, LEFT_ASSOC});
            OPERATORS.put("/", new int[]{5, LEFT_ASSOC});
        }

        // Test if token is an operator
        private static boolean isOperator(String token) {
            return OPERATORS.containsKey(token);
        }

        // Test associativity of operator token
        private static boolean isAssociative(String token, int type) {
            if (!isOperator(token)) {
                throw new IllegalArgumentException("Invalid token: " + token);
            }

            if (OPERATORS.get(token)[1] == type) {
                return true;
            }
            return false;
        }

        // Compare precedence of operators.
        private static final int cmpPrecedence(String token1, String token2) {
            if (!isOperator(token1) || !isOperator(token2)) {
                throw new IllegalArgumentException("Invalid tokens: " + token1
                        + " " + token2);
            }
            return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
        }

        // Convert infix expression format into reverse Polish notation
        public static String[] expToRPN(String[] inputTokens) {
            ArrayList<String> out = new ArrayList<String>();
            Stack<String> stack = new Stack<String>();

            // For each token
            for (String token : inputTokens) {
                // If token is an operator
                if (isOperator(token)) {
                    // While stack not empty AND stack top element
                    // is an operator
                    while (!stack.empty() && isOperator(stack.peek())) {
                        if ((isAssociative(token, LEFT_ASSOC) &&
                                cmpPrecedence(token, stack.peek()) <= 0) ||
                                (isAssociative(token, RIGHT_ASSOC) &&
                                        cmpPrecedence(token, stack.peek()) < 0)) {
                            out.add(stack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    stack.push(token);
                }
                // If token is a left bracket '('
                else if (token.equals("(")) {
                    stack.push(token);  //
                }
                // If token is a right bracket ')'
                else if (token.equals(")")) {
                    while (!stack.empty() && !stack.peek().equals("(")) {
                        out.add(stack.pop());
                    }
                    stack.pop();
                }
                // If token is a number
                else {
                    //  if(!isOperator(stack.peek())){
                    //      out.add(String.valueOf(token*10));
                    //      }
                    out.add(token);
                }
            }
            while (!stack.empty()) {
                out.add(stack.pop());
            }
            String[] output = new String[out.size()];
            return out.toArray(output);
        }

        public static double RPNtoDouble(String[] tokens) {
            Stack<String> stack = new Stack<String>();

            // For each token
            for (String token : tokens) //for each
            {
                // If the token is a value push it onto the stack
                if (!isOperator(token)) {
                    stack.push(token);
                } else {
                    // Token is an operator: pop top two entries
                    Double d2 = Double.valueOf(stack.pop());
                    Double d1 = Double.valueOf(stack.pop());

                    //Get the result
                    Double result = token.compareTo("*") == 0 ? d1 * d2 :
                            token.compareTo("/") == 0 ? d1 / d2 :
                                    token.compareTo("+") == 0 ? d1 + d2 :
                                            d1 - d2;
                    // Push result onto stack
                    stack.push(String.valueOf(result));
                }
            }

            return Double.valueOf(stack.pop());
        }
        public static String parseCalculation(String userInput) {
            String reg = "((?<=[<=|>=|==|\\+|\\*|\\-|<|>|/|=])|(?=[<=|>=|==|\\+|\\*|\\-|<|>|/|=]))";
            String resultStr = "Not calculated";
            try{
                System.out.println("Enter Your Expression");
                //String[] input = "( 1 + 2 ) * ( 3 / 4 ) - ( 5 + 6 )".split(" ");
                String[] input =  userInput.split(reg);
                String[] output = expToRPN(input);

                // Build output RPN string minus the commas
                System.out.print("Stack: ");
                for (String token : output) {
                    System.out.print("[ ");System.out.print(token + " "); System.out.print("]");
                }
                System.out.println(" ");
                // Feed the RPN string to RPNtoDouble to give result
                Double result = RPNtoDouble( output );
                resultStr = result.toString();
            } catch (NumberFormatException | EmptyStackException nfe){
                System.out.println("INVALID EXPRESSION");
            }
            return resultStr;
        }
    }

}
