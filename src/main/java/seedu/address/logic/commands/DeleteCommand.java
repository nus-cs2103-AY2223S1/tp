package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Model.ListType;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;


/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ENTITY_SUCCESS = "Deleted Entity: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ListType type = model.getCurrentListType();
        List<Student> lastShownStudentList;
        List<Tutor> lastShownTutorList;
        List<TuitionClass> lastShownTuitionClassList;
        String entityInformation;

        try {
            switch (type) {
            case STUDENT_LIST:
                lastShownStudentList = model.getFilteredStudentList();
                Student studentToDelete = lastShownStudentList.get(targetIndex.getZeroBased());
                model.deletePerson(studentToDelete);
                entityInformation = studentToDelete.toString();
                break;
            case TUTOR_LIST:
                lastShownTutorList = model.getFilteredTutorList();
                Tutor tutorToDelete = lastShownTutorList.get(targetIndex.getZeroBased());
                model.deletePerson(tutorToDelete);
                entityInformation = tutorToDelete.toString();
                break;
            default:
                assert (type == ListType.TUITIONCLASS_LIST);
                lastShownTuitionClassList = model.getFilteredTuitionClassList();
                TuitionClass tuitionClassToDelete = lastShownTuitionClassList.get(targetIndex.getZeroBased());
                model.deleteTuitionClass(tuitionClassToDelete);
                entityInformation = tuitionClassToDelete.toString();
                break;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ENTITY_SUCCESS, entityInformation));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
