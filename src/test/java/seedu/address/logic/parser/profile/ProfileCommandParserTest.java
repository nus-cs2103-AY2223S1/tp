package seedu.address.logic.parser.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.profile.FindProfileCommand;
import seedu.address.logic.commands.profile.ProfileCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.profile.NameContainsKeywordsPredicate;

public class ProfileCommandParserTest {

    private ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_missingOptionFlag_failure() {
        assertParseFailure(parser, " HELLO WORLD",
                String.format(Messages.MESSAGE_FLAG_NOT_SPECIFIED,
                        ProfileCommand.PROFILE_FORMAT + ProfileCommand.VALID_FLAGS));

        assertParseFailure(parser, " TEXT -e", ProfileCommand.OPTION_WRONG_ORDER);

        assertParseFailure(parser, " TEXT -e -e", ProfileCommand.OPTION_WRONG_ORDER_NO_MULTIPLE);
    }

    @Test
    public void parse_multipleOptionFlags_failure() {
        assertParseFailure(parser, " -e -e", ProfileCommand.OPTION_NO_MULTIPLE);
        assertParseFailure(parser, " -e Hello -e", ProfileCommand.OPTION_NO_MULTIPLE);
        assertParseFailure(parser, " -hello -world -cs2103t", ProfileCommand.OPTION_NO_MULTIPLE);
    }

    @Test
    public void parse_profileOption_failure() {
        assertParseFailure(parser, " -unknowncommand",
                ProfileCommand.OPTION_UNKNOWN + ProfileCommand.VALID_FLAGS);
    }

    @Test
    public void parse_profileOption_find() {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        try {
            FindProfileCommand command = (FindProfileCommand) parser.parse(
                    " " + PREFIX_OPTION + FindProfileCommand.COMMAND_OPTION + " "
                            + keywords.stream().collect(Collectors.joining(" ")));
            assertEquals(new FindProfileCommand(new NameContainsKeywordsPredicate(keywords)), command);
        } catch (ParseException e) {
            fail();
        }
    }
}
