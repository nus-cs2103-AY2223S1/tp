package seedu.application.testutil;

import seedu.application.model.application.Application;
import seedu.application.model.application.Company;
import seedu.application.model.application.Contact;
import seedu.application.model.application.Date;
import seedu.application.model.application.Email;
import seedu.application.model.application.Position;

/**
 * A utility class to help with building Application objects.
 */
public class ApplicationBuilder {

    public static final String DEFAULT_COMPANY = "Google";
    public static final String DEFAULT_CONTACT = "85355255";
    public static final String DEFAULT_DATE = "2022-01-01";
    public static final String DEFAULT_EMAIL = "google@gmail.com";
    public static final String DEFAULT_POSITION = "Software Engineer";

    private Company company;
    private Contact contact;
    private Date date;
    private Email email;
    private Position position;

    /**
     * Creates an {@code ApplicationBuilder} with the default details.
     */
    public ApplicationBuilder() {
        company = new Company(DEFAULT_COMPANY);
        contact = new Contact(DEFAULT_CONTACT);
        date = new Date(DEFAULT_DATE);
        email = new Email(DEFAULT_EMAIL);
        position = new Position(DEFAULT_POSITION);
    }

    /**
     * Initializes the ApplicationBuilder with the data of {@code applicationToCopy}.
     */
    public ApplicationBuilder(Application applicationToCopy) {
        company = applicationToCopy.getCompany();
        contact = applicationToCopy.getContact();
        date = applicationToCopy.getDate();
        email = applicationToCopy.getEmail();
        position = applicationToCopy.getPosition();
    }

    /**
     * Sets the {@code Company} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withContact(String contact) {
        this.contact = new Contact(contact);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code Application} that we are building.
     */
    public ApplicationBuilder withPosition(String position) {
        this.position = new Position(position);
        return this;
    }

    public Application build() {
        return new Application(company, contact, email, position, date);
    }

}
