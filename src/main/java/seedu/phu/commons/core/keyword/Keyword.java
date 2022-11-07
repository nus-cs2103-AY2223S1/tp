package seedu.phu.commons.core.keyword;

import static java.util.Objects.requireNonNull;

import seedu.phu.model.internship.ApplicationProcess;
import seedu.phu.model.internship.Date;

/**
 * Represent a String of keyword.
 */
public class Keyword {
    private String keyword;


    /**
     * Constructs an instance representing the given keyword.
     *
     * @param keyword a string.
     */
    public Keyword(String keyword) {
        requireNonNull(keyword);
        this.keyword = keyword;
    }

    public boolean isKeywordFound(String string) {
        return string.toLowerCase().contains(keyword.toLowerCase());
    }

    public boolean isDate() {
        return Date.isValidDate(keyword);
    }

    public boolean isValidApplicationProcess() {
        return ApplicationProcess.isValidApplicationProcess(keyword.toUpperCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Keyword // instanceof handles nulls
                && keyword.equals(((Keyword) other).keyword)); // state check
    }

    @Override
    public int hashCode() {
        return keyword.hashCode();
    }

}
