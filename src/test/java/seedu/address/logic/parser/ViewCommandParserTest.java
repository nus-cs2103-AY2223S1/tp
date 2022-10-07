package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.PersonContainsAttributePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations outside of the DeleteCommand code. For example, inputs "1" and "1
 * abc" take the same path through the DeleteCommand, and therefore we test only
 * one of them. The path variation for those two cases occur inside the
 * ParserUtil, and therefore should be covered by the ParserUtilTest.
 */
public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgsIndex_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ViewCommand expectedViewCommand =
                new ViewCommand(new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("male"), new ArrayList<>(),
                        List.of("chinese"), new ArrayList<>(), new ArrayList<>()));

        assertParseSuccess(parser, " g/male ra/chinese", expectedViewCommand);

        // repeated prefixes
        assertParseSuccess(parser, " g/female g/male ra/italian ra/chinese", expectedViewCommand);
    }

}
