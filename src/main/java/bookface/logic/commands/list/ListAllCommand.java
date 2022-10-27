package bookface.logic.commands.list;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.model.Model;

/**
 * Lists all users and books
 */
public class ListAllCommand extends ListCommand {
    public static final String COMMAND_WORD = "all";
    public static final String MESSAGE_USAGE = ListCommand.generateMessage(COMMAND_WORD, COMMAND_WORD, COMMAND_WORD);
    public static final String MESSAGE_SUCCESS = "Listed all users and books";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList(Model.PREDICATE_SHOW_ALL_BOOKS);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListAllCommand); // all instances of ListAllCommand are equal
    }
}
