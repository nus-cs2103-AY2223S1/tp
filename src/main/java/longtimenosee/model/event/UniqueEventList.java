package longtimenosee.model.event;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;
import static longtimenosee.model.event.Event.EVENT_COMPARATOR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import longtimenosee.model.event.exceptions.DuplicateEventException;
import longtimenosee.model.event.exceptions.EventNotFoundException;
import longtimenosee.model.event.exceptions.OverlapEventException;
import longtimenosee.model.person.Person;


/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * A Event is considered unique by comparing using {@code Event#isSameEvent(Event)}. As such, adding and updating of
 * events uses Event#isSameEvent(Event) for equality to ensure that the Event being added or updated is
 * unique in terms of identity in the UniqueEventList. However, the removal of a Event uses Event#equals(Object)
 * to ensure that the Event with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {


    private final ObservableList<Event> internalList = FXCollections.observableArrayList();

    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds an Event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) throws DuplicateEventException, OverlapEventException {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
        if (overlaps(toAdd)) {
            throw new OverlapEventException();
        }
        internalList.add(toAdd);
        FXCollections.sort(internalList, EVENT_COMPARATOR);

    }

    /**
     * Checks if an incoming event will cause the events to overlap
     * @param toAdd
     * @return
     */
    public boolean overlaps(Event toAdd) {
        for (Event e : internalList) {
            if (Event.eventClash(e, toAdd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }
    /**
     * Replaces the contents of this list with {@code events}.
     * {@code events} must not contain duplicate persons.
     */
    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }
        if (!eventsNoClash(events)) {
            throw new OverlapEventException();
        }
        internalList.setAll(events);
    }
    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEventList // instanceof handles nulls
                && internalList.equals(((UniqueEventList) other).internalList));
    }

    /**
     * Check if the internal list has said event.
     * Less strict equalty used (i.e: Only the person's name /descriptor)
     * @param toCheck
     * @return
     */
    public boolean hasEvent(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Attempts to delete an event
     * Strict equality applies for deleting events, as opposed to hasEvent
     * @param e
     */
    public void deleteEvent(Event e) {
        requireNonNull(e);
        if (!internalList.remove(e)) {
            throw new EventNotFoundException();
        }
    }
    //TODO: Events are unique?

    /**
     * Returns true if {@code persons} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if{@code events} contain only events that do not clash.
     * @param events
     * @return
     */
    public boolean eventsNoClash(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (Event.eventClash(events.get(i), events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * List overlapping events in UniqueEventList
     * comparing event passed into parameter
     * @param toAdd
     * @return list of events which overlap with incoming event.
     */
    public List<Event> listEventOverlap(Event toAdd) {
        List<Event> clashingEvents = new ArrayList<>();
        for (int i = 0; i < internalList.size(); i++) {
            if (Event.eventClash(internalList.get(i), toAdd)) {
                clashingEvents.add(internalList.get(i));
            }
        }
        return clashingEvents;
    }
    /**
     * List events occuring on the same day in UniqueEventList
     * comparing event passed into parameter
     * @param toAdd
     * @return list of events on the same day.
     */
    public List<Event> listEventSameDay(Event toAdd) {
        List<Event> sameDayEvents = new ArrayList<>();
        for (int i = 0; i < internalList.size(); i++) {
            if (toAdd.getDate().equals(internalList.get(i).getDate())) {
                sameDayEvents.add(internalList.get(i));
            }
        }
        return sameDayEvents;
    }

    /**
     * Lists upcoming events in the next 7 days from the day that this method is called
     * @return list of events in the next 7 days
     */
    public List<Event> calendarView() {
        List<Event> thisWeek = new ArrayList<>();
        LocalDate todaysDate = LocalDate.now();
        LocalDate weekLater = todaysDate.plusDays(7);
        for (Event event : internalList) {
            LocalDate eventDate = event.getDate().getDate();
            if ((eventDate.isAfter(todaysDate) || eventDate.isEqual(todaysDate))
                    && (eventDate.isBefore(weekLater) || eventDate.isEqual(weekLater))) {
                thisWeek.add(event);
            }
        }
        return thisWeek;
    }

    /**
     * Removes all events in internal event list associated with given person.
     * @param person
     */
    public void removeEventsUnderPerson(Person person) {
        List<Event> eventsAssociatedWithPerson = new ArrayList<>();
        for (Event event : internalList) {
            if (event.getPersonName().fullName.equals(person.getName().fullName)) {
                eventsAssociatedWithPerson.add(event);
            }
        }
        for (Event event : eventsAssociatedWithPerson) {
            remove(event);
        }
    }
}
