package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PROJECT_DONT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_INSURANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;

/**
 * Adds a staff to the address book.
 */
public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "addStaff";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to the address book. "
            + "Parameters: "
            + PREFIX_PROJECT_NAME + "PROJECT NAME "
            + PREFIX_STAFF_NAME + "NAME "
            + PREFIX_STAFF_CONTACT + "PHONE_NUMBER "
            + PREFIX_STAFF_INSURANCE + "INSURANCE_STATUS "
            + PREFIX_STAFF_TITLE + "STAFF_TITLE "
            + PREFIX_STAFF_DEPARTMENT + "STAFF_DEPARTMENT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT_NAME + "CS2103T TP "
            + PREFIX_STAFF_NAME + "John Doe "
            + PREFIX_STAFF_CONTACT + "98765432 "
            + PREFIX_STAFF_INSURANCE + "true "
            + PREFIX_STAFF_TITLE + "Accountant "
            + PREFIX_STAFF_DEPARTMENT + "Accounting";

    public static final String MESSAGE_ADD_STAFF_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_STAFF = "This staff already exists in the project";

    private final Staff toAdd;
    private final ProjectName addTo;

    /**
     * Creates an AddStaffCommand to add the specified {@code Staff} to the
     * {@code Project} with specified {@code pname}.
     */
    public AddStaffCommand(Staff staff, ProjectName pname) {
        requireNonNull(staff);
        toAdd = staff;
        addTo = pname;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();
        int projectIndex = 0;

        for (int i = 0; i < lastShownList.size(); ++i) {
            if (lastShownList.get(i).getProjectName().equals(addTo)) {
                projectIndex = i;
            }
        }

        Index index = Index.fromZeroBased(projectIndex);

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        ProjectName projectName = lastShownList.get(index.getZeroBased()).getProjectName();
        if (!projectName.equals(addTo)) {
            throw new CommandException(String.format(MESSAGE_PROJECT_DONT_EXIST, addTo.fullName));
        }

        Project projectToAdd = lastShownList.get(index.getZeroBased());
        assert projectToAdd != null;

        if (projectToAdd.getStaffList().contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STAFF);
        }

        projectToAdd.getStaffList().add(toAdd);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_ADD_STAFF_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                && toAdd.equals(((AddStaffCommand) other).toAdd)
                && addTo.equals(((AddStaffCommand) other).addTo));
    }

}
