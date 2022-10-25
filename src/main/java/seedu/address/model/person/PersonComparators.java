package seedu.address.model.person;

import java.util.Comparator;
import java.util.Optional;

/**
 * Comparators for fields in person model.
 */
public class PersonComparators {
    public static final Comparator<Person> NAME_COMPARATOR = (Comparator.comparing(Person::getName));

    public static final Comparator<Person> ADDRESS_COMPARATOR = ((o1, o2) -> {
        if (o1.getAddress().isEmpty() || o2.getAddress().isEmpty()) {
            return compareOptional(o1.getAddress(), o2.getAddress());
        }

        return o1.getAddress().get().compareTo(o2.getAddress().get());
    });

    public static final Comparator<Person> ROLE_COMPARATOR = ((o1, o2) -> {
        if (o1.getRole().isEmpty() || o2.getRole().isEmpty()) {
            return compareOptional(o1.getRole(), o2.getRole());
        }

        return o1.getRole().get().compareTo(o2.getRole().get());
    });

    /**
     * Reverses the comparator so we can sort in descending order.
     */
    public static Comparator<Person> reverseComparator(Comparator<Person> comparator) {
        return ((o1, o2) -> -comparator.compare(o1, o2));
    }


    /**
     * Compare between two Optional fields, {@code o1} and {@code o2}.
     * When both of them are present, returns 0 as it cannot be determined.
     * When one of them is present, returns 1 when {@code o1} is present else -1.
     */
    private static int compareOptional(Optional<?> o1, Optional<?> o2) {
        if (o1.isPresent() && o2.isPresent()) {
            return 0; // cannot be determined
        }

        if (o1.isEmpty() && o2.isEmpty()) {
            return 0;
        }

        return o1.isPresent() ? -1 : 1;
    }
}
