package bookface.model.book;

import static java.util.Objects.requireNonNull;

import bookface.commons.util.AppUtil;

/**
 * Represents an Author of a Book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAuthor(String)}
 */
public class Author {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphabetical characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String bookAuthor;

    /**
     * Constructs an {@code Author}.
     *
     * @param author A valid name of an Author.
     */
    public Author(String author) {
        requireNonNull(author);
        AppUtil.checkArgument(isValidAuthor(author), MESSAGE_CONSTRAINTS);
        bookAuthor = author;
    }

    /**
     * Returns true if a given string is a valid title.
     */
    public static boolean isValidAuthor(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return bookAuthor;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Author // instanceof handles nulls
                && bookAuthor.equals(((Author) other).bookAuthor)); // state check
    }

    @Override
    public int hashCode() {
        return bookAuthor.hashCode();
    }

}
