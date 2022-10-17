package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.interest.Interest;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitHub;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String handle;
    private final String username;
    private final List<JsonAdaptedInterest> tagged = new ArrayList<>();
    private final List<JsonAdaptedMod> mods = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("handle") String handle,
                             @JsonProperty("username") String username,
                             @JsonProperty("tagged") List<JsonAdaptedInterest> tagged,
                             @JsonProperty("mods") List<JsonAdaptedMod> mods) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.handle = handle;
        this.username = username;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (mods != null) {
            this.mods.addAll(mods);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        if (source.getPhone() == null) {
            phone = null;
        } else {
            phone = source.getPhone().value;
        }
        if (source.getEmail() == null) {
            email = null;
        } else {
            email = source.getEmail().value;
        }
        if (source.getGitHub() == null) {
            username = null;
        } else {
            username = source.getGitHub().username;
        }
        handle = source.getTelegram().handle;
        tagged.addAll(source.getInterests().stream()
                .map(JsonAdaptedInterest::new)
                .collect(Collectors.toList()));
        mods.addAll(source.getMods().stream()
                .map(JsonAdaptedMod::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Interest> interests = new ArrayList<>();
        final List<Mod> personMods = new ArrayList<>();
        for (JsonAdaptedInterest interest : tagged) {
            interests.add(interest.toModelType());
        }
        for (JsonAdaptedMod mod : mods) {
            personMods.add(mod.toModelType());
        }

        Phone modelPhone = null;
        Email modelEmail = null;
        GitHub modelGitHub = null;

        // mandatory
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (handle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(handle)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelHandle = new Telegram(handle);

        // optional
        if (phone != null && !Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        if (phone != null) {
            modelPhone = new Phone(phone);
        }

        if (email != null && !Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        if (email != null) {
            modelEmail = new Email(email);
        }

        if (username != null && !GitHub.isValidGitHub(username)) {
            throw new IllegalValueException(GitHub.MESSAGE_CONSTRAINTS);
        }
        if (username != null) {
            modelGitHub = new GitHub(username);
        }

        final Set<Interest> interestSet = new HashSet<>(interests);
        final ObservableList<Mod> modelMods = FXCollections.observableArrayList(personMods);
        return new Person(modelName, modelPhone, modelEmail, modelHandle, modelGitHub, interestSet, modelMods);
    }

}
