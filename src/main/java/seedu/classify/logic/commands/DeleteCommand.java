package seedu.classify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.classify.commons.core.Messages;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.model.Model;
import seedu.classify.model.student.Id;
import seedu.classify.model.student.IdPredicate;
import seedu.classify.model.student.Name;
import seedu.classify.model.student.NamePredicate;
import seedu.classify.model.student.Student;

/**
 * Deletes an existing student record from the list, using the student’s name or ID.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a student record identified by the student name or student ID used in the student list.\n"
            + "Parameters:\n"
            + "NAME (should only contain alphanumeric characters and spaces) or\n"
            + "ID (should only contain 3 digits followed by 1 character)\n"
            + "Example: " + COMMAND_WORD + " nm/Jonathan Tan" + " or " + COMMAND_WORD + " id/123A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";

    private final Optional<Id> targetId;

    private final Optional<Name> targetName;

    private final Optional<IdPredicate> idPredicate;

    private final Optional<NamePredicate> namePredicate;

    /**
     * @param targetId of the student in the filtered student list to delete
     * @param idPredicate to filter the student list
     */
    public DeleteCommand(Id targetId, IdPredicate idPredicate) {
        this.targetId = Optional.of(targetId);
        this.targetName = Optional.empty();
        this.idPredicate = Optional.of(idPredicate);
        this.namePredicate = Optional.empty();
    }

    /**
     * @param targetName of the student in the filtered student list to delete
     * @param namePredicate to filter the student list
     */
    public DeleteCommand(Name targetName, NamePredicate namePredicate) {
        this.targetId = Optional.empty();
        this.targetName = Optional.of(targetName);
        this.idPredicate = Optional.empty();
        this.namePredicate = Optional.of(namePredicate);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetId.isEmpty()) {
            assert(namePredicate.isPresent());
            model.updateFilteredStudentList(namePredicate.get());
        } else {
            assert(idPredicate.isPresent());
            model.updateFilteredStudentList(idPredicate.get());
        }

        List<Student> studentList = model.getFilteredStudentList();

        if (studentList.size() == 0) {
            model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
            if (targetId.isEmpty()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NAME);
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_ID);
            }
        }

        Student studentToDelete = studentList.get(0);
        model.deleteStudent(studentList.get(0));
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.storePredicate(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (targetId.isEmpty()) {
            return other instanceof DeleteCommand && targetName.equals(((DeleteCommand) other).targetName);
        } else {
            return other instanceof DeleteCommand && targetId.equals(((DeleteCommand) other).targetId);
        }
    }
}
