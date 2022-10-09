package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "%1$s has been sorted successfully %2$s";
    public static final String MESSAGE_USAGE = "sort"; //sort buyers based on the priority of their orders.

    public SortCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_SUCCESS, "buyer book","2333"));
    }
}
