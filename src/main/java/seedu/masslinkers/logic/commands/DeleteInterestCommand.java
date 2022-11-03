package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Student;

//@@author ElijahS67
/**
 * Deletes interests from the specified batchmate.
 */
public class DeleteInterestCommand extends Command {

    public static final String COMMAND_WORD = "deleteInt";
    public static final String MESSAGE_USAGE = "Delete interests from a batchmate in this manner: "
            +
            "\ndeleteInt INDEX INTEREST [MORE_INTERESTS]...";
    public static final String MESSAGE_SUCCESS = "Interest(s) deleted successfully!";
    public static final String MESSAGE_INVALID_INTEREST = "This batchmate does not have all of the interests specified."
            + "\nPlease check the entered interests and try again.";

    private final Index targetIndex;
    private final Set<Interest> interests;

    /**
     * Constructs a command that deletes all interests specified from the target batchmate.
     *
     * @param index The index of the batchmate.
     * @param interests The set of interests to be deleted.
     */
    public DeleteInterestCommand(Index index, Set<Interest> interests) {
        requireNonNull(index);
        requireNonNull(interests);

        this.targetIndex = index;
        this.interests = interests;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());

        if (studentToEdit.canDeleteInterests(this.interests)) {
            studentToEdit.deleteInterests(this.interests);
        } else {
            throw new CommandException(MESSAGE_INVALID_INTEREST);
        }
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToEdit), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteInterestCommand)) {
            return false;
        }

        // state check
        DeleteInterestCommand e = (DeleteInterestCommand) other;
        return targetIndex.equals(e.targetIndex)
                && interests.equals(e.interests);
    }
}
