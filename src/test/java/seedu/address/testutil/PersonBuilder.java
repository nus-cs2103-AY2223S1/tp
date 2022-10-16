package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.*;
import seedu.address.model.server.Server;
import seedu.address.model.tag.Tag;


/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MINECRAFTNAME = "AmyBee123";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TIMEZONE = "+08:30";

    private Name name;
    private MinecraftName minecraftName;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Social> socials;
    private Set<Tag> tags;
    private Set<Server> servers;
    private TimeZone timeZone;
    private Set<GameType> gameTypes;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        minecraftName = new MinecraftName(DEFAULT_MINECRAFTNAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        socials = new HashSet<>();
        tags = new HashSet<>();
        servers = new HashSet<>();
        timeZone = new TimeZone(DEFAULT_TIMEZONE);
        gameTypes = new HashSet<>();
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
        timeZone = personToCopy.getTimeZone();
        gameTypes = new HashSet<>(personToCopy.getGameType());
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
     * Sets the {@code timeZone} of the {@code Person} that we are building.
     */
    public PersonBuilder withTimeZone(String timeZone) {
        this.timeZone = new TimeZone(timeZone);
        return this;
    }

    public PersonBuilder withGameType(String ... gameType) {
        this.gameTypes = new HashSet<>(Arrays.asList(gameType).stream()
                .map(GameType::new)
                .collect(Collectors.toList()));
        return this;
    }

    public Person build() {
        return new Person(name, minecraftName, phone, email, address, socials, tags, servers, timeZone, gameTypes);
    }

}
