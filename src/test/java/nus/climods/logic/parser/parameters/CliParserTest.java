package nus.climods.logic.parser.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.jupiter.api.Test;

import nus.climods.logic.parser.ParserUtil;

/**
 * Includes test for RelaxedParser, and DefaultParser as well to verify expected behaviour
 */
public class CliParserTest {

    private static final String LAB_SHORT = "l";
    private static final String LAB_LONG = "lab";
    private static final String TUT_SHORT = "t";
    private static final String TUT_LONG = "tut";
    private static final Option LAB_OPTION = Option.builder().option(LAB_SHORT).longOpt(LAB_LONG).build();
    private static final Option TUTORIAL_OPTION = Option.builder().option(TUT_SHORT).longOpt(TUT_LONG).build();

    private static CommandLine defaultParse(String argsString) throws org.apache.commons.cli.ParseException {
        Options options = new Options();
        options.addOption(LAB_OPTION);
        options.addOption(TUTORIAL_OPTION);
        CommandLineParser defaultParser = new DefaultParser();
        return defaultParser.parse(options, ParserUtil.convertArgumentStringToArray(argsString));
    }

    private static CommandLine relaxedParse(String argsString) throws org.apache.commons.cli.ParseException {
        Options options = new Options();
        options.addOption(LAB_OPTION);
        options.addOption(TUTORIAL_OPTION);
        CommandLineParser defaultParser = new RelaxedParser();
        return defaultParser.parse(options, ParserUtil.convertArgumentStringToArray(argsString));
    }

    private static void hasLab(CommandLine cmd) {
        assertTrue(cmd.hasOption(LAB_OPTION));
        assertTrue(cmd.hasOption(LAB_SHORT));
        assertTrue(cmd.hasOption(LAB_LONG));

    }

    private static void noLab(CommandLine cmd) {
        assertFalse(cmd.hasOption(LAB_OPTION));
        assertFalse(cmd.hasOption(LAB_SHORT));
        assertFalse(cmd.hasOption(LAB_LONG));

    }

    private static void hasTutorial(CommandLine cmd) {
        assertTrue(cmd.hasOption(TUTORIAL_OPTION));
        assertTrue(cmd.hasOption(TUT_SHORT));
        assertTrue(cmd.hasOption(TUT_LONG));
    }

    private static void noTutorial(CommandLine cmd) {
        assertFalse(cmd.hasOption(TUTORIAL_OPTION));
        assertFalse(cmd.hasOption(TUT_SHORT));
        assertFalse(cmd.hasOption(TUT_LONG));
    }

    // Relaxed parse ignores unrecognised options
    @Test
    public void relaxedParseMultipleArguments_unrecognisedOptionStart_hasOptions() {
        String argsString = "-gibberish --lab --tut ";
        try {
            CommandLine cmd = relaxedParse(argsString);
            hasLab(cmd);
            hasTutorial(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            fail();
        }
    }

    @Test
    public void relaxedParseMultipleArguments_unrecognisedOptionMiddle_hasOptions() {
        String argsString = "--lab -gibberish --tut ";
        try {
            CommandLine cmd = relaxedParse(argsString);
            hasLab(cmd);
            hasTutorial(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            fail();
        }
    }

    @Test
    public void relaxedParseMultipleArguments_unrecognisedOptionEnd_hasOptions() {
        String argsString = "--lab --tut -gibberish";
        try {
            CommandLine cmd = relaxedParse(argsString);
            hasLab(cmd);
            hasTutorial(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            fail();
        }
    }

    @Test
    public void relaxedParseMultipleArguments_bothLongOption_hasBothOptions() {
        String argsString = "--lab --tut";
        try {
            CommandLine cmd = relaxedParse(argsString);
            hasLab(cmd);
            hasTutorial(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void relaxedParseMultipleArguments_oneShortOption_hasBothOptions() {
        String argsString = "--t -lab";
        try {
            CommandLine cmd = relaxedParse(argsString);
            hasLab(cmd);
            hasTutorial(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void relaxedParseMultipleArguments_oneMissing_hasOnlyOne() {
        String argsString = "-lab";
        try {
            CommandLine cmd = relaxedParse(argsString);
            hasLab(cmd);
            noTutorial(cmd);

        } catch (org.apache.commons.cli.ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void relaxedParseMultipleArguments_bothMissing_hasNone() {
        String argsString = "gibberish";
        try {
            CommandLine cmd = relaxedParse(argsString);
            noLab(cmd);
            noTutorial(cmd);
        } catch (org.apache.commons.cli.ParseException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * IMPORTANT: Even if recognised options exist, DefaultParser throws ParseException if any unrecognised option is
     * there
     */
    @Test
    public void defaultParseMultipleArguments_unrecognisedOptionStart_throwsParseException() {
        String argsString = "-gibberish --lab --tut ";
        try {
            defaultParse(argsString);
            fail();
        } catch (org.apache.commons.cli.ParseException e) {
            assertEquals("Unrecognized option: -gibberish", (e.getMessage()));
        }
    }

    @Test
    public void defaultParseMultipleArguments_unrecognisedOptionMiddle_throwsParseException() {
        String argsString = "--lab -gibberish --tut ";
        try {
            defaultParse(argsString);
            fail();
        } catch (org.apache.commons.cli.ParseException e) {
            assertEquals("Unrecognized option: -gibberish", e.getMessage());
        }
    }

    @Test
    public void defaultParseMultipleArguments_unrecognisedOptionEnd_throwsParseException() {
        String argsString = "--lab --tut -gibberish";
        try {
            defaultParse(argsString);
            fail();
        } catch (org.apache.commons.cli.ParseException e) {
            assertEquals("Unrecognized option: -gibberish", e.getMessage());
        }
    }
}
