package hobbylist.model.activity;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import hobbylist.model.tag.Tag;

/**
 * Tests that an {@code Activity}'s {@code Tags} matches any of the keywords given.
 */
public class TagMatchesKeyword implements Predicate<Activity> {
    private final List<String> keywords;

    public TagMatchesKeyword(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Activity activity) {
        Set<Tag> tags = activity.getTags();
        return keywords.stream()
                .anyMatch(keyword -> tags.stream().anyMatch(tag -> tag.match(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagMatchesKeyword // instanceof handles nulls
                && keywords.equals(((TagMatchesKeyword) other).keywords)); // state check
    }
}
