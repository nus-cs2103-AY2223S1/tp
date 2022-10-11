package seedu.address.logic.commands.ta;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ta.TeachingAssistant;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

public class AddTeachingAssistantCommand extends Command {
    public static final String COMMAND_WORD = "add ta";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Teaching Assistant to the ModQuik. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_VENUE + "VENUE "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T W17 "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_VENUE + "COM1-0203 "
            + PREFIX_TIMESLOT + "1500-1800 ";

    public static final String MESSAGE_SUCCESS = "New Teaching Assistant added: %1$s";
    public static final String MESSAGE_DUPLICATE_TA = "This Teaching Assistant already exists in the ModQuik";

    private final TeachingAssistant toAdd;

    /**
     * Creates an AddTeachingAssistantCommand to add the specified {@code TeachingAssistant}
     */
    public AddTeachingAssistantCommand(TeachingAssistant ta) {
        requireNonNull(ta);
        toAdd = ta;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTeachingAssistant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TA);
        }
        model.addTeachingAssistant(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTeachingAssistantCommand // instanceof handles nulls
                && toAdd.equals(((AddTeachingAssistantCommand) other).toAdd));
    }
}
