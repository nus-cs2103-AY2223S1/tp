package seedu.address.logic.commands.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelType;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a tutorial to ModQuik.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "add tutorial";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to ModQuik.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_VENUE + "VENUE "
            + PREFIX_TIME + "TIMESLOT "
            + PREFIX_DATE_DAY + "DAY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T W17 "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_VENUE + "COM1-0203 "
            + PREFIX_TIME + "1500-1800 "
            + PREFIX_DATE_DAY + "2 ";

    public static final String MESSAGE_SUCCESS = "New tutorial added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in ModQuik";
    public static final String MESSAGE_CLASH_TUTORIAL =
            "There exists a tutorial with overlapping timeslot in the ModQuik";

    private final Tutorial toAdd;

    /**
     * Creates an AddTutorialCommand to add the specified {@code Tutorial}
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorial(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }
        if (model.hasClashingTutorial(toAdd)) {
            throw new CommandException(MESSAGE_CLASH_TUTORIAL);
        }
        model.addTutorial(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ModelType.TUTORIAL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTutorialCommand // instanceof handles nulls
                && toAdd.equals(((AddTutorialCommand) other).toAdd));
    }
}
