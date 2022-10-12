package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;



/**
 * Represents a class in the school. Renamed to Clazz as the word "class" is a reserve word
 */
public class Clazz {

    public static final String MESSAGE_CONSTRAINTS =
            "Class should only contain numbers and decimal, and it should not be blank";

    public final String className;

    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    private ArrayList<Person> personsInClass;

    /**
     * Constructs a {@code Clazz}.
     *
     * @param clazz A valid clazz.
     */
    public Clazz(String clazz) {
        requireNonNull(clazz);
        personsInClass = new ArrayList<>();
        className = clazz;
    }

    public void add(Person student) {
        personsInClass.add(student);
    }

    @Override
    public String toString() {
        return "Class: " + className;
    }

    public static boolean isValidClazz(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Clazz // instanceof handles nulls
                && className.equals(((Clazz) other).className)); // state check
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

}
