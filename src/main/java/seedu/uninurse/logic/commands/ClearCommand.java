package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.person.Person;

/**
 * Clears the uninurse book.
 */
public class ClearCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Listed patients have been cleared!";
    public static final CommandType CLEAR_COMMAND_TYPE = CommandType.CLEAR;

    @Override
    public CommandResult execute(Model model) {
        requireAllNonNull(model);
        List<Person> lastShownList = new ArrayList<>(model.getFilteredPersonList());
        PersonListTracker personListTracker = model.clearPersons(lastShownList);
        return new CommandResult(MESSAGE_SUCCESS, CLEAR_COMMAND_TYPE, personListTracker);
    }
}
