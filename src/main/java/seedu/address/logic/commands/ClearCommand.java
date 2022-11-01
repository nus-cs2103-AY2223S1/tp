package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.model.ModelType.CONSULTATION;
import static seedu.address.model.ModelType.STUDENT;
import static seedu.address.model.ModelType.TUTORIAL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all data in all specific fields or the entire app.\n"
            + "Parameters: "
            + PREFIX_FIELD + "FIELD (all, student, tutorial, consultation, reminder) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FIELD + "student";

    public static final String MESSAGE_SUCCESS_ALL = "All data in ModQuik have been cleared!";
    public static final String MESSAGE_SUCCESS_STUDENT = "All students in ModQuik have been cleared!";
    public static final String MESSAGE_SUCCESS_CONSULTATION = "All consultations in ModQuik have been cleared!";
    public static final String MESSAGE_SUCCESS_TUTORIAL = "All tutorials in ModQuik have been cleared!";
    public static final String MESSAGE_SUCCESS_REMINDER = "All reminders in ModQuik have been cleared!";

    private static final String[] temp = {"all", "student", "consultation", "tutorial", "reminder"};
    private static final Set<String> possibleFields = new HashSet<>(Arrays.asList(temp));


    private final String field;
    public ClearCommand(String field) {
        this.field = field;
    }

    public static boolean isValidField(String field) {
        return possibleFields.contains(field);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (field) {
        case "all":
            model.setAddressBook(new AddressBook());
            return new CommandResult(MESSAGE_SUCCESS_ALL);

        case "student":
            model.resetStudents();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT, STUDENT);

        case "consultation":
            model.resetConsultations();
            return new CommandResult(MESSAGE_SUCCESS_CONSULTATION, CONSULTATION);

        case "tutorial":
            model.resetTutorials();
            return new CommandResult(MESSAGE_SUCCESS_TUTORIAL, TUTORIAL);

        case "reminder":
            model.resetReminders();
            return new CommandResult(MESSAGE_SUCCESS_REMINDER);

        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
