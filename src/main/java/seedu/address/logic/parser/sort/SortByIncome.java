package seedu.address.logic.parser.sort;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class SortByIncome implements Comparator<Person> {

    private final String order;

    public SortByIncome(String order) {
        this.order = order;
    }

    @Override
    public int compare(Person p1, Person p2) {

        if (this.order.equals("desc")) {
            return p2.getIncome().value.compareTo(p1.getIncome().value);
        }

        return p1.getIncome().value.compareTo(p2.getIncome().value);
    }

    @Override
    public boolean equals(Object other) {
        // if object is the same, short circuit this code
        if (other == this) {
            return true;
        }

        if (other instanceof SortByIncome) {
            SortByIncome s = (SortByIncome) other;
            return this.order.equals(s.order);
        }

        // handles null and if object is not an instance of SortByName
        return false;
    }
}
