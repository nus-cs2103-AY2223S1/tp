package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_CLASS_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_INDEX;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1231S;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taassist.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.AssignCommand;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.testutil.ModuleClassBuilder;

public class AssignCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.COMMAND_WORD, AssignCommand.MESSAGE_USAGE);
    private AssignCommandParser parser = new AssignCommandParser();

    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        String userInput = INVALID_INDEX + CLASS_DESC_CS1231S;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, CLASS_DESC_CS1231S, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidModuleClass_failure() {
        String userInput = INDEX_FIRST_STUDENT + INVALID_CLASS_DESC;
        assertParseFailure(parser, userInput, ModuleClass.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingModuleClass_failure() {
        assertParseFailure(parser, INDEX_FIRST_STUDENT.toString(), MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validSingleIndexAndModuleClass_success() {
        List<Index> indices = new ArrayList<>();
        indices.add(INDEX_FIRST_STUDENT);
        ModuleClass moduleClass = new ModuleClassBuilder().withName(VALID_CLASS_CS1231S).build();
        String userInput = INDEX_FIRST_STUDENT + CLASS_DESC_CS1231S;
        assertParseSuccess(parser, userInput, new AssignCommand(indices, moduleClass));
    }

    @Test
    public void parse_validMultipleIndicesAndModuleClass_success() {
        List<Index> indices = new ArrayList<>();
        indices.add(INDEX_FIRST_STUDENT);
        indices.add(INDEX_THIRD_STUDENT);
        ModuleClass moduleClass = new ModuleClassBuilder().withName(VALID_CLASS_CS1231S).build();
        String userInput = INDEX_FIRST_STUDENT + " " + INDEX_THIRD_STUDENT + CLASS_DESC_CS1231S;
        assertParseSuccess(parser, userInput, new AssignCommand(indices, moduleClass));
    }
}
