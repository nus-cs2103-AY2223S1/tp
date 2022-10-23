package seedu.application.model.application;

import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.application.model.application.interview.Interview;
import seedu.application.model.application.interview.exceptions.InvalidInterviewException;
import seedu.application.model.tag.Tag;

/**
 * Represents an Application in the Application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Application {

    // Identity fields
    private final Company company;
    private final Contact contact;
    private final Email email;
    private final Position position;
    private final Date date;
    private final boolean isArchived;
    private final Optional<Interview> interview;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Instantiate Application with empty Interview.
     *
     * @param company name that represents the company
     * @param contact number of the company.
     * @param email of the company.
     * @param position that the user applies to.
     * @param date of the user applies to this position.
     */
    public Application(Company company, Contact contact, Email email, Position position, Date date, Set<Tag> tags) {
        requireAllNonNull(company, contact, email, position, date, tags);
        this.company = company;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.date = date;
        this.tags.addAll(tags);
        this.interview = Optional.empty();
        this.isArchived = false;
    }

    //Private constructor for set application to archive application or retrieve application.
    private Application(Application application, boolean isArchived) {
        requireAllNonNull(application);
        this.company = application.getCompany();
        this.contact = application.getContact();
        this.email = application.getEmail();
        this.position = application.getPosition();
        this.date = application.getDate();
        this.tags.addAll(application.getTags());
        this.isArchived = isArchived;
        this.interview = application.getInterview();
    }

    /**
     * Instantiate Application with empty Interview.
     *
     * @param application that either contains empty interview or non-empty interview.
     */
    public Application(Application application) {
        requireAllNonNull(application);
        this.company = application.getCompany();
        this.contact = application.getContact();
        this.email = application.getEmail();
        this.position = application.getPosition();
        this.date = application.getDate();
        this.tags.addAll(application.getTags());
        this.interview = Optional.empty();
        this.isArchived = application.isArchived();
    }

    /**
     * Instantiate Application with non-empty Interview.
     *
     * @param application that either contains empty interview or non-empty interview.
     * @param interview to be added into the application.
     */
    public Application(Application application, Interview interview) throws InvalidInterviewException {
        requireAllNonNull(application, interview);
        this.company = application.getCompany();
        this.contact = application.getContact();
        this.email = application.getEmail();
        this.position = application.getPosition();
        this.date = application.getDate();
        this.tags.addAll(application.getTags());
        interviewIsAfterApplication(interview, application);
        this.interview = Optional.of(interview);
        this.isArchived = application.isArchived();
    }

    public Company getCompany() {
        return company;
    }

    public Contact getContact() {
        return contact;
    }

    public Email getEmail() {
        return email;
    }

    public Position getPosition() {
        return position;
    }

    public Date getDate() {
        return date;
    }
    public Optional<Interview> getInterview() {
        return interview;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public Optional<LocalDateTime> getInterviewDateTime() {
        return interview.map(Interview::getInterviewDateTime);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both applications have the same company and position.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(Application otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && otherApplication.getCompany().equals(getCompany())
                && otherApplication.getPosition().equals(getPosition());
    }

    private void interviewIsAfterApplication(Interview interview, Application application) {
        if (interview.getInterviewDate().value.isBefore(application.getDate().value)) {
            throw new InvalidInterviewException();
        }
    }

    /**
     * Returns a new Application object with its archive status set to true and other attributes remain the same.
     */
    public Application setToArchive() {
        return new Application(this, true);
    }

    /**
     * Returns a new Application object with its archive status set to false and other attributes remain the same.
     */
    public Application retrieveFromArchive() {
        return new Application(this, false);
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return otherApplication.getCompany().equals(getCompany())
                && otherApplication.getContact().equals(getContact())
                && otherApplication.getEmail().equals(getEmail())
                && otherApplication.getPosition().equals(getPosition())
                && otherApplication.getDate().equals(getDate())
                && otherApplication.getTags().equals(getTags())
                && otherApplication.getInterview().equals((getInterview()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, contact, email, position, date, tags, interview);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCompany())
                .append("; Position applied: ")
                .append(getPosition())
                .append("; Contact number: ")
                .append(getContact())
                .append("; Email: ")
                .append(getEmail())
                .append("; Apply on: ")
                .append(getDate());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (getInterview().isPresent()) {
            builder.append("; \nInterview: ")
                    .append(getInterview().get());
        }

        return builder.toString();
    }

}
