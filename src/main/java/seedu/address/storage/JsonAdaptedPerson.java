package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Occupation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Tutorial;
import seedu.address.model.social.Social;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String occupation;
    private final String name;
    private final String phone;
    private final String email;
    private final String tutorial;
    private final String address;
    private final String social;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedGroup> grouped = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("occupation") String occupation,
                             @JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("tutorial") String tutorial,
                             @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("social") String social, @JsonProperty("grouped") List<JsonAdaptedGroup> grouped) {
        this.occupation = occupation;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tutorial = tutorial;
        this.address = address;
        this.social = social;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (grouped != null) {
            this.grouped.addAll(grouped);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        social = source.getSocial().toString();
        occupation = source.getOccupation().toString();
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        tutorial = source.getTutorial().tut;
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        grouped.addAll(source.getGroups().stream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Gets the Social from String input
     * @param input
     * @return Social of the Person
     */
    public Social getModelSocial(String input) {
        String[] socialArray = input.split(" ");
        Social modelSocial = new Social();
        String whatsapp = socialArray[0];
        String telegram = socialArray[1];
        String email = socialArray[2];
        String instagram = socialArray[3];
        String preferred = socialArray[4];
        if (!whatsapp.equals("<none>")) {
            modelSocial.addWhatsapp(whatsapp);
        }
        if (!telegram.equals("<none>")) {
            modelSocial.addTelegram(telegram);
        }
        if (!email.equals("<none>")) {
            modelSocial.addEmail(email);
        }
        if (!instagram.equals("<none>")) {
            modelSocial.addInstagram(instagram);
        }
        if (!preferred.equals("<none>")) {
            modelSocial.prefer(preferred);
        }

        return modelSocial;
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

        final List<Group> personGroups = new ArrayList<>();
        for (JsonAdaptedGroup group : grouped) {
            personGroups.add(group.toModelType());
        }

        if (occupation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Occupation.class.getSimpleName()));
        }
        if (!Occupation.isValidOccupation(occupation)) {
            throw new IllegalValueException(Occupation.MESSAGE_CONSTRAINTS);
        }
        final Occupation modelOccupation = new Occupation(occupation);

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

        if (tutorial == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Tutorial.class.getSimpleName()));
        }
        if (!Tutorial.isValidTutorial(tutorial)) {
            throw new IllegalValueException(Tutorial.MESSAGE_CONSTRAINTS);
        }
        final Tutorial modelTutorial = new Tutorial(tutorial);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Group> modelGroups = new HashSet<>(personGroups);

        final Social modelSocial = getModelSocial(social);

        return new Person(modelOccupation, modelName, modelPhone, modelEmail, modelTutorial, modelAddress, modelTags,
                modelSocial, modelGroups);
    }

}
