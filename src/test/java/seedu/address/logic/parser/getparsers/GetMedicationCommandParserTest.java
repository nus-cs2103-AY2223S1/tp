package seedu.address.logic.parser.getparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.getcommands.GetMedicationCommand;
import seedu.address.model.person.MedicationContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.List;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class GetMedicationCommandParserTest {

    private GetMedicationCommandParser parser = new GetMedicationCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // empty input
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetMedicationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_garbledArg_returnsGetMedicationCommand() {
        // extra prefixes
        String userInput = "/inp /fn /outp";
        GetMedicationCommand expectedGetMedicationCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetMedicationCommand);

        // garbled input
        userInput = "asdfghjkl";
        expectedGetMedicationCommand = prepareCommand(userInput);
        assertParseSuccess(parser, userInput, expectedGetMedicationCommand);
    }

    @Test
    public void parse_validArgs_returnsGetMedicationCommand() {
        // no leading and trailing whitespaces
        GetMedicationCommand expectedGetMedicationCommand = prepareCommand("paracetamol ibuprofen");
        assertParseSuccess(parser, "paracetamol ibuprofen", expectedGetMedicationCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n paracetamol \n \t ibuprofen  \t", expectedGetMedicationCommand);
    }

    private GetMedicationCommand prepareCommand(String userInput) {
        String[] st = userInput.split("\\s+");
        List<String> medications = new ArrayList<>();
        for (String s : st) {
            medications.add(s);
        }
        return new GetMedicationCommand(new MedicationContainsKeywordsPredicate(medications));
    }
}
