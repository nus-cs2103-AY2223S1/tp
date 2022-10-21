package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents an Internship's company name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Company {
    public static final String MESSAGE_CONSTRAINTS =
            "Company names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
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

    @Override
    public boolean equals(Object other) {
        return Objects.equals(fullName, ((Company) other).fullName);
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}

