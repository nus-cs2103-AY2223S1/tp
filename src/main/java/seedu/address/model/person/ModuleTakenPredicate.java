package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.NusModule;

/**
 * Tests if any of a {@code Person}'s {@code Module} matches any of the keywords given.
 */
public class ModuleTakenPredicate implements Predicate<Person> {
    private final List<NusModule> modules;

    public ModuleTakenPredicate(List<NusModule> modules) {
        this.modules = modules;
    }

    @Override
    public boolean test(Person person) {
        boolean flag = false;
        Set<NusModule> personMods = person.getMods();
        for (NusModule mod : modules) {
            for (NusModule pmod : personMods) {
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
