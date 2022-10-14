package seedu.address.testutil;

import seedu.address.model.person.Student;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder extends PersonBuilder {


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        super(personToCopy);
    }

    @Override
    public Student build() {
        return new Student(getName(), getPhone(), getEmail(), getGender(), getTags(), getLocation(),
                getGithubUsername());
    }

}
