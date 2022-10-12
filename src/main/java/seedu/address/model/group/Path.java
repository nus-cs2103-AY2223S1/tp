package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Path to the nested group in the address book.
 */
public class Path {

    public static final String MESSAGE_CONSTRAINTS = "A path to a  should be in "
            + "the format of group[/nested-group/...], where [...] refers to optional "
            + "arguments in the path. The path should adhere to the following "
            + "constraints:\n"
            + "1. Both group and nested-groups can contain all alphanumeric characters, "
            + "hyphens and underscores only.\n"
            + "2. Each of the group or nested groups are being split by a slash (/).\n";

    private final String path;

    /**
     * Constructs a path. Takes in a full path, typically denoted by
     * the form group[/nested-group/...].
     *
     * @param path that leads to an existing group.
     */
    public Path(String path) {
        requireNonNull(path);
        this.path = path;
    }

    /**
     * Gets the path, denoted by the form group[/nested-group/...].
     *
     * @return a String that stores the path to a group.
     */
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }
}
