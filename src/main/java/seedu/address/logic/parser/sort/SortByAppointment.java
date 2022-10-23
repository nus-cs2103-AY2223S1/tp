package seedu.address.logic.parser.sort;

import java.util.Comparator;

import seedu.address.logic.util.exceptions.SortedListException;
import seedu.address.model.person.Person;

/**
 * Custom comparator that sorts Person based on their Names.
 */
public class SortByAppointment implements Comparator<Person> {

    private String order;

    /**
     * Constructor that takes in the order to sort the contact book by.
     * @param order the order to sort by.
     */
    public SortByAppointment(String order) {
        this.order = order;
    }

    // This implementation is such that the people without appointments will be placed below at the addressbook,
    // below the people with appointments when sorting.
    @Override
    public int compare(Person p1, Person p2) {

        // if 1 person has no appointments and 1 has, the guy with the appointment
        // should be placed in front of the guy with no appointments.
        if (p1.getAppointments().isEmpty() && !p2.getAppointments().isEmpty()) {
            return 1;
        } else if (p2.getAppointments().isEmpty() && !p1.getAppointments().isEmpty()) {
            return 0;
        }
        // if both person do not have appointments, order remains, no swap needed.
        else if (p1.getAppointments().isEmpty() && p2.getAppointments().isEmpty()) {
            return 0;
        } else {

            if (this.order.equals("desc")) {
                try {
                    return p2.getAppointments().get(0).getDateTime()
                            .compareTo(p1.getAppointments().get(0).getDateTime());
                } catch (SortedListException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    return p1.getAppointments().get(0).getDateTime()
                            .compareTo(p2.getAppointments().get(0).getDateTime());
                } catch (SortedListException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // if object is the same, short circuit this code
        if (other == this) {
            return true;
        }

        if (other instanceof SortByAppointment) {
            SortByAppointment s = (SortByAppointment) other;
            return this.order.equals(s.order);
        }

        // handles null and if object is not an instance of SortByName
        return false;
    }
}
