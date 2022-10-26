package paymelah.logic.parser;

import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.logic.commands.CommandTestUtil.INVALID_DEBT_INDEX;
import static paymelah.logic.commands.CommandTestUtil.INVALID_DEBT_INDEX_ZERO;
import static paymelah.logic.commands.CommandTestUtil.INVALID_DEBT_INDICES;
import static paymelah.logic.commands.CommandTestUtil.VALID_DEBT_INDEX;
import static paymelah.logic.commands.CommandTestUtil.VALID_DEBT_INDICES;
import static paymelah.logic.commands.CommandTestUtil.VALID_DEBT_INDICES_REPEAT;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseFailure;
import static paymelah.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_DEBT;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_DEBT;
import static paymelah.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_DEBT;
import static paymelah.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import paymelah.commons.core.index.Index;
import paymelah.logic.commands.MarkCommand;

public class MarkCommandParserTest {

    //private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no person index specified
        assertParseFailure(parser, VALID_DEBT_INDEX, MESSAGE_INVALID_FORMAT);

        // no debt specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no person and no debts specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative person index
        assertParseFailure(parser, "-5" + VALID_DEBT_INDEX, MESSAGE_INVALID_FORMAT);

        // zero person index
        assertParseFailure(parser, "0" + VALID_DEBT_INDEX, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + VALID_DEBT_INDEX, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string" + VALID_DEBT_INDEX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DEBT_INDEX, MESSAGE_INVALID_FORMAT); // non-index
        assertParseFailure(parser, "1" + INVALID_DEBT_INDEX_ZERO, MESSAGE_INVALID_FORMAT); // zero index
        assertParseFailure(parser, "1" + INVALID_DEBT_INDICES, MESSAGE_INVALID_FORMAT); // multiple spacing
    }

    @Test
    public void parse_oneDebt_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_DEBT_INDEX;

        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        MarkCommand expectedCommand = new MarkCommand(targetIndex, debtIndexSet);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleDebt_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_DEBT_INDICES;

        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT, INDEX_SECOND_DEBT, INDEX_THIRD_DEBT));
        MarkCommand expectedCommand = new MarkCommand(targetIndex, debtIndexSet);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleDebtWithRepeat_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_DEBT_INDICES_REPEAT;

        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT, INDEX_THIRD_DEBT));
        MarkCommand expectedCommand = new MarkCommand(targetIndex, debtIndexSet);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_DEBT_INDEX + VALID_DEBT_INDEX;

        Set<Index> debtIndexSet = new HashSet<>(List.of(INDEX_FIRST_DEBT));
        MarkCommand expectedCommand = new MarkCommand(targetIndex, debtIndexSet);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
