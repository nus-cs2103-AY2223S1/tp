package seedu.boba.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.boba.model.customer.BirthdayMonth;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Name;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.tag.Tag;
import seedu.boba.model.util.SampleDataUtil;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_BIRTHDAY_MONTH = "1";
    public static final String DEFAULT_REWARD = "6900";

    private Name name;
    private Phone phone;
    private Email email;
    private BirthdayMonth birthdayMonth;
    private Reward reward;
    private Set<Tag> tags;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        birthdayMonth = new BirthdayMonth(DEFAULT_BIRTHDAY_MONTH);
        reward = new Reward(DEFAULT_REWARD);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        birthdayMonth = customerToCopy.getBirthdayMonth();
        reward = customerToCopy.getReward();
        tags = new HashSet<>(customerToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Reward} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withReward(String reward) {
        this.reward = new Reward(reward);
        return this;
    }

    /**
     * Sets the {@code BirthdayMonth} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = new BirthdayMonth(birthdayMonth);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Customer build() {
        return new Customer(name, phone, email, birthdayMonth, reward, tags);
    }

}
