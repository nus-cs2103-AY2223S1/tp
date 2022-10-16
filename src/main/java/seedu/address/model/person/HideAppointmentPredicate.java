package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class HideAppointmentPredicate implements Predicate<Appointment> {

    public enum hideBy {
        TAG, KEYWORD, IS_MARKED
    }
    private final hideBy condition;
    private final String keywords;

    public HideAppointmentPredicate(hideBy condition, String keywords) {
        this.condition = condition;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appt) {
        List<String> keywordsParts = Arrays.asList(keywords.split(" "));
        boolean passed = false;
        switch (condition) {
        case KEYWORD:
            passed = keywordsParts.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(appt.getReason(), keyword));
            break;
        case TAG:
            Set<Tag> tags = appt.getTags(); //appt does not have tag yet
            for (Tag t: tags) {
                if (keywordsParts.stream().anyMatch(t.tagName::equalsIgnoreCase)) {
                    passed = true;
                }
            }
            break;
        case IS_MARKED:
            passed = appt.isMarked();
            break;
        default:
            assert false : condition; //should not reach here
        }
        if (passed) {
            HiddenPredicateSingleton.addToHiddenApptList(appt);
        }
        return passed;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HideAppointmentPredicate // instanceof handles nulls
                && keywords.equals(((HideAppointmentPredicate) other).keywords)); // state check
    }

}
