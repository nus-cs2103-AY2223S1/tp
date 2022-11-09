package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.commons.core.Messages.MESSAGE_MULTIPLE_TAGS_ERROR;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.CALORIE_DESC_BREAD;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.NAME_DESC_BREAD;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.TAG_DESC_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.TAG_DESC_LUNCH;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_APPLE_CALORIE;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_APPLE_NAME;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_BREAD_CALORIE;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.nutrigoals.testutil.TypicalFoods.BREAD;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.AddCommand;
import seedu.nutrigoals.model.meal.Food;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;
import seedu.nutrigoals.testutil.FoodBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Food expectedFood = new FoodBuilder(BREAD).withCalorie(VALID_BREAD_CALORIE).withTag(VALID_TAG_LUNCH).build();

        // whitespace only preamble
        assertParseSuccess(parser, NAME_DESC_BREAD + CALORIE_DESC_BREAD
                + TAG_DESC_LUNCH, new AddCommand(expectedFood));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BREAD + CALORIE_DESC_BREAD
                + TAG_DESC_LUNCH, new AddCommand(expectedFood));

        // multiple tags - should not be allowed to input multiple tags
        assertParseFailure(parser, NAME_DESC_BREAD + CALORIE_DESC_BREAD
                + TAG_DESC_LUNCH + TAG_DESC_BREAKFAST, MESSAGE_MULTIPLE_TAGS_ERROR);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_APPLE_NAME,
                expectedMessage);

        // missing calorie field
        assertParseFailure(parser, NAME_DESC_BREAD + TAG_DESC_BREAKFAST, expectedMessage);

        // missing calorie prefix
        assertParseFailure(parser, NAME_DESC_BREAD + VALID_APPLE_CALORIE + TAG_DESC_BREAKFAST,
                expectedMessage);

        // missing tag
        assertParseFailure(parser, NAME_DESC_BREAD + CALORIE_DESC_BREAD + VALID_BREAD_CALORIE,
                expectedMessage);

        // missing tag prefix
        assertParseFailure(parser, NAME_DESC_BREAD + CALORIE_DESC_BREAD + VALID_TAG_BREAKFAST,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + CALORIE_DESC_BREAD
                + TAG_DESC_BREAKFAST , Name.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BREAD + CALORIE_DESC_BREAD
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // one invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + CALORIE_DESC_BREAD + TAG_DESC_LUNCH,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BREAD + CALORIE_DESC_BREAD
                        + TAG_DESC_BREAKFAST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
