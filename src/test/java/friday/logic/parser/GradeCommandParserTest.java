package friday.logic.parser;

/*
import static friday.logic.parser.CliSyntax.PREFIX_RA1;
import static friday.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import friday.commons.core.index.Index;
import friday.logic.commands.GradeCommand;
*/

public class GradeCommandParserTest {

    private GradeCommandParser parser = new GradeCommandParser();
    private final String nonEmptyScore = "69.99";

    /*
    @Test
    public void parse_indexSpecified_success() {
        // have remark
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_RA1 + nonEmptyScore;
//        GradeCommand.EditGradeDescriptor descriptor =
//        GradeCommand expectedCommand = new GradeCommand(INDEX_FIRST_STUDENT, new Grade(nonEmptyRemark));
//        assertParseSuccess(parser, userInput, expectedCommand);
//
//        // no remark
//        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
//        expectedCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(""));
//        assertParseSuccess(parser, userInput, expectedCommand);
    }
    */
}
