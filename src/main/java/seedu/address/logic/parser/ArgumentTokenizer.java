package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utility method to tokenize strings.
 */
public class ArgumentTokenizer {
    public static final String MESSAGE_QUOTATIONS_NOT_CLOSED = "Please ensure all quotation marks are closed, i.e. "
            + "every opened quotation mark has a corresponding closed quotation mark.";

    /**
     * Tokenizes space-separated string into an array of string tokens.
     * Quoted strings (e.g. "my string") will be considered as a single token.
     *
     * @param argsString String to be tokenized.
     * @return Array of String tokens.
     * @throws ParseException if the quotation marks are not closed at the end of the string.
     */
    public static String[] tokenize(String argsString) throws ParseException {
        List<String> results = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        boolean inQuote = false;
        char quoteChar = '"'; // Store the type of quote used to ensure closing quote is of same type
        for (int i = 0; i < argsString.length(); i++) {
            char currentChar = argsString.charAt(i);

            if ((currentChar == '"' || currentChar == '\'')
                    && (!inQuote || currentChar == quoteChar)) { // Only close quotes if quoteChar is matching
                inQuote = !inQuote;
                quoteChar = currentChar;
            } else if (Character.isWhitespace(currentChar) && !inQuote) {
                if (currentToken.length() > 0) {
                    results.add(currentToken.toString().trim());
                }
                currentToken.setLength(0);
            } else {
                currentToken.append(currentChar);
            }
        }

        if (inQuote) {
            throw new ParseException(MESSAGE_QUOTATIONS_NOT_CLOSED);
        }

        if (currentToken.length() > 0) {
            results.add(currentToken.toString().trim());
        }

        return results.toArray(String[]::new);
    }
}
