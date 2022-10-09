package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Record;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a new record into the record list of the current person whose record list is being displayed.
 * Record Date provided must be formatted in dd-MM-yyyy HHmm.
 */
public class AddRecordCommand extends Command {

    public static final String COMMAND_WORD = "addR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new record to the list of records.\n"
            + "Parameters: "
            + PREFIX_DATE + "Record Date "
            + PREFIX_RECORD + "Record Content\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "31-10-2022 1430 (must be formatted in dd-MM-yyyy HHmm)"
            + PREFIX_RECORD + "suffers from common cold";

    public static final String MESSAGE_SUCCESS = "New record added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This record already exists in the record list";

    private final Record toAdd;

    /**
     * Creates an AddRecordCommand to add the specified {@code Record}
     */
    public AddRecordCommand(Record record) {
        requireNonNull(record);
        toAdd = record;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, toAdd),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecordCommand // instanceof handles nulls
                && toAdd.equals(((AddRecordCommand) other).toAdd));
    }
}
