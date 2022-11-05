package foodwhere.logic.parser;

import static foodwhere.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_CONTENT_AMY;
import static foodwhere.logic.commands.REditCommand.MESSAGE_INVALID_INDEX_ERROR;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
//import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.REditCommand;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
//import foodwhere.testutil.EditReviewDescriptorBuilder;
//import foodwhere.testutil.TypicalIndexes;

public class REditCommandParserTest {
    private static final String TAG_EMPTY = " " + CliSyntax.PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, REditCommand.MESSAGE_USAGE);

    private REditCommandParser parser = new REditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CONTENT_AMY, MESSAGE_INVALID_INDEX_ERROR);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_CONTENT_AMY, MESSAGE_INVALID_INDEX_ERROR);

        // zero index
        assertParseFailure(parser, "0" + VALID_CONTENT_AMY, MESSAGE_INVALID_INDEX_ERROR);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX_ERROR);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX_ERROR);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Review} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_DATE_DESC + INVALID_RATING_DESC, Date.MESSAGE_CONSTRAINTS);
    }

    /*
    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_SECOND_REVIEW;
        String userInput = targetIndex.getOneBased() + TAG_DESC_HUSBAND + DATE_DESC_AMY
                + CONTENT_DESC_AMY + RATING_DESC_AMY + TAG_DESC_FRIEND;

        REditCommand.EditReviewDescriptor descriptor = new EditReviewDescriptorBuilder().withDate(VALID_DATE_AMY)
                .withContent(VALID_CONTENT_AMY)
                .withRating(VALID_RATING_AMY.toString())
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        REditCommand expectedCommand = new REditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

     */

    /*
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_REVIEW;
        String userInput = targetIndex.getOneBased() + DATE_DESC_BOB;

        REditCommand.EditReviewDescriptor descriptor = new EditReviewDescriptorBuilder()
                .withDate(VALID_DATE_BOB).build();
        REditCommand expectedCommand = new REditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

     */

    /*
    @Test
    public void parse_oneFieldSpecified_success() {
        // Content
        Index targetIndex = TypicalIndexes.INDEX_THIRD_REVIEW;
        String userInput = targetIndex.getOneBased() + CONTENT_DESC_AMY;
        REditCommand.EditReviewDescriptor descriptor =
                new EditReviewDescriptorBuilder().withContent(VALID_CONTENT_AMY).build();
        REditCommand expectedCommand = new REditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Date
        userInput = targetIndex.getOneBased() + DATE_DESC_AMY;
        descriptor = new EditReviewDescriptorBuilder().withDate(VALID_DATE_AMY).build();
        expectedCommand = new REditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Rating
        userInput = targetIndex.getOneBased() + RATING_DESC_AMY;
        descriptor = new EditReviewDescriptorBuilder().withRating(VALID_RATING_AMY.toString()).build();
        expectedCommand = new REditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditReviewDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new REditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

     */

    /*
    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = TypicalIndexes.INDEX_FIRST_REVIEW;
        String userInput = targetIndex.getOneBased() + DATE_DESC_AMY
                + TAG_DESC_FRIEND + DATE_DESC_AMY + TAG_DESC_FRIEND
                + DATE_DESC_BOB + TAG_DESC_HUSBAND;

        REditCommand.EditReviewDescriptor descriptor = new EditReviewDescriptorBuilder()
                .withDate(VALID_DATE_BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        REditCommand expectedCommand = new REditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

     */

    /*
    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = TypicalIndexes.INDEX_FIRST_REVIEW;
        String userInput = targetIndex.getOneBased() + INVALID_DATE_DESC + DATE_DESC_BOB;
        REditCommand.EditReviewDescriptor descriptor =
                new EditReviewDescriptorBuilder().withDate(VALID_DATE_BOB).build();
        REditCommand expectedCommand = new REditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_RATING_DESC + DATE_DESC_BOB
                + RATING_DESC_BOB;
        descriptor = new EditReviewDescriptorBuilder().withRating(VALID_NAME_BOB)
                .withDate(VALID_DATE_BOB).build();
        expectedCommand = new REditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

     */

    /*
    @Test
    public void parse_resetDetails_success() {
        Index targetIndex = TypicalIndexes.INDEX_THIRD_REVIEW;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        REditCommand.EditReviewDescriptor descriptor = new EditReviewDescriptorBuilder().withTags().build();
        REditCommand expectedCommand = new REditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
     */
}
