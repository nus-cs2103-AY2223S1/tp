package longtimenosee.testutil;

import java.util.HashSet;
import java.util.Set;

import longtimenosee.model.person.Address;
import longtimenosee.model.person.Birthday;
import longtimenosee.model.person.Email;
import longtimenosee.model.person.Income;
import longtimenosee.model.person.Name;
import longtimenosee.model.person.Person;
import longtimenosee.model.person.Phone;
import longtimenosee.model.person.RiskAppetite;
import longtimenosee.model.tag.Tag;
import longtimenosee.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    public static final String DEFAULT_BIRTHDAY = "2000-05-05";
    public static final String DEFAULT_INCOME = "100.0";
    public static final String DEFAULT_RISK_APPETITE = "H";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    private Birthday birthday;
    private Income income;
    private RiskAppetite riskAppetite;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        address = new Address(DEFAULT_ADDRESS);
        riskAppetite = new RiskAppetite(DEFAULT_RISK_APPETITE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        birthday = personToCopy.getBirthday();
        income = personToCopy.getIncome();
        riskAppetite = personToCopy.getRiskAppetite();

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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */

    public PersonBuilder withIncome(String income) {
        this.income = new Income(income);
        return this;
    }

    /**
     * Sets the {@code RiskAppetite} of the {@code Person} that we are building.
     */

    public PersonBuilder withRiskAppetite(String ra) {
        this.riskAppetite = new RiskAppetite(ra);
        return this;
    }

    /**
     * Main builds a copy of the current client stored.
     * @return person
     */

    public Person build() {
        return new Person(name, phone, email, address, tags, birthday,
                income, riskAppetite);
    }
}
