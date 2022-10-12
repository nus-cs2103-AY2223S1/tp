package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMT_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.AMT_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.DATE_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_MEAL;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_PERSONAL;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_DINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_LUNCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PERSONAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENDITURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.address.model.entry.Amount;
import seedu.address.model.entry.Date;
import seedu.address.model.entry.Description;
import seedu.address.model.entry.EntryType;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEntryDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESC_LUNCH, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESC_LUNCH, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESC_LUNCH, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + TYPE_EXPENDITURE + INVALID_DESC,
                Description.MESSAGE_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1" + INVALID_TYPE, EntryType.MESSAGE_CONSTRAINTS); // invalid type
        assertParseFailure(parser, "1" + TYPE_EXPENDITURE + INVALID_AMT,
                Amount.MESSAGE_CONSTRAINTS); // invalid amount
        assertParseFailure(parser, "1" + TYPE_EXPENDITURE + INVALID_DATE,
                Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + TYPE_EXPENDITURE + INVALID_TAG,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid type followed by valid amount
        assertParseFailure(parser, "1" + INVALID_TYPE + VALID_AMT_LUNCH, EntryType.MESSAGE_CONSTRAINTS);

        // valid type followed by invalid type. The test case for invalid type followed by valid type
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TYPE_EXPENDITURE + INVALID_TYPE, EntryType.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_MEAL + TAG_DESC_PERSONAL + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_MEAL + TAG_EMPTY + TAG_DESC_PERSONAL, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_MEAL + TAG_DESC_PERSONAL, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DESC + TYPE_EXPENDITURE + INVALID_AMT + VALID_DATE_LUNCH,
                Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + TAG_DESC_MEAL
                + AMT_LUNCH + DATE_LUNCH + DESC_LUNCH + TAG_DESC_PERSONAL;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withDescription(VALID_DESC_LUNCH)
                .withType(VALID_TYPE_EXPENDITURE).withAmount(VALID_AMT_LUNCH).withDate(VALID_DATE_LUNCH)
                .withTags(VALID_TAG_MEAL, VALID_TAG_PERSONAL).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + DESC_LUNCH + AMT_LUNCH;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_LUNCH).withAmount(VALID_AMT_LUNCH).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // desc
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + DESC_LUNCH;
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withDescription(VALID_DESC_LUNCH).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // type
        userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE;
        descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // amount
        userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + AMT_LUNCH;
        descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withAmount(VALID_AMT_LUNCH).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + DATE_LUNCH;
        descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withDate(VALID_DATE_LUNCH).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + TAG_DESC_MEAL;
        descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withTags(VALID_TAG_MEAL).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + DATE_LUNCH + AMT_LUNCH
                + TAG_DESC_MEAL + TYPE_EXPENDITURE + DATE_LUNCH + AMT_LUNCH + TAG_DESC_MEAL
                + TYPE_EXPENDITURE + DATE_DINNER + AMT_DINNER + TAG_DESC_PERSONAL;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE)
                .withDate(VALID_DATE_DINNER)
                .withAmount(VALID_AMT_DINNER)
                .withTags(VALID_TAG_PERSONAL, VALID_TAG_MEAL)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_TYPE + TYPE_EXPENDITURE;
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + AMT_LUNCH + INVALID_TYPE + DATE_LUNCH
                + TYPE_EXPENDITURE;
        descriptor = new EditEntryDescriptorBuilder().withType(VALID_TYPE_EXPENDITURE).withAmount(VALID_AMT_LUNCH)
                .withDate(VALID_DATE_LUNCH).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TYPE_EXPENDITURE + TAG_EMPTY;

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder()
                .withType(VALID_TYPE_EXPENDITURE).withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
