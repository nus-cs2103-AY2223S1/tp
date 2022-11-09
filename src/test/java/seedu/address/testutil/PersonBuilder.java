package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.Address;
import seedu.address.model.person.Country;
import seedu.address.model.person.Email;
import seedu.address.model.person.GameType;
import seedu.address.model.person.ITimesAvailable;
import seedu.address.model.person.MinecraftName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Server;
import seedu.address.model.person.Social;
import seedu.address.model.person.Tag;
import seedu.address.model.person.TimeInterval;


/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MINECRAFTNAME = "AmyBee123";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_COUNTRY = "Singapore";

    private Name name;
    private MinecraftName minecraftName;
    private Phone phone;
    private Email email;
    private Address address;
    private Country country;
    private Set<Social> socials;
    private Set<Tag> tags;
    private Set<Server> servers;
    private Set<GameType> gameTypes;
    private Set<ITimesAvailable> timeIntervals;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        minecraftName = new MinecraftName(DEFAULT_MINECRAFTNAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        country = new Country(DEFAULT_COUNTRY);
        socials = new HashSet<>();
        tags = new HashSet<>();
        servers = new HashSet<>();
        gameTypes = new HashSet<>();
        timeIntervals = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        minecraftName = personToCopy.getMinecraftName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        socials = new HashSet<>(personToCopy.getSocials());
        tags = new HashSet<>(personToCopy.getTags());
        servers = new HashSet<>(personToCopy.getServers());
        country = personToCopy.getCountry();
        gameTypes = new HashSet<>(personToCopy.getGameType());
        timeIntervals = new HashSet<>(personToCopy.getTimesAvailable());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code MinecraftName} of the {@code Person} that we are building.
     */
    public PersonBuilder withMinecraftName(String name) {
        this.minecraftName = new MinecraftName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = new HashSet<>(Arrays.asList(tags).stream()
                .map(Tag::new)
                .collect(Collectors.toList()));
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
     * Sets the {@code country} of the {@code Person} that we are building.
     */
    public PersonBuilder withCountry(String country) {
        this.country = new Country(country);
        return this;
    }

    /**
     * Sets the {@code socials} of the {@code Person} that we are building.
     */
    public PersonBuilder withSocial(String ... socials) {
        this.socials = new HashSet<>(Arrays.asList(socials).stream()
                .map(Social::new)
                .collect(Collectors.toList()));
        return this;
    }

    /**
     * Sets the {@code servers} of the {@code Person} that we are building.
     */
    public PersonBuilder withServers(String ... servers) {
        this.servers = new HashSet<>(Arrays.asList(servers).stream()
                .map(Server::new)
                .collect(Collectors.toList()));
        return this;
    }

    /**
     * Sets the {@code gameTypes} of the {@code Person} that we are building.
     */
    public PersonBuilder withGameType(String ... gameType) {
        this.gameTypes = new HashSet<>(Arrays.asList(gameType).stream()
                .map(GameType::new)
                .collect(Collectors.toList()));
        return this;
    }

    /**
     * Sets the {@code gameTypes} of the {@code Person} that we are building.
     */
    public PersonBuilder withTimeIntervals(String ... timeIntervals) {
        this.timeIntervals = new HashSet<>(Arrays.asList(timeIntervals).stream()
                .map(TimeInterval::new)
                .collect(Collectors.toList()));
        return this;
    }

    /**
     * Builds a {@code Person} through the different fields
     */
    public Person build() {
        return new Person(name, minecraftName, phone, email, address, socials, tags, servers, country, gameTypes,
                timeIntervals);
    }

}
