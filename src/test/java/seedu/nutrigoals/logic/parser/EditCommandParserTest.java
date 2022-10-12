package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.NAME_DESC_BREAD;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.TAG_DESC_LUNCH;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_BREAD_NAME;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_TAG_LUNCH;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.nutrigoals.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_FIRST_MEAL;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_SECOND_MEAL;
import static seedu.nutrigoals.testutil.TypicalIndexes.INDEX_THIRD_MEAL;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.commons.core.index.Index;
import seedu.nutrigoals.logic.commands.EditCommand;
import seedu.nutrigoals.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.nutrigoals.model.meal.Name;
import seedu.nutrigoals.model.tag.Tag;
import seedu.nutrigoals.testutil.EditFoodDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String TAG_DESC_BREAKFAST = " " + PREFIX_TAG + "breakfast";
    private static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Bread&";
    private static final String VALID_CALORIE = "200";
    private static final String VALID_CALORIE_DESC = " " + EditCommand.PREFIX_CALORIE + VALID_CALORIE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_BREAD_NAME, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_BREAD, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_BREAD, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + " " + PREFIX_TAG + "breakfast*", Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // as long as the last tag provided is valid, i.e. not {@code PREFIX_TAG} alone or tags with
        // invalid tag names, no error will be thrown
        assertParseFailure(parser, "1" + TAG_DESC_BREAKFAST + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_CALORIE_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_MEAL;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BREAKFAST
                + NAME_DESC_BREAD + VALID_CALORIE_DESC;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withName(VALID_BREAD_NAME)
                .withTags(VALID_TAG_BREAKFAST)
                .withCalorie(VALID_CALORIE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEAL;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BREAKFAST;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withTags(VALID_TAG_BREAKFAST).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_MEAL;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BREAD;
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_BREAD_NAME).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_BREAKFAST;
        descriptor = new EditFoodDescriptorBuilder().withTags(VALID_TAG_BREAKFAST).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // calorie
        userInput = targetIndex.getOneBased() + VALID_CALORIE_DESC;
        descriptor = new EditFoodDescriptorBuilder().withCalorie(VALID_CALORIE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_MEAL;
        String userInput = targetIndex.getOneBased()
                + TAG_DESC_BREAKFAST + TAG_DESC_BREAKFAST
                + TAG_DESC_LUNCH;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withTags(VALID_TAG_BREAKFAST, VALID_TAG_BREAKFAST, VALID_TAG_LUNCH)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
