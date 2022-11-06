package seedu.address.model.attribute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.model.AccessDisplayFlags.BOLD;
import static seedu.address.model.AccessDisplayFlags.CENTER_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.DEFAULT;
import static seedu.address.model.AccessDisplayFlags.DEFAULT_STYLE;
import static seedu.address.model.AccessDisplayFlags.DISPLAY_OK;
import static seedu.address.model.AccessDisplayFlags.DROPSHADOW;
import static seedu.address.model.AccessDisplayFlags.FONT_SIZE_BIG;
import static seedu.address.model.AccessDisplayFlags.FONT_SIZE_NORMAL;
import static seedu.address.model.AccessDisplayFlags.FONT_SIZE_SMALL;
import static seedu.address.model.AccessDisplayFlags.HIDE_TYPE;
import static seedu.address.model.AccessDisplayFlags.ITALIC;
import static seedu.address.model.AccessDisplayFlags.LEFT_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.MENU_OK;
import static seedu.address.model.AccessDisplayFlags.PERSON;
import static seedu.address.model.AccessDisplayFlags.RIGHT_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.STRIKETHROUGH;
import static seedu.address.model.AccessDisplayFlags.UNDERLINE;
import static seedu.address.testutil.TypicalAttributes.AGE;
import static seedu.address.testutil.TypicalAttributes.POSITION;

class AbstractAttributeTest {

    @Test
    void constructor_validInputs_success() {
        Attribute<Integer> AGE = new AbstractAttribute<Integer>("Age", 5) { };
        assertNotNull(AGE);
    }

    @Test
    void constructor_nullTypeName_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new AbstractAttribute<Integer>(null, 5) { });
    }

    @Test
    void isNameMatch_sameCase_returnsTrue() {
        assertTrue(POSITION.isNameMatch("Position"));
    }

    @Test
    void isNameMatch_differentCase_returnsTrue() {
        assertTrue(POSITION.isNameMatch("poSItION"));
    }

    @Test
    void isNameMatch_differentString_returnsFalse() {
        assertFalse(POSITION.isNameMatch("Positions"));
    }

    @Test
    void isAllFlagMatch_containsFlag_returnsTrue() {
        assertTrue(AGE.isAllFlagMatch(DEFAULT));
    }

    @Test
    void isAllFlagMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(AGE.isAllFlagMatch(HIDE_TYPE));
    }

    @Test
    void isAnyFlagMatch_containsFlag_returnsTrue() {
        assertTrue(AGE.isAnyFlagMatch(DEFAULT));
    }

    @Test
    void isAnyFlagMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(AGE.isAnyFlagMatch(HIDE_TYPE));
    }

    @Test
    void isAnyStyleMatch_containsFlag_returnsTrue() {
        assertTrue(AGE.isAnyStyleMatch(LEFT_JUSTIFY));
    }

    @Test
    void isAnyStyleMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(AGE.isAnyStyleMatch(RIGHT_JUSTIFY));
    }

    @Test
    void isAllStyleMatch_containsFlag_returnsTrue() {
        assertTrue(AGE.isAllStyleMatch(DEFAULT_STYLE));
    }

    @Test
    void isAllStyleMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(AGE.isAllStyleMatch(RIGHT_JUSTIFY));
    }

    @Test
    void getAttributeContent_success() {
        assertEquals(AGE.getAttributeContent(), 1);
    }

    @Test
    void getAttributeType_success() {
        assertEquals(AGE.getAttributeType(), "Integer");
    }

    @Test
    void isVisibleInMenu_hasMenuOkFlag_returnsTrue() {
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Telegram", "bunz", MENU_OK, DEFAULT_STYLE) { };
        assertTrue(attr.isVisibleInMenu());
    }

    @Test
    void isVisibleInMenu_doesNotHaveMenuOkFlag_returnsFalse() {
        assertFalse(POSITION.isVisibleInMenu());
    }

    @Test
    void isDisplayable_hasDisplayOkFlag_returnsTrue() {
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Telegram", "bunz", DISPLAY_OK, DEFAULT_STYLE) { };
        assertTrue(attr.isDisplayable());
    }

    @Test
    void isDisplayable_doesNotHaveDisplayOkFlag_returnsFalse() {
        assertFalse(POSITION.isDisplayable());
    }

    @Test
    void isSameType() {
        
    }

    @Test
    void testEquals() {
    }

    @Test
    void toString_noHideType_success() {
        assertEquals(POSITION.toString(), "Stringtest: test");
    }

    @Test
    void toString_hideType_success() {
        Attribute<String> POSITION =
                new AbstractAttribute<String>("String", "value", HIDE_TYPE, DEFAULT_STYLE) { };
        assertEquals(POSITION.toString(), "value");
    }

    @Test
    void getJavaFxRepresentation() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void toSaveableData() {
    }

    @Test
    void getFormatCss() {
    }

    @Test
    void testGetFormatCss() {
    }
}