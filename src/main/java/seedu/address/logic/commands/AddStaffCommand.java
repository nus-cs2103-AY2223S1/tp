package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STAFF;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.UniqueStaffList;

/**
 * Adds a staff to HR Pro Max++.
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
        toAdd = staff;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);;
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX));
        }

        Project targetProject = lastShownList.get(index.getZeroBased());
        ProjectName projectName = targetProject.getProjectName();

        if (targetProject.getStaffList().contains(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_STAFF, projectName));
        }

        UniqueStaffList targetStaffList = targetProject.getStaffList();
        targetStaffList.add(toAdd);
        model.setFilteredStaffList(targetStaffList);
        model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_ADD_STAFF_SUCCESS, toAdd, projectName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                && toAdd.equals(((AddStaffCommand) other).toAdd)
                && index.equals(((AddStaffCommand) other).index));
    }

}
