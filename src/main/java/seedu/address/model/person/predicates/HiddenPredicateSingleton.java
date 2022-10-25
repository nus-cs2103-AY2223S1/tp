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
    private static Predicate<Person> currPersonPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
    private static Predicate<Appointment> currApptPredicate = Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
    private static List<Person> hiddenPersons = new ArrayList<>();
    private static List<Appointment> hiddenAppts = new ArrayList<>();
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
    public static void addToHiddenPersonList(Person p) {
        hiddenPersons.add(p);
    }

    /**
     * Removes the person from the current list of hidden persons.
     * @param p Person to be unhidden.
     */
    public static void removeFromHiddenPersonList(Person p) {
        hiddenPersons.remove(p);
    }

    /**
     * Removes the appointment from the current list of hidden persons.
     * @param a Appointment to be unhidden.
     */
    public static void removeFromHiddenAppointmentList(Appointment a) {
        hiddenAppts.remove(a);
    }

    /**
     * Adds to the current list of hidden appointments.
     * @param p Appointment to be hidden.
     */
    public static void addToHiddenApptList(Appointment p) {
        hiddenAppts.add(p);
    }

    public static HiddenPredicateSingleton getInstance() {
        return instance;
    }

    /**
     * Combines the current predicate shown on idENTify with the new predicate to hide patients by name or tag.
     * @param p Predicate to exclude patients by.
     * @return The predicate result of combining the exclusion predicate with the current predicate.
     */
    public static Predicate<Person> combineWithHiddenPredicate(Predicate<Person> p) {
        currPersonPredicate = currPersonPredicate.and(Predicate.not(p));
        return currPersonPredicate;
    }

    /**
     * Combines the current predicate shown on idENTify with the new predicate to unhide patients by name or tag.
     * @param p Predicate to show patients by.
     * @return The predicate result of combining the non-exclusion predicate with the current predicate.
     */
    public static Predicate<Person> combineWithUnhiddenPredicate(Predicate<Person> p) {
        currPersonPredicate = currPersonPredicate.or(p);
        return currPersonPredicate;
    }

    /**
     * Combines current predicate with the new appointment predicate.
     * @param a Predicate to exclude the appointment.
     * @return The predicate result of combining the appointment predicate with the current predicate.
     */
    public static Predicate<Appointment> combineWithApptPredicate(Predicate<Appointment> a) {
        currApptPredicate = currApptPredicate.and(Predicate.not(a));
        return currApptPredicate;
    }

    /**
     * Combines current predicate with the new appointment predicate.
     * @param a Predicate to include the appointment.
     * @return The predicate result of combining the appointment predicate with the current predicate.
     */
    public static Predicate<Appointment> combineWithUnhiddenApptPredicate(Predicate<Appointment> a) {

        currApptPredicate = currApptPredicate.or(a);
        return currApptPredicate;
    }

    /**
     * Resets the list of hidden patients to empty.
     */
    public static void clearHiddenPatients() {
        currPersonPredicate = Model.PREDICATE_SHOW_ALL_PERSONS;
        hiddenPersons.clear();
    }

    /**
     * Resets the list of hidden appointments to empty.
     */
    public static void clearHiddenAppts() {
        currApptPredicate = Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
        hiddenAppts.clear();
    }

    /**
     * Resets the list of hidden patients and appointments to empty.
     */
    public static void clearHiddenAll() {
        clearHiddenPatients();
        clearHiddenAppts();
    }

    public static Predicate<Appointment> getCurrApptPredicate() {
        return currApptPredicate;
    }

}
