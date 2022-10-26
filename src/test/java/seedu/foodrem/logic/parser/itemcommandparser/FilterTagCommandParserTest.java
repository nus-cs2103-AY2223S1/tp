package seedu.foodrem.logic.parser.itemcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.itemcommands.FilterTagCommand;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;

public class FilterTagCommandParserTest {
    private final FilterTagCommandParser parser = new FilterTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTagCommand.getUsage()));
    }

    @Test
    public void parse_validArgs_returnsFilterTagCommand() {
        Tag fruitsTag = new TagBuilder()
                .withTagName(VALID_TAG_NAME_FRUITS)
                .build();
        FilterTagCommand expectedFilterTagCommand =
               new FilterTagCommand(fruitsTag);
        assertParseSuccess(parser, VALID_DESC_TAG_NAME_FRUITS, expectedFilterTagCommand);
    }
}
