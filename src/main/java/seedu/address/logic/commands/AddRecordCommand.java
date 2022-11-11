package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.Record;

/**
 * Adds a new record into the record list of the current person whose record list is being displayed.
 * Record Date provided must be formatted in dd-MM-yyyy HHmm.
 */
public class AddRecordCommand extends Command {

    public static final String COMMAND_WORD = "radd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new record to the list of records.\n"
            + "Parameters: "
            + PREFIX_DATE + "RECORD_DATE_TIME "
            + PREFIX_RECORD + "RECORD_DETAILS "
            + "[" + PREFIX_MEDICATION + "MEDICATION]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "31-10-2022 1430 (must be formatted in dd-MM-yyyy HHmm) "
            + PREFIX_RECORD + "suffers from common cold "
            + PREFIX_MEDICATION + "Paracetamol 500mg "
            + PREFIX_MEDICATION + "Phenylephrine oral 10mg";

    public static final String MESSAGE_SUCCESS = "New record added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECORD = "A record with this date & time"
            + " already exists in the record list.";

    private final Record toAdd;

    /**
     * Creates an AddRecordCommand to add the specified {@code Record}
     */
    public AddRecordCommand(Record record) {
        requireNonNull(record);
        toAdd = record;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_RECORD_COMMAND_PREREQUISITE);
        }

        if (model.hasRecord(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        model.addRecord(toAdd);

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
