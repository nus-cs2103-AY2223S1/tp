package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_YYYY_M_D;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_DATE_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_DESCRIPTION_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_FEEDBACK_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.ITERATION_IMAGEPATH_DESC_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FIRST_DAY_OF_YEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_HORRIBLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_DESCRIPTION_FINALISE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITERATION_IMAGEPATH_FINALISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITERATION_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.iteration.EditIterationCommand;
import seedu.address.logic.commands.iteration.EditIterationCommand.EditIterationDescriptor;
import seedu.address.logic.parser.iteration.EditIterationCommandParser;
import seedu.address.testutil.EditIterationDescriptorBuilder;

public class EditIterationCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIterationCommand.MESSAGE_USAGE);

    private EditIterationCommandParser parser = new EditIterationCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + ITERATION_DATE_DESC_FINALISE
                + ITERATION_DESCRIPTION_DESC_FINALISE + ITERATION_FEEDBACK_DESC_FINALISE
                + ITERATION_IMAGEPATH_DESC_FINALISE;

        EditIterationDescriptor editIterationDescriptor = new EditIterationDescriptorBuilder()
                .withDescription(VALID_ITERATION_DESCRIPTION_FINALISE)
                .withDate(LocalDate.parse(VALID_DATE_FIRST_DAY_OF_YEAR))
                .withFeedback(VALID_FEEDBACK_HORRIBLE)
                .withPath(Path.of(VALID_ITERATION_IMAGEPATH_FINALISE)).build();
        EditIterationCommand expectedCommand = new EditIterationCommand(targetIndex, editIterationDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, ITERATION_DATE_DESC_FINALISE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditIterationCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-8" + ITERATION_DATE_DESC_FINALISE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ITERATION_DATE_DESC_FINALISE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 two three", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/hello", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, "1 " + PREFIX_ITERATION_DATE + INVALID_DATE_YYYY_M_D,
                Messages.MESSAGE_INVALID_ITERATION_DATE);

        // invalid date followed by valid description
        assertParseFailure(parser, "1 " + PREFIX_ITERATION_DATE + INVALID_DATE_YYYY_M_D
                + ITERATION_DESCRIPTION_DESC_FINALISE, Messages.MESSAGE_INVALID_ITERATION_DATE);

        // valid date followed by invalid date
        assertParseFailure(parser, "1" + ITERATION_DATE_DESC_FINALISE + " "
                        + PREFIX_ITERATION_DATE + INVALID_DATE_YYYY_M_D,
                Messages.MESSAGE_INVALID_ITERATION_DATE);
    }
}
