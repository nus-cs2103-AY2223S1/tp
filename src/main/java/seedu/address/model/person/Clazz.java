package seedu.address.model.person;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Clazz {

    public static final String MESSAGE_CONSTRAINTS =
            "Class should only contain numbers and decimal, and it should not be blank";

    public final String className;

    public ArrayList<Person> PersonsInClass;

    /**
     * Constructs a {@code Clazz}.
     *
     * @param clazz A valid clazz.
     */
    public Clazz(String clazz) {
        requireNonNull(clazz);
        PersonsInClass = new ArrayList<>();
        className = clazz;
    }

    public void add(Person student) {
        PersonsInClass.add(student);
    }

    @Override
    public String toString() {
        return "Class: " + className;
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
