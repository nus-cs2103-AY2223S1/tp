package seedu.hrpro.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_PROJECT;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_STAFF;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.hrpro.commons.core.Messages.MESSAGE_NO_STAFF_DISPLAYED;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;

import java.util.List;
import java.util.Optional;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.exceptions.CommandException;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;

/**
 * Delete a staff from a project in HR Pro Max++.
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

        Optional<Staff> staffToDelete = model.getStaffFromProjectAtIndex(new ProjectName(projectName), index);
        Optional<Project> projectToDelete = model.getProjectWithName(new ProjectName(projectName));

        Project project = projectToDelete.orElseThrow(() ->
                new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectName)));

        Staff toDelete = staffToDelete.orElseThrow(() ->
                new CommandException(MESSAGE_INVALID_STAFF_DISPLAYED_INDEX));

        boolean isSuccessfulDelete = model.removeStaffFromProject(new ProjectName(projectName), index);
        if (!isSuccessfulDelete) {
            throw new CommandException(String.format(MESSAGE_INVALID_STAFF, toDelete.getStaffName()));
        }
        model.setFilteredStaffList(project.getStaffList());
        model.updateFilteredStaffList(Model.PREDICATE_SHOW_ALL_STAFF);
        return new CommandResult(String.format(MESSAGE_DELETE_STAFF_SUCCESS,
                toDelete.getStaffName(), project.getProjectName()));
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStaffCommand // instanceof handles nulls
                && projectName.equals(((DeleteStaffCommand) other).projectName)
                && index.equals(((DeleteStaffCommand) other).index));
    }
}
