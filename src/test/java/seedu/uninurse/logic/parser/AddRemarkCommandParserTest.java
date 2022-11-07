package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.model.remark.Remark.MESSAGE_CONSTRAINTS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalRemarks.REMARK_MEDICAL_ALLERGY;
import static seedu.uninurse.testutil.TypicalRemarks.TYPICAL_REMARK_MEDICAL_ALLERGY;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.AddRemarkCommand;

/**
 * Contains unit tests for {@code AddRemarkCommandParser}.
 */
public class AddRemarkCommandParserTest {

    private final AddRemarkCommandParser parser = new AddRemarkCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        String args = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + TYPICAL_REMARK_MEDICAL_ALLERGY;
        assertParseSuccess(parser, args, new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_MEDICAL_ALLERGY));
        assertParseSuccess(parser, " " + args + " ",
                new AddRemarkCommand(INDEX_FIRST_PERSON, REMARK_MEDICAL_ALLERGY));
    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        assertParseFailure(parser, "a " + PREFIX_REMARK + TYPICAL_REMARK_MEDICAL_ALLERGY, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "0 " + PREFIX_REMARK + TYPICAL_REMARK_MEDICAL_ALLERGY, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingPatientIndex_failure() {
        assertParseFailure(parser, PREFIX_REMARK + TYPICAL_REMARK_MEDICAL_ALLERGY, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyRemark_failure() {
        String args = "1 " + PREFIX_REMARK;
        assertParseFailure(parser, args, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingRemarkPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRemarkCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1" , expectedMessage);
    }
}
