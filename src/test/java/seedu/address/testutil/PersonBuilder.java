package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.FilePath;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.NetWorth;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DESCRIPTION = "Has family health history of diabetes.";
    public static final String DEFAULT_NETWORTH = "$2000";
    public static final String DEFAULT_FILEPATH = "src/test/data/TestPDFs/Test_PDF.pdf";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Description description;
    private NetWorth netWorth;
    private Set<MeetingTime> meetingTimes;
    private FilePath filePath;
    private Set<Tag> tags;

    /**
     * Initializes the PersonBuilder with default data.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        description = new Description(DEFAULT_DESCRIPTION);
        netWorth = new NetWorth(DEFAULT_NETWORTH);
        meetingTimes = new HashSet<>();
        filePath = new FilePath(DEFAULT_FILEPATH);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        description = personToCopy.getDescription();
        netWorth = personToCopy.getNetWorth();
        meetingTimes = new HashSet<>(personToCopy.getMeetingTimes());
        filePath = personToCopy.getFilePath();
        tags = new HashSet<>(personToCopy.getTags());
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
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
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withDescription(String remark) {
        this.description = new Description(remark);
        return this;
    }

    /**
     * Sets the {@code netWorth} of the {@code Person} that we are building.
     */
    public PersonBuilder withNetWorth(String netWorth) {
        this.netWorth = new NetWorth(netWorth);
        return this;
    }

    /**
     * Sets the {@code meetingTime} of the {@code Person} that we are building.
     */
    public PersonBuilder withMeetingTimes(String ... meetingTimes) {
        this.meetingTimes = SampleDataUtil.getMeetingTimeSet(meetingTimes);
        return this;
    }

    /**
     * Sets the {@code filePath} of the {@code Person} that we are building.
     */
    public PersonBuilder withFilePath(String filePath) {
        this.filePath = new FilePath(filePath);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, description, netWorth, meetingTimes, filePath, tags);
    }

    public Person buildNoFilePath() {
        return new Person(name, phone, email, address, description, netWorth, meetingTimes, tags);
    }
}
