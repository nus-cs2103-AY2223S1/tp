package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ModuleCodeTest {

    @Test
    void isValidModuleCode() {
        // invalid whitespace
        assertFalse(ModuleCode.isValidModuleCode(""));
        assertFalse(ModuleCode.isValidModuleCode("     "));
        assertFalse(ModuleCode.isValidModuleCode("      "));

        // invalid characters for first 2-3 characters
        assertFalse(ModuleCode.isValidModuleCode("  1234"));
        assertFalse(ModuleCode.isValidModuleCode("  1234S"));
        assertFalse(ModuleCode.isValidModuleCode("   1234"));
        assertFalse(ModuleCode.isValidModuleCode("   1234S"));
        assertFalse(ModuleCode.isValidModuleCode("!A1234"));
        assertFalse(ModuleCode.isValidModuleCode("@A1234"));
        assertFalse(ModuleCode.isValidModuleCode("#A1234"));
        assertFalse(ModuleCode.isValidModuleCode("$A1234"));
        assertFalse(ModuleCode.isValidModuleCode("%A1234"));
        assertFalse(ModuleCode.isValidModuleCode("^A1234"));
        assertFalse(ModuleCode.isValidModuleCode("&A1234"));
        assertFalse(ModuleCode.isValidModuleCode("*A1234"));
        assertFalse(ModuleCode.isValidModuleCode("(A1234"));
        assertFalse(ModuleCode.isValidModuleCode(")A1234"));
        assertFalse(ModuleCode.isValidModuleCode("-A1234"));
        assertFalse(ModuleCode.isValidModuleCode("_A1234"));
        assertFalse(ModuleCode.isValidModuleCode("=A1234"));
        assertFalse(ModuleCode.isValidModuleCode("+A1234"));
        assertFalse(ModuleCode.isValidModuleCode("[A1234"));
        assertFalse(ModuleCode.isValidModuleCode("{A1234"));
        assertFalse(ModuleCode.isValidModuleCode("}A1234"));
        assertFalse(ModuleCode.isValidModuleCode("]A1234"));
        assertFalse(ModuleCode.isValidModuleCode("\\A1234"));
        assertFalse(ModuleCode.isValidModuleCode("|A1234"));
        assertFalse(ModuleCode.isValidModuleCode(";A1234"));
        assertFalse(ModuleCode.isValidModuleCode(":A1234"));
        assertFalse(ModuleCode.isValidModuleCode("'A1234"));
        assertFalse(ModuleCode.isValidModuleCode("\"A1234"));
        assertFalse(ModuleCode.isValidModuleCode(",A1234"));
        assertFalse(ModuleCode.isValidModuleCode("<A1234"));
        assertFalse(ModuleCode.isValidModuleCode(".A1234"));
        assertFalse(ModuleCode.isValidModuleCode("?A1234"));
        assertFalse(ModuleCode.isValidModuleCode(">A1234"));
        assertFalse(ModuleCode.isValidModuleCode(".A1234"));
        assertFalse(ModuleCode.isValidModuleCode("/A1234"));



        // invalid whitespace for 4 digits
        assertFalse(ModuleCode.isValidModuleCode("AA    "));
        assertFalse(ModuleCode.isValidModuleCode("AA    S"));
        assertFalse(ModuleCode.isValidModuleCode("AAA    "));
        assertFalse(ModuleCode.isValidModuleCode("AAA    S"));

        // invalid characters for optional last character
        assertFalse(ModuleCode.isValidModuleCode("AA1234 "));
        assertFalse(ModuleCode.isValidModuleCode("AA1234!"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234@"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234#"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234$"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234%"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234^"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234&"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234*"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234("));
        assertFalse(ModuleCode.isValidModuleCode("AA1234)"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234-"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234_"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234+"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234="));
        assertFalse(ModuleCode.isValidModuleCode("AA1234`"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234~"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234["));
        assertFalse(ModuleCode.isValidModuleCode("AA1234{"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234]"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234}"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234\\"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234|"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234:"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234;"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234'"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234\""));
        assertFalse(ModuleCode.isValidModuleCode("AA1234,"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234<"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234."));
        assertFalse(ModuleCode.isValidModuleCode("AA1234>"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234?"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234/"));

        // invalid
        assertFalse(ModuleCode.isValidModuleCode("A1234"));
        assertFalse(ModuleCode.isValidModuleCode("AA123"));
        assertFalse(ModuleCode.isValidModuleCode("AA1234 "));
        assertFalse(ModuleCode.isValidModuleCode("A1234"));
        assertFalse(ModuleCode.isValidModuleCode("A1234S"));
        assertFalse(ModuleCode.isValidModuleCode("AAA123"));
        assertFalse(ModuleCode.isValidModuleCode("AA12345"));
        assertFalse(ModuleCode.isValidModuleCode("AA12345S"));
        assertFalse(ModuleCode.isValidModuleCode("AAA12345"));
        assertFalse(ModuleCode.isValidModuleCode("AAA12345S"));

        // valid
        assertTrue(ModuleCode.isValidModuleCode("AA1234"));
        assertTrue(ModuleCode.isValidModuleCode("AA1234S"));
        assertTrue(ModuleCode.isValidModuleCode("AAA1234"));
        assertTrue(ModuleCode.isValidModuleCode("AA1234S"));
        assertTrue(ModuleCode.isValidModuleCode("aa1234"));
        assertTrue(ModuleCode.isValidModuleCode("aa1234s"));
    }

    @Test
    void getLevel() {
        ModuleCode test = new ModuleCode("AA1234S");
        int expected = 1234;
        assertEquals(expected, test.getLevel());
    }

    @Test
    void getPrefix() {
        ModuleCode test = new ModuleCode("AA1234S");
        String expected = "AAS";
        assertEquals(expected, test.getPrefix());
    }

    @Test
    void compareTo() {
        ModuleCode lowerLevel = new ModuleCode("AA1000");
        ModuleCode higherLevel = new ModuleCode("AA2000");
        ModuleCode lowerPrefix = new ModuleCode("AA0000");
        ModuleCode higherPrefix = new ModuleCode("BB0000");
        assertEquals(-1, lowerLevel.compareTo(higherLevel));
        assertEquals(1, higherLevel.compareTo(lowerLevel));
        assertEquals(-1, lowerPrefix.compareTo(higherPrefix));
        assertEquals(1, higherPrefix.compareTo(lowerPrefix));
        assertEquals(0, lowerLevel.compareTo(lowerLevel));
        assertEquals(0, higherLevel.compareTo(higherLevel));
        assertEquals(0, lowerPrefix.compareTo(lowerPrefix));
        assertEquals(0, higherPrefix.compareTo(higherPrefix));
    }
}
