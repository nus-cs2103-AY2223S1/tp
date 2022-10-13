package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.PersonBook;
import seedu.address.model.PropertyBook;

/**
 * Clears both person and property list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Properties and Persons list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPersonModel(new PersonBook());
        model.setPropertyModel(new PropertyBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
