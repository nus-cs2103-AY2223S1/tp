package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Mod} matches any of the keywords given.
 */
public class ModContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ModContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        ObservableList<Mod> mods = person.getMods();
        for (int i = 0; i < mods.size(); i++) {
            Mod mod = mods.get(i);
            if (keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(mod.modName, keyword))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModContainsKeywordsPredicate) other).keywords)); // state check
    }

}
