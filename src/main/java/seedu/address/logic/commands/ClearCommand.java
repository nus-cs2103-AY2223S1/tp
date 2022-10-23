package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all date of a field in ModQuik. "
            + "Parameters: "
            + PREFIX_FIELD + "FIELD (all, student, tutorial, consultation, reminder) \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FIELD + "student";

    public static final String MESSAGE_SUCCESS = "All data of your selected field in ModQuik have been cleared!";

    private static String temp[] = {"all","student", "consultation", "tutorial", "reminder"};
    private static Set<String> possibleFields = new HashSet<>(Arrays.asList(temp));


    private String field;
    public ClearCommand (String field) {
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
            return new CommandResult(MESSAGE_SUCCESS);

        case "student":
            model.resetStudents();
            return new CommandResult(MESSAGE_SUCCESS);

        case "consultation":
            model.resetConsultations();
            return new CommandResult(MESSAGE_SUCCESS);

        case "tutorial":
            model.resetTutorials();
            return new CommandResult(MESSAGE_SUCCESS);

        case "reminder":
            model.resetReminders();
            return new CommandResult(MESSAGE_SUCCESS);

        default:
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
    }
}
