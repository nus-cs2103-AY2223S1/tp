package bookface.logic.commands.list;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.model.Model;

/**
 * Lists all users with loans and books that are loaned out
 */

public class ListLoansCommand extends ListCommand {
    public static final String COMMAND_WORD = "loans";
    public static final String MESSAGE_USAGE = ListCommand.generateMessage(COMMAND_WORD, COMMAND_WORD, COMMAND_WORD);
    public static final String MESSAGE_SUCCESS = "Listed all users with loans and books loaned out";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof ListLoansCommand);
    }
    
}
