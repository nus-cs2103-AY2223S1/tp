package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STAFF;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffName;
import seedu.address.model.staff.UniqueStaffList;

/**
 * Delete a staff from a project.
 */
public class DeleteStaffCommand extends Command {
    public static final String COMMAND_WORD = "delstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": The project name refers to the project whose staff will be deleted. The command "
            + "looks for the staff identified by the index\n"
            + "within the displayed staff list and deletes the "
            + "staff if its in the project. Checking if the staff is in the project is through staff name only.\n"
            + "Make sure you view the correct staff list before deleting a staff.\n"
            + "Parameters: "
            + "INDEX (must be a number from 1 to 2147483647) "
            + PREFIX_PROJECT_NAME + "PROJECT_NAME\n"
            + "Example: " + COMMAND_WORD + " 1" + " pn/CS2103T TP";

    public static final String MESSAGE_DELETE_STAFF_SUCCESS = "Deleted Staff from %2$s: %1$s\n"
            + "Displaying all staff in project: %2$s ";

    private String projectName;

    private Index index;

    /**
     * Creates a DeleteStaffCommand.
     * @param index the staff name to check for
     * @param projectName the project name to check for
     */
    public DeleteStaffCommand(Index index, ProjectName projectName) {
        this.index = index;
        this.projectName = projectName.fullName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Project> projectList = model.getFilteredProjectList();
        List<Staff> lastShownStaffList = model.getFilteredStaffList();

        if (index.getZeroBased() >= lastShownStaffList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX));
        }

        Staff staffToDelete = lastShownStaffList.get(index.getZeroBased());
        StaffName staffName = staffToDelete.getStaffName();

        int len = projectList.size();
        Project project = null;
        for (int x = 0; x < len; x++) {
            String nameToCheck = projectList.get(x).getProjectName().toString();
            if (nameToCheck.equalsIgnoreCase(projectName)) {
                project = projectList.get(x);
                break;
            }
        }

        if (project == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectName));
        }

        UniqueStaffList staffList = project.getStaffList();
        Staff staff = null;
        for (Staff tempStaff : staffList) {
            if (tempStaff.getStaffName().staffName.equalsIgnoreCase(staffName.toString())) {
                staff = tempStaff;
                break;
            }
        }
        if (staff == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_STAFF, staffName));
        }
        staffList.remove(staff);
        model.setFilteredStaffList(project);
        model.updateFilteredStaffList(Model.PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_DELETE_STAFF_SUCCESS, staffName, projectName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStaffCommand // instanceof handles nulls
                && projectName.equals(((DeleteStaffCommand) other).projectName)
                && index.equals(((DeleteStaffCommand) other).index));
    }
}
