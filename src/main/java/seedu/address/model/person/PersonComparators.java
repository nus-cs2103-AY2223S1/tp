package seedu.address.model.person;

import java.util.Comparator;
import java.util.Optional;

public class PersonComparators {

    public final static Comparator<Person> NAME_COMPARATOR = (Comparator.comparing(Person::getName));

    public final static Comparator<Person> ADDRESS_COMPARATOR = ((o1, o2) -> {
        if (o1.getAddress().isEmpty() || o2.getAddress().isEmpty()) {
            return compareOptional(o1.getAddress(), o2.getAddress());
        }

        return o1.getAddress().get().compareTo(o2.getAddress().get());
    });

    public final static Comparator<Person> ROLE_COMPARATOR = ((o1, o2) -> {
        if (o1.getRole().isEmpty() || o2.getRole().isEmpty()) {
            return compareOptional(o1.getRole(), o2.getRole());
        }

        return o1.getRole().get().compareTo(o2.getRole().get());
    });

    public static int compareOptional(Optional<?> o1, Optional<?> o2) {
        if (o1.isPresent() && o2.isPresent()) {
            return 0; // cannot be determined
        }

        if (o1.isEmpty() && o2.isEmpty()) {
            return 0;
        }

        return o1.isPresent() ? -1 : 1;
    }
}
