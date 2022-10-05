package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BREAD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BREAKFAST_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BREAKFAST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditFoodDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditFoodDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String TAG_DESC_BREAKFAST = " " + PREFIX_TAG + "breakfast";
    private static final String TAG_DESC_QUANTITY = " " + PREFIX_TAG + "oneServing";
    private static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Bread&";
    private static final String VALID_CALORIE = "200";
    private static final String VALID_CALORIE_DESC = " " + EditCommand.PREFIX_CALORIE + VALID_CALORIE;
    private static final String VALID_QUANTITY_TAG = "oneServing";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_BREAKFAST_NAME, MESSAGE_INVALID_FORMAT);

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
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_BREAKFAST + TAG_DESC_QUANTITY + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_BREAKFAST + TAG_EMPTY + TAG_DESC_QUANTITY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_BREAKFAST + TAG_DESC_QUANTITY,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_CALORIE_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BREAKFAST
                + NAME_DESC_BREAD + TAG_DESC_QUANTITY + VALID_CALORIE_DESC;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withName(VALID_BREAKFAST_NAME)
                .withTags(VALID_TAG_BREAKFAST, VALID_QUANTITY_TAG)
                .withCalorie(VALID_CALORIE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_BREAKFAST;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withTags(VALID_TAG_BREAKFAST).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BREAD;
        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withName(VALID_BREAKFAST_NAME).build();
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
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()
                + TAG_DESC_BREAKFAST + TAG_DESC_BREAKFAST
                + TAG_DESC_QUANTITY;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder()
                .withTags(VALID_QUANTITY_TAG, VALID_TAG_BREAKFAST)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditFoodDescriptor descriptor = new EditFoodDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
