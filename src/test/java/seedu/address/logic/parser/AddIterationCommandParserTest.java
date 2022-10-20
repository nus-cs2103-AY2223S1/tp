package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_NO_DELIMINATOR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_YYYY_M_D;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_YY_MM_DD;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_DATE_DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_DATE_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_DESCRIPTION_DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_DESCRIPTION_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_FEEDBACK_DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_FEEDBACK_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_IMAGEPATH_DESC_COLOR;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_IMAGEPATH_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FIRST_DAY_OF_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_HORRIBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_IMAGEPATH_FINALISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.iteration.AddIterationCommand;
import seedu.address.logic.parser.iteration.AddIterationCommandParser;
import seedu.address.model.iteration.Iteration;
import seedu.address.testutil.TypicalIterations;

public class AddIterationCommandParserTest {
    private final AddIterationCommandParser parser = new AddIterationCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Iteration expectedIteration = TypicalIterations.FINALISED;
        AddIterationCommand expectedAddIterationCommand = new AddIterationCommand(expectedIteration);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITERATION_DESCRIPTION_DESC_FINALISE
                + ITERATION_DATE_DESC_FINALISE + ITERATION_FEEDBACK_DESC_FINALISE
                + ITERATION_IMAGEPATH_DESC_FINALISE, expectedAddIterationCommand);

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, ITERATION_DESCRIPTION_DESC_COLOR + ITERATION_DESCRIPTION_DESC_FINALISE
                + ITERATION_DATE_DESC_FINALISE + ITERATION_FEEDBACK_DESC_FINALISE
                + ITERATION_IMAGEPATH_DESC_FINALISE, expectedAddIterationCommand);

        // multiple dates - last date accepted
        assertParseSuccess(parser, ITERATION_DESCRIPTION_DESC_FINALISE + ITERATION_DATE_DESC_COLOR
                + ITERATION_DATE_DESC_FINALISE + ITERATION_FEEDBACK_DESC_FINALISE
                + ITERATION_IMAGEPATH_DESC_FINALISE, expectedAddIterationCommand);

        // multiple feedbacks - last feedback accepted
        assertParseSuccess(parser, ITERATION_DESCRIPTION_DESC_FINALISE + ITERATION_DATE_DESC_FINALISE
                + ITERATION_FEEDBACK_DESC_COLOR + ITERATION_FEEDBACK_DESC_FINALISE
                + ITERATION_IMAGEPATH_DESC_FINALISE, expectedAddIterationCommand);

        // multiple image paths - last image path accepted
        assertParseSuccess(parser, ITERATION_DESCRIPTION_DESC_FINALISE + ITERATION_DATE_DESC_FINALISE
                + ITERATION_FEEDBACK_DESC_FINALISE + ITERATION_IMAGEPATH_DESC_COLOR
                + ITERATION_IMAGEPATH_DESC_FINALISE, expectedAddIterationCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIterationCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_ITERATION_DESCRIPTION_FINALISE + ITERATION_DATE_DESC_FINALISE
                + ITERATION_FEEDBACK_DESC_FINALISE + ITERATION_IMAGEPATH_DESC_FINALISE, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, ITERATION_DESCRIPTION_DESC_FINALISE + VALID_DATE_FIRST_DAY_OF_YEAR
                + ITERATION_FEEDBACK_DESC_FINALISE + ITERATION_IMAGEPATH_DESC_FINALISE, expectedMessage);

        // missing feedback prefix
        assertParseFailure(parser, ITERATION_DESCRIPTION_DESC_FINALISE + ITERATION_DATE_DESC_FINALISE
                + VALID_FEEDBACK_HORRIBLE + ITERATION_IMAGEPATH_DESC_FINALISE, expectedMessage);

        // missing image path prefix
        assertParseFailure(parser, ITERATION_DESCRIPTION_DESC_FINALISE + ITERATION_DATE_DESC_FINALISE
                + ITERATION_FEEDBACK_DESC_FINALISE + VALID_ITERATION_IMAGEPATH_FINALISE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ITERATION_DESCRIPTION_FINALISE + VALID_DATE_FIRST_DAY_OF_YEAR
                + VALID_FEEDBACK_HORRIBLE + VALID_ITERATION_IMAGEPATH_FINALISE, expectedMessage);
    }

    @Test
    public void parse_invalidDate_failure() {
        String invalidDateInput = ITERATION_DESCRIPTION_DESC_FINALISE + " " + PREFIX_ITERATION_DATE
                + "%s" + ITERATION_FEEDBACK_DESC_FINALISE + ITERATION_IMAGEPATH_DESC_FINALISE;

        // invalid date formats
        assertParseFailure(parser, String.format(invalidDateInput, INVALID_DATE_YYYY_M_D),
                Messages.MESSAGE_INVALID_ITERATION_DATE);

        assertParseFailure(parser, String.format(invalidDateInput, INVALID_DATE_YY_MM_DD),
                Messages.MESSAGE_INVALID_ITERATION_DATE);

        assertParseFailure(parser, String.format(invalidDateInput, INVALID_DATE_NO_DELIMINATOR),
                Messages.MESSAGE_INVALID_ITERATION_DATE);

        // empty date
        assertParseFailure(parser, String.format(invalidDateInput, " "), Messages.MESSAGE_INVALID_ITERATION_DATE);
    }
}
