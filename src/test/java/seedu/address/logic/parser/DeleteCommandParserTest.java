package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RACE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELIGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SURVEY_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.Race;
import seedu.address.model.person.Religion;
import seedu.address.model.person.Survey;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations outside of the DeleteCommand code. For example, inputs "1" and "1
 * abc" take the same path through the DeleteCommand, and therefore we test only
 * one of them. The path variation for those two cases occur inside the
 * ParserUtil, and therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgsIndex_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(Optional.of(INDEX_FIRST_PERSON), Optional.empty(),
                Optional.empty(), Optional.empty()));
    }

    @Test
    public void parse_invalidArgsIndex_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsRace_returnsDeleteCommand() {
        Race race = new Race(VALID_RACE_AMY);
        assertParseSuccess(parser, " ra/Chinese",
                new DeleteCommand(Optional.empty(), Optional.of(race), Optional.empty(), Optional.empty()));
    }

    @Test
    public void parse_invalidArgsRace_throwsParseException() {
        assertParseFailure(parser, " ra/123", Race.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgsReligion_returnsDeleteCommand() {
        Religion religion = new Religion(VALID_RELIGION_BOB);
        assertParseSuccess(parser, " re/Muslim",
                new DeleteCommand(Optional.empty(), Optional.empty(), Optional.of(religion), Optional.empty()));
    }

    @Test
    public void parse_invalidArgsReligion_throwsParseException() {
        assertParseFailure(parser, " re/75575", Religion.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validArgsSurvey_returnsDeleteCommand() {
        Survey survey = new Survey(VALID_SURVEY_AMY);
        assertParseSuccess(parser, " s/Environment Survey",
                new DeleteCommand(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(survey)));
    }
}
