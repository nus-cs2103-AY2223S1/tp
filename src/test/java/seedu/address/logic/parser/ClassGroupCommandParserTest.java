package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClassGroupCommand;
import seedu.address.model.student.ClassGroup;

class ClassGroupCommandParserTest {

    private ClassGroupCommandParser parser = new ClassGroupCommandParser();
    private final String nonEmptyClassGroup = "Some class group.";

    @Test
    public void parse_indexSpecified_success() {
        // have class group
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_CLASS_GROUP + nonEmptyClassGroup;
        ClassGroupCommand expectedCommand =
                new ClassGroupCommand(INDEX_FIRST_STUDENT, new ClassGroup(nonEmptyClassGroup));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no class group
        userInput = targetIndex.getOneBased() + " " + PREFIX_CLASS_GROUP;
        expectedCommand = new ClassGroupCommand(INDEX_FIRST_STUDENT, new ClassGroup("NA"));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClassGroupCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, ClassGroupCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, ClassGroupCommand.COMMAND_WORD + " " + nonEmptyClassGroup, expectedMessage);
    }
}
