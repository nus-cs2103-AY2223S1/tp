package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commons.ModuleCode;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.Grade;
import seedu.address.model.student.Name;
import seedu.address.model.student.Participation;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.TelegramHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_ID = "A0000000Z";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "amy_bee";
    public static final String DEFAULT_MODULE = "CS2103T";
    public static final String DEFAULT_TUTORIAL = "W17";
    public static final String DEFAULT_ATTENDANCE = "0";
    public static final String DEFAULT_PARTICIPATION = "0";
    public static final String DEFAULT_GRADE = "PENDING...";

    //public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private StudentId id;
    private Phone phone;
    private Email email;
    private TelegramHandle telegramHandle;
    private ModuleCode moduleCode;
    private TutorialName tutorialName;
    private Attendance attendance;
    private Participation participation;
    private Grade grade;

    private Set<Tag> tags;

    //private Address address;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new StudentId(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM);
        moduleCode = new ModuleCode(DEFAULT_MODULE);
        tutorialName = new TutorialName(DEFAULT_TUTORIAL);
        attendance = new Attendance(DEFAULT_ATTENDANCE);
        participation = new Participation(DEFAULT_PARTICIPATION);
        grade = new Grade(DEFAULT_GRADE);
        tags = new HashSet<>();

        //address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        id = studentToCopy.getId();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        telegramHandle = studentToCopy.getTelegram();
        moduleCode = studentToCopy.getModuleCode();
        tutorialName = studentToCopy.getTutorialName();
        attendance = studentToCopy.getAttendance();
        participation = studentToCopy.getParticipation();
        grade = studentToCopy.getGrade();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Creates a new Student object.
     */
    public Student build() {
        return new Student(name, id, phone, email,
                telegramHandle, moduleCode,
                tutorialName, attendance,
                participation, grade, tags);
    }

    /**
     * Sets the {@code ID} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = new StudentId(id);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegramHandle = new TelegramHandle(telegram);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Person} that we are building.
     */
    public PersonBuilder withTutorialModule(String tutorialModule) {
        this.moduleCode = new ModuleCode(tutorialModule);
        return this;
    }

    /**
     * Sets the {@code TutorialName} of the {@code Person} that we are building.
     */
    public PersonBuilder withTutorialName(String tutorialName) {
        this.tutorialName = new TutorialName(tutorialName);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code Person} that we are building.
     */
    public PersonBuilder withParticipation(String participation) {
        this.participation = new Participation(participation);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Person} that we are building.
     */
    public PersonBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }
}
