package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.model.Model;
import seedu.address.model.task.Project;
import seedu.address.model.task.Task;

/**
 * Lists all project names available in task panel.
 */
public class TaskProjectCommand extends TaskCommand {

    public static final String COMMAND_WORD = "project";

    public static final String COMMAND_WORD_FULL = TaskCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all projects";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        StringBuilder builder = new StringBuilder();
        Set<Project> projectList = model.getTaskPanel().getTaskList()
            .stream()
            .map(Task::getProject)
            .filter(t -> !t.isUnspecified())
            .collect(Collectors.toSet());
        final int[] counter = { 1 };
        projectList.forEach(p -> builder.append("\n").append(counter[0]++).append(". ").append(p.projectName));

        return new CommandResult(MESSAGE_SUCCESS + builder);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TaskProjectCommand;
    }
}
