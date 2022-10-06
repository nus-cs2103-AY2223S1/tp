//package seedu.address.testutil;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.Phone;
//import seedu.address.model.role.Characteristics;
//import seedu.address.model.role.PriceRange;
//import seedu.address.model.tag.Tag;
//import seedu.address.model.util.SampleDataUtil;
//
///**
// * A utility class to help with building Buyer objects.
// */
//public class BuyerBuilder {
//
//    public static final PriceRange DEFAULT_PRICE_RANGE = new PriceRange(Optional.of(200000), Optional.of(250000));
//    public static final Characteristics DEFAULT_CHARACTERISTICS = new Characteristics(new String[1]{"Sunny"});
//
//    private PriceRange priceRange;
//    private Characteristics characteristics;
//
//    /**
//     * Creates a {@code PersonBuilder} with the default details.
//     */
//    public BuyerBuilder() {
//        priceRange = DEFAULT_PRICE_RANGE;
//
//    }
//
//    /**
//     * Initializes the PersonBuilder with the data of {@code personToCopy}.
//     */
//    public BuyerBuilder(Person personToCopy) {
//        name = personToCopy.getName();
//        phone = personToCopy.getPhone();
//        email = personToCopy.getEmail();
//        address = personToCopy.getAddress();
//        tags = new HashSet<>(personToCopy.getTags());
//    }
//
//    /**
//     * Sets the {@code Name} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withName(String name) {
//        this.name = new Name(name);
//        return this;
//    }
//
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
//     */
//    public PersonBuilder withTags(String ... tags) {
//        this.tags = SampleDataUtil.getTagSet(tags);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Address} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withAddress(String address) {
//        this.address = new Address(address);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withEmail(String email) {
//        this.email = new Email(email);
//        return this;
//    }
//
//    public Person build() {
//        return new Person(name, phone, email, address, tags);
//    }
//
//}
