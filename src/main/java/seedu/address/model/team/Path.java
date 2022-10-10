package seedu.address.model.team;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Specifies a path to a team.
 */
public class Path {
    private Queue<String> path;
    //TODO Eric: update Queue<String> to Queue<Team>

    public Path(String path) {
        this.path = parsePath(path);
    }

    /**
     * Get a path to the specified team.
     * @param path to the specified team in command line format.
     * @return a queue that specifies the path to a team.
     */
    private static Queue<String> parsePath(String path) {
        return Arrays.stream(path.split("\\\\"))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
