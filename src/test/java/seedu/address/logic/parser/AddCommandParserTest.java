package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BREAD;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BREAKFAST;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLE_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.APPLE;
import static seedu.address.testutil.TypicalPersons.BREAD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Food;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FoodBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Food expectedFood = new FoodBuilder(BREAD).withTags(VALID_TAG_LUNCH).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BREAD
                + TAG_DESC_LUNCH, new AddCommand(expectedFood));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_APPLE + NAME_DESC_BREAD
                + TAG_DESC_BREAKFAST, new AddCommand(expectedFood));

        // multiple tags - all accepted
        Food expectedFoodMultipleTags = new FoodBuilder(BREAD).withTags(VALID_TAG_BREAKFAST, VALID_TAG_LUNCH)
                .build();
        assertParseSuccess(parser, NAME_DESC_BREAD
                + TAG_DESC_BREAKFAST + TAG_DESC_LUNCH, new AddCommand(expectedFoodMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Food expectedFood = new FoodBuilder(APPLE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_APPLE,
                new AddCommand(expectedFood));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_APPLE_NAME,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + TAG_DESC_BREAKFAST + TAG_DESC_LUNCH, Name.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BREAD
                + INVALID_TAG_DESC + VALID_TAG_LUNCH, Tag.MESSAGE_CONSTRAINTS);

        // one invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BREAD
                        + TAG_DESC_BREAKFAST + TAG_DESC_LUNCH,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
