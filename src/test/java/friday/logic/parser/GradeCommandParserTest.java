package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.PREFIX_FINALS;
import static friday.logic.parser.CommandParserTestUtil.assertParseFailure;
import static friday.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import friday.commons.core.index.Index;
import friday.logic.commands.GradeCommand;
import friday.logic.commands.GradeCommand.EditGradeDescriptor;
import friday.testutil.EditGradeDescriptorBuilder;

public class GradeCommandParserTest {

    private GradeCommandParser parser = new GradeCommandParser();
    private final String nonEmptyScore = "69.99";

    @Test
    public void parse_indexSpecified_success() {
        // have grade
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_FINALS + nonEmptyScore;
        EditGradeDescriptor descriptor = new EditGradeDescriptorBuilder()
                .withGrade("Finals", nonEmptyScore).build();
        GradeCommand expectedCommand = new GradeCommand(INDEX_FIRST_STUDENT, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, GradeCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, GradeCommand.COMMAND_WORD + " " + nonEmptyScore, expectedMessage);

        // no score
        assertParseFailure(parser, GradeCommand.COMMAND_WORD + " " + PREFIX_FINALS, expectedMessage);
    }

}
