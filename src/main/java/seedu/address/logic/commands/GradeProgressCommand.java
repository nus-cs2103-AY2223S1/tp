package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.GradeProgress;
import seedu.address.model.person.GradeProgressList;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Adds homework to an existing person in the address book.
 */
public class GradeProgressCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds grade progress to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing grade progress will not be modified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_GRADE_PROGRESS + " GRADEPROGRESS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GRADE_PROGRESS + " MATH:A";

    public static final String MESSAGE_ADD_GRADE_PROGRESS_SUCCESS = "Added grade progress to Person: %1$s";

    public static final String MESSAGE_IN_DAY_MODE = "You need to be in list or view mode to add a grade progress.";

    private final Index index;
    private final GradeProgress gradeProgress;

    /**
     * @param index of the person in the filtered person list to edit the homework
     * @param gradeProgress of the person to add
     */
    public GradeProgressCommand(Index index, GradeProgress gradeProgress) {
        requireAllNonNull(index, gradeProgress);
        this.index = index;
        this.gradeProgress = gradeProgress;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.isDayView()) {
            throw new CommandException(MESSAGE_IN_DAY_MODE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        GradeProgressList gradeProgressList = personToEdit.getGradeProgressList();
        gradeProgressList.addGradeProgress(gradeProgress);

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getLessonPlan(),
                personToEdit.getHomeworkList(), personToEdit.getAttendanceList(),
                personToEdit.getSessionList(),
                gradeProgressList, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        if (model.isFullView()) {
            editedPerson.setFullView();
            String[] newNameKeywords = {personToEdit.getName().fullName};
            model.updateFilteredPersonList(new NameIsKeywordsPredicate(Arrays.asList(newNameKeywords)));
        }
        return new CommandResult(String.format(MESSAGE_ADD_GRADE_PROGRESS_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeProgressCommand)) {
            return false;
        }

        // state check
        GradeProgressCommand e = (GradeProgressCommand) other;
        return index.equals(e.index)
                && gradeProgress.equals(e.gradeProgress);
    }
}

