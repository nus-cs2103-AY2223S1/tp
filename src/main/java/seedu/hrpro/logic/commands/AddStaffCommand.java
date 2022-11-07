package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.hrpro.model.Model.PREDICATE_SHOW_ALL_STAFF;

import java.util.Optional;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;

/**
 * Adds a staff to a project in HR Pro Max++.
 */
public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "addstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to the project identified "
            + "by the index number used in the displayed project list.\n"
            + "Parameters: INDEX (must be a number from 1 to 2147483647) "
            + PREFIX_STAFF_NAME + "STAFF_NAME "
            + PREFIX_STAFF_CONTACT + "STAFF_PHONE "
            + PREFIX_STAFF_LEAVE + "LEAVE_STATUS "
            + PREFIX_STAFF_TITLE + "STAFF_TITLE "
            + PREFIX_STAFF_DEPARTMENT + "STAFF_DEPARTMENT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_STAFF_NAME + "John Doe "
            + PREFIX_STAFF_CONTACT + "98765432 "
            + PREFIX_STAFF_LEAVE + "true "
            + PREFIX_STAFF_TITLE + "Accountant "
            + PREFIX_STAFF_DEPARTMENT + "Accounting";

    public static final String MESSAGE_ADD_STAFF_SUCCESS = "New staff added to %2$s: %1$s\n"
            + "Displaying all staff in project: %2$s";
    public static final String MESSAGE_DUPLICATE_STAFF = "Staff already exists in the project: %1$s";
    private final Staff toAdd;
    private final Index index;

    /**
     * Creates an AddStaffCommand to add the specified {@code Staff} to the
     * {@code Project} with specified {@code pname}.
     */
    public AddStaffCommand(Staff staff, Index index) {
        requireNonNull(staff);
        requireNonNull(index);
        toAdd = staff;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);;

        Optional<Project> projectToAdd = model.getProjectWithIndex(index);
        Project project = projectToAdd.orElseThrow(() ->
                new CommandException(String.format(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX)));

        if (model.targetProjectContainsStaff(index, toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STAFF, project.getProjectName()));
        }

        model.addStaffToProject(index, toAdd);
        model.setFilteredStaffList(project.getStaffList());
        model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_ADD_STAFF_SUCCESS, toAdd, project.getProjectName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                && toAdd.equals(((AddStaffCommand) other).toAdd)
                && index.equals(((AddStaffCommand) other).index));
    }

}
