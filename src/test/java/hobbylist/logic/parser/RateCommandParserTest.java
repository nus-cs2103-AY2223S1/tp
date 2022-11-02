package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.commons.core.Messages.MESSAGE_INVALID_RATING;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.RateCommand;
import hobbylist.model.activity.Review;

public class RateCommandParserTest {
    private RateCommandParser parser = new RateCommandParser();

    @Test
    public void parse_emptyString_throwsParseException() {
        String expectedString = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "       ", expectedString);
    }

    @Test
    public void parse_ratingOutOfBounds_throwsParseException() {
        String expectedString = MESSAGE_INVALID_RATING;
        assertParseFailure(parser, "2 r/10", expectedString);
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        String expectedString = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "test r/2", expectedString);
    }

    @Test
    public void parse_correctInputs_throwsParseException() {
        RateCommand expectedInput = new RateCommand(Index.fromOneBased(1), 2, Optional.of(new Review("great!")));
        assertParseSuccess(parser, "1 r/2 re/great!", expectedInput);
    }
}
