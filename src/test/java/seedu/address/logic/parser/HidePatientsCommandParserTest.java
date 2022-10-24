package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HidePatientsCommand;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class HidePatientsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, HidePatientsCommand.MESSAGE_USAGE);
    private HidePatientsCommandParser parser = new HidePatientsCommandParser();


    @Test
    public void parse_validArgs_returnHideCommand() {
        assertParseSuccess(parser, "sam", new HidePatientsCommand(
                new NameContainsKeywordsPredicate(Arrays.asList("sam"))));
    }

    @Test
    public void parse_invalidArgs_fails() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
