package seedu.application.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.application.model.application.Application;

public class ShowArchiveOnlyPredicate implements Predicate<Application> {
    private final List<Application> archives;

    public ShowArchiveOnlyPredicate(List<Application> archives) {
        this.archives = archives;
    }

    @Override
    public boolean test(Application application) {
        return archives.stream()
                .anyMatch(archive -> archive.equals(application));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowArchiveOnlyPredicate // instanceof handles nulls
                && archives.equals(((ShowArchiveOnlyPredicate) other).archives)); // state check
    }
}
