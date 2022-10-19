package seedu.application.model;

import java.util.function.Predicate;

import seedu.application.model.application.Application;

public class ShowArchiveOnlyPredicate implements Predicate<Application> {

    @Override
    public boolean test(Application application) {
        return application.isArchived();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowArchiveOnlyPredicate); // instanceof handles nulls
    }
}
