package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SetPersonFileCommand;
import seedu.address.model.person.FilePath;

public class SetPersonFileCommandParserTest {

    private final SetPersonFileCommandParser parser = new SetPersonFileCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SetPersonFileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSetPersonFileCommand() {
        // no leading and trailing whitespaces
        String userInputFirst = " 2 " + PREFIX_FILEPATH + "src/test/data/TestPDFs/Test_PDF6.pdf";
        SetPersonFileCommand expectedSetPersonFileCommand =
                new SetPersonFileCommand(
                        Index.fromOneBased(2), new FilePath("src/test/data/TestPDFs/Test_PDF6.pdf"));
        assertParseSuccess(parser, userInputFirst, expectedSetPersonFileCommand);

        String userInputSecond = "\n 2 " + PREFIX_FILEPATH + "\n src/test/data/TestPDFs/Test_PDF6.pdf \n \t  \t";
        // multiple whitespaces between keywords
        assertParseSuccess(parser, userInputSecond, expectedSetPersonFileCommand);
    }
}
