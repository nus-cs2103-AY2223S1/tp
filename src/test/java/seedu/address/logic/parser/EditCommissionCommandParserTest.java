package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FEE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_ANIMAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_CAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditCommissionCommand;
import seedu.address.model.commission.CompletionStatus;
import seedu.address.model.commission.Fee;
import seedu.address.model.commission.Title;
import seedu.address.model.tag.Tag;

class EditCommissionCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommissionCommand.MESSAGE_USAGE);

    private EditCommissionCommandParser parser = new EditCommissionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_CAT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommissionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_CAT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_CAT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Messages.MESSAGE_INVALID_COMMISSION_DEADLINE);
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, CompletionStatus.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FEE_DESC, Fee.MESSAGE_CONSTRAINTS);

        // invalid deadline followed by valid title
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC + TITLE_DESC_CAT,
                Messages.MESSAGE_INVALID_COMMISSION_DEADLINE);

        // valid title followed by invalid title
        assertParseFailure(parser, "1" + TITLE_DESC_CAT + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + TAG_DESC_ANIMAL + TAG_DESC_FOOD + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_ANIMAL + TAG_EMPTY + TAG_DESC_FOOD, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_ANIMAL + TAG_DESC_FOOD, Tag.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DEADLINE_DESC + TAG_DESC_ANIMAL,
                Title.MESSAGE_CONSTRAINTS);
    }
}
