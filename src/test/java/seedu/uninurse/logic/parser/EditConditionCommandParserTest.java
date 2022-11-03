package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.model.condition.Condition.MESSAGE_CONSTRAINTS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditConditionCommand;

/**
 * Contains unit tests for {@code EditConditionCommandParser}.
 */
public class EditConditionCommandParserTest {
    private final EditConditionCommandParser parser = new EditConditionCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        String args = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_CONDITION + TYPICAL_CONDITION_DIABETES;
        assertParseSuccess(parser, args,
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES));
        assertParseSuccess(parser, " " + args + " ",
                new EditConditionCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, CONDITION_DIABETES));

    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        String userInput = " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_CONDITION
                + TYPICAL_CONDITION_DIABETES;
        assertParseFailure(parser, "a" + userInput, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "0" + userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingPatientIndex_failure() {
        String userInput = INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_CONDITION + TYPICAL_CONDITION_DIABETES;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidConditionIndex_failure() {
        String userInput = " " + PREFIX_CONDITION + TYPICAL_CONDITION_DIABETES;
        assertParseFailure(parser, "1 a" + userInput, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 0" + userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingConditionIndex_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_CONDITION + TYPICAL_CONDITION_DIABETES;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyCondition_failure() {
        String userInput = "1 1 " + PREFIX_CONDITION;
        assertParseFailure(parser, userInput, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingConditionPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditConditionCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1 1" , expectedMessage);
    }
}
