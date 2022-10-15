package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Session;
import seedu.address.model.person.SessionList;

/**
 * Adds session to an existing person in the address book.
 */
public class SessionCommand extends Command {

    public static final String COMMAND_WORD = "session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds session to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing session will not be modified.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SESSION + "SESSION]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SESSION + "08:30-09:30";

    public static final String MESSAGE_ADD_SESSION_SUCCESS = "Added session to Person: %1$s";
    public static final String MESSAGE_DELETE_SESSION_SUCCESS = "Removed session from Person: %1$s";

    private final Index index;
    private final Session session;

    /**
     * @param index of the person in the filtered person to edit the session.
     * @param session of the person to add.
     */
    public SessionCommand(Index index, Session session) {
        requireAllNonNull(index, session);

        this.index = index;
        this.session = session;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());

        SessionList sessionList = personToEdit.getSessionList();
        sessionList.addSession(session);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getLessonPlan(),
                personToEdit.getHomeworkList(), personToEdit.getAttendanceList(),
                sessionList,
                personToEdit.getGradeProgressList(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on
     * whether the session is added or removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !session.toString().isEmpty() ? MESSAGE_ADD_SESSION_SUCCESS
                : MESSAGE_DELETE_SESSION_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        //short circuit if same object
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof SessionCommand)) {
            return false;
        }

        //state check
        SessionCommand temp = (SessionCommand) other;
        return index.equals(temp.index) && session.equals(temp.session);
    }


}
