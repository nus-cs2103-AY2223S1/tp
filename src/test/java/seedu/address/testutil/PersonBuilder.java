package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.ApplicationProcess;
import seedu.address.model.person.Date;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Position;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Website;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_REMARK = "winter internship '22";
    public static final String DEFAULT_POSITION = "Software Intern";
    public static final String DEFAULT_APPLICATION_PROCESS = "interview";
    public static final String DEFAULT_DATE = "01-12-2022";
    public static final String DEFAULT_WEBSITE = "https://defaultweb.com/careers";


    private Name name;
    private Phone phone;
    private Email email;
    private Remark remark;
    private Set<Tag> tags;
    private Position position;
    private ApplicationProcess applicationProcess;
    private Date date;
    private Website website;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        position = new Position(DEFAULT_POSITION);
        applicationProcess = new ApplicationProcess(DEFAULT_APPLICATION_PROCESS);
        date = new Date(DEFAULT_DATE);
        website = new Website(DEFAULT_WEBSITE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
        position = personToCopy.getPosition();
        applicationProcess = personToCopy.getApplicationProcess();
        date = personToCopy.getDate();
        website = personToCopy.getWebsite();
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
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
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
     * Sets the {@code Position} of the {@code Person} that we are building.
     */
    public PersonBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    /**
     * Sets the {@code ApplicationProcess} of the {@code Person} that we are building.
     */
    public PersonBuilder withApplicationProcess(String applicationProcess) {
        this.applicationProcess = new ApplicationProcess(applicationProcess);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Person} that we are building.
     */
    public PersonBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code Person} that we are building.
     */
    public PersonBuilder withWebsite(String website) {
        this.website = new Website(website);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, remark, position, applicationProcess, date, website, tags);
    }

}
