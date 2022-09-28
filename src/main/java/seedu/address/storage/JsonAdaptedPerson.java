package seedu.address.storage;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
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
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedSocial> social = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedMinecraftServer> serverList = new ArrayList<>();
    private final String timeZone;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("socials") List<JsonAdaptedSocial> socials, @JsonProperty("tagged") List<JsonAdaptedTag> tagged, @JsonProperty("servers") List<JsonAdaptedMinecraftServer> serverList,
                             @JsonProperty("timeZone") String timeZone) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (socials != null) {
            this.social.addAll(social);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (serverList != null) {
            this.serverList.addAll(serverList);
        }
        this.timeZone = timeZone;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        social.addAll(source.getSocials().stream()
                .map(JsonAdaptedSocial::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        serverList.addAll(source.getServers().stream()
                .map(JsonAdaptedMinecraftServer::new)
                .collect(Collectors.toList()));
        timeZone = source.getTimeZone().getOffsetInString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Social> socialsTags = new ArrayList<>();
        for (JsonAdaptedSocial social : social) {
            socialsTags.add(social.toModelType());
        }
            
        final List<Server> personServers = new ArrayList<>();
        for (JsonAdaptedMinecraftServer server: serverList) {
            personServers.add(server.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

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

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Social> modelSocials = new HashSet<>(socialsTags);

        final Set<Server> modelServers = new HashSet<>(personServers);
        final TimeZone modelTimeZone = new TimeZone(timeZone);
        return new Person(modelName, new MinecraftName("df"), modelPhone, modelEmail, modelAddress, modelSocials, modelTags, modelServers, modelTimeZone);

    }

}
