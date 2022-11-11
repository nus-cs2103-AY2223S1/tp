package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.DeleteTagCommand;
import seedu.intrack.model.tag.Tag;

public class DeleteTagCommandParserTest {
    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    private Tag urgent = new Tag("Urgent");
    private Tag remote = new Tag("Remote");
    private Tag help = new Tag("Help");
    private List<Tag> taglistStub0 = new ArrayList<Tag>(Arrays.asList(remote, urgent));

    @Test
    public void parse_inputSpecified_success() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = " " + targetIndex.getOneBased() + " Remote" + " Urgent";
        DeleteTagCommand expectedCommand = new DeleteTagCommand(targetIndex, taglistStub0);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, DeleteTagCommand.COMMAND_WORD, expectedMessage);
    }
}
