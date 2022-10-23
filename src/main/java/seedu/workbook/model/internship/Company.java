package seedu.workbook.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.workbook.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's company in WorkBook.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidCompany(String)}
 */
public class Company {

    // CHECKSTYLE.OFF: LineLength
    public static final String MESSAGE_CONSTRAINTS = "Company names should only contain alphanumeric characters and spaces, and it should not be blank";
    // CHECKSTYLE.ON: LineLength

    /*
     * The first character of the Company must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code Company}.
     *
     * @param name A valid Company name.
     */
    public Company(String name) {
        requireNonNull(name);
        checkArgument(isValidCompany(name), MESSAGE_CONSTRAINTS);
        String modifiedName = toUpperCase(name);
        this.name = modifiedName;
    }

    /**
     * Returns true if a given string is a valid Company Name.
     */
    public static boolean isValidCompany(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Company // instanceof handles nulls
                        && name.equals(((Company) other).name)); // state check
    }

    /**
     * Returns company name with first letter after every space capitalized.
     */
    public String toUpperCase(String companyName) {
        char[] chars = companyName.toLowerCase().toCharArray();
        boolean isNotWhitespace = false;
        for (int i = 0; i < chars.length; i++) {
            if (!isNotWhitespace && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                isNotWhitespace = true;

            } else if (Character.isWhitespace(chars[i])) {
                isNotWhitespace = false;
            }
        }
        return String.valueOf(chars);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
