package seedu.address.model.person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests if any of a {@code Person}'s {@code Module} matches any of the keywords given.
 */
public class ModuleTakenPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ModuleTakenPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Module> personMods = person.getModules();

        return keywords.stream().anyMatch(keyword -> personMods.stream()
                .anyMatch(module -> module.moduleName.toUpperCase()
                        .contains(keyword.toUpperCase())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ModuleTakenPredicate) {
            Set<String> thisKeywordSet = new HashSet<>(keywords);
            Set<String> otherKeywordSet = new HashSet<>(((ModuleTakenPredicate) other).keywords);
            return thisKeywordSet.equals(otherKeywordSet);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String moduleKeywords = "";
        for (String keyword : keywords) {
            moduleKeywords += keyword + " ";
        }
        return moduleKeywords.trim();
    }

}
