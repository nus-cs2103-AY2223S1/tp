package seedu.address.model.person;

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
