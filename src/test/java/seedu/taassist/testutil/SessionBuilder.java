package seedu.taassist.testutil;

import java.time.LocalDate;

import seedu.taassist.commons.util.StringUtil;
import seedu.taassist.model.session.Date;
import seedu.taassist.model.session.Session;

/**
 * A utility class to help with building Session objects.
 */
public class SessionBuilder {
    public static final String DEFAULT_NAME = "Tutorial 1";
    public static final LocalDate DEFAULT_LOCAL_DATE = LocalDate.now();

    private String name;
    private Date date;

    /**
     * Creates a {@code SessionBuilder} with the default details.
     */
    public SessionBuilder() {
        name = DEFAULT_NAME;
        date = new Date(DEFAULT_LOCAL_DATE);
    }

    /**
     * Creates a {@code SessionBuilder} with the provided {@code session}.
     */
    public SessionBuilder(Session sessionToCopy) {
        this.name = sessionToCopy.getSessionName();
        this.date = sessionToCopy.getDate();
    }

    /**
     * Sets the {@code name} of the {@code Session} that we are building.
     */
    public SessionBuilder withName(String name) {
        this.name = StringUtil.capitalise(name);
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Session} that we are building.
     */
    public SessionBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * Parses and sets the {@code date} of the {@code Session} that we are building.
     */
    public SessionBuilder withDate(String date) {
        this.date = new Date(LocalDate.parse(date));
        return this;
    }

    public Session build() {
        return new Session(name, date);
    }
}
