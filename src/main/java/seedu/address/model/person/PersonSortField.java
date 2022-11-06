package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a field to sort Persons by.
 * Guarantees: immutable; is valid as declared in {@link #isValidSortField(String)}
 */
public class PersonSortField {

    public static final String MESSAGE_CONSTRAINTS = "Person sort field must either be n, N, d, D, g or G.";

    // Accept exactly one upper or lower case n, d or g
    public static final String VALIDATION_REGEX = "[nNdDgG]{1}";


    // Comparator logic
    private static final Comparator<Person> SORT_BY_NAME = (p1, p2) -> p1.getName().compareTo(p2.getName());
    private static final Comparator<Person> SORT_BY_DATE_OF_BIRTH = (p1, p2) -> p1.getDob().compareTo(p2.getDob());
    private static final Comparator<Person> SORT_BY_GENDER = (p1, p2) -> p1.getGender().compareTo(p2.getGender());
    private static final Comparator<Person> SORT_BY_NO_FIELD = (p1, p2) -> 0;


    private final PersonSortFieldType field;
    private final Comparator<Person> comp;


    private PersonSortField(PersonSortFieldType type, Comparator<Person> c) {
        requireAllNonNull(type, c);
        field = type;
        comp = c;
    }

    public PersonSortFieldType getField() {
        return field;
    }

    public Comparator<Person> getComparator() {
        return comp;
    }


    /**
     * Constructs a {@code PersonSortField}.
     *
     * @param sortFieldLetter A valid sort field letter.
     * @return {@code PersonSortField} representing the sort field letter.
     */
    public static PersonSortField createSortField(String sortFieldLetter) {
        requireNonNull(sortFieldLetter);
        checkArgument(isValidSortField(sortFieldLetter), MESSAGE_CONSTRAINTS);
        return mapLetterToSortField(sortFieldLetter);
    }


    /**
     * Constructs a {@code PersonSortField} that doesn't sort by any field.
     *
     * @return {@code PersonSortField} that doesn't sort by any field.
     */
    public static PersonSortField sortByNoField() {
        return new PersonSortField(PersonSortFieldType.NO_FIELD, SORT_BY_NO_FIELD);
    }


    /**
     * Returns true if the given {@code String} is a valid sort field.
     *
     * @param test {@code String} to test.
     * @return boolean result of test.
     */
    public static boolean isValidSortField(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Maps the given sort field letter to its corresponding {@code PersonSortFieldType}.
     *
     * @param sortFieldLetter letter to map to {@code PersonSortFieldType}.
     * @return {@code PersonSortField} representing the sort field letter.
     */
    private static PersonSortField mapLetterToSortField(String sortFieldLetter) {

        switch(sortFieldLetter) {

        case "n":
        case "N":
            return new PersonSortField(PersonSortFieldType.NAME, SORT_BY_NAME);

        case "d":
        case "D":
            return new PersonSortField(PersonSortFieldType.DATE_OF_BIRTH, SORT_BY_DATE_OF_BIRTH);

        case "g":
        case "G":
            return new PersonSortField(PersonSortFieldType.GENDER, SORT_BY_GENDER);

        // Don't sort by any field by default
        default:
            return sortByNoField();
        }
    }

    @Override
    public String toString() {
        return String.format("Person Sort Field: %s\nComparator: %s", field.toString(), comp.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonSortField)) {
            return false;
        }

        // state check
        PersonSortField s = (PersonSortField) other;

        return this.getField().equals(s.getField())
            && this.getComparator().equals(s.getComparator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, comp);
    }
}
