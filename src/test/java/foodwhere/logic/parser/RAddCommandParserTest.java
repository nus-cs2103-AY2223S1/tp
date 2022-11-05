package foodwhere.logic.parser;

import static foodwhere.logic.commands.CommandTestUtil.CONTENT_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.CONTENT_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.DATE_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.DATE_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_A;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_B;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_C;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_D;
import static foodwhere.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static foodwhere.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static foodwhere.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static foodwhere.logic.commands.CommandTestUtil.RATING_DESC_AMY;
import static foodwhere.logic.commands.CommandTestUtil.RATING_DESC_BOB;
import static foodwhere.logic.commands.CommandTestUtil.STALL_INDEX_DESC;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_CONTENT_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static foodwhere.logic.commands.CommandTestUtil.VALID_RATING_AMY;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static foodwhere.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static foodwhere.logic.commands.RAddCommand.MESSAGE_INVALID_INDEX_ERROR;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static foodwhere.testutil.TypicalIndexes.INDEX_FIRST_STALL;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.RAddCommand;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Date;
import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.testutil.TypicalReviews;

public class RAddCommandParserTest {
    private RAddCommandParser parser = new RAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Review expectedReview = new ReviewBuilder(TypicalReviews.AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + STALL_INDEX_DESC + DATE_DESC_AMY
                + CONTENT_DESC_AMY + RATING_DESC_AMY + TAG_DESC_FRIEND, new RAddCommand(INDEX_FIRST_STALL,
                expectedReview.getDate(), expectedReview.getContent(), expectedReview.getRating(),
                expectedReview.getTags()));

        // multiple tags - all accepted
        Review expectedReviewMultipleTags =
                new ReviewBuilder(TypicalReviews.BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        RAddCommand expectedCommandMultipleTags = new RAddCommand(INDEX_FIRST_STALL,
                expectedReviewMultipleTags.getDate(), expectedReviewMultipleTags.getContent(),
                expectedReviewMultipleTags.getRating(), expectedReviewMultipleTags.getTags());
        assertParseSuccess(parser, STALL_INDEX_DESC + DATE_DESC_BOB + CONTENT_DESC_BOB + RATING_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, expectedCommandMultipleTags);

        // index 999999
        RAddCommand expectedCommand = new RAddCommand(Index.fromOneBased(999999), expectedReview.getDate(),
                expectedReview.getContent(), expectedReview.getRating(), expectedReview.getTags());
        assertParseSuccess(parser, " " + CliSyntax.PREFIX_STALL_INDEX + "999999"
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Review expectedReview = new ReviewBuilder(TypicalReviews.AMY).build();
        RAddCommand expectedCommand = new RAddCommand(INDEX_FIRST_STALL, expectedReview.getDate(),
                expectedReview.getContent(), expectedReview.getRating(), expectedReview.getTags());
        assertParseSuccess(parser, STALL_INDEX_DESC
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedCommand);
    }

    @Test
    public void parse_stallIndexFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_MISSING_INDEX, RAddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, INDEX_FIRST_STALL
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedMessage);
    }

    @Test
    public void parse_stallIndexInvalid_failure() {
        String expectedMessage = MESSAGE_INVALID_INDEX_ERROR;

        // "hubby*" for index
        assertParseFailure(parser, INVALID_STALL_INDEX_A
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedMessage);

        // -5 for index
        assertParseFailure(parser, INVALID_STALL_INDEX_B
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedMessage);

        // "test" for index
        assertParseFailure(parser, INVALID_STALL_INDEX_C
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedMessage);

        // blank index
        assertParseFailure(parser, INVALID_STALL_INDEX_D
                        + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                expectedMessage);

    }

    @Test
    public void parse_dateFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_MISSING_DATE, RAddCommand.MESSAGE_USAGE);

        // missing date prefix
        assertParseFailure(parser, STALL_INDEX_DESC + VALID_DATE_BOB + CONTENT_DESC_BOB + RATING_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_contentFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_MISSING_CONTENT, RAddCommand.MESSAGE_USAGE);

        // missing content prefix
        assertParseFailure(parser, STALL_INDEX_DESC
                        + DATE_DESC_BOB + VALID_CONTENT_BOB + RATING_DESC_AMY,
                expectedMessage);
    }

    @Test
    public void parse_ratingFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_MISSING_RATING, RAddCommand.MESSAGE_USAGE);

        // missing rating prefix
        assertParseFailure(parser, STALL_INDEX_DESC
                        + DATE_DESC_BOB + CONTENT_DESC_BOB + VALID_RATING_AMY,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, STALL_INDEX_DESC + INVALID_DATE_DESC
                + CONTENT_DESC_AMY + RATING_DESC_AMY, Date.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, STALL_INDEX_DESC + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, STALL_INDEX_DESC + INVALID_DATE_DESC + CONTENT_DESC_AMY
                + RATING_DESC_AMY + INVALID_TAG_DESC, Date.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + STALL_INDEX_DESC
                + DATE_DESC_AMY + CONTENT_DESC_AMY + RATING_DESC_AMY,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RAddCommand.MESSAGE_USAGE));
    }
}
