package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STAFF;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NO_STAFF_DISPLAYED;
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

        checkForEmptyList(projectList, lastShownStaffList);
        if (index.getZeroBased() >= lastShownStaffList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX));
        }

        Staff staffToDelete = lastShownStaffList.get(index.getZeroBased());
        StaffName staffName = staffToDelete.getStaffName();

        Project project = getProjectFrom(projectList);

        UniqueStaffList staffList = project.getStaffList();
        Staff staff = getStaffFrom(staffList, staffName);

        staffList.remove(staff);
        model.setFilteredStaffList(project);
        model.updateFilteredStaffList(Model.PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_DELETE_STAFF_SUCCESS, staffName, project.getProjectName()));
    }

    /**
     * Checks if there are Projects and Staff displayed on their respectively list.
     * @param projectList The displayed Project list
     * @param staffList The displayed Staff list
     * @throws CommandException Exception thrown if either list do not have anything displayed
     */
    private void checkForEmptyList(List<Project> projectList, List<Staff> staffList)
            throws CommandException {
        if (projectList.size() == 0) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectName));
        }

        if (staffList.size() == 0) {
            throw new CommandException(String.format(MESSAGE_NO_STAFF_DISPLAYED, "delstaff command"));
        }
    }

    private Project getProjectFrom(List<Project> projectList) throws CommandException {
        Project tempProject = null;
        for (int x = 0; x < projectList.size(); x++) {
            String nameToCheck = projectList.get(x).getProjectName().toString();
            if (nameToCheck.equalsIgnoreCase(projectName)) {
                tempProject = projectList.get(x);
                break;
            }
        }

        if (tempProject == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectName));
        }
        return tempProject;
    }

    private Staff getStaffFrom(UniqueStaffList staffList, StaffName staffName) throws CommandException {
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
        return staff;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStaffCommand // instanceof handles nulls
                && projectName.equals(((DeleteStaffCommand) other).projectName)
                && index.equals(((DeleteStaffCommand) other).index));
    }
}
