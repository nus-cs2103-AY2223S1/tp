package jeryl.fyp.logic.parser;

import static jeryl.fyp.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static jeryl.fyp.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.commands.DeleteStudentCommand;
import jeryl.fyp.logic.commands.FindCommand;
import jeryl.fyp.logic.commands.FindProjectNameCommand;
import jeryl.fyp.logic.commands.FindTagsCommand;
import jeryl.fyp.logic.commands.HelpCommand;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.commands.SortProjectNameCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_commandWordArgs_returnsHelpSingleCommand() {
        assertParseSuccess(parser, AddStudentCommand.COMMAND_WORD, new HelpCommand(
                AddStudentCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, AddStudentCommand.ALTERNATIVE_COMMAND_WORD, new HelpCommand(
                AddStudentCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, DeleteStudentCommand.COMMAND_WORD, new HelpCommand(
                DeleteStudentCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, DeleteStudentCommand.ALTERNATIVE_COMMAND_WORD, new HelpCommand(
                DeleteStudentCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, SortProjectNameCommand.COMMAND_WORD, new HelpCommand(
                SortProjectNameCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, SortProjectNameCommand.ALTERNATIVE_COMMAND_WORD, new HelpCommand(
                SortProjectNameCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, FindProjectNameCommand.COMMAND_WORD, new HelpCommand(
                FindProjectNameCommand.MESSAGE_USAGE));
        assertParseSuccess(parser, FindProjectNameCommand.ALTERNATIVE_COMMAND_WORD, new HelpCommand(
                FindCommand.MESSAGE_USAGE)); // special treatment

        // multiple command words, the first one is returned
        assertParseSuccess(parser, MarkCommand.COMMAND_WORD + " " + FindTagsCommand.COMMAND_WORD,
                new HelpCommand(MarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonCommandWordArgs_returnsRegularHelpCommand() {
        assertParseSuccess(parser, "a", new HelpCommand());
        assertParseSuccess(parser, "", new HelpCommand());
        assertParseSuccess(parser, VALID_STUDENT_ID_AMY, new HelpCommand());

        // invalid followed by valid
        assertParseSuccess(parser, VALID_STUDENT_ID_AMY + " " + MarkCommand.COMMAND_WORD,
                new HelpCommand());
    }
}
