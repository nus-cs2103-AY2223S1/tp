package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Path to the nested team in the address book.
 */
public class Path {

    public static final String MESSAGE_CONSTRAINTS = "A path to a team should be in "
            + "the format of team[\\nested-team\\...], where [...] refers to optional "
            + "arguments in the path. The path should adhere to the following "
            + "constraints:\n"
            + "1. Both team and nested-teams can contain all alphanumeric characters except "
            + "for backslash and whitespace\n"
            + "2. Each of the team or nested teams are being split by a (\\).\n";

    private final String path;

    public Path(String path) {
        requireNonNull(path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}
