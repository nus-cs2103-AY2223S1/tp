package seedu.application.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.application.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company in the application list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompany(String)}
 */
public class Company implements Comparable<Company> {

    public static final String MESSAGE_CONSTRAINTS =
            "Company names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the company must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String company;

    /**
     * Constructs a {@code Company}.
     *
     * @param name A valid company name.
     */
    public Company(String name) {
        requireNonNull(name);
        checkArgument(isValidCompany(name), MESSAGE_CONSTRAINTS);
        company = name;
    }

    /**
     * Returns true if a given string is a valid company name.
     */
    public static boolean isValidCompany(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return company;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                && company.toLowerCase().equals(((Company) other).company.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return company.hashCode();
    }

    @Override
    public int compareTo(Company other) {
        return company.toLowerCase().compareTo(other.company.toLowerCase());
    }
}
