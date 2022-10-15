package seedu.nutrigoals.logic.parser;


import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.commons.core.Messages.MESSAGE_MULTIPLE_TAGS_ERROR;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.GENDER_VALID_GENDER;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.HEIGHT_VALID_HEIGHT;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.IDEAL_VALID_WEIGHT;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_HEIGHT_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_HEIGHT_NEGATIVE;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_HEIGHT_TOO_MUCH;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_IDEAL_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_IDEAL_NEGATIVE;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_IDEAL_TOO_MUCH;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_WEIGHT_NEGATIVE;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.INVALID_WEIGHT_TOO_MUCH;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_GENDER;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_HEIGHT_AMOUNT;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.VALID_WEIGHT_AMOUNT;
import static seedu.nutrigoals.logic.commands.CommandTestUtil.WEIGHT_VALID_WEIGHT;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.SetupCommand;
import seedu.nutrigoals.model.user.Gender;
import seedu.nutrigoals.model.user.Height;
import seedu.nutrigoals.model.user.User;
import seedu.nutrigoals.model.user.Weight;
import seedu.nutrigoals.testutil.UserBuilder;

public class SetupCommandParserTest {
    private SetupCommandParser parser = new SetupCommandParser();

    @Test
    public void parse_allFieldsPresentSuccess() {
        User expected = new UserBuilder().withHeight(VALID_HEIGHT_AMOUNT).withIdeal(VALID_WEIGHT_AMOUNT)
                .withWeight(VALID_WEIGHT_AMOUNT).withGender(VALID_GENDER).build();

        assertParseSuccess(parser, WEIGHT_VALID_WEIGHT + HEIGHT_VALID_HEIGHT + IDEAL_VALID_WEIGHT
                + GENDER_VALID_GENDER, new SetupCommand(expected));
    }

    @Test
    public void parse_fieldsMissingFailure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetupCommand.MESSAGE_USAGE);
        assertParseFailure(parser, WEIGHT_VALID_WEIGHT, expectedMessage); //only weight is given
        assertParseFailure(parser, IDEAL_VALID_WEIGHT, expectedMessage); //only ideal is given
        assertParseFailure(parser, HEIGHT_VALID_HEIGHT, expectedMessage); //only height is given
        assertParseFailure(parser, GENDER_VALID_GENDER, expectedMessage); //only gender is given
        assertParseFailure(parser, WEIGHT_VALID_WEIGHT + HEIGHT_VALID_HEIGHT, expectedMessage); //two given
        assertParseFailure(parser, WEIGHT_VALID_WEIGHT + HEIGHT_VALID_HEIGHT
                + IDEAL_VALID_WEIGHT, expectedMessage); //missing one arg

    }

    @Test
    public void parse_multiplePrefixFailure() {
        assertParseFailure(parser, WEIGHT_VALID_WEIGHT + WEIGHT_VALID_WEIGHT, MESSAGE_MULTIPLE_TAGS_ERROR);

    }

    @Test
    public void parse_invalidHeightFailure() {
        String expected = Height.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, INVALID_HEIGHT_DESC + GENDER_VALID_GENDER + WEIGHT_VALID_WEIGHT
                + IDEAL_VALID_WEIGHT, expected);
        assertParseFailure(parser, INVALID_HEIGHT_TOO_MUCH + GENDER_VALID_GENDER + WEIGHT_VALID_WEIGHT
                + IDEAL_VALID_WEIGHT, expected);
        assertParseFailure(parser, INVALID_HEIGHT_NEGATIVE + GENDER_VALID_GENDER + WEIGHT_VALID_WEIGHT
                + IDEAL_VALID_WEIGHT, expected);
    }

    @Test
    public void parse_invalidWeightFailure() {
        String expected = Weight.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, INVALID_WEIGHT_DESC + GENDER_VALID_GENDER + HEIGHT_VALID_HEIGHT
                + IDEAL_VALID_WEIGHT, expected);
        assertParseFailure(parser, INVALID_WEIGHT_NEGATIVE + GENDER_VALID_GENDER + HEIGHT_VALID_HEIGHT
                + IDEAL_VALID_WEIGHT, expected);
        assertParseFailure(parser, INVALID_WEIGHT_TOO_MUCH + GENDER_VALID_GENDER + HEIGHT_VALID_HEIGHT
                + IDEAL_VALID_WEIGHT, expected);
    }

    @Test
    public void parse_invalidGenderFailure() {
        String expected = Gender.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, INVALID_GENDER_DESC + WEIGHT_VALID_WEIGHT + HEIGHT_VALID_HEIGHT
                + IDEAL_VALID_WEIGHT, expected);
    }

    @Test
    public void parse_invalidIdealFailure() {
        String expected = Weight.MESSAGE_CONSTRAINTS;
        assertParseFailure(parser, INVALID_IDEAL_DESC + GENDER_VALID_GENDER + HEIGHT_VALID_HEIGHT
                + WEIGHT_VALID_WEIGHT, expected);
        assertParseFailure(parser, INVALID_IDEAL_NEGATIVE + GENDER_VALID_GENDER + HEIGHT_VALID_HEIGHT
                + WEIGHT_VALID_WEIGHT, expected);
        assertParseFailure(parser, INVALID_IDEAL_TOO_MUCH + GENDER_VALID_GENDER + HEIGHT_VALID_HEIGHT
                + WEIGHT_VALID_WEIGHT, expected);
    }


}
