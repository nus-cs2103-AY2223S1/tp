package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECORDS;

import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.Model;

/**
 * Displays all persons or records after a find or rfind command is executed
 * Keyword matching is case insensitive.
 */
public class ShowAllCommand extends Command {

    public static final String COMMAND_WORD = "showall";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Displaying full list!";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.isRecordListDisplayed()) {
            model.updateFilteredRecordList(PREDICATE_SHOW_ALL_RECORDS);
            logger.info("Displaying all records in the Record List View.");
            return new CommandResult(MESSAGE_SUCCESS, false, false, true);
        } else {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            logger.info("Displaying all patients in the Patient List View.");
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
