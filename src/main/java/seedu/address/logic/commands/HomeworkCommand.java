package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Homework;
import seedu.address.model.person.HomeworkList;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Adds homework to an existing person in the address book.
 */
public class HomeworkCommand extends Command {

    public static final String COMMAND_WORD = "hw";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds homework to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing homework will not be modified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_HOMEWORK + "HOMEWORK]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_HOMEWORK + "Science worksheet";

    public static final String MESSAGE_ADD_HOMEWORK_SUCCESS = "Added homework to Person: %1$s";

    public static final String MESSAGE_IN_DAY_MODE = "You need to be in list or view mode to add a homework.";

    private final Index index;
    private final Homework homework;

    /**
     * @param index of the person in the filtered person list to edit the homework
     * @param homework of the person to add
     */
    public HomeworkCommand(Index index, Homework homework) {
        requireAllNonNull(index, homework);

        this.index = index;
        this.homework = homework;
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

        HomeworkList homeworkList = personToEdit.getHomeworkList();
        homeworkList.addHomework(homework);

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getLessonPlan(),
                homeworkList, personToEdit.getAttendanceList(),
                personToEdit.getSessionList(),
                personToEdit.getGradeProgressList(),
                personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        if (model.isFullView()) {
            editedPerson.setFullView();
            String[] newNameKeywords = {personToEdit.getName().fullName};
            model.updateFilteredPersonList(new NameIsKeywordsPredicate(Arrays.asList(newNameKeywords)));
        }
        return new CommandResult(String.format(MESSAGE_ADD_HOMEWORK_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HomeworkCommand)) {
            return false;
        }

        // state check
        HomeworkCommand e = (HomeworkCommand) other;
        return index.equals(e.index)
                && homework.equals(e.homework);
    }
}
