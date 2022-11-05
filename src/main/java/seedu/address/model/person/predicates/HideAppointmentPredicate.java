package seedu.address.model.person.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class HideAppointmentPredicate implements Predicate<Appointment> {

    /**
     * The conditions that we can hide the appointments by.
     */
    public enum HideBy {
        TAG, KEYWORD, IS_MARKED
    }
    private final HideBy condition;
    private final List<String> keywords;

    /**
     * Constructor to initialise the hide appointment predicate.
     * @param condition The condition to hide appointment by.
     * @param keywords The keywords to search for within the tag/reason/status.
     */
    public HideAppointmentPredicate(HideBy condition, List<String> keywords) {
        this.condition = condition;
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appt) {
        boolean passed = false;

        switch (condition) {
        case KEYWORD:
            passed = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsIgnoreCase(appt.getReason(), keyword));
            break;
        case TAG:
            Set<Tag> tags = appt.getTags();
            for (Tag t: tags) {
                if (keywords.stream().anyMatch(t.toString()::equalsIgnoreCase)) {
                    passed = true;
                }
            }
            break;
        case IS_MARKED:
            String kw = keywords.get(0);
            passed = kw.equalsIgnoreCase("marked") || kw.equalsIgnoreCase("m")
                    ? appt.isMarked() : !appt.isMarked();
            break;
        default:
            assert false : condition; //should not reach here
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
