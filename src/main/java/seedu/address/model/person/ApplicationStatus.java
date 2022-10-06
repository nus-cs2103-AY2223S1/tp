package seedu.address.model.person;

/**
 * Represents an Internship's application status in the address book.
 */
public enum ApplicationStatus {

    Accepted("accepted"),
    Applied("applied"),
    Interviewed("interviewed"),
    Rejected("Rejected");

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
    public static ApplicationStatus parse(String status) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.status.equals(status)) {
                return applicationStatus;
            }
        }
        // should throw exception instead
        return null;
    }
}
