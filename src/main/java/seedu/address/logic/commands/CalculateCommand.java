package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.List;

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

        return new CommandResult("to be implemented");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalculateCommand // instanceof handles nulls
                && expression.equals(((CalculateCommand) other).expression)); // state check
    }

}
