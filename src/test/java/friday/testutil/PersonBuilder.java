package friday.testutil;

import java.util.HashSet;
import java.util.Set;

import friday.model.student.*;
import friday.model.student.Student;
import friday.model.tag.Tag;
import friday.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Name name;
    private TelegramHandle telegramHandle;
    private Consultation consultation;
    private MasteryCheck masteryCheck;
    private Remark remark;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        telegramHandle = new TelegramHandle(DEFAULT_PHONE);
        consultation = new Consultation(DEFAULT_EMAIL);
        masteryCheck = new MasteryCheck(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        telegramHandle = studentToCopy.getTelegramHandle();
        consultation = studentToCopy.getConsultation();
        masteryCheck = studentToCopy.getMasteryCheck();
        remark = studentToCopy.getRemark();
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
     * Sets the {@code MasteryCheck} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.masteryCheck = new MasteryCheck(address);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.telegramHandle = new TelegramHandle(phone);
        return this;
    }

    /**
     * Sets the {@code Consultation} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.consultation = new Consultation(email);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Student build() {
        return new Student(name, telegramHandle, consultation, masteryCheck, remark, tags);
    }

}
