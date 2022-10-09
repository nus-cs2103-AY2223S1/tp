package nus.climods.logic.parser.parameters.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nus.climods.logic.parser.exceptions.ParseException;

public class OptionalArgumentsParserTest {
    private static final String LAB_SHORT = "l";
    private static final String LAB_LONG = "lab";
    private static final String TUT_SHORT = "t";
    private static final String TUT_LONG = "tut";
    private OptionalArgument lab = new OptionalArgument(LAB_SHORT, LAB_LONG);
    private OptionalArgument tutorial = new OptionalArgument(TUT_SHORT, TUT_LONG);

    /**
     * IMPORTANT: Even if recognised options exist, current implementation throws ParseException
     * if any unrecognised option is there
     */
    @Test
    public void parseMultipleArguments_unrecognisedOption_throwsParseException() {
        String argsString = "--lab --tut -gibberish";
        try {
            OptionalArgumentsParser oap = new OptionalArgumentsParser(argsString, lab, tutorial);
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Unrecognized option: -gibberish");
        }
    }

    @Test
    public void parseMultipleArguments_bothLongOption_hasBothOptions() {
        String argsString = "--lab --tut";
        try {
            OptionalArgumentsParser oap = new OptionalArgumentsParser(argsString, lab, tutorial);
            assertTrue(oap.hasOption(lab));
            assertTrue(oap.hasOption(tutorial));
            assertTrue(oap.hasOption(LAB_SHORT));
            assertTrue(oap.hasOption(LAB_LONG));
            assertTrue(oap.hasOption(TUT_SHORT));
            assertTrue(oap.hasOption(TUT_LONG));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseMultipleArguments_bothShortOption_hasBothOptions() {
        String argsString = "-l --t";
        try {
            OptionalArgumentsParser oap = new OptionalArgumentsParser(argsString, lab, tutorial);
            assertTrue(oap.hasOption(lab));
            assertTrue(oap.hasOption(tutorial));
            assertTrue(oap.hasOption(LAB_SHORT));
            assertTrue(oap.hasOption(LAB_LONG));
            assertTrue(oap.hasOption(TUT_SHORT));
            assertTrue(oap.hasOption(TUT_LONG));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseMultipleArguments_oneShortOption_hasBothOptions() {
        String argsString = "--t -lab";
        try {
            OptionalArgumentsParser oap = new OptionalArgumentsParser(argsString, lab, tutorial);
            assertTrue(oap.hasOption(lab));
            assertTrue(oap.hasOption(tutorial));
            assertTrue(oap.hasOption(LAB_SHORT));
            assertTrue(oap.hasOption(LAB_LONG));
            assertTrue(oap.hasOption(TUT_SHORT));
            assertTrue(oap.hasOption(TUT_LONG));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseMultipleArguments_oneMissing_hasOnlyOne() {
        String argsString = "-lab";
        try {
            OptionalArgumentsParser oap = new OptionalArgumentsParser(argsString, lab, tutorial);
            assertTrue(oap.hasOption(lab));
            assertTrue(oap.hasOption(LAB_SHORT));
            assertTrue(oap.hasOption(LAB_LONG));

            assertFalse(oap.hasOption(tutorial));
            assertFalse(oap.hasOption(TUT_SHORT));
            assertFalse(oap.hasOption(TUT_LONG));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseMultipleArguments_bothMissing_hasNone() {
        String argsString = "gibberish";
        try {
            OptionalArgumentsParser oap = new OptionalArgumentsParser(argsString, lab, tutorial);
            assertFalse(oap.hasOption(lab));
            assertFalse(oap.hasOption(LAB_SHORT));
            assertFalse(oap.hasOption(LAB_LONG));

            assertFalse(oap.hasOption(tutorial));
            assertFalse(oap.hasOption(TUT_SHORT));
            assertFalse(oap.hasOption(TUT_LONG));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
