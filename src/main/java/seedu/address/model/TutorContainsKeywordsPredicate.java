package seedu.address.model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashMap;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.tutor.Tutor;

/**
 * Tests that a {@code Tutor}'s fields matches the respective keywords given.
 */
public class TutorContainsKeywordsPredicate<T> implements Predicate<T> {
    private final HashMap<Prefix, String> keywords;

    public TutorContainsKeywordsPredicate(HashMap<Prefix, String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(T t) {
        if (t instanceof Tutor) {
            Tutor tutor = (Tutor) t;
            return tutor.getName().fullName.toLowerCase().contains(keywords.get(PREFIX_NAME).toLowerCase())
                    && tutor.getAddress().value.toLowerCase().contains(keywords.get(PREFIX_ADDRESS).toLowerCase())
                    && tutor.getEmail().value.toLowerCase().contains(keywords.get(PREFIX_EMAIL).toLowerCase())
                    && tutor.getPhone().value.toLowerCase().contains(keywords.get(PREFIX_PHONE).toLowerCase())
                    && tutor.getQualification().qualification.toLowerCase()
                    .contains(keywords.get(PREFIX_QUALIFICATION).toLowerCase())
                    && tutor.getInstitution().institution.toLowerCase()
                    .contains(keywords.get(PREFIX_INSTITUTION).toLowerCase())
                    && (tutor.getTags().stream().anyMatch(tag -> tag.tagName.toLowerCase()
                    .contains(keywords.get(PREFIX_TAG).toLowerCase()))
                    || (tutor.getTags().isEmpty() && keywords.get(PREFIX_TAG).equals("")));
        }
        throw new ClassCastException("TutorContainsKeywords predicate can only be applied to Tutor.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorContainsKeywordsPredicate<?>) other).keywords)); // state check
    }
}
