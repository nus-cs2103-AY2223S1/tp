package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import coydir.model.Database;
import coydir.model.Model;
import coydir.model.person.EmployeeId;

/**
 * Clears the database.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Database has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setDatabase(new Database());
        EmployeeId.setCount(1);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
