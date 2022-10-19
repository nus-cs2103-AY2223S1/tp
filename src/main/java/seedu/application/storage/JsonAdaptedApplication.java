package seedu.application.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Application}.
 */
class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    private final String company;
    private final String contact;
    private final String email;
    private final String position;
    private final String date;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedApplication} with the given application details.
     */
    @JsonCreator
    public JsonAdaptedApplication(@JsonProperty("company") String company, @JsonProperty("contact") String contact,
                                  @JsonProperty("email") String email, @JsonProperty("position") String position,
                                  @JsonProperty("date") String date,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.company = company;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.date = date;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Application} into this class for Jackson use.
     */
    public JsonAdaptedApplication(Application source) {
        company = source.getCompany().company;
        contact = source.getContact().value;
        email = source.getEmail().value;
        position = source.getPosition().value;
        date = source.getDate().value.toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted application object into the model's {@code Application} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public Application toModelType() throws IllegalValueException {
        final List<Tag> applicationTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            applicationTags.add(tag.toModelType());
        }

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (contact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName()));
        }
        if (!Contact.isValidContact(contact)) {
            throw new IllegalValueException(Contact.MESSAGE_CONSTRAINTS);
        }
        final Contact modelContact = new Contact(contact);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        final Set<Tag> modelTags = new HashSet<>(applicationTags);
        return new Application(modelCompany, modelContact, modelEmail, modelPosition, modelDate, modelTags);
    }

}
