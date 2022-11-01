package seedu.address.model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.HashMap;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.tuitionclass.TuitionClass;

/**
 * Tests that a {@code TuitionClass}'s fields matches the respective keywords given.
 */
public class TuitionClassContainsKeywordsPredicate<T> implements Predicate<T> {
    private final HashMap<Prefix, String> keywords;

    public TuitionClassContainsKeywordsPredicate(HashMap<Prefix, String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof TuitionClass) {
            TuitionClass tuitionClass = (TuitionClass) t;
            return tuitionClass.getName().name.toLowerCase().contains(keywords.get(PREFIX_NAME).toLowerCase())
                    && tuitionClass.getDay().day.toLowerCase().contains(keywords.get(PREFIX_DAY).toLowerCase())
                    && tuitionClass.getSubject().subject.toLowerCase()
                    .contains(keywords.get(PREFIX_SUBJECT_OR_SCHOOL).toLowerCase())
                    && tuitionClass.getLevel().level.toLowerCase()
                    .contains(keywords.get(PREFIX_LEVEL).toLowerCase())
                    && tuitionClass.getTime().toString().toLowerCase()
                    .contains(keywords.get(PREFIX_TIME).toLowerCase())
                    && (tuitionClass.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase()
                    .contains(keywords.get(PREFIX_TAG).toLowerCase()))
                    || (tuitionClass.getTags().isEmpty() && keywords.get(PREFIX_TAG).equals("")));
        }
        throw new ClassCastException("TuitionClassContainsKeywords predicate can only be applied to TuitionClass.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TuitionClassContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TuitionClassContainsKeywordsPredicate<?>) other).keywords)); // state check
    }
}
