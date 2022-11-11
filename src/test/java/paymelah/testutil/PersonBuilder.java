package paymelah.testutil;

import java.util.HashSet;
import java.util.Set;

import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;
import paymelah.model.person.Address;
import paymelah.model.person.Name;
import paymelah.model.person.Person;
import paymelah.model.person.Phone;
import paymelah.model.person.Telegram;
import paymelah.model.tag.Tag;
import paymelah.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_TELEGRAM = "amy_handle";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Telegram telegram;
    private Address address;
    private Set<Tag> tags;
    private DebtList debts;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        debts = new DebtList();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        telegram = personToCopy.getTelegram();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        debts = personToCopy.getDebts();
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
     * Sets the {@code Telegram} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code DebtList} of the {@code Person} that we are building.
     */
    public PersonBuilder withDebts(Debt... debts) {
        this.debts = SampleDataUtil.getDebtList(debts);
        return this;
    }

    /**
     * Sets the {@code DebtList} of the {@code Person} that we are building using a pre-existing DebtList.
     */
    public PersonBuilder withDebts(DebtList debts) {
        this.debts = SampleDataUtil.getDebtList(debts.asList().toArray(new Debt[0]));
        return this;
    }

    public Person build() {
        return new Person(name, phone, telegram, address, tags, debts);
    }

}
