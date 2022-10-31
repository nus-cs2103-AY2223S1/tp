package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Getter;
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

    Getter<Person> getter = (m, i) -> m.getFromFilteredPerson(i);
    Changer<Person> deleter = (m, item) -> m.deletePerson(item);
    Predicate<Object> tester = o -> o instanceof Person;

    private Parser<DeleteCommand<Person>> parserP = CmdBuilder.makeDelParserPerson();
    private Parser<DeleteCommand<Group>> parserG = CmdBuilder.makeDelParserGroup();
    private Parser<DeleteCommand<Task>> parserT = CmdBuilder.makeDelParserTask();

    @Test
    public void parse_person_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parserP, "1", CmdBuilder.makeDelPerson(INDEX_FIRST));
    }

    @Test
    public void parse_person_invalidArgs_throwsParseException() {
        assertParseFailure(parserP, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_task_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parserT, "1", CmdBuilder.makeDelTask(INDEX_FIRST));
    }

    @Test
    public void parse_task_invalidArgs_throwsParseException() {
        assertParseFailure(parserT, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_grp_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parserG, "1", CmdBuilder.makeDelGrp(INDEX_FIRST));
    }

    @Test
    public void parse_grp_invalidArgs_throwsParseException() {
        assertParseFailure(parserG, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
