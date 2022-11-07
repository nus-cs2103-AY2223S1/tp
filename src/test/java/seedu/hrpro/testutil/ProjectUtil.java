package seedu.hrpro.testutil;

import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.hrpro.logic.commands.AddCommand;
import seedu.hrpro.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.tag.Tag;

/**
 * A utility class for Project.
 */
public class ProjectUtil {

    /**
     * Returns an add command string for adding the {@code project}.
     */
    public static String getAddCommand(Project project) {
        return AddCommand.COMMAND_WORD + " " + getProjectDetails(project);
    }

    /**
     * Returns the part of command string for the given {@code project}'s details.
     */
    public static String getProjectDetails(Project project) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_PROJECT_NAME + project.getProjectName().fullName + " ");
        sb.append(PREFIX_BUDGET + project.getBudget().value + " ");
        sb.append(PREFIX_DEADLINE + project.getDeadline().deadline.toString() + " ");
        project.getStaffList().asUnmodifiableObservableList().stream().forEach(
                s -> sb.append(PREFIX_STAFF_NAME + s.getStaffName().toString() + " ")
        );
        project.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditProjectDescriptor}'s details.
     */
    public static String getEditProjectDescriptorDetails(EditProjectDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getProjectName().ifPresent(projectName -> sb.append(PREFIX_PROJECT_NAME)
                .append(projectName).append(" "));
        descriptor.getBudget().ifPresent(budget -> sb.append(PREFIX_BUDGET)
                .append(budget).append(" "));
        descriptor.getDeadline().ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(
                deadline).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
