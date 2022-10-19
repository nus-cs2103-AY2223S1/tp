package seedu.application.logic.commands;

import static seedu.application.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.application.logic.parser.CliSyntax.PREFIX_REVERSE;

/**
 * Sorts the applications list using some order
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the application list "
            + "in a specified order (either by company, position, or date) "
            + "and optionally in reverse.\n"
            + "Parameters: [" + PREFIX_ORDER + "ORDER] [" + PREFIX_REVERSE + "]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ORDER + "date " + PREFIX_REVERSE;

    private final boolean shouldReverse;

    public SortCommand(boolean shouldReverse) {
        this.shouldReverse = shouldReverse;
    }

    boolean shouldReverse() {
        return shouldReverse;
    }
}
