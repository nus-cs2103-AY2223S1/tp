package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations outside of
 * the DeleteCommand code. For example, inputs "1" and "1 abc" take the same path through the
 * DeleteCommand, and therefore we test only one of them. The path variation for those two cases
 * occur inside the ParserUtil, and therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private Parser<DeleteCommand<Person>> parserP = CmdBuilder.makeDelParserPerson();
    private Parser<DeleteCommand<Group>> parserG = CmdBuilder.makeDelParserGroup();
    private Parser<DeleteCommand<Task>> parserT = CmdBuilder.makeDelParserTask();

    @Test
    public void parsePerson_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parserP, "1", CmdBuilder.makeDelPerson(INDEX_FIRST));
    }

    @Test
    public void parsePerson_invalidArgs_throwsParseException() {
        assertParseFailure(parserP, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseTask_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parserT, "1", CmdBuilder.makeDelTask(INDEX_FIRST));
    }

    @Test
    public void parseTask_invalidArgs_throwsParseException() {
        assertParseFailure(parserT, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseGrp_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parserG, "1", CmdBuilder.makeDelGrp(INDEX_FIRST));
    }

    @Test
    public void parseGrp_invalidArgs_throwsParseException() {
        assertParseFailure(parserG, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
