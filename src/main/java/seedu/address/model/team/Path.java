package seedu.address.model.team;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    private final Queue<String> path;
    //TODO Eric: update Queue<String> to Queue<Team>

    public Path(String path) {
        this.path = parsePath(path);
    }

    /**
     * Get a path to the specified team.
     *
     * @param path to the specified team in command line format.
     * @return a queue that specifies the path to a team.
     */
    private static Queue<String> parsePath(String path) {
        return Arrays.stream(path.split("\\\\"))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    /**
     * Checks if the given path is valid.
     *
     * @param path to the specified team in command line format.
     * @return true if the given path is valid, false otherwise.
     */
    public static boolean isValidPath(String path) {
        return !containsWhitespace(path); //TODO Eric: characters in other languages?
    }

    /**
     * Checks if the given argument string contains any whitespace.
     *
     * @param path refer to the subsequent arguments after the initial command word.
     * @return true if the string contains a whitespace, false otherwise.
     */
    private static boolean containsWhitespace(String path) {
        return Pattern.matches("\\s", path);
    }
}
