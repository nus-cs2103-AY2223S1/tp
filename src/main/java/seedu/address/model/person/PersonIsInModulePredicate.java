package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.module.Module;

/**
 * Tests that a {@code Person} is in the list of {@code Person}s of the given {@code Module}.
 */
public class PersonIsInModulePredicate implements Predicate<Person> {
    private final List<Person> listOfPersonsInModule;

    /**
     * Obtains the list of {@code Person}s in the given {@code Module} and assigns it to the relevant field.
     *
     * @param module The {@code Module} to obtain list of {@code Person}s from.
     */
    public PersonIsInModulePredicate(Module module) {
        List<Person> listOfPersonsInModule = module.getListOfPersons();
        this.listOfPersonsInModule = listOfPersonsInModule;
    }

    @Override
    public boolean test(Person person) {
        return listOfPersonsInModule.contains(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonIsInModulePredicate // instanceof handles nulls
                && listOfPersonsInModule.equals(((PersonIsInModulePredicate) other).listOfPersonsInModule));
        // state check
    }
}
