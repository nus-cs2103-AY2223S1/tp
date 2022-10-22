package seedu.address.model.person;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s current {@code Mod} matches any of the keywords given.
 */
public class ModTakingContainsKeywordsPredicate extends ModContainsKeywordsPredicate {
    private final List<String> keywords;

    /**
     * Constructor for ModTakingContainsKeywordsPredicate
     * @param keywords list of user inputs.
     */
    public ModTakingContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        ObservableList<Mod> mods = person.getMods();
        return keywords.stream().count() != 0
                && keywords.stream().allMatch(keyword -> mods.stream()
                .anyMatch(mod -> !mod.getModStatus() && StringUtil.containsWordIgnoreCase(mod.getModName(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModTakingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModTakingContainsKeywordsPredicate) other).keywords)); // state check
    }

}
