package eatwhere.foodguide.logic.parser;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.logic.commands.AddCommand;
import eatwhere.foodguide.logic.commands.CommandTestUtil;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Phone;
import eatwhere.foodguide.model.tag.Tag;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Eatery expectedEatery = new EateryBuilder(TypicalEateries.BOB)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.CUISINE_DESC_BOB
                + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedEatery));

        // multiple names - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.CUISINE_DESC_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                        new AddCommand(expectedEatery));

        // multiple phones - last phone accepted
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.CUISINE_DESC_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                        new AddCommand(expectedEatery));

        // multiple emails - last email accepted
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_AMY + CommandTestUtil.CUISINE_DESC_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                        new AddCommand(expectedEatery));

        // multiple addresses - last address accepted
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_AMY
                        + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND,
                        new AddCommand(expectedEatery));

        // multiple tags - all accepted
        Eatery expectedEateryMultipleTags = new EateryBuilder(TypicalEateries.BOB)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                        new AddCommand(expectedEateryMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Eatery expectedEatery = new EateryBuilder(TypicalEateries.AMY).withTags().build();
        CommandParserTestUtil.assertParseSuccess(parser,
                CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.CUISINE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY,
                        new AddCommand(expectedEatery));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                        expectedMessage);

        // missing cuisine prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.VALID_CUISINE_BOB + CommandTestUtil.ADDRESS_DESC_BOB,
                        expectedMessage);

        // missing address prefix
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.VALID_ADDRESS_BOB,
                        expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.VALID_PHONE_BOB
                        + CommandTestUtil.VALID_CUISINE_BOB + CommandTestUtil.VALID_ADDRESS_BOB,
                        expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                        Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                        Phone.MESSAGE_CONSTRAINTS);

        // invalid cuisine
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.INVALID_CUISINE_DESC + CommandTestUtil.ADDRESS_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                        Cuisine.MESSAGE_CONSTRAINTS);

        // invalid address
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                        Location.MESSAGE_CONSTRAINTS);

        // invalid tag
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB
                        + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.VALID_TAG_FRIEND,
                        Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.CUISINE_DESC_BOB + CommandTestUtil.INVALID_ADDRESS_DESC,
                        Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser,
                CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.CUISINE_DESC_BOB
                        + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                        + CommandTestUtil.TAG_DESC_FRIEND,
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
