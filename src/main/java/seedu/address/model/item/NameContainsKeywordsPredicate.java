package seedu.address.model.item;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate<U extends DisplayItem> implements Predicate<U> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(U item) {
        return keywords.stream()
            .map(k -> k.trim())
            .anyMatch(keyword -> {
                return StringUtil.containsWordIgnoreCase(item.getName().fullName, keyword)
                    || item.getAttributes()
                        .stream()
                        .anyMatch(
                            attr -> StringUtil
                                .containsWordIgnoreCase(attr.getAttributeContent().toString(), keyword));

            });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate<?>) other).keywords)); // state check
    }

}
