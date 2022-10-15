package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.MainPanelName;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class SetCommand extends Command {
    public static final String COMMAND_WORD = "set";

    // TODO: add prefixes for each Contact
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a person's Contacts. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "Contact set: %1$s";

//    private final Person toSet;

    

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("incomplete command");
    }

    @Override
    public boolean canExecuteAt(MainPanelName name) {
        return name.equals(MainPanelName.Detail);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetCommand)) {
            return false;
        }

        // state check
        SetCommand s = (SetCommand) other;
        return true;
    }
}
