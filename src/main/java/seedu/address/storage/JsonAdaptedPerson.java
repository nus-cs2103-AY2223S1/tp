package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.server.Server;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String minecraftName;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedSocial> socials = new ArrayList<>();
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedMinecraftServer> servers = new ArrayList<>();
    private final String timeZone;
    private final List<JsonAdaptedGameType> gameTypes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("minecraftName") String minecraftName,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("socials") List<JsonAdaptedSocial> socials,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("servers") List<JsonAdaptedMinecraftServer> servers,
                             @JsonProperty("timeZone") String timeZone,
                             @JsonProperty("gameTypes") List<JsonAdaptedGameType> gameTypes) {
        this.name = name;
        this.minecraftName = minecraftName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (socials != null) {
            this.socials.addAll(socials);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (servers != null) {
            this.servers.addAll(servers);
        }
        this.timeZone = timeZone;

        if (gameTypes != null) {
            this.gameTypes.addAll(gameTypes);
        }
    }

    /**
     * Converts a given {@code Person} into this class for json use.
     */
    public JsonAdaptedPerson(Person source) {

        name = source.getName().toString();
        minecraftName = source.getMinecraftName().toString();
        phone = source.getPhone().toString();
        email = source.getEmail().toString();
        address = source.getAddress().toString();
        socials.addAll(source.getSocials().stream()
                .map(JsonAdaptedSocial::new)
                .collect(Collectors.toList()));
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        servers.addAll(source.getServers().stream()
                .map(JsonAdaptedMinecraftServer::new)
                .collect(Collectors.toList()));
        timeZone = source.getTimeZone().toString();
        gameTypes.addAll(source.getGameType().stream()
                .map(JsonAdaptedGameType::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

        final List<Tag> tags = new ArrayList<>();
        for (JsonAdaptedTag tag : this.tags) {
            tags.add(tag.toModelType());
        }

        final List<Social> socials = new ArrayList<>();
        for (JsonAdaptedSocial social : this.socials) {
            socials.add(social.toModelType());
        }

        final List<Server> servers = new ArrayList<>();
        for (JsonAdaptedMinecraftServer server : this.servers) {
            servers.add(server.toModelType());
        }

        final List<GameType> gameTypes = new ArrayList<>();
        for (JsonAdaptedGameType gameType : this.gameTypes) {
            gameTypes.add(gameType.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (minecraftName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MinecraftName.class.getSimpleName()));
        }
        if (!MinecraftName.isValidMinecraftName(minecraftName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final MinecraftName modelMinecraftName = new MinecraftName(minecraftName);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(tags);

        final Set<Social> modelSocials = new HashSet<>(socials);

        final Set<Server> modelServers = new HashSet<>(servers);

        final Set<GameType> modelGameTypes = new HashSet<>(gameTypes);

        if (timeZone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimeZone.class.getSimpleName()));
        }
        if (!TimeZone.isValidTimeZone(timeZone)) {
            throw new IllegalValueException(TimeZone.MESSAGE_CONSTRAINTS);
        }
        final TimeZone modelTimeZone = new TimeZone(timeZone);

        return new Person(modelName, modelMinecraftName, modelPhone, modelEmail,
                modelAddress, modelSocials, modelTags, modelServers, modelTimeZone, modelGameTypes);

    }

}
