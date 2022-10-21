package seedu.address.model.internship;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents an Internship's application status in the address book.
 */
public enum ApplicationStatus {

    Accepted("accepted"),
    Applied("applied"),
    Interviewed("interviewed"),
    Shortlisted("shortlisted"),
    Rejected("rejected");

    public static final String MESSAGE_CONSTRAINTS = "Application status can be Accepted, Applied, Shortlisted, Interviewed,"
            + " or Rejected. ";

    private final String status;

    /**
     * Constructs a new ApplicationStatus.
     *
     * @param status Type of command.
     */
    ApplicationStatus(String status) {
        this.status = status;
    }

    /**
     * Returns an ApplicationStatus based on the given input status.
     *
     * @param status String representation of the input status.
     * @return ApplicationStatus based on the input status.
     */
    public static ApplicationStatus parse(String status) throws ParseException {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.status.equals(status.toLowerCase())) {
                return applicationStatus;
            }
        }
        throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid application status.
     */
    public static boolean isValidApplicationStatus(String test) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.status.matches(test)) {
                return true;
            }
        }
        return false;
    }
}
