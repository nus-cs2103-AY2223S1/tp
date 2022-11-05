package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.UidList;
import seedu.address.model.person.Person;

/**
 * Untags customers in Person List to an event.
 */
public class UntagEventCommand extends Command {
    public static final String COMMAND_WORD = "untagEvent";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untags 1 or more persons to an event in the address book "
            + "by the index number used in the displayed person and event lists. "
            + "Parameters: "
            + "EVENT_INDEX (must be a positive integer) "
            + PREFIX_PERSONS + "PERSON_INDEX [MORE_PERSON_INDEXES] (each PERSON_INDEX must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PERSONS + "1 3 4";
    public static final String MESSAGE_UNTAG_EVENT_SUCCESS = "Untagged Persons: %s to Event: %s";
    public static final String MESSAGE_INVALID_PERSON = "This person: %d. %s is not tagged to the event";
    private final Index eventIndex;
    private final List<Index> personIndexes;

    /**
     * @param eventIndex index of the event to be untagged with persons.
     * @param personIndexes index of persons to be untagged to the event.
     */
    public UntagEventCommand(Index eventIndex, List<Index> personIndexes) {
        requireNonNull(eventIndex);
        requireNonNull(personIndexes);

        this.eventIndex = eventIndex;
        this.personIndexes = personIndexes;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Event> lastShownEventList = model.getFilteredEventList();
        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }
        Event eventToUntag = lastShownEventList.get(eventIndex.getZeroBased());
        UidList uids = eventToUntag.getUids();
        List<String> personNamesUntagged = new ArrayList<>();
        // loop through personIndexes, raise exceptions if any (invalid index or duplicated person)
        for (Index personIndex : personIndexes) {
            if (personIndex.getZeroBased() >= lastShownPersonList.size()) { // index is invalid
                throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person person = lastShownPersonList.get(personIndex.getZeroBased());
            if (!uids.contains(person.getUid())) { // person's uid not in the event to untag
                throw new CommandException(String.format(MESSAGE_INVALID_PERSON, personIndex.getOneBased(),
                        person.getName()));
            }
        }
        for (Index personIndex : personIndexes) {
            Person person = lastShownPersonList.get(personIndex.getZeroBased());
            personNamesUntagged.add(person.getName().toString());
            uids.remove(person.getUid());
        }
        // create editedEvent, set event, update event list
        Event untaggedEvent = new Event(eventToUntag, uids);
        model.setEvent(eventToUntag, untaggedEvent);
        model.updateEventPersonReference();
        return new CommandResult(String.format(MESSAGE_UNTAG_EVENT_SUCCESS,
                String.join(", ", personNamesUntagged), untaggedEvent.getEventTitle()));
    }
}
