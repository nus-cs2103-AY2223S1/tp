package seedu.address.model.person.predicates;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Name} is contained inside the current list of hidden persons.
 */
public class HiddenPredicateSingleton implements Predicate<Person> {
    private static HiddenPredicateSingleton instance = new HiddenPredicateSingleton();
    private Predicate<Person> currPersonPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
    private Predicate<Appointment> currApptPredicate = Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
    private List<Person> hiddenPersons = new ArrayList<>();
    private List<Appointment> hiddenAppts = new ArrayList<>();
    private HiddenPredicateSingleton() {}

    @Override
    public boolean test(Person person) {
        if (hiddenPersons.contains(person)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // singleton only 1 instance
    }

    /**
     * Adds to the current list of hidden persons.
     * @param p Person to be hidden.
     */
    public void addToHiddenPersonList(Person p) {
        hiddenPersons.add(p);
    }

    public static HiddenPredicateSingleton getInstance() {
        return instance;
    }

    /**
     * Combines the current predicate shown on idENTify with the new predicate to hide patients by name or tag.
     * @param p Predicate to exclude patients by.
     * @return The predicate result of combining the exclusion predicate with the current predicate.
     */
    public Predicate<Person> combineWithHiddenPredicate(Predicate<Person> p) {
        currPersonPredicate = currPersonPredicate.and(Predicate.not(p));
        return currPersonPredicate;
    }

    /**
     * Combines the current predicate shown on idENTify with a new person predicate,
     * @param p Predicate for the current model.
     * @return The predicate result of combining the new predicate with the current predicate.
     */
    public Predicate<Person> combineWithRegularPredicate(Predicate<Person> p) {
        currPersonPredicate = currPersonPredicate.and(p);
        return currPersonPredicate;
    }

    /**
     * Combines the current predicate shown on idENTify with a new appointment predicate,
     * @param p Predicate for the current model.
     * @return The predicate result of combining the new predicate with the current predicate.
     */
    public Predicate<Appointment> combineWithRegularApptPredicate(Predicate<Appointment> p) {
        currApptPredicate = currApptPredicate.and(p);
        return currApptPredicate;
    }

    /**
     * Combines the current predicate shown on idENTify with the new predicate to unhide patients by name or tag.
     * @param p Predicate to show patients by.
     * @return The predicate result of combining the non-exclusion predicate with the current predicate.
     */
    public Predicate<Person> combineWithUnhiddenPredicate(Predicate<Person> p) {
        currPersonPredicate = currPersonPredicate.or(p);
        return currPersonPredicate;
    }

    /**
     * Combines current predicate with the new appointment predicate.
     * @param a Predicate to exclude the appointment.
     * @return The predicate result of combining the appointment predicate with the current predicate.
     */
    public Predicate<Appointment> combineWithApptPredicate(Predicate<Appointment> a) {
        currApptPredicate = currApptPredicate.and(Predicate.not(a));
        return currApptPredicate;
    }

    /**
     * Combines current predicate with the new appointment predicate.
     * @param a Predicate to include the appointment.
     * @return The predicate result of combining the appointment predicate with the current predicate.
     */
    public Predicate<Appointment> combineWithUnhiddenApptPredicate(Predicate<Appointment> a) {
        currApptPredicate = currApptPredicate.or(a);
        return currApptPredicate;
    }

    /**
     * Resets the list of hidden patients to empty.
     */
    public void clearHiddenPatients() {
        currPersonPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
        hiddenPersons.clear();
    }

    /**
     * Resets the list of hidden appointments to empty.
     */
    public void clearHiddenAppts() {
        currApptPredicate = Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
        hiddenAppts.clear();
    }

    /**
     * Resets the list of hidden patients and appointments to empty.
     */
    public void clearHiddenAll() {
        clearHiddenPatients();
        clearHiddenAppts();
    }

    public Predicate<Person> getCurrPersonPredicate() {
        return currPersonPredicate;
    }

    public Predicate<Appointment> getCurrApptPredicate() {
        return currApptPredicate;
    }

}
