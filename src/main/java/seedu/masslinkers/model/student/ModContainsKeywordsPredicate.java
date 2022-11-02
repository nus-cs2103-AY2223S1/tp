package seedu.masslinkers.model.student;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.util.StringUtil;

//@author jonasgwt
/**
 * Tests that a {@code Student}'s {@code Mod} matches any of the keywords given.
 */
public class ModContainsKeywordsPredicate implements Predicate<Student> {
    private final List<String> keywords;

    /**
     * Constructs for ModContainsKeywordsPredicate
     * @param keywords list of user inputs.
     */
    public ModContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        ObservableList<Mod> mods = student.getMods();
        return keywords.stream().count() != 0
            && keywords.stream().allMatch(keyword -> mods.stream()
                .anyMatch(mod -> StringUtil.containsWordIgnoreCase(mod.getModName(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModContainsKeywordsPredicate) other).keywords)); // state check
    }

}
