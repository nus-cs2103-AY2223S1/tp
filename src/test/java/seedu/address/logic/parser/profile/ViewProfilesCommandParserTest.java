package seedu.address.logic.parser.profile;

import static seedu.address.logic.commands.profile.ViewProfilesCommand.MESSAGE_FAILURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.profile.ViewProfilesCommand;

public class ViewProfilesCommandParserTest {
    private ViewProfilesCommandParser parser = new ViewProfilesCommandParser();

    @Test
    public void parse_noTrailingCharacters_success() {
        assertParseSuccess(parser, "", new ViewProfilesCommand());
    }

    @Test
    public void parse_trailingWhitespace_success() {
        assertParseSuccess(parser, "     ", new ViewProfilesCommand());
    }

    @Test
    public void parse_trailingCharacters_failure() {
        assertParseFailure(parser, "   asdf ", MESSAGE_FAILURE);
        assertParseFailure(parser, "1", MESSAGE_FAILURE);
    }
}
