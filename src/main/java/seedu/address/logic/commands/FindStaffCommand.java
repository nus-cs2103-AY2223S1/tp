package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffName;

/**
 * Finds a staff within a given project in HR Pro Max++.
 */
public class FindStaffCommand extends Command {

    public static final String COMMAND_WORD = "findstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all staffs in a given project whose names "
            + "contain any of the specified keywords (case-insensitive) and displays them "
            + "as a list. \n "
            + "Parameters: "
            + "Parameters: KEYWORD [MORE_KEYWORDS]..."
            + PREFIX_PROJECT_NAME + "PROJECT_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + "alice bob charlie "
            + PREFIX_PROJECT_NAME + "CS2103T TP";

    public static final String MESSAGE_FIND_STAFF_SUCCESS = "Staff found: %1$s";

    public static final String MESSAGE_STAFF_NOT_FOUND = "Staff wasn't found in given project. "
            + "Perhaps check another project? ";

    private final ProjectName projectToFind;

    private final List<String> keywords;

    /**
     * Creats a FindStaffCommand to find the specified
     * {@code Project} with the specified {@code pname}
     * to look for a {@code Staff} with specified {@code sname}.
     */
    public FindStaffCommand(ProjectName pname, List<String> keywords) {
        requireNonNull(pname);
        requireNonNull(keywords);
        this.projectToFind = pname;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();
        int projectIndex = 0;
        List<StaffName> foundStaffNames = new ArrayList<>();

        for (int i = 0; i < lastShownList.size(); ++i) {
            if (lastShownList.get(i).getProjectName().equals(projectToFind)) {
                projectIndex = i;
            }
        }

        Index pIndex = Index.fromZeroBased(projectIndex);

        ProjectName projectName = lastShownList.get(pIndex.getZeroBased()).getProjectName();

        if (!projectName.equals(projectToFind)) {
            throw new CommandException(String.format(MESSAGE_INVALID_PROJECT, projectToFind.fullName));
        }

        Project projectToFind = lastShownList.get(pIndex.getZeroBased());

        for (Staff staff : projectToFind.getStaffList()) {
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(staff.getStaffName().toString(), keyword))) {
                foundStaffNames.add(staff.getStaffName());
            }
        }

        if (foundStaffNames.size() == 0) {
            throw new CommandException(MESSAGE_STAFF_NOT_FOUND);
        }

        return new CommandResult(String.format(MESSAGE_FIND_STAFF_SUCCESS, foundStaffNames));

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof FindStaffCommand)
                && projectToFind.equals(((FindStaffCommand) other).projectToFind)
                && keywords.equals(((FindStaffCommand) other).keywords);
    }
}
