package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;



/**
 * Represents a class in the school. Renamed to Clazz as the word "class" is a reserve word
 */
public class StudentClass {

    public static final String MESSAGE_CONSTRAINTS =
            "A student's Class should only contain numbers, letters and the decimal point, and it should not be blank";

    public static final String VALIDATION_REGEX = "[a-zA-Z\\d.]+";

    // For consistency
    public final String value;

    private ArrayList<Person> personsInClass;

    /**
     * Constructs a {@code StudentClass}.
     *
     * @param studentClass A valid StudentClass object.
     */
    public StudentClass(String studentClass) {
        requireNonNull(studentClass);
        personsInClass = new ArrayList<>();
        value = studentClass;
    }

    public void add(Person student) {
        personsInClass.add(student);
    }

    @Override
    public String toString() {
        return value;
    }

    public static boolean isValidStudentClass(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both classes have the same name.
     * @param other The other class to compare with.
     * @return True if both classes have the same name.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentClass // instanceof handles nulls
                    && value.equals(((StudentClass) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
