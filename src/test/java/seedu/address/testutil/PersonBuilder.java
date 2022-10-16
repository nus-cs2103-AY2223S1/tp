package seedu.address.testutil;

import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.util.MaximumSortedList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Email;
import seedu.address.model.person.IncomeLevel;
import seedu.address.model.person.Monthly;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.RiskTag;
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
    public static final String DEFAULT_RISKTAG = "LOW";
    public static final String DEFAULT_INCOME = "$1000";
    public static final String DEFAULT_MONTHLY = "$200";
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private RiskTag riskTag;
    private IncomeLevel income;
    private Monthly monthly;
    private Set<Tag> tags;
    private MaximumSortedList<Appointment> appointments;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        riskTag = new RiskTag(DEFAULT_RISKTAG);
        income = new IncomeLevel(DEFAULT_INCOME);
        monthly = new Monthly(DEFAULT_MONTHLY);
        tags = new HashSet<>();
        appointments = new MaximumSortedList<>(MAXIMUM_NUM_OF_APPOINTMENTS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        income = personToCopy.getIncome();
        monthly = personToCopy.getMonthly();
        riskTag = personToCopy.getRiskTag();
        income = personToCopy.getIncome();
        tags = new HashSet<>(personToCopy.getTags());
        appointments = new MaximumSortedList<>(personToCopy.getAppointments());
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
     * Sets the {@code RiskTag} of the {@code Person} that we are building.
     */
    public PersonBuilder withRiskTag(String riskTag) {
        this.riskTag = new RiskTag(riskTag);
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code Person} that we are building.
     */
    public PersonBuilder withIncome(String income) {
        this.income = new IncomeLevel(income);
        return this;
    }

    /**
     * Sets the {@code Monthly} of the {@code Person} that we are building.
     */
    public PersonBuilder withMonthly(String monthly) {
        this.monthly = new Monthly(monthly);
        return this;
    }
    /**
     * Sets the {@code Appointment} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointment(Appointment appointmentToBeAdded) {
        this.appointments.add(appointmentToBeAdded);
        return this;
    }

    /**
     * Returns a Person with the respective arguments as fields
     * By default, the set of appointments field is created but is empty
     */
    public Person build() {
        return new Person(name, phone, email, address, income, monthly, risktag, tags, appointments);
    }

}
