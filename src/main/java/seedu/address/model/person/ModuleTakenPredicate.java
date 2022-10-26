package seedu.address.model.person;

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
        return other == this // short circuit if same instance
                || (other instanceof ModuleTakenPredicate// instanceof handles nulls
                && keywords.equals(((ModuleTakenPredicate) other).keywords)); // state check
    }

}
