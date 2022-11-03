package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.watson.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.watson.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.watson.commons.core.Messages;
import seedu.watson.commons.core.index.Index;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.student.Remark;
import seedu.watson.model.student.Student;

/**
 * Adds a remark to a student.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a remark for a student "
            + "by the index number used in the displayed student list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[REMARK] (must not be blank)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "He participates often in class";

    public static final String MESSAGE_SUCCESS = "Added remark for %s: %s";

    private final Index index;
    private final Remark remark;

    /**
     * Creates a remark command.
     * @param index of the student in the filtered student list to edit
     * @param remark remark to add
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    /**
     * Adds a remark to a student.
     * @param studentToAddRemark student to add remark
     * @param remark remark to add
     */
    private static Student addRemark(Student studentToAddRemark, Remark remark) {
        assert studentToAddRemark != null;
        Set<Remark> remarksList = studentToAddRemark.getRemarks();
        if (remarksList == null) {
            remarksList = new HashSet<>();
        }
        remarksList.add(remark);
        studentToAddRemark.setRemarks(remarksList);
        return studentToAddRemark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToAddRemark = lastShownList.get(index.getZeroBased());
        Student studentAddedRemark = addRemark(studentToAddRemark, remark);

        model.setPerson(studentToAddRemark, studentAddedRemark);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentAddedRemark.getName(), remark));
    }
}
