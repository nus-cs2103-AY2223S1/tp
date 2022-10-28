package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.Department;

/**
 * view a person's particular identified using its displayed index from the database.
 */
public class ViewDepartmentCommand extends Command {
    public static final String COMMAND_WORD = "view-department";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the department information in the displayed person list.\n"
            + "Parameters: DEPARTMENT \n"
            + "Example: " + COMMAND_WORD + " Finance";

    public static final String MESSAGE_VIEW_DEPARTMENT_SUCCESS = "Viewing Department's info: %1$s";

    private final String d;

    public ViewDepartmentCommand(String d) {
        this.d = d;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (Department.isValidDepartment(d)) {
            return new CommandResult(String.format(MESSAGE_VIEW_DEPARTMENT_SUCCESS, d), d);
        } else {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_DEPARTMENT);
        }
    }
}
