package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.Department;

/**
 * View a declared Department along with relevant information.
 */
public class ViewDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "view-department";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the department information in the displayed person list.\n"
            + "Parameters: DEPARTMENT \n"
            + "Example: " + COMMAND_WORD + " Finance";

    public static final String MESSAGE_VIEW_DEPARTMENT_SUCCESS = "Viewing Department's info: %1$s";
    public static final String MESSAGE_UNKNOWN_DEPARTMENT = " is not a valid department!";

    private final String department;

    public ViewDepartmentCommand(String department) {
        this.department = department;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (Department.isValidDepartment(department)) {
            return new CommandResult(String.format(MESSAGE_VIEW_DEPARTMENT_SUCCESS, department), department);
        } else {
            throw new CommandException(MESSAGE_UNKNOWN_DEPARTMENT);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewDepartmentCommand // instanceof handles nulls
                && department.equals(((ViewDepartmentCommand) other).department));
    }

}
