package nus.climods.model.module;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Tests that a {@code Module}'s {@code Module Code} matches any of the keywords given.
 */
public class CodeContainsKeywordsPredicate implements Predicate<Module> {

    private final Optional<String> facultyCode;

    /**
     * Makes a predicate testing {@code Module} for
     * @param facultyCode
     */
    public CodeContainsKeywordsPredicate(Optional<String> facultyCode) {
        this.facultyCode = facultyCode;
    }

    @Override
    public boolean test(Module module) {
        if (facultyCode.isPresent()) {
            Pattern facultyCodeRegex = Pattern.compile(String.format("(?i)%s\\d{4}$", facultyCode.get()));
            return facultyCodeRegex.matcher(module.getCode()).find();
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CodeContainsKeywordsPredicate // instanceof handles nulls
            && facultyCode.equals(((CodeContainsKeywordsPredicate) other).facultyCode)); // state check
    }

}
