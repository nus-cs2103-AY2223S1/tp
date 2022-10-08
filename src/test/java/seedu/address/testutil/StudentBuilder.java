package seedu.address.testutil;

import seedu.address.model.person.student.Level;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder extends PersonBuilder {

    public static final String DEFAULT_SCHOOL = "Bukit Panjang Primary School";
    public static final String DEFAULT_LEVEL = "PRIMARY1";
    public static final String DEFAULT_NEXTOFKIN = "John Tay";

    private School school;
    private Level level;
    private NextOfKin nextOfKin;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
        school = new School(DEFAULT_SCHOOL);
        level = Level.createLevel(DEFAULT_LEVEL);
        nextOfKin = new NextOfKin(DEFAULT_NEXTOFKIN);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        super(studentToCopy);
        school = studentToCopy.getSchool();
        level = studentToCopy.getLevel();
        nextOfKin = studentToCopy.getNextOfKin();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    @Override
    public StudentBuilder withName(String name) {
        return (StudentBuilder) super.withName(name);
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    @Override
    public StudentBuilder withTags(String ... tags) {
        return (StudentBuilder) super.withTags(tags);
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    @Override
    public StudentBuilder withAddress(String address) {
        return (StudentBuilder) super.withAddress(address);

    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    @Override
    public StudentBuilder withPhone(String phone) {
        return (StudentBuilder) super.withPhone(phone);
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    @Override
    public StudentBuilder withEmail(String email) {
        return (StudentBuilder) super.withEmail(email);
    }

    /**
     * Sets the {@code School} of the {@code Student} that we are building.
     */
    public StudentBuilder withSchool(String school) {
        this.school = new School(school);
        return this;
    }

    /**
     * Sets the {@code Level} of the {@code Student} that we are building.
     */
    public StudentBuilder withLevel(String level) {
        this.level = Level.createLevel(level);
        return this;
    }

    /**
     * Sets the {@code NextOfKin} of the {@code Student} that we are building.
     */
    public StudentBuilder withNextOfKin(String nextOfKin) {
        this.nextOfKin = new NextOfKin(nextOfKin);
        return this;
    }

    @Override
    public Student build() {
        return new Student(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getTags(), school, level, nextOfKin);
    }
}
