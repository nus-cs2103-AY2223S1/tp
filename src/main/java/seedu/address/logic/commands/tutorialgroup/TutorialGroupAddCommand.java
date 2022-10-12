package seedu.address.logic.commands.tutorialgroup;

import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.TutorialGroup;

/**
 * Adds a tutorial group to the address book.
 */
public class TutorialGroupAddCommand extends Command {
    public static final String COMMAND_WORD = "tutorialAdd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial group to the address book. "
        + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "New tutorial group added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the address book";

    private final TutorialGroup toAdd;

    /**
     * Creates an TaskAddCommand to add the specified {@code Person}
     */
    public TutorialGroupAddCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toAdd = tutorialGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorialGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addTutorialGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                    || (other instanceof TutorialGroupAddCommand // instanceof handles nulls
                    && toAdd.equals(((TutorialGroupAddCommand) other).toAdd));
    }
}
