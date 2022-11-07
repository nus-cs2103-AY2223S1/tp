package seedu.address.model.attribute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.AccessDisplayFlags.DEFAULT;
import static seedu.address.model.AccessDisplayFlags.DEFAULT_STYLE;
import static seedu.address.model.AccessDisplayFlags.DISPLAY_OK;
import static seedu.address.model.AccessDisplayFlags.HIDE_TYPE;
import static seedu.address.model.AccessDisplayFlags.LEFT_JUSTIFY;
import static seedu.address.model.AccessDisplayFlags.MENU_OK;
import static seedu.address.model.AccessDisplayFlags.RIGHT_JUSTIFY;
import static seedu.address.model.attribute.AbstractAttribute.SAVE_KEY_DISPLAY_FORMAT;
import static seedu.address.model.attribute.AbstractAttribute.SAVE_KEY_STYLE_FORMAT;
import static seedu.address.model.attribute.AbstractAttribute.SAVE_KEY_TYPE_NAME;
import static seedu.address.model.attribute.AbstractAttribute.SAVE_KEY_VALUE;
import static seedu.address.testutil.TypicalAttributes.AGE;
import static seedu.address.testutil.TypicalAttributes.POSITION;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AbstractAttributeTest {

    @Test
    void constructor_validInputs_success() {
        Attribute<Integer> age = new AbstractAttribute<Integer>("Age", 5) { };
        assertNotNull(age);
    }

    @Test
    void constructor_nullTypeName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AbstractAttribute<Integer>(null, 5) { });
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
        assertEquals(AGE.getAttributeContent(), 20);
    }

    @Test
    void getAttributeType_success() {
        assertEquals(AGE.getAttributeType(), "Age");
    }

    @Test
    void isVisibleInMenu_hasMenuOkFlag_returnsTrue() {
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Telegram", "bunz", MENU_OK, DEFAULT_STYLE) { };
        assertTrue(attr.isVisibleInMenu());
    }

    @Test
    void isVisibleInMenu_doesNotHaveMenuOkFlag_returnsFalse() {
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Telegram", "bunz", DISPLAY_OK, DEFAULT_STYLE) { };
        assertFalse(attr.isVisibleInMenu());
    }

    @Test
    void isDisplayable_hasDisplayOkFlag_returnsTrue() {
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Telegram", "bunz", DISPLAY_OK, DEFAULT_STYLE) { };
        assertTrue(attr.isDisplayable());
    }

    @Test
    void isDisplayable_doesNotHaveDisplayOkFlag_returnsFalse() {
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Telegram", "bunz", HIDE_TYPE, DEFAULT_STYLE) { };
        assertFalse(attr.isDisplayable());
    }

    @Test
    void isSameType_sameTypeSameValue_returnsTrue() {
        assertTrue(AGE.isSameType(AGE));
        assertTrue(POSITION.isSameType(POSITION));
    }

    @Test
    void isSameType_sameTypeDifferentValue_returnsTrue() {
        assertTrue(AGE.isSameType(new AbstractAttribute<>("Age", 100) { }));
        assertTrue(POSITION.isSameType(new AbstractAttribute<>("Position", 100) { }));
    }

    @Test
    void isSameType_differentType_returnsFalse() {
        assertFalse(AGE.isSameType(new AbstractAttribute<>("Year", 100) { }));
        assertFalse(POSITION.isSameType(new AbstractAttribute<>("Rank", "CEO") { }));
    }

    @Test
    void equals_instanceOfAbstractAttribute() {
        // Same object -> return true
        assertTrue(AGE.equals(AGE));
        assertTrue(POSITION.equals(POSITION));

        // Same type same value -> return true
        assertTrue(AGE.equals(new AbstractAttribute<>("Age", 20) { }));
        assertTrue(POSITION.equals(new AbstractAttribute<>("Position", "CEO") { }));

        // Same type Different Value -> return false
        assertFalse(AGE.equals(new AbstractAttribute<>("Age", 100) { }));
        assertFalse(POSITION.equals(new AbstractAttribute<>("Position", "President") { }));

        // Different type same value -> return false
        assertFalse(AGE.equals(new AbstractAttribute<>("Year", 20) { }));
        assertFalse(POSITION.equals(new AbstractAttribute<>("Rank", "CEO") { }));
    }

    @Test
    void equals_notAbstractAttributeInstance_returnsFalse() {
        assertFalse(AGE.equals(5));
    }

    @Test
    void toString_noHideType_success() {
        assertEquals(POSITION.toString(), "Position: CEO");
    }

    @Test
    void toString_hideType_success() {
        Attribute<String> attr =
                new AbstractAttribute<String>("String", "value", HIDE_TYPE, DEFAULT_STYLE) { };
        assertEquals(attr.toString(), "value");
    }

    @Test
    void toString_nullValue_success() {
        Attribute<String> attr = new AbstractAttribute<String>("String", null) { };
        assertEquals(attr.toString(), "");
    }

    @Test
    void hashCode_success() {
        assertEquals(AGE.hashCode(), 66036);
        assertEquals(POSITION.hashCode(), 812513371);
    }

    @Test
    void toSaveableData_success() {
        Map<String, Object> toMatch = new HashMap<>();
        toMatch.put(SAVE_KEY_TYPE_NAME, "Age");
        toMatch.put(SAVE_KEY_VALUE, 20);
        toMatch.put(SAVE_KEY_DISPLAY_FORMAT, DEFAULT);
        toMatch.put(SAVE_KEY_STYLE_FORMAT, DEFAULT_STYLE);
        assertEquals(AGE.toSaveableData(), toMatch);
    }

    @Test
    void getFormatCss_inMenu_success() {
        // Font Size Normal, Left-justify
        AbstractAttribute<?> attribute = (AbstractAttribute<?>) POSITION;
        assertEquals(attribute.getFormatCss(true),
                "-fx-font: normal 10.000000pt 'Segoe UI'; -fx-text-alignment: left;");
    }

    @Test
    void getFormatCss_notInMenu_success() {
        // Bold, Underline, Dropshadow, Center-Justify, Font size Big
        AbstractAttribute<String> attr =
                new AbstractAttribute<>("Position", "Director", DEFAULT, 0b01001010101) { };
        assertEquals(attr.getFormatCss(false),
                "-fx-font: normal bold 32.000000pt 'Segoe UI'; -fx-underline: true; -fx-effect: dropshadow(three-pass"
                        + "-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0); -fx-text-alignment: center;");
    }

    @Test
    void testGetFormatCss() {
        AbstractAttribute<?> attribute = (AbstractAttribute<?>) POSITION;
        assertEquals(attribute.getFormatCss(true), attribute.getFormatCss());
    }
}
