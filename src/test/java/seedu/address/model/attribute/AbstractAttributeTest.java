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

class AbstractAttributeTest {

    private final Attribute<String> stringAttribute = new AbstractAttribute<String>("Stringtest", "test") { };
    private final Attribute<Integer> integerAttribute =
            new AbstractAttribute<>("Integer", 1, DEFAULT, DEFAULT_STYLE) { };


    @Test
    void constructor_validInputs_success() {
        Attribute<Integer> integerAttribute = new AbstractAttribute<Integer>("Integer", 5) { };
        assertNotNull(integerAttribute);
    }

    @Test
    void constructor_nullTypeName_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> new AbstractAttribute<Integer>(null, 5) { });
    }

    @Test
    void isNameMatch_sameCase_returnsTrue() {
        assertTrue(stringAttribute.isNameMatch("Stringtest"));
    }

    @Test
    void isNameMatch_differentCase_returnsTrue() {
        assertTrue(stringAttribute.isNameMatch("sTRiNgTEsT"));
    }

    @Test
    void isNameMatch_differentString_returnsFalse() {
        assertFalse(stringAttribute.isNameMatch("StringTestt"));
    }

    @Test
    void isAllFlagMatch_containsFlag_returnsTrue() {
        assertTrue(integerAttribute.isAllFlagMatch(DEFAULT));
    }

    @Test
    void isAllFlagMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(integerAttribute.isAllFlagMatch(HIDE_TYPE));
    }

    @Test
    void isAnyFlagMatch_containsFlag_returnsTrue() {
        assertTrue(integerAttribute.isAnyFlagMatch(DEFAULT));
    }

    @Test
    void isAnyFlagMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(integerAttribute.isAnyFlagMatch(HIDE_TYPE));
    }

    @Test
    void isAnyStyleMatch_containsFlag_returnsTrue() {
        assertTrue(integerAttribute.isAnyStyleMatch(LEFT_JUSTIFY));
    }

    @Test
    void isAnyStyleMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(integerAttribute.isAnyStyleMatch(RIGHT_JUSTIFY));
    }

    @Test
    void isAllStyleMatch_containsFlag_returnsTrue() {
        assertTrue(integerAttribute.isAllStyleMatch(DEFAULT_STYLE));
    }

    @Test
    void isAllStyleMatch_doesNotContainFlag_returnsFalse() {
        assertFalse(integerAttribute.isAllStyleMatch(RIGHT_JUSTIFY));
    }

    @Test
    void getAttributeContent() {
    }

    @Test
    void getAttributeType() {
    }

    @Test
    void isVisibleInMenu() {
    }

    @Test
    void isDisplayable() {
    }

    @Test
    void isSameType() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void toString_noHideType_success() {
        assertEquals(stringAttribute.toString(), "Stringtest: test");
    }

    @Test
    void toString_hideType_success() {
        Attribute<String> stringAttribute =
                new AbstractAttribute<String>("String", "value", HIDE_TYPE, DEFAULT_STYLE) { };
        assertEquals(stringAttribute.toString(), "value");
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