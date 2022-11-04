package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddCommissionCommand;
import seedu.address.model.commission.*;
import seedu.address.testutil.CommissionBuilder;

import java.time.LocalDate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;


public class AddCommissionCommandParserTest {
    private final AddCommissionCommandParser parser = new AddCommissionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // Test various completion status parsing
        CommissionBuilder commissionTestBuilder = new CommissionBuilder()
                .withTitle(VALID_TITLE_CAT)
                .withFee(Double.parseDouble(VALID_FEE_NORMAL))
                .withDeadline(LocalDate.parse(VALID_DATE_FIRST_DAY_OF_YEAR))
                .withDescription(VALID_DESCRIPTION_CAT)
                .withCompletionStatus(true)
                .withTags(VALID_TAG_ANIMAL);

        Commission.CommissionBuilder genericBuilder = commissionTestBuilder.toCommissionBuilder();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_CAT + FEE_DESC_NORMAL
                        + DEADLINE_DESC_START + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL,
                new AddCommissionCommand(genericBuilder));

        assertParseSuccess(parser, TITLE_DESC_DOG + TITLE_DESC_CAT +  FEE_DESC_NORMAL
                        + DEADLINE_DESC_START + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL,
                new AddCommissionCommand(genericBuilder));

        assertParseSuccess(parser, TITLE_DESC_CAT + FEE_DESC_LITTLE + FEE_DESC_LITTLE + FEE_DESC_NORMAL
                        + DEADLINE_DESC_START + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL,
                new AddCommissionCommand(genericBuilder));

        assertParseSuccess(parser, TITLE_DESC_CAT + FEE_DESC_NORMAL
                        + DEADLINE_DESC_END + DEADLINE_DESC_START + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL,
                new AddCommissionCommand(genericBuilder));

        assertParseSuccess(parser, TITLE_DESC_CAT + FEE_DESC_NORMAL
                        + DEADLINE_DESC_START + STATUS_DESC_FALSE + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL,
                new AddCommissionCommand(genericBuilder));

        assertParseSuccess(parser, TITLE_DESC_CAT + FEE_DESC_NORMAL
                        + DEADLINE_DESC_START + STATUS_DESC_TRUE + DESC_OF_DOG + DESC_OF_CAT + TAG_DESC_ANIMAL,
                new AddCommissionCommand(genericBuilder));

        Commission.CommissionBuilder multiTagBuilder = commissionTestBuilder
                .withTags(VALID_TAG_ANIMAL, VALID_TAG_FOOD).toCommissionBuilder();

        assertParseSuccess(parser, TITLE_DESC_CAT + FEE_DESC_NORMAL
                        + DEADLINE_DESC_START + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL + TAG_DESC_FOOD,
                new AddCommissionCommand(multiTagBuilder));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        Commission.CommissionBuilder expectedCommissionBuilder = new CommissionBuilder()
                .withTitle(VALID_TITLE_DOG).withFee(Double.parseDouble(VALID_FEE_LITTLE)).withCompletionStatus(true)
                .withDeadline(LocalDate.parse(VALID_DATE_LAST_DAY_OF_YEAR))
                .withoutDescription().withTags().toCommissionBuilder();
        assertParseSuccess(parser, TITLE_DESC_DOG + FEE_DESC_LITTLE + DEADLINE_DESC_END + STATUS_DESC_TRUE,
                new AddCommissionCommand(expectedCommissionBuilder));
    }

    @Test
    public void parse_compulsoryFieldMissingFailure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommissionCommand.MESSAGE_USAGE);

        assertParseFailure(parser, VALID_TITLE_ELEPHANT + FEE_DESC_NORMAL + DEADLINE_DESC_START
                + STATUS_DESC_TRUE + DESC_OF_ELEPHANT, expectedMessage);

        assertParseFailure(parser, TITLE_DESC_ELEPHANT + VALID_FEE_NORMAL + DEADLINE_DESC_START
                + STATUS_DESC_TRUE + DESC_OF_ELEPHANT, expectedMessage);

        assertParseFailure(parser, TITLE_DESC_ELEPHANT + FEE_DESC_NORMAL + VALID_DATE_FIRST_DAY_OF_YEAR
                + STATUS_DESC_TRUE + DESC_OF_ELEPHANT, expectedMessage);

        assertParseFailure(parser, TITLE_DESC_ELEPHANT + FEE_DESC_NORMAL + DEADLINE_DESC_START
                + VALID_TRUE_STATUS + DESC_OF_ELEPHANT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + FEE_DESC_NORMAL + DEADLINE_DESC_START
                + STATUS_DESC_TRUE + DESC_OF_CAT + TAG_DESC_ANIMAL, Title.MESSAGE_CONSTRAINTS);

        // invalid fee
        assertParseFailure(parser, TITLE_DESC_CAT + INVALID_FEE_DESC + DEADLINE_DESC_END
                + STATUS_DESC_FALSE + DESC_OF_CAT + TAG_DESC_ANIMAL, Fee.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, TITLE_DESC_ELEPHANT + FEE_DESC_LITTLE + INVALID_DEADLINE_DESC
                + STATUS_DESC_TRUE + DESC_OF_ELEPHANT + TAG_DESC_FOOD, Messages.MESSAGE_INVALID_COMMISSION_DEADLINE);

        // invalid status
        assertParseFailure(parser, TITLE_DESC_DOG + FEE_DESC_NORMAL + DEADLINE_DESC_START
                + INVALID_STATUS_DESC + DESC_OF_DOG + TAG_DESC_ANIMAL, CompletionStatus.MESSAGE_CONSTRAINTS);
    }
}
