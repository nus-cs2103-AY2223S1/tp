package foodwhere.logic.parser;

import static foodwhere.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.logic.commands.SFindCommand;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.stall.StallContainsKeywordsPredicate;

public class SFindCommandParserTest {

    private SFindCommandParser parser = new SFindCommandParser();

    @Test
    public void parse_validArgs_returnsSFindCommand() {
        //both name and tag
        SFindCommand expectedSFindCommand =
                new SFindCommand(new StallContainsKeywordsPredicate(Arrays.asList(new Name("Amy"), new Name("Bee")),
                        Arrays.asList(new Tag("friend"))));
        assertParseSuccess(parser, NAME_DESC_AMY + TAG_DESC_FRIEND , expectedSFindCommand);

        //only name
        expectedSFindCommand =
                new SFindCommand(new StallContainsKeywordsPredicate(Arrays.asList(new Name("Amy"), new Name("Bee")),
                        Collections.emptyList()));
        assertParseSuccess(parser, NAME_DESC_AMY , expectedSFindCommand);
        //only tag
        expectedSFindCommand =
                new SFindCommand(new StallContainsKeywordsPredicate(Collections.emptyList(),
                        Arrays.asList(new Tag("friend"))));
        assertParseSuccess(parser, TAG_DESC_FRIEND , expectedSFindCommand);

    }

    @Test
    public void parse_emptyPreamble_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SFindCommand.MESSAGE_USAGE);

        // missing prefix
        assertParseFailure(parser, VALID_NAME_BOB,
                expectedMessage);

    }
    @Test
    public void parse_fieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SFindCommand.MESSAGE_USAGE);

        // missing prefix
        assertParseFailure(parser, VALID_NAME_BOB + TAG_DESC_FRIEND,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                         + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SFindCommand.MESSAGE_USAGE));
    }

}
