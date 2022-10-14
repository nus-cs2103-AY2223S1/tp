package seedu.address.model.task;

import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordPredicate implements Predicate<Task> {
    private final String keyword;

    public TitleContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Task task) {
        return task.getTitle().value.toLowerCase().contains(keyword.toLowerCase(Locale.ROOT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((TitleContainsKeywordPredicate) other).keyword)); // state check
    }

}
