package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Task}'s {@code Project} matches any of the project names given.
 */
public class ContainsProjectsPredicate implements Predicate<Task> {
    private final List<String> projectNames;

    /**
     * Constructs an ContainsProjectsPredicate.
     * @param projectNames the list of project names to search for
     */
    public ContainsProjectsPredicate(List<String> projectNames) {
        this.projectNames = projectNames
                .stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public boolean test(Task task) {
        return projectNames.isEmpty() || projectNames.contains(task.getProject().projectName.trim());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsProjectsPredicate // instanceof handles nulls
                && projectNames.equals(((ContainsProjectsPredicate) other).projectNames)); // state check
    }

}
