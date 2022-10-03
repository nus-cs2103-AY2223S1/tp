package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.moduleclass.ModuleClass;

/**
 * Tests that a {@code Person} is part of a class.
 */
public class IsPartOfClassPredicate implements Predicate<Person> {
    private final ModuleClass targetClass;


    /**
     * Returns a predicate that checks if a Person object is part of the {@code targetClass}.
     *
     * @param targetClass Class to check against the Person object.
     */
    public IsPartOfClassPredicate(ModuleClass targetClass) {
        requireNonNull(targetClass);
        this.targetClass = targetClass;
    }

    /**
     * Tests if a Person object is part of the target class.
     *
     * @param person The Person object to test.
     * @return True, if the Person object is part of the target class.
     */
    @Override
    public boolean test(Person person) {
        return person.getModuleClasses().stream().anyMatch(moduleClass -> moduleClass.equals(targetClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsPartOfClassPredicate // instanceof handles nulls
                && targetClass.equals(((IsPartOfClassPredicate) other).targetClass)); // state check
    }
}
