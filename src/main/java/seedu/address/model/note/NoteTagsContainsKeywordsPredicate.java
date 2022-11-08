package seedu.address.model.note;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Note}'s {@code Tags} matches any of the keywords given.
 */
public class NoteTagsContainsKeywordsPredicate implements Predicate<Note> {
    private final List<String> keywords;

    public NoteTagsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Note note) {
        boolean result = false;

        Set<Tag> tagSet = note.getTags();
        for (Tag tag : tagSet) {
            result = result || keywords.stream().anyMatch(
                    keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword));
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteTagsContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NoteTagsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
