package seedu.application.model.application;

import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.application.model.application.exceptions.InvalidFutureApplicationException;
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
    private final Status status;
    private final boolean isArchived;
    private final Optional<Interview> interview;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Instantiates Application with an empty Interview.
     *
     * @param company Company name that represents the company
     * @param contact Contact number of the company.
     * @param email Email of the company.
     * @param position Position that the user applies to.
     * @param date Date on which the user submits the application.
     * @param status Status of the application.
     * @param tags Set of tags the user can add to the application.
     */
    public Application(Company company, Contact contact, Email email, Position position, Date date, Status status,
            Set<Tag> tags) throws InvalidFutureApplicationException {
        requireAllNonNull(company, contact, email, position, date, tags);
        this.company = company;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.date = date;
        applicationDateIsInFuture(date);
        this.status = status;
        this.tags.addAll(tags);
        this.interview = Optional.empty();
        this.isArchived = false;
    }

    // Private constructor for set application to archive application or retrieve application.
    private Application(Application application, boolean isArchived) {
        requireAllNonNull(application);
        this.company = application.company;
        this.contact = application.contact;
        this.email = application.email;
        this.position = application.position;
        this.date = application.date;
        this.status = application.status;
        this.tags.addAll(application.tags);
        this.isArchived = isArchived;
        this.interview = application.interview;
    }

    /**
     * Instantiate Application with an empty Interview.
     *
     * @param application Application that either contains empty interview or non-empty interview.
     */
    public Application(Application application) {
        requireAllNonNull(application);
        this.company = application.company;
        this.contact = application.contact;
        this.email = application.email;
        this.position = application.position;
        this.date = application.date;
        this.status = application.status;
        this.tags.addAll(application.tags);
        this.isArchived = application.isArchived;
        this.interview = Optional.empty();
    }

    /**
     * Instantiate Application with non-empty Interview.
     *
     * @param application Application that either contains empty interview or non-empty interview.
     * @param interview Interview to be added into the application.
     */
    public Application(Application application, Interview interview) throws InvalidInterviewException {
        requireAllNonNull(application, interview);
        this.company = application.company;
        this.contact = application.contact;
        this.email = application.email;
        this.position = application.position;
        this.date = application.date;
        this.status = application.status;
        this.tags.addAll(application.tags);
        interviewIsAfterApplication(interview, application);
        this.interview = Optional.of(interview);
        this.isArchived = application.isArchived;
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

    public Status getStatus() {
        return status;
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

    public boolean hasInterview() {
        return interview.isPresent();
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
                && otherApplication.company.equals(company)
                && otherApplication.position.equals(position);
    }

    private void applicationDateIsInFuture(Date date) {
        if (date.value.isAfter(LocalDate.now())) {
            throw new InvalidFutureApplicationException();
        }
    }

    private void interviewIsAfterApplication(Interview interview, Application application) {
        if (interview.getInterviewDate().value.isBefore(application.date.value)) {
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
        return otherApplication.company.equals(company)
                && otherApplication.contact.equals(contact)
                && otherApplication.email.equals(email)
                && otherApplication.position.equals(position)
                && otherApplication.date.equals(date)
                && otherApplication.status.equals(status)
                && otherApplication.tags.equals(tags)
                && otherApplication.interview.equals((interview));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, contact, email, position, date, tags, interview);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(company)
                .append("; Position applied: ")
                .append(position)
                .append("; Contact number: ")
                .append(contact)
                .append("; Email: ")
                .append(email)
                .append("; Apply on: ")
                .append(date);

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        interview.ifPresent(x -> builder.append("; \nInterview: ").append(x));

        return builder.toString();
    }

}
