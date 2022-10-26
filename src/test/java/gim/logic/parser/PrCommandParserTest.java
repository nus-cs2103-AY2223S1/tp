package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.commands.CommandTestUtil.DESC_PREFIX_ALL;
import static gim.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_ARM_CURLS;
import static gim.logic.commands.CommandTestUtil.NAME_DESC_BENCH_PRESS;
import static gim.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static gim.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static gim.logic.commands.CommandTestUtil.VALID_NAME_BENCH_PRESS;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static gim.testutil.TypicalExercises.ARM_CURLS;
import static gim.testutil.TypicalExercises.BENCH_PRESS;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gim.logic.commands.PrCommand;
import gim.model.exercise.Name;



public class PrCommandParserTest {
    private PrCommandParser parser = new PrCommandParser();
    private Set<Name> expectedNameSet;

    @BeforeEach
    public void setUp() {
        expectedNameSet = new HashSet<>();
    }

    @Test
    public void parse_oneNameFieldPresent_setHasOneName() {
        expectedNameSet.add(BENCH_PRESS.getName());

        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS, new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_oneNameFieldWithWhitespacePreamble_setOneName() {
        expectedNameSet.add(BENCH_PRESS.getName());

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BENCH_PRESS,
                new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_twoNameFieldsPresent_setHasBothNames() {
        expectedNameSet.add(ARM_CURLS.getName());
        expectedNameSet.add(BENCH_PRESS.getName());

        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + NAME_DESC_ARM_CURLS,
                new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_repeatedNameFieldsPresent_setOnlyHasOneName() {
        expectedNameSet.add(new Name(VALID_NAME_BENCH_PRESS));

        assertParseSuccess(parser, NAME_DESC_BENCH_PRESS + NAME_DESC_BENCH_PRESS,
                new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_oneAllFieldPresentNoMessage_setIsEmpty() {
        assertParseSuccess(parser, DESC_PREFIX_ALL, new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_oneAllFieldPresentWithMessage_setIsEmpty() {
        assertParseSuccess(parser, DESC_PREFIX_ALL + VALID_NAME_BENCH_PRESS, new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_oneAllFieldAndOneNameFieldPresent_setIsEmpty() {
        assertParseSuccess(parser, DESC_PREFIX_ALL + NAME_DESC_BENCH_PRESS, new PrCommand(expectedNameSet));
    }

    @Test
    public void parse_missingNamePrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_NAME_BENCH_PRESS, expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BENCH_PRESS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrCommand.MESSAGE_USAGE));
    }
}
