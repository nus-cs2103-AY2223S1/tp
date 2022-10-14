package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;



/**
 * Represents a class in the school. Renamed to Clazz as the word "class" is a reserve word
 */
public class StudentClass {

    public static final String MESSAGE_CONSTRAINTS =
            "Class should only contain numbers and decimal, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[a-zA-Z]*$";

    public final String className;

    private ArrayList<Person> personsInClass;

    /**
     * Constructs a {@code Clazz}.
     *
     * @param clazz A valid clazz.
     */
    public StudentClass(String clazz) {
        requireNonNull(clazz);
        personsInClass = new ArrayList<>();
        className = clazz;
    }

    public void add(Person student) {
        personsInClass.add(student);
    }

    @Override
    public String toString() {
        return className;
    }

    public static boolean isValidClazz(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentClass // instanceof handles nulls
                && className.equals(((StudentClass) other).className)); // state check
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

}
