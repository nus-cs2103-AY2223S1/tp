package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.AppliedDate;
import seedu.address.model.internship.Company;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.InterviewDateTime;
import seedu.address.model.internship.Link;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Internship}.
 */
class JsonAdaptedInternship {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final String company;
    private final String link;
    private final String description;
    private final String applicationStatus;
    private final String appliedDate;
    private final String interviewDateTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("name") String company, @JsonProperty("link") String link,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("applicationStatus") String applicationStatus,
                                 @JsonProperty("appliedDate") String appliedDate,
                                 @JsonProperty("interviewDateTime") String interviewDateTime,
                                 @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.company = company;
        this.link = link;
        this.description = description;
        this.applicationStatus = applicationStatus;
        this.appliedDate = appliedDate;
        this.interviewDateTime = interviewDateTime;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {


        company = source.getCompany().value;
        link = source.getLink().value;
        description = source.getDescription().value;
        applicationStatus = source.getApplicationStatus().toString().toLowerCase();
        appliedDate = source.getAppliedDate().value;

        if (source.getInterviewDateTime() == null) {
            interviewDateTime = "";
        } else {
            interviewDateTime = source.getInterviewDateTime().value;
        }

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {
        final List<Tag> internshipTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            internshipTags.add(tag.toModelType());
        }

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (link == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Link.class.getSimpleName()));
        }
        if (!Link.isValidLink(link)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
        }
        final Link modelLink = new Link(link);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (applicationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        final ApplicationStatus modelApplicationStatus = ApplicationStatus.parse(applicationStatus);

        if (appliedDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppliedDate.class.getSimpleName()));
        }
        if (!AppliedDate.isValidFormat(appliedDate)) {
            throw new IllegalValueException(AppliedDate.FORMAT_CONSTRAINTS);
        } else if (!AppliedDate.isValidDate(appliedDate)) {
            throw new IllegalValueException(AppliedDate.DATE_CONSTRAINTS);
        }
        final AppliedDate modelAppliedDate = new AppliedDate(appliedDate);

        final Set<Tag> modelTags = new HashSet<>(internshipTags);

        if (interviewDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewDateTime.class.getSimpleName()));
        }


        if (interviewDateTime == "") {
            return new Internship(modelCompany, modelLink, modelDescription,
                modelApplicationStatus, modelAppliedDate, null, modelTags);
        } else if (!InterviewDateTime.isValidFormat(interviewDateTime)) {
            throw new IllegalValueException(InterviewDateTime.FORMAT_CONSTRAINTS);
        } else if (!InterviewDateTime.isValidDateTime(interviewDateTime)) {
            throw new IllegalValueException(InterviewDateTime.DATE_TIME_CONSTRAINTS);
        } else {
            final InterviewDateTime modelInterviewDateTime = new InterviewDateTime(interviewDateTime);
            return new Internship(modelCompany, modelLink, modelDescription,
                    modelApplicationStatus, modelAppliedDate, modelInterviewDateTime, modelTags);
        }
    }

}
