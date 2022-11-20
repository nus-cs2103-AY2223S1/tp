package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_ID = "A0123123X";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@amyb123";

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private StudentId id;
    private TelegramHandle telegramHandle;
    private Set<ModuleCode> studentInfo;
    private Set<ModuleCode> teachingInfo;
    private Set<String> classGroups;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        id = new StudentId(DEFAULT_ID);
        tags = new HashSet<>();
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        studentInfo = new HashSet<>();
        teachingInfo = new HashSet<>();
        classGroups = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        tags = studentToCopy.getTags();
        id = studentToCopy.getId();
        telegramHandle = studentToCopy.getTelegramHandle();
        studentInfo = studentToCopy.getStudentModuleInfo();
        teachingInfo = studentToCopy.getTeachingAssistantInfo();
        classGroups = studentToCopy.getClassGroups();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = personToCopy.getTags();
        if (personToCopy instanceof Student) {
            Student temp = (Student) personToCopy;
            id = temp.getId();
            telegramHandle = temp.getTelegramHandle();
            studentInfo = temp.getStudentModuleInfo();
            teachingInfo = temp.getTeachingAssistantInfo();
            classGroups = temp.getClassGroups();
        } else {
            id = new StudentId(DEFAULT_ID);
            telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
            studentInfo = new HashSet<>();
            teachingInfo = new HashSet<>();
            classGroups = new HashSet<>();
        }
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
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
     * Sets the {@code studentInfo} of the {@code Student} that we are building.
     */
    public StudentBuilder withTeachingInfo(String ... moduleCodes) {
        this.teachingInfo = SampleDataUtil.getModuleCodeSet(moduleCodes);
        return this;
    }

    /**
     * Sets the {@code classGroup} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassGroup(String ... classGroup) {
        this.classGroups = SampleDataUtil.getClassGroups(classGroup);
        return this;
    }


    /**
     * Builds a {@code Student}.
     *
     * @return the student built for testing
     */
    public Student build() {
        return new Student(name, phone, email, address, tags, id, telegramHandle, studentInfo, teachingInfo,
                classGroups);
    }

}
