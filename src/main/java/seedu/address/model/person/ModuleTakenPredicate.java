package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class ModuleTakenPredicate implements Predicate<Person> {
    private final List<Mod> modules;

    public ModuleTakenPredicate(List<Mod> modules) {
        this.modules = modules;
    }

    @Override
    public boolean test(Person person) {
        boolean flag = false;
        Set<Mod> personMods = person.getMods();
        for (Mod mod : modules) {
            for (Mod pmod : personMods) {
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
