package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.CriticalIllnessInsurance;
import seedu.address.model.person.DisabilityInsurance;
import seedu.address.model.person.Email;
import seedu.address.model.person.HealthInsurance;
import seedu.address.model.person.Insurance;
import seedu.address.model.person.LifeInsurance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reminder;
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
    public static final String DEFAULT_BIRTHDAY = "1 January 2000";
    public static final boolean DEFAULT_HEALTH_INSURANCE = false;
    public static final boolean DEFAULT_DISABILITY_INSURANCE = false;
    public static final boolean DEFAULT_CRITICAL_ILLNESS_INSURANCE = false;
    public static final boolean DEFAULT_LIFE_INSURANCE = false;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Birthday birthday;
    private Insurance healthInsurance;
    private Insurance disabilityInsurance;
    private Insurance criticalIllnessInsurance;
    private Insurance lifeInsurance;
    private Set<Tag> tags;
    private Set<Reminder> reminders;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        healthInsurance = new HealthInsurance(DEFAULT_HEALTH_INSURANCE);
        disabilityInsurance = new DisabilityInsurance(DEFAULT_DISABILITY_INSURANCE);
        criticalIllnessInsurance = new CriticalIllnessInsurance(DEFAULT_CRITICAL_ILLNESS_INSURANCE);
        lifeInsurance = new LifeInsurance(DEFAULT_LIFE_INSURANCE);
        tags = new HashSet<>();
        reminders = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        birthday = personToCopy.getBirthday();
        healthInsurance = personToCopy.getHealthInsurance();
        disabilityInsurance = personToCopy.getDisabilityInsurance();
        criticalIllnessInsurance = personToCopy.getCriticalIllnessInsurance();
        lifeInsurance = personToCopy.getLifeInsurance();
        tags = new HashSet<>(personToCopy.getTags());
        reminders = new HashSet<>(personToCopy.getReminders());
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
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code HealthInsurance} of the {@code Person} that we are building.
     */
    public PersonBuilder withHealthInsurance(boolean healthInsurance) {
        this.healthInsurance = new HealthInsurance(healthInsurance);
        return this;
    }

    /**
     * Sets the {@code DisabilityInsurance} of the {@code Person} that we are building.
     */
    public PersonBuilder withDisabilityInsurance(boolean disabilityInsurance) {
        this.disabilityInsurance = new DisabilityInsurance(disabilityInsurance);
        return this;
    }

    /**
     * Sets the {@code CriticalIllnessInsurance} of the {@code Person} that we are building.
     */
    public PersonBuilder withCriticalIllnessInsurance(boolean criticalIllnessInsurance) {
        this.criticalIllnessInsurance = new CriticalIllnessInsurance(criticalIllnessInsurance);
        return this;
    }

    /**
     * Sets the {@code LifeInsurance} of the {@code Person} that we are building.
     */
    public PersonBuilder withLifeInsurance(boolean lifeInsurance) {
        this.lifeInsurance = new LifeInsurance(lifeInsurance);
        return this;
    }

    /**
     * Sets the {@code Reminder} of the {@code Person} that we are building.
     */
    @SafeVarargs
    public final PersonBuilder withReminders(Pair<String, String>... reminders) {
        this.reminders = SampleDataUtil.getReminderSet(reminders);
        return this;
    }
    /**
     * Builds and returns the Person
     *
     * @return Person
     */
    public Person build() {
        return new Person(name, phone, email, address, birthday,
                healthInsurance, disabilityInsurance, criticalIllnessInsurance, lifeInsurance, reminders, tags);
    }
}
