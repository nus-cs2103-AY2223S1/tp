package seedu.address.logic.parser.sort;

import seedu.address.model.person.Person;

import  java.util.Comparator;
public class SortByName implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().fullName.compareTo(p2.getName().fullName);
    }
}
