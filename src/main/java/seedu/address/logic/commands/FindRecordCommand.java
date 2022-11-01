package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;

import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.record.RecordContainsKeywordsPredicate;



/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRecordCommand extends Command {

    public static final String COMMAND_WORD = "rfind";
    public static final DateTimeFormatter FIND_RECORD_DATE_FORMAT = DateTimeFormatter.ofPattern("MM-yyyy");
    public static final String MESSAGE_EMPTY_PREFIX = "Specified field must not be empty. eg. rfind m/";
    public static final String MESSAGE_NOTHING_TO_FIND = "At least one field must be specified.";
    public static final String MESSAGE_INVALID_FIND_DATE_FORMAT = "Invalid find date format! "
            + "\n Please use the format MM-yyyy and input MonthYear must not be after current month!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all records whose content contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_DATE + "RECORD_DATE] "
            + "[" + PREFIX_RECORD + "RECORD_DETAILS] "
            + "[" + PREFIX_MEDICATION + "MEDICATION] "
            + "(at least 1 field must be specified)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "10-2011 (must be formatted in MM-yyyy) "
            + PREFIX_RECORD + "suffers from common cold "
            + PREFIX_MEDICATION + "Paracetamol";

    private final RecordContainsKeywordsPredicate predicate;

    public FindRecordCommand(RecordContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_RECORD_COMMAND_PREREQUISITE);
        }

        model.updateFilteredRecordList(predicate);

        return new CommandResult(
                String.format(Messages.MESSAGE_RECORDS_LISTED_OVERVIEW, model.getFilteredRecordList().size()),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindRecordCommand // instanceof handles nulls
                && predicate.equals(((FindRecordCommand) other).predicate)); // state check
    }
}
