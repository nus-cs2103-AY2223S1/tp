package bookface.model.book;

import java.util.List;
import java.util.function.Predicate;

import bookface.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Title} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(book.getTitle().bookTitle, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof bookface.model.book.TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((bookface.model.book.TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
