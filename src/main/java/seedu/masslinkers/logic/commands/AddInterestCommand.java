package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.masslinkers.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Set;

import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Student;

//@author sltsheryl
/**
 * Adds a list of interests to the specified student.
 */
public class AddInterestCommand extends Command {

    public static final String COMMAND_WORD = "addInt";
    public static final String MESSAGE_USAGE = "Add interests to a batchmate in this manner: "
            +
            "\naddInt INDEX INTEREST [MORE_INTERESTS]...";
    public static final String MESSAGE_SUCCESS = "Interest(s) added successfully!";

    private final Index targetIndex;
    private final Set<Interest> interests;

    /**
     * Constructs a command that adds all interests specified to the target batchmate.
     *
     * @param index The index of the batchmate.
     * @param interests The set of interests to be added.
     */
    public AddInterestCommand(Index index, Set<Interest> interests) {
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
        studentToEdit.addInterests(interests);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToEdit), false, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddInterestCommand)) {
            return false;
        }

        // state check
        AddInterestCommand e = (AddInterestCommand) other;
        return targetIndex.equals(e.targetIndex)
                && interests.equals(e.interests);
    }
}
