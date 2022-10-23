package seedu.address.model.person;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s taken {@code Mod} matches any of the keywords given.
 */
public class ModTakenContainsKeywordsPredicate extends ModContainsKeywordsPredicate {
    private final List<String> keywords;

    /**
     * Constructs the ModTakenContainsKeywordsPredicate
     * @param keywords list of user inputs.
     */
    public ModTakenContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        ObservableList<Mod> mods = person.getMods();
        return keywords.stream().count() != 0
                && keywords.stream().allMatch(keyword -> mods.stream()
                .anyMatch(mod -> mod.getModStatus() && StringUtil.containsWordIgnoreCase(mod.getModName(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModTakenContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModTakenContainsKeywordsPredicate) other).keywords)); // state check
    }

}
