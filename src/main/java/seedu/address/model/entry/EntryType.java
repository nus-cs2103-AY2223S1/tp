package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Entry's type in the penny wise application.
 * Guarantees: immutable; is valid as declared in {@link #isValidEntryType(String)}}
 */
public class EntryType {
    public static final String ENTRY_TYPE_EXPENDITURE = "e";
    public static final String ENTRY_TYPE_INCOME = "i";

    public static final String MESSAGE_CONSTRAINTS = "Entry type should only be either '"
            + ENTRY_TYPE_EXPENDITURE
            + "' for expenditure or '"
            + ENTRY_TYPE_INCOME
            + "' for income";
    public static final String VALIDATION_REGEX = "^\\s*([ei])\\s*$";
    /**
     * Types that can be used
     */
    public enum Type {
        EXPENDITURE() {
            @Override
            public String toString() {
                return ENTRY_TYPE_EXPENDITURE;
            }
        },
        INCOME() {
            @Override
            public String toString() {
                return ENTRY_TYPE_INCOME;
            }
        };

        /**
         * Factory method that creates a returns a {@code Type} with the input of {@code entryType}
         */
        public static Type of(String entryType) {
            boolean isExpenditureEntry = entryType.equals(ENTRY_TYPE_EXPENDITURE);
            boolean isIncomeEntry = entryType.equals(ENTRY_TYPE_INCOME);

            assert isExpenditureEntry || isIncomeEntry;

            if (isExpenditureEntry) {
                return Type.EXPENDITURE;
            }
            return Type.INCOME;
        }
    };

    private final Type entryType;

    /**
     * Constructs a {@code EntryType}.
     *
     * @param entryType A valid entry type.
     */
    public EntryType(String entryType) {
        requireNonNull(entryType);
        checkArgument(isValidEntryType(entryType), MESSAGE_CONSTRAINTS);
        this.entryType = Type.of(entryType);
    }

    /**
     * Returns true if a given string is a valid entry type.
     */
    public static boolean isValidEntryType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Type getEntryType() {
        return entryType;
    }

    @Override
    public String toString() {
        return entryType.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EntryType // instanceof handles nulls
                && entryType.equals(((EntryType) other).entryType)); // state check
    }

    @Override
    public int hashCode() {
        return entryType.hashCode();
    }
}
