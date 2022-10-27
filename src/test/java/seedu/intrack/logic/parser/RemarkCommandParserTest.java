package seedu.intrack.logic.parser;

public class RemarkCommandParserTest {
    private RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "New remark.";

    /*
    @Test
    public void parse_indexSpecified_success() {
        // remark present
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK + nonEmptyRemark;
        RemarkCommand expectedCommand = new RemarkCommand(INDEX_FIRST_INTERNSHIP, new Remark(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark not present
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new RemarkCommand(INDEX_FIRST_INTERNSHIP, new Remark(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }
    */
}
