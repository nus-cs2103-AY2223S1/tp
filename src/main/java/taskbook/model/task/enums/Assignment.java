package taskbook.model.task.enums;

import java.util.Arrays;

import taskbook.commons.core.Messages;
import taskbook.logic.parser.exceptions.ParseException;

/**
 * Represents whether the Task has been assigned to the user,
 * or the user has assigned to someone else.
 */
public enum Assignment {
    FROM, TO;

    public static final String MESSAGE_CONSTRAINTS =
            "Assignment should be either FROM or TO";

    @Override
    public String toString() {
        switch (this) {
        case FROM:
            return "Assigned by";
        case TO:
            return "Assigned to";
        default:
            return " ";
        }
    }

    public static boolean isValidAssignment(String test) {
        return Arrays.stream(values()).map(Assignment::name).anyMatch(code -> code.equals(test));
    }

    /**
     * Checks if the query is a valid assignment and returns the corresponding Assignment.
     * @param query
     * @return Assignment
     * @throws ParseException
     */
    public static Assignment parseAssignment(String query) throws ParseException {
        if (!isValidAssignment(query)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    "Assignments should be either <FROM> or <TO>"));
        }
        return query.toUpperCase().equals(FROM.name()) ? FROM : TO;
    }
}
