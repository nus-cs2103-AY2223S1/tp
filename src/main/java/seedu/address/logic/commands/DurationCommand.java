package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Duration;
import seedu.address.model.person.DurationList;
import seedu.address.model.person.Person;

import java.util.List;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class DurationCommand extends Command {

    public static final String COMMAND_WORD = "duration";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds duration to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing duration will not be modified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DURATION + "DURATION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DURATION + "08:30-09:30";

    public static final String MESSAGE_ADD_DURATION_SUCCESS = "Added duration to Person: %1$s";
    public static final String MESSAGE_DELETE_DURATION_SUCCESS = "Removed duration from Person: %1$s";

    private final Index index;
    private final Duration duration;

    public DurationCommand(Index index, Duration duration) {
        requireAllNonNull(index, duration);

        this.index = index;
        this.duration = duration;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());

        DurationList durationList = personToEdit.getDurationList();
        durationList.addDuration(duration);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getLessonPlan(),
                personToEdit.getHomeworkList(), personToEdit.getAttendanceList(),
                durationList,
                personToEdit.getGradeProgressList(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on
     * whether the duration is added or removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !duration.toString().isEmpty() ? MESSAGE_ADD_DURATION_SUCCESS
                : MESSAGE_DELETE_DURATION_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        //short circuit if same object
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof DurationCommand)) {
            return false;
        }

        //state check
        DurationCommand temp = (DurationCommand) other;
        return index.equals(temp.index) && duration.equals(temp.duration);
    }


}