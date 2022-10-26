package seedu.address.logic.commands.task;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.Model;
import seedu.address.model.task.Project;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Lists all project names available in task panel.
 */
public class ListTaskProjectsCommand extends TaskCommand {
    public static final String COMMAND_WORD = "project";

    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all projects";

    /**
     * Prints contents in projectList.
     * @param projectList List of available projects.
     * @return In string format.
     */
    public String printContents(List<Project> projectList) {
        String result = "";
        for(int i = 0; i < projectList.size(); i++) {
            result = (i + 1) + ". " + projectList.get(i).projectName + "\n";
        }
        return result + "\n";
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Project> projectList = model.getProjectList();
        String result = printContents(projectList);

        return new CommandResult(result + MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListTaskProjectsCommand);
    }
}
