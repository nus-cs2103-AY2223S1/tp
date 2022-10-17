package seedu.application.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.application.model.application.Application;

public class HideArchiveFromListPredicate implements Predicate<Application> {
    private final List<Application> archives;

    public HideArchiveFromListPredicate(List<Application> archives) {
        this.archives = archives;
    }

    @Override
    public boolean test(Application application) {
        return !archives.contains(application);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HideArchiveFromListPredicate // instanceof handles nulls
                && archives.equals(((HideArchiveFromListPredicate) other).archives)); // state check
    }
}
