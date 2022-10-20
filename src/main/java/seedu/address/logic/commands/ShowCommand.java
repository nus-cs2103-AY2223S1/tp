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



/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the entity identified by the index number used in the displayed current list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ENTITY_SUCCESS = "Showing Entity: %1$s";

    public static final String MESSAGE_INVALID_CURRENT_LIST = "The current list type is invalid for assign command \n"
            + "Valid list type are tutor and student list";

    private final Index targetIndex;

    public ShowCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ListType type = model.getCurrentListType();
        List<Student> lastShownStudentList;
        List<Tutor> lastShownTutorList;
        String entityInformation;

        try {
            switch (type) {
            case STUDENT_LIST:
                lastShownStudentList = model.getFilteredStudentList();
                Student studentToShow = lastShownStudentList.get(targetIndex.getZeroBased());
                entityInformation = studentToShow.getName().fullName;
                break;
            case TUTOR_LIST:
                lastShownTutorList = model.getFilteredTutorList();
                Tutor tutorToShow = lastShownTutorList.get(targetIndex.getZeroBased());
                entityInformation = tutorToShow.getName().fullName;
                break;
            default:
                throw new CommandException(MESSAGE_INVALID_CURRENT_LIST);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_ENTITY_SUCCESS, entityInformation),
                targetIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && targetIndex.equals(((ShowCommand) other).targetIndex)); // state check
    }
}
