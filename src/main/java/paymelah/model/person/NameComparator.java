package paymelah.model.person;

import java.util.Comparator;

/**
 * Compares a {@code Person} to another using the alphabetical ordering of their {@code Name}.
 * Note: this comparator imposes orderings that are inconsistent with {@code equals} in {@code Person}.
 */
public class NameComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.compareNameWith(o2);
    }

}
