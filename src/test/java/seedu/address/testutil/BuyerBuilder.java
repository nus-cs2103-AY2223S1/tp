package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;

/**
 * A utility class to help with building Buyer objects.
 */
public class BuyerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PRIORITY = "normal";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private PriceRange priceRange;
    private Characteristics characteristics;
    private Priority priority;
    private LocalDateTime entryTime;

    /**
     * Creates a {@code BuyerBuilder} with the default details.
     */
    public BuyerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        priceRange = null;
        characteristics = null;
        priority = new Priority(DEFAULT_PRIORITY);
        entryTime = LocalDateTime.now();
    }

    /**
     * Initializes the BuyerBuilder with the data of {@code buyerToCopy}.
     */
    public BuyerBuilder(Buyer buyerToCopy) {
        name = buyerToCopy.getName();
        phone = buyerToCopy.getPhone();
        email = buyerToCopy.getEmail();
        address = buyerToCopy.getAddress();
        priceRange = buyerToCopy.getPriceRange().orElse(null);
        characteristics = buyerToCopy.getDesiredCharacteristics().orElse(null);
        priority = buyerToCopy.getPriority();
        entryTime = buyerToCopy.getEntryTime();
    }

    /**
     * Sets the {@code Name} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Price Range} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withPriceRange(String priceRange) {
        this.priceRange = new PriceRange(priceRange);
        return this;
    }

    /**
     * Removes the {@code Price Range} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withNoPriceRange() {
        this.priceRange = null;
        return this;
    }

    /**
     * Sets the {@code DesiredCharacteristics} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withDesiredCharacteristics(String desiredCharacteristics) {
        this.characteristics = new Characteristics(desiredCharacteristics);
        return this;
    }

    /**
     * Removes the {@code DesiredCharacteristics} of the {@code Buyer} that we are building.
     */
    public BuyerBuilder withNoDesiredCharacteristics() {
        this.characteristics = null;
        return this;
    }

    public Buyer build() {
        return new Buyer(name, phone, email, address, priceRange, characteristics, priority, entryTime);
    }

}
