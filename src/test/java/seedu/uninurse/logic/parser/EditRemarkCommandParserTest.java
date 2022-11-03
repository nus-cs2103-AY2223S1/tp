package seedu.uninurse.logic.parser;

import static seedu.uninurse.logic.commands.EditRemarkCommand.MESSAGE_NOT_EDITED;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.model.remark.Remark.MESSAGE_CONSTRAINTS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditRemarkCommand;
import seedu.uninurse.model.remark.Remark;

/**
 * Contains unit tests for {@code EditRemarkCommandParser}.
 */
class EditRemarkCommandParserTest {
    private static final String REMARK_STUB = "Some remark";

    private final EditRemarkCommandParser parser = new EditRemarkCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_patientIndexSpecifiedRemarkIndexSpecified_success() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_REMARK + REMARK_STUB;

        EditRemarkCommand expectedCommand =
                new EditRemarkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, new Remark(REMARK_STUB));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_patientIndexMissingRemarkIndexSpecified_failure() {
        String userInput = INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_REMARK + REMARK_STUB;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_patientIndexSpecifiedRemarkIndexMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + REMARK_STUB;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyRemarkEdit_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_REMARK;

        assertParseFailure(parser, userInput, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_remarkPrefixMissing_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " ";

        assertParseFailure(parser, userInput, MESSAGE_NOT_EDITED);
    }
}
