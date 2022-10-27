package seedu.pennywise.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.pennywise.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the PennyWise application.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(EntryType, String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag names should be alphanumeric";
    public static final String INCOME_CONSTRAINTS = "Income tag must only be one of the following: \n"
            + IncomeTag.getAllTags();
    public static final String EXPENDITURE_CONSTRAINTS = "Expenditure tag must only be one of the following: \n"
            + ExpenditureTag.getAllTags();
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    /**
     * Wrapper for tags.
     */
    public interface EntryTag {

    }

    /**
     * Income tags that can be used.
     */
    public enum IncomeTag implements EntryTag {
        SALARY,
        ALLOWANCE,
        PROFIT,
        INVESTMENT,
        GIFTS,
        OTHERS;

        /**
         * Checks that the input of {@code tag} is a valid income tag.
         */
        public static boolean isValid(String tag) {
            for (IncomeTag incomeTag: values()) {
                if (incomeTag.name().equalsIgnoreCase(tag)) {
                    return true;
                }
            }
            return false;
        }

        public static String getAllTags() {
            StringBuilder sb = new StringBuilder();
            for (IncomeTag incomeTag: values()) {
                sb.append(incomeTag.toString());
                if (!incomeTag.equals(IncomeTag.OTHERS)) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }


        @Override
        public String toString() {
            String name = this.name();
            String camelCaseName = name.substring(0, 1) + name.substring(1).toLowerCase();
            return camelCaseName;
        }

    }
    /**
     * Expenditure tags that can be used.
     */
    public enum ExpenditureTag implements EntryTag {
        FOOD,
        GROCERIES,
        ENTERTAINMENT,
        TRANSPORT,
        EDUCATION,
        HOUSING,
        OTHERS;
        /**
         * Checks that the input of {@code tag} is a valid expenditure tag.
         */
        public static boolean isValid(String tag) {
            for (ExpenditureTag expenditureTag: values()) {
                if (expenditureTag.name().equalsIgnoreCase(tag)) {
                    return true;
                }
            }
            return false;
        }

        public static String getAllTags() {
            StringBuilder sb = new StringBuilder();
            for (ExpenditureTag expenditureTag: values()) {
                sb.append(expenditureTag.toString());
                if (!expenditureTag.equals(ExpenditureTag.OTHERS)) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            String name = this.name();
            String camelCaseName = name.substring(0, 1) + name.substring(1).toLowerCase();
            return camelCaseName;
        }

    }
    private final EntryTag tag;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(EntryType type, String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(type, tagName), MESSAGE_CONSTRAINTS);
        switch (type.getEntryType()) {
        case INCOME:
            this.tag = IncomeTag.valueOf(tagName.toUpperCase());
            break;
        case EXPENDITURE:
            this.tag = ExpenditureTag.valueOf(tagName.toUpperCase());
            break;
        default:
            // Should not reach here
            this.tag = null;
        }
    }

    /**
     * Returns true if a given string is a valid tag name.
     *
     * @return True if a given string is a valid tag name.
     */
    public static boolean isValidTagName(EntryType type, String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }
        switch (type.getEntryType()) {
        case INCOME:
            if (!IncomeTag.isValid(test)) {
                return false;
            }
            break;
        case EXPENDITURE:
            if (!ExpenditureTag.isValid(test)) {
                return false;
            }
            break;
        default:
            return false;
        }
        return true;

    }

    /**
     * Returns the name of the tag.
     *
     * @return Name of the tag.
     */
    public String getTagName() {
        return tag.toString();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tag // instanceof handles nulls
            && tag.equals(((Tag) other).tag)); // state check
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

    /**
     * Formats the tag as text for viewing.
     *
     * @return Formatted tag.
     */
    public String toString() {
        return tag.toString();
    }

}
