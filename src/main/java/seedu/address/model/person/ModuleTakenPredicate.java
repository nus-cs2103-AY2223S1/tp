package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests if any of a {@code Person}'s {@code Module} matches any of the keywords given.
 */
public class ModuleTakenPredicate implements Predicate<Person> {
    private final List<Module> modules;

    public ModuleTakenPredicate(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public boolean test(Person person) {
        boolean flag = false;
        Set<Module> personMods = person.getModules();
        for (Module mod : modules) {
            for (Module pmod : personMods) {
                if (mod.equals(pmod)) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTakenPredicate// instanceof handles nulls
                && modules.equals(((ModuleTakenPredicate) other).modules)); // state check
    }

}
