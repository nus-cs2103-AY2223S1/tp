package seedu.address.model.tag;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ALLOWANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.entry.EntryType;

public class TagTest {
    private EntryType expenditureType = new EntryType(EntryType.ENTRY_TYPE_EXPENDITURE);
    private EntryType incomeType = new EntryType(EntryType.ENTRY_TYPE_INCOME);
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(expenditureType, null));
        assertThrows(NullPointerException.class, () -> new Tag(incomeType, null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        String invalidIncomeTag = VALID_TAG_LUNCH;
        String invalidExpenditureTag = VALID_TAG_ALLOWANCE;
        assertThrows(IllegalArgumentException.class, () -> new Tag(expenditureType, invalidTagName));
        assertThrows(IllegalArgumentException.class, () -> new Tag(expenditureType, invalidExpenditureTag));

        assertThrows(IllegalArgumentException.class, () -> new Tag(incomeType, invalidTagName));
        assertThrows(IllegalArgumentException.class, () -> new Tag(incomeType, invalidIncomeTag));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(expenditureType, null));
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(incomeType, null));

    }

}
