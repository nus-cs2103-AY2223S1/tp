package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder extends PersonBuilder {

    public static final String DEFAULT_ID = "A0123123X";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@amyb123";

    private StudentId id;
    private TelegramHandle telegramHandle;
    private Set<ModuleCode> studentInfo;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        super();
        id = new StudentId(DEFAULT_ID);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        studentInfo = new HashSet<>();
    }

    /**
     * Creates a {@code StudentBuilder} from the person's details.
     * @param person The person to copy.
     */
    public StudentBuilder(Person person) {
        super(person);
        id = new StudentId(DEFAULT_ID);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        studentInfo = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        id = studentToCopy.getId();
        telegramHandle = studentToCopy.getTelegramHandle();
        studentInfo = studentToCopy.getStudentModuleInfo();
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
    public StudentBuilder withStudentInfo(String ... moduleCodes) {
        this.studentInfo = SampleDataUtil.getModuleCodeSet(moduleCodes);
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
