package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;

/**
 * LinkExamCommand represents a command which links the exam to the task.
 */
public class LinkExamCommand extends Command {
    public static final String COMMAND_WORD = "link";
    public static final String MESSAGE_USAGE = "e " + COMMAND_WORD + ": links the exam to the task.\n"
            + "Parameters: " + PREFIX_EXAM_INDEX + "EXAM_INDEX" + " "
            + PREFIX_TASK_INDEX + "TASK_INDEX\n"
            + "Example: " + "e " + COMMAND_WORD + " t/1 e/1";
    public static final String EXAM_LINKED_SUCCESS = "The exam has been successfully linked to the task!";
    public static final String TASK_ALREADY_LINKED = "This task is already linked to an exam";
    public static final String DIFFERENT_MODULE_CODE = "This task has a different module code from the exam.";
    private final Index examIndex;
    private final Index taskIndex;

    /**
     * The constructor of the LinkExamCommand class. Sets
     * the index in the exam list and the index in the task list.
     *
     * @param examIndex The index in the exam list.
     * @param taskIndex The index in the task list.
     */
    public LinkExamCommand(Index examIndex, Index taskIndex) {
        requireAllNonNull(examIndex, taskIndex);
        this.examIndex = examIndex;
        this.taskIndex = taskIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> tasks = model.getFilteredTaskList();
        List<Exam> exams = model.getFilteredExamList();
        if (taskIndex.getZeroBased() >= tasks.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        if (examIndex.getZeroBased() >= exams.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }
        Task task = tasks.get(taskIndex.getZeroBased());
        if (task.isLinked()) {
            throw new CommandException(TASK_ALREADY_LINKED);
        }
        Exam exam = exams.get(examIndex.getZeroBased());
        if (!Module.isSameModule(task.getModule(), exam.getModule())) {
            throw new CommandException(DIFFERENT_MODULE_CODE);
        }
        Task linkedTask = task.linkTask(exam);
        model.replaceTask(task, linkedTask, true);
        return new CommandResult(EXAM_LINKED_SUCCESS);
    }
}
