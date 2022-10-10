package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder extends PersonBuilder {

    public static final String DEFAULT_ID = "A0123123X";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@amyb123";
    public static final String DEFAULT_STUDENT_INFO = "abc";

    private StudentId id;
    private TelegramHandle telegramHandle;
    private String studentInfo;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
        id = new StudentId(DEFAULT_ID);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        studentInfo = DEFAULT_STUDENT_INFO;
    }

    /**
     * Creates a {@code StudentBuilder} from the person's details.
     * @param person The person to copy.
     */
    public StudentBuilder(Person person) {
        super(person);
        id = new StudentId(DEFAULT_ID);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        studentInfo = DEFAULT_STUDENT_INFO;
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        id = studentToCopy.getId();
        telegramHandle = studentToCopy.getTelegramHandle();
        studentInfo = studentToCopy.getStudentInfo();
    }



    /**
     * Sets the {@code id} of the {@code Student} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new StudentId(id);
        return this;
    }

    /**
     * Sets the {@code telegramHandle} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code studentInfo} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentInfo(String studentInfo) {
        this.studentInfo = studentInfo;
        return this;
    }

    /**
     * Builds a {@code Student}.
     *
     * @return the student built for testing
     */
    public Student build() {
        Person person = super.build();
        return new Student(person.getName(), person.getPhone(), person.getEmail(),
                person.getAddress(), person.getTags(), id, telegramHandle, studentInfo);
    }

}
