package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all patients";

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isRecordListDisplayed()) {
            logger.warning("Record List View is not currently being displayed.");
            throw new CommandException(MESSAGE_LIST_COMMAND_PREREQUISITE);
        }

        // Update model record list display flag
        model.setRecordListDisplayed(false);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        logger.info("Patient List View has been successfully displayed.");

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
