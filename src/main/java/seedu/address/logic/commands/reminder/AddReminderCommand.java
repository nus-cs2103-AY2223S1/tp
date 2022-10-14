package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Adds a reminder to the ModQuik.
 */
public class AddReminderCommand extends Command {

    public static final String COMMAND_WORD = "add reminder";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T W17 "
            + PREFIX_TIMESLOT + "1500-1800 ";

    public static final String MESSAGE_SUCCESS = "New reminder added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This reminder already exists in the ModQuik";
    public static final String MESSAGE_CLASH_TUTORIAL =
            "There exists a reminder having same name in the ModQuik";
    
    private final Reminder toAdd;
    
    /**
     * Creates an AddReminderCommand to add the specified {@code Reminder}
     */
    public AddReminderCommand(Reminder reminder) {
        requireNonNull(reminder);
        toAdd = reminder;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasReminder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }
        if (model.hasReminderClashingWith(toAdd)) {
            throw new CommandException(MESSAGE_CLASH_TUTORIAL);
        }
        model.addReminder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddReminderCommand // instanceof handles nulls
                && toAdd.equals(((AddReminderCommand) other).toAdd));
    }
}
