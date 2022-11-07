package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.AddCommand;
import hobbylist.logic.commands.CommandTestUtil;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.tag.Tag;
import hobbylist.testutil.ActivityBuilder;
import hobbylist.testutil.TypicalActivities;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Activity expectedActivity = new ActivityBuilder(TypicalActivities.BOXING)
                .withTags(CommandTestUtil.VALID_TAG_EXERCISE).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING
                + CommandTestUtil.TAG_DESC_EXERCISE, new AddCommand(expectedActivity));

        // multiple names - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_ANIME
                + CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING
                + CommandTestUtil.TAG_DESC_EXERCISE, new AddCommand(expectedActivity));

        // multiple phones - last phone accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING + CommandTestUtil.TAG_DESC_EXERCISE,
                new AddCommand(expectedActivity));

        // multiple emails - last email accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING + CommandTestUtil.TAG_DESC_EXERCISE,
                new AddCommand(expectedActivity));

        // multiple descriptions - last description accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_ANIME
                + CommandTestUtil.DESCRIPTION_DESC_BOXING + CommandTestUtil.TAG_DESC_EXERCISE,
                new AddCommand(expectedActivity));

        // multiple tags - all accepted
        Activity expectedActivityMultipleTags = new ActivityBuilder(TypicalActivities.BOXING)
                .withTags(CommandTestUtil.VALID_TAG_EXERCISE, CommandTestUtil.VALID_TAG_ENTERTAINMENT)
                .build();
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT
                + CommandTestUtil.TAG_DESC_EXERCISE, new AddCommand(expectedActivityMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Activity expectedActivity = new ActivityBuilder(TypicalActivities.ANIME).withTags().build();
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_ANIME
                        + CommandTestUtil.DESCRIPTION_DESC_ANIME,
                new AddCommand(expectedActivity));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOXING
                        + CommandTestUtil.DESCRIPTION_DESC_BOXING,
                expectedMessage);

        // missing description prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOXING
                        + CommandTestUtil.VALID_DESCRIPTION_BOXING,
                expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOXING
                        + CommandTestUtil.VALID_DESCRIPTION_BOXING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.DESCRIPTION_DESC_BOXING
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT + CommandTestUtil.TAG_DESC_EXERCISE, Name.MESSAGE_CONSTRAINTS);

        // invalid description
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.INVALID_DESCRIPTION_DESC
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT
                + CommandTestUtil.TAG_DESC_EXERCISE, Description.MESSAGE_CONSTRAINTS);

        // tag containing invalid character
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING
                + CommandTestUtil.INVALID_TAG_DESC
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT, Tag.MESSAGE_CONSTRAINTS);

        // tag more than 15 characters
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING
                + CommandTestUtil.TOO_LONG_TAG_DESC
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT, Tag.TAG_NAME_TOO_LONG);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.INVALID_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.NAME_DESC_BOXING
                + CommandTestUtil.DESCRIPTION_DESC_BOXING + CommandTestUtil.TAG_DESC_ENTERTAINMENT
                        + CommandTestUtil.TAG_DESC_EXERCISE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
