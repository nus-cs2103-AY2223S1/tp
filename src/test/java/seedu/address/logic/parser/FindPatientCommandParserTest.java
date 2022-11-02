package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailureForPrefix;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindPatientCommandParserTest {
    private FindPatientCommandParser parser = new FindPatientCommandParser();
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() throws ParseException {
        // no leading and trailing whitespaces
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        FindPatientCommand firstCommand = parser.parse(" n/pauline");
        expectedModel.updateFilteredPatientList(firstCommand.getPredicate());
        assertCommandSuccess(firstCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPatientList());

        // multiple whitespaces between keywords
        FindPatientCommand secondCommand = parser.parse(" n/    pauline  ");
        expectedModel.updateFilteredPatientList(secondCommand.getPredicate());
        assertCommandSuccess(secondCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPatientList());
    }

    @Test
    public void checkNumberOfPrefixes() {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" a/west a/ave", PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK);
        assertParseFailureForPrefix(parser, PREFIX_ADDRESS, argMultimap, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPatientCommand.MESSAGE_USAGE));
    }

    @Test
    public void createPredicateString() throws ParseException {
        String testString = parser.createPredicateString("   alice      pauline  ".trim());
        String expectedMessage = String.format(MESSAGE_PATIENTS_LISTED_OVERVIEW, 1);
        FindPatientCommand firstCommand = parser.parse(" n/" + testString);
        expectedModel.updateFilteredPatientList(firstCommand.getPredicate());
        assertCommandSuccess(firstCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPatientList());
    }
}
