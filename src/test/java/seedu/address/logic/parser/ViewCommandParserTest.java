package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.PersonContainsAttributePredicate;



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
        PersonContainsAttributePredicate testPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("male"), new ArrayList<>(),
                        List.of("chinese"), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());
        ViewCommand expectedViewCommand = new ViewCommand(testPredicate);

        assertParseSuccess(parser, " g/male ra/chinese", expectedViewCommand);

        // repeated prefixes
        assertParseSuccess(parser, " g/female g/male ra/italian ra/chinese", expectedViewCommand);
    }

}
