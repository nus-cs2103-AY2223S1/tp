package seedu.masslinkers.model.student;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.util.StringUtil;

//@@author chm252
/**
 * Tests that a {@code Student}'s current {@code Mod} matches any of the keywords given.
 */
public class ModTakingContainsKeywordsPredicate extends ModContainsKeywordsPredicate {
    private final List<String> keywords;

    /**
     * Constructs the ModTakingContainsKeywordsPredicate
     * @param keywords list of user inputs.
     */
    public ModTakingContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        ObservableList<Mod> mods = student.getMods();
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
