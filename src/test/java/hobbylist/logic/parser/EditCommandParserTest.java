package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.CommandTestUtil;
import hobbylist.logic.commands.EditCommand;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.tag.Tag;
import hobbylist.testutil.EditActivityDescriptorBuilder;
import hobbylist.testutil.TypicalIndexes;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_ANIME, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_ANIME,
                MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0" + CommandTestUtil.NAME_DESC_ANIME,
                MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_DESCRIPTION_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid description
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Activity} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_EXERCISE
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.TAG_DESC_EXERCISE + TAG_EMPTY
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT, Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "1" + TAG_EMPTY + CommandTestUtil.TAG_DESC_EXERCISE
                + CommandTestUtil.TAG_DESC_ENTERTAINMENT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, "1" + CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.VALID_DESCRIPTION_ANIME,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_ACTIVITY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_ENTERTAINMENT
                + CommandTestUtil.DESCRIPTION_DESC_ANIME + CommandTestUtil.NAME_DESC_ANIME
                + CommandTestUtil.TAG_DESC_EXERCISE;

        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_ANIME)
                .withDescription(CommandTestUtil.VALID_DESCRIPTION_ANIME)
                .withTags(CommandTestUtil.VALID_TAG_ENTERTAINMENT, CommandTestUtil.VALID_TAG_EXERCISE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_ACTIVITY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_ANIME;
        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_ANIME).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + CommandTestUtil.DESCRIPTION_DESC_ANIME;
        descriptor = new EditActivityDescriptorBuilder().withDescription(CommandTestUtil.VALID_DESCRIPTION_ANIME)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_ENTERTAINMENT;
        descriptor = new EditActivityDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_ENTERTAINMENT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_ACTIVITY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.DESCRIPTION_DESC_ANIME
                + CommandTestUtil.TAG_DESC_EXERCISE + CommandTestUtil.DESCRIPTION_DESC_ANIME
                + CommandTestUtil.TAG_DESC_EXERCISE
                + CommandTestUtil.DESCRIPTION_DESC_BOXING + CommandTestUtil.TAG_DESC_ENTERTAINMENT;

        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder()
                .withDescription(CommandTestUtil.VALID_DESCRIPTION_BOXING).withTags(CommandTestUtil.VALID_TAG_EXERCISE,
                        CommandTestUtil.VALID_TAG_ENTERTAINMENT)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_ACTIVITY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditActivityDescriptor descriptor = new EditActivityDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
