package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.MoneyPaid;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Integer DEFAULT_MONEY_OWED = 0;
    public static final Integer DEFAULT_MONEY_PAID = 0;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private MoneyOwed moneyOwed;
    private MoneyPaid moneyPaid;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        moneyOwed = new MoneyOwed(DEFAULT_MONEY_OWED);
        moneyPaid = new MoneyPaid(DEFAULT_MONEY_PAID);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        moneyOwed = personToCopy.getMoneyOwed();
        moneyPaid = personToCopy.getMoneyPaid();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
     * Sets the {@code MoneyOwed} of the {@code Person} that we are building.
     */
    public PersonBuilder withMoneyOwed(Integer moneyOwed) {
        this.moneyOwed = new MoneyOwed(moneyOwed);
        return this;
    }

    /**
     * Sets the {@code MoneyPaid} of the {@code Person} that we are building.
     */
    public PersonBuilder withMoneyPaid(Integer moneyPaid) {
        this.moneyPaid = new MoneyPaid(moneyPaid);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, moneyOwed, moneyPaid);
    }

}
