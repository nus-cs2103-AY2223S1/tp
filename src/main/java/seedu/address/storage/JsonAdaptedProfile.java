package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Profile}.
 */
class JsonAdaptedProfile {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Profile's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String telegram;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedProfile} with the given profile details.
     */
    @JsonCreator
    public JsonAdaptedProfile(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("telegram") String telegram,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Profile} into this class for Jackson use.
     */
    public JsonAdaptedProfile(Profile source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        telegram = source.getTelegram().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted profile object into the model's {@code Profile} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted profile.
     */
    public Profile toModelType() throws IllegalValueException {
        final List<Tag> profileTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            profileTags.add(tag.toModelType());
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

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Telegram.class.getSimpleName()));
        }
        if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        }
        final Telegram modelTelegram = new Telegram(telegram);
        final Set<Tag> modelTags = new HashSet<>(profileTags);
        return new Profile(modelName, modelPhone, modelEmail, modelTelegram, modelTags);
    }

}
