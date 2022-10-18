package longtimenosee.model.policy;

import static longtimenosee.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Represents a Policy in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Policy {

    // Identity fields
    private final Title title;
    private final Company company;

    // Data fields
    private final Commission commission;
    private final Set<Coverage> coverages = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Policy(Title title, Company company, Commission commission, Set<Coverage> coverages) {
        requireAllNonNull(title, company, commission, coverages);
        this.commission = commission;
        this.company = company;
        this.title = title;
        this.coverages.addAll(coverages);
    }

    public Title getTitle() {
        return title;
    }

    public Company getCompany() {
        return company;
    }

    public Commission getCommission() {
        return commission;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Coverage> getCoverages() {
        return Collections.unmodifiableSet(coverages);
    }

    /**
     * Returns true if both policies have the same Title and company.
     * This defines a relatively strong notion of equality between two policies.
     */
    public boolean isSamePolicy(Policy otherPolicy) {
        if (otherPolicy == this) {
            return true;
        }

        return otherPolicy != null
                && otherPolicy.getCompany().equals(getCompany())
                && otherPolicy.getTitle().equals(getCompany());
    }

    /**
     * Returns true if both policies have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        return otherPolicy.getCompany().equals(getCompany())
                && otherPolicy.getTitle().equals(getTitle())
                && otherPolicy.getCommission().equals(getCommission())
                && otherPolicy.getCoverages().equals(getCoverages());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, company, commission, coverages);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append("; Company: ")
                .append(getCompany())
                .append("; Commission: ")
                .append(getCommission());

        Set<Coverage> coverages = getCoverages();
        if (!coverages.isEmpty()) {
            builder.append("; Coverages: ");
            coverages.forEach(builder::append);
        }
        return builder.toString();
    }
}
