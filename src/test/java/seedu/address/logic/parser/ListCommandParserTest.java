package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListModuleCommand;
import seedu.address.logic.commands.list.ListUnmarkedCommand;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;
import seedu.address.model.task.ModuleIsDonePredicate;

public class ListCommandParserTest {

    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseCommand_listAll() throws Exception {
        // no leading and trailing whitespaces
        String str = ListCommand.COMMAND_WORD + " " + ListAllCommand.COMMAND_WORD;
        assertTrue(parser.parse(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
        assertTrue(parser.parse(ListAllCommand.COMMAND_WORD + " 3") instanceof ListAllCommand);

    }

    @Test
    public void parseCommand_listUnmarked() throws Exception {
        // no leading and trailing whitespaces
        ListUnmarkedCommand expectedCommand =
                new ListUnmarkedCommand(new ModuleIsDonePredicate(List.of("false")));
        assertParseSuccess(parser, "-u false", expectedCommand);
    }

    @Test
    public void parseCommand_listModule() throws Exception {
        // no leading and trailing whitespaces
        ListModuleCommand expectedCommand =
                new ListModuleCommand(new ModuleContainsKeywordsPredicate(List.of("CS2100")));
        assertParseSuccess(parser, "--module CS2100", expectedCommand);
    }

}
