package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

public class ModuleContainsKeywordsPredicate implements Predicate<Task> {

    private final List<String> keywords;

    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getModule().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleContainsKeywordsPredicate) other).keywords)); // state check
    }

}

