package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.parser.CliSyntax.PREFIX_CONTACT;
import static swift.logic.parser.CliSyntax.PREFIX_TASK;
import static swift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static swift.testutil.TypicalPersonIndexes.INDEX_FIRST_PERSON;
import static swift.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import swift.logic.commands.UnassignCommand;

public class UnassignCommandParserTest {

    private UnassignCommandParser parser = new UnassignCommandParser();

    @Test
    public void parse_validArgs_returnsAssignCommand() {
        assertParseSuccess(parser, " " + PREFIX_CONTACT + "1 " + PREFIX_TASK + "1",
                new UnassignCommand(INDEX_FIRST_PERSON, INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String
                .format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
    }
}
