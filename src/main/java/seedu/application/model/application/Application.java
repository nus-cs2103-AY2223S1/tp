package seedu.application.model.application;

import static seedu.application.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.application.model.application.interview.Interview;

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
    private final Optional<Interview> interview;

    /**
     * Instantiate Application with empty Interview.
     */
    public Application(Company company, Contact contact, Email email, Position position, Date date) {
        requireAllNonNull(company, contact, email, position, date);
        this.company = company;
        this.contact = contact;
        this.email = email;
        this.position = position;
        this.date = date;
        this.interview = Optional.empty();
    }

    /**
     * Instantiate Application with non-empty Interview.
     */
    public Application(Application application, Interview interview) {
        requireAllNonNull(application, interview);
        this.company = application.getCompany();
        this.contact = application.getContact();
        this.email = application.getEmail();
        this.position = application.getPosition();
        this.date = application.getDate();
        this.interview = Optional.of(interview);
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
                && otherApplication.getInterview().equals((getInterview()));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(company, contact, email, position, date, interview);
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
                .append(getDate())
                .append("; Interview: ")
                .append(getInterview());

        return builder.toString();
    }

}
