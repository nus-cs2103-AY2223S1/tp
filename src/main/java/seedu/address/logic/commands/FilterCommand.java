package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FilterCommand extends Command{

    public static final String COMMAND_WORD = "find";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
