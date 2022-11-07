package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LessonPlan;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Updates a lesson plan for an existing person in the address book.
 */
public class LessonPlanCommand extends Command {
    public static final String COMMAND_WORD = "lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a lesson plan to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing lesson plan will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_LESSON_PLAN + "LESSON PLAN]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LESSON_PLAN + "Data structures and algorithms.";

    public static final String MESSAGE_ADD_LESSON_PLAN_SUCCESS = "Updated lesson plan for Person: %1$s";
    public static final String MESSAGE_DELETE_LESSON_PLAN_SUCCESS = "Removed lesson plan from Person: %1$s";

    public static final String MESSAGE_IN_DAY_MODE = "You need to be in list or view mode to update a lesson plan.";

    private final Index index;
    private final LessonPlan lessonPlan;

    /**
     * @param index of the person in the filtered person list to edit the lesson plan
     * @param lessonPlan of the person to add
     */
    public LessonPlanCommand(Index index, LessonPlan lessonPlan) {
        requireAllNonNull(index, lessonPlan);

        this.index = index;
        this.lessonPlan = lessonPlan;
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

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), lessonPlan, personToEdit.getHomeworkList(),
                personToEdit.getAttendanceList(),
                personToEdit.getSessionList(),
                personToEdit.getGradeProgressList(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        if (model.isFullView()) {
            editedPerson.setFullView();
            String[] newNameKeywords = {personToEdit.getName().fullName};
            model.updateFilteredPersonList(new NameIsKeywordsPredicate(Arrays.asList(newNameKeywords)));
        }
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the lesson plan is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !lessonPlan.value.isEmpty()
                ? MESSAGE_ADD_LESSON_PLAN_SUCCESS
                : MESSAGE_DELETE_LESSON_PLAN_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LessonPlanCommand)) {
            return false;
        }

        // state check
        LessonPlanCommand e = (LessonPlanCommand) other;
        return index.equals(e.index)
                && lessonPlan.equals(e.lessonPlan);
    }
}
