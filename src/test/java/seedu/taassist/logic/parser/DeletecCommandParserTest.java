package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1101S;
import static seedu.taassist.logic.commands.CommandTestUtil.CLASS_DESC_CS1231S;
import static seedu.taassist.logic.commands.CommandTestUtil.INVALID_CLASS_DESC;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.taassist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.taassist.logic.commands.CommandTestUtil.VALID_CLASS_CS1101S;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_MODULE_CLASS;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.taassist.testutil.TestUtil.joinWithSpace;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1101S;
import static seedu.taassist.testutil.TypicalModuleClasses.CS1231S;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.DeletecCommand;
import seedu.taassist.model.moduleclass.ModuleClass;

public class DeletecCommandParserTest {
    private DeletecCommandParser parser = new DeletecCommandParser();

    @Test
    public void parse_emptyUserInput_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletecCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingPrefix_failure() {
        assertParseFailure(parser, VALID_CLASS_CS1101S, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeletecCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CLASS_DESC_CS1101S,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletecCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_prefixWithEmptyModuleClass_failure() {
        assertParseFailure(parser, PREFIX_MODULE_CLASS + PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletecCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidModuleClassName_failure() {
        assertParseFailure(parser, INVALID_CLASS_DESC, ModuleClass.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validSingleModuleClassPresent_success() {
        Set<ModuleClass> moduleClassSet = new HashSet<>();
        moduleClassSet.add(CS1231S);
        assertParseSuccess(parser, CLASS_DESC_CS1231S, new DeletecCommand(moduleClassSet));
    }

    @Test
    public void parse_multipleModuleClassPresent_success() {
        Set<ModuleClass> moduleClassSet = new HashSet<>();
        moduleClassSet.add(CS1231S);
        moduleClassSet.add(CS1101S);
        assertParseSuccess(parser, joinWithSpace(CLASS_DESC_CS1101S, CLASS_DESC_CS1231S),
                new DeletecCommand(moduleClassSet));
    }
}
