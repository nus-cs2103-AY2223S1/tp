package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents an Internship's company name in InterNUS.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Company implements Comparable<Company> {
    public static final String MESSAGE_CONSTRAINTS =
            "Company names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * However, the parser would have ignored it anyway.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param name A valid company name.
     */
    public Company(String name) {
        if (name != null) {
            checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
            fullName = name;
        } else {
            fullName = null;
        }
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (fullName == null) {
            return "No Company";
        }
        return fullName;
    }

    // solution adapted from
    // https://stackoverflow.com/a/36716166
    @Override
    public boolean equals(Object other) {
        return Objects.equals(fullName, ((Company) other).fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    // solution adapted from
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html
    // #compareToIgnoreCase(java.lang.String)
    @Override
    public int compareTo(Company other) {
        return -other.toString().compareToIgnoreCase(this.toString());
    }
}

