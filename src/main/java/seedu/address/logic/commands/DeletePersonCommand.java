package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the contact list of the application.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "deletePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person based on the index number in the contact list displayed.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetPersonIndex;
    /**
     * Creates a DeletePersonCommand.
     * @param targetPersonIndex displayed index of Event to be deleted.
     */
    public DeletePersonCommand(Index targetPersonIndex) {
        this.targetPersonIndex = targetPersonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Event> lastShownEventList = model.getFilteredEventList();

        Integer zeroBasedIndex = targetPersonIndex.getZeroBased();
        if (zeroBasedIndex >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        // untag personToDelete from all events in the event list
        Person personToDelete = lastShownPersonList.get(zeroBasedIndex);
        int eventIndex = 0;
        for (Event event : lastShownEventList) {
            if (event.containsPerson(personToDelete)) { // use untag command to untag person for each event
                new UntagEventCommand(Index.fromZeroBased(eventIndex), Arrays.asList(targetPersonIndex)).execute(model);
            }
            eventIndex += 1;
        }
        model.updateEventPersonReference();
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetPersonIndex.equals(((DeletePersonCommand) other).targetPersonIndex)); // state check
    }
}
