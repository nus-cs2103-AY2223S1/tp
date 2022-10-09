package nus.climods.logic.parser.parameters.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;



import org.junit.jupiter.api.Test;

import nus.climods.logic.parser.exceptions.ParseException;



public class UserFlagParserTest {
    /**
     * IMPORTANT: throws ParseException if there is any unrecognised option, even if a recognised option
     * exists
     */
    @Test
    public void parseUserFlag_unrecognisedOption_throwsParseException() {
        String argsString = "CS --user -gibberish";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            fail();
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Unrecognized option: -gibberish");
        }
    }

    /**
     * Once either short or long option are matched, either String or the respective OptionalArgument object itself
     * can be used to check presence
     */
    @Test
    public void parseUserFlag_inputShortOptionDoubleDash_hasOption() {
        String argsString = "CS --u";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertTrue(ufp.hasOption(UserFlag.LONG_OPTION));
            assertTrue(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertTrue(ufp.hasOption(new UserFlag()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Once either short or long option are matched, either String or the respective OptionalArgument object itself
     * can be used to check presence
     */
    @Test
    public void parseUserFlag_inputLongOptionDoubleDash_hasOption() {
        String argsString = "CS --user";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertTrue(ufp.hasOption(UserFlag.LONG_OPTION));
            assertTrue(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertTrue(ufp.hasOption(new UserFlag()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Once either short or long option are matched, either String or the respective OptionalArgument object itself
     * can be used to check presence
     */
    @Test
    public void parseUserFlag_inputShortOptionOneDash_hasOption() {
        String argsString = "CS -u";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertTrue(ufp.hasOption(UserFlag.LONG_OPTION));
            assertTrue(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertTrue(ufp.hasOption(new UserFlag()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Once either short or long option are matched, either String or the respective OptionalArgument object itself
     * can be used to check presence
     */
    @Test
    public void parseUserFlag_inputLongOptionOneDash_hasOption() {
        String argsString = "CS -user";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertTrue(ufp.hasOption(UserFlag.LONG_OPTION));
            assertTrue(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertTrue(ufp.hasOption(new UserFlag()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Doesn't match if no prefix
     */
    @Test
    public void parseUserFlag_inputNoDashLongOption_hasOptionReturnsFalse() {
        String argsString = "CS user";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertFalse(ufp.hasOption(UserFlag.LONG_OPTION));
            assertFalse(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertFalse(ufp.hasOption(new UserFlag()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Doesn't match if no prefix
     */
    @Test
    public void parseUserFlag_inputNoDashShortOption_hasOptionReturnsFalse() {
        String argsString = "CS u";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertFalse(ufp.hasOption(UserFlag.LONG_OPTION));
            assertFalse(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertFalse(ufp.hasOption(new UserFlag()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Doesn't match if no prefix
     */
    @Test
    public void parseUserFlag_emptyString_hasOptionReturnsFalse() {
        String argsString = " ";
        try {
            UserFlagParser ufp = new UserFlagParser(argsString);
            assertFalse(ufp.hasOption(UserFlag.LONG_OPTION));
            assertFalse(ufp.hasOption(UserFlag.SHORT_OPTION));
            assertFalse(ufp.hasOption(new UserFlag()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
