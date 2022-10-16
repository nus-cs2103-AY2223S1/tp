package seedu.application.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;
import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.InterviewDate;
import seedu.application.model.application.interview.InterviewTime;
import seedu.application.model.application.interview.Location;
import seedu.application.model.application.interview.Round;

/**
 * Jackson-friendly version of {@link Application}.
 */
class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    //Fields in Application
    private final String company;
    private final String contact;
    private final String email;
    private final String position;
    private final String date;
    //Fields in Interview
    private final String round;
    private final String interviewDate;
    private final String interviewTime;
    private final String location;


    /*
    @JsonCreator
    public JsonAdaptedApplication(@JsonProperty("company") String company, @JsonProperty("contact") String contact,
                                  @JsonProperty("email") String email, @JsonProperty("position") String position,
                                  @JsonProperty("date") String date) {
        this.company = company;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.date = date;

        this.round = "";
        this.interviewDate = "";
        this.interviewTime = "";
        this.location = "";
    }

     */


    /**
     * Constructs a {@code JsonAdaptedApplication} with the given application details with non-empty Interview.
     */
    @JsonCreator
    public JsonAdaptedApplication(@JsonProperty("company") String company, @JsonProperty("contact") String contact,
                                  @JsonProperty("email") String email, @JsonProperty("position") String position,
                                  @JsonProperty("date") String date, @JsonProperty("round") String round,
                                  @JsonProperty("interviewDate") String interviewDate,
                                  @JsonProperty("interviewTime") String interviewTime,
                                  @JsonProperty("location") String location) {
        this.company = company;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.date = date;
        this.round = round;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.location = location;
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

        Optional<Interview> interview = source.getInterview();
        if (interview.isEmpty()) {
            this.round = "";
            this.interviewDate = "";
            this.interviewTime = "";
            this.location = "";
        } else {
            this.round = interview.get().getRound().value;
            this.interviewDate = interview.get().getInterviewDate().value.toString();
            this.interviewTime = interview.get().getInterviewTime().toCommandString();
            this.location = interview.get().getLocation().value;
        }
    }

    /**
     * Converts this Jackson-friendly adapted application object into the model's {@code Application} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public Application toModelType() throws IllegalValueException {
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

        Application modelApplication = new Application(modelCompany, modelContact, modelEmail, modelPosition,
                modelDate);
        if (round.equals("") && interviewDate.equals("") && interviewTime.equals("") && location.equals("")) {
            return modelApplication;
        } else {
            Interview modelInterview = toModelTypeInterview();
            return new Application(modelApplication, modelInterview);
        }
    }

    /**
     * Converts this Jackson-friendly adapted interview object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview.
     */
    public Interview toModelTypeInterview() throws IllegalValueException {
        if (round == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Round.class.getSimpleName()));
        }
        if (!Round.isValidRound(round)) {
            throw new IllegalValueException(Round.MESSAGE_CONSTRAINTS);
        }
        final Round modelRound = new Round(round);

        if (interviewDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewDate.class.getSimpleName()));
        }
        if (!InterviewDate.isValidDate(interviewDate)) {
            throw new IllegalValueException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        final InterviewDate modelInterviewDate = new InterviewDate(interviewDate);

        if (interviewTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewTime.class.getSimpleName()));
        }
        if (!InterviewTime.isValidTime(interviewTime)) {
            throw new IllegalValueException(InterviewTime.MESSAGE_CONSTRAINTS);
        }
        final InterviewTime modelInterviewTime = new InterviewTime(interviewTime);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        return new Interview(modelRound, modelInterviewDate, modelInterviewTime, modelLocation);
    }
}
