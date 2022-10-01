package seedu.address.model.policy;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Policy {

    // Identity fields
    private final Title title;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Company company;

    // Data fields
    private final Commission commisionEarned;
    private final Set<Coverage> coverages = new HashSet<>();

}
