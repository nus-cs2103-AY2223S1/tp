package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static eatwhere.foodguide.logic.commands.CommandTestUtil.HELP_DESC;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.CommandTestUtil;
import eatwhere.foodguide.logic.commands.EditCommand;
import eatwhere.foodguide.logic.commands.EditCommand.EditEateryDescriptor;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Price;
import eatwhere.foodguide.model.tag.Tag;
import eatwhere.foodguide.testutil.EditEateryDescriptorBuilder;
import eatwhere.foodguide.testutil.TypicalIndexes;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser,
                "-5" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser,
                "0" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser,
                "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser,
                "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS); // invalid phone
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_CUISINE_DESC, Cuisine.MESSAGE_CONSTRAINTS); // invalid email
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_ADDRESS_DESC, Location.MESSAGE_CONSTRAINTS); // invalid address
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_PRICE_DESC + CommandTestUtil.CUISINE_DESC_AMY,
                Price.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_PRICE_DESC,
                Price.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Eatery} being edited,
        // parsing it together with a valid tag results in error
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.TAG_DESC_FRIEND + TAG_EMPTY + CommandTestUtil.TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + TAG_EMPTY + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.INVALID_CUISINE_DESC
                        + CommandTestUtil.VALID_ADDRESS_AMY + CommandTestUtil.VALID_PRICE_AMY,
                        Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_EATERY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.CUISINE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND;

        EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
                .withPhone(CommandTestUtil.VALID_PRICE_BOB).withEmail(CommandTestUtil.VALID_CUISINE_AMY)
                .withAddress(CommandTestUtil.VALID_ADDRESS_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND, CommandTestUtil.VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_EATERY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.CUISINE_DESC_AMY;

        EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder().withPhone(CommandTestUtil.VALID_PRICE_BOB)
                .withEmail(CommandTestUtil.VALID_CUISINE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = TypicalIndexes.INDEX_THIRD_EATERY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.NAME_DESC_AMY;
        EditCommand.EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + CommandTestUtil.PRICE_DESC_AMY;
        descriptor = new EditEateryDescriptorBuilder().withPhone(CommandTestUtil.VALID_PRICE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + CommandTestUtil.CUISINE_DESC_AMY;
        descriptor = new EditEateryDescriptorBuilder().withEmail(CommandTestUtil.VALID_CUISINE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + CommandTestUtil.ADDRESS_DESC_AMY;
        descriptor = new EditEateryDescriptorBuilder().withAddress(CommandTestUtil.VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + CommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditEateryDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_EATERY;
        String userInput = targetIndex.getOneBased()
                + CommandTestUtil.PRICE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.CUISINE_DESC_AMY
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.PRICE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.CUISINE_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.CUISINE_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND;

        EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder().withPhone(CommandTestUtil.VALID_PRICE_BOB)
                .withEmail(CommandTestUtil.VALID_CUISINE_BOB).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST_EATERY;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_PRICE_DESC
                + CommandTestUtil.PHONE_DESC_BOB;
        EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_PRICE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.INVALID_PRICE_DESC
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB;
        descriptor = new EditEateryDescriptorBuilder()
                .withPhone(CommandTestUtil.VALID_PRICE_BOB).withEmail(CommandTestUtil.VALID_CUISINE_BOB)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_EATERY;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditEateryDescriptor descriptor = new EditEateryDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_displayHelp_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_EATERY;
        String userInput = targetIndex.getOneBased() + HELP_DESC;

        CommandParserTestUtil.assertParseDisplayCommandHelp(parser, userInput, EditCommand.MESSAGE_USAGE);

        // help prefix overrides normal edit command behavior
        userInput = targetIndex.getOneBased()
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.CUISINE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND + HELP_DESC;

        CommandParserTestUtil.assertParseDisplayCommandHelp(parser, userInput, EditCommand.MESSAGE_USAGE);
    }
}
