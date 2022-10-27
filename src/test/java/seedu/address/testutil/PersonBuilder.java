package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingLocation;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Income;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;
import seedu.address.model.portfolio.Risk;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_INCOME = "$5230";
    public static final String DEFAULT_MEETINGDATE = "20 Nov 2022";
    public static final String DEFAULT_MEETINGLOCATION = "13 Computing Drive";
    public static final String DEFAULT_RISK = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Income income;
    private MeetingDate meetingDate;
    private MeetingLocation meetingLocation;
    private Set<Tag> tags;
    private Risk risk;
    private Set<Plan> plans;
    private Set<Note> note;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        income = new Income(DEFAULT_INCOME);
        meetingDate = new MeetingDate(DEFAULT_MEETINGDATE);
        meetingLocation = new MeetingLocation(DEFAULT_MEETINGLOCATION);
        tags = new HashSet<>();
        risk = new Risk(DEFAULT_RISK);
        plans = new HashSet<>();
        note = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        Portfolio portfolio = personToCopy.getPortfolio();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        income = personToCopy.getIncome();
        meetingDate = personToCopy.getMeeting().getMeetingDate();
        meetingLocation = personToCopy.getMeeting().getMeetingLocation();
        tags = new HashSet<>(personToCopy.getTags());
        risk = portfolio.getRisk();
        plans = new HashSet<>(portfolio.getPlans());
        note = portfolio.getNotes();
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
    public PersonBuilder withTags(String... tags) {
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
     * Sets the {@code Income} of the {@code Person} that we are building.
     */
    public PersonBuilder withIncome(String income) {
        this.income = new Income(income);
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
     * Sets the {@code MeetingDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withMeetingDate(String meetingDate) {
        this.meetingDate = new MeetingDate(meetingDate);
        return this;
    }

    /**
     * Sets the {@code MeetingLocation} of the {@code Person} that we are building.
     */
    public PersonBuilder withMeetingLocation(String meetingLocation) {
        this.meetingLocation = new MeetingLocation(meetingLocation);
        return this;
    }

    /**
     * Sets the {@code Risk} of the {@code Person} that we are building.
     */
    public PersonBuilder withRisk(String risk) {
        this.risk = new Risk(risk);
        return this;
    }

    /**
     * Parses the {@code plans} into a {@code Set<Plan>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withPlans(String... plans) {
        this.plans = SampleDataUtil.getPlanSet(plans);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNotes(String... notes) {
        this.note = SampleDataUtil.getNoteSet(notes);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, income, meetingDate, meetingLocation, tags, risk, plans, note);
    }

}
