package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListPersonsCommand;
import seedu.address.model.person.PersonSortField;

public class ListPersonCommandParserTest {

    private static final String VALID_LOWERCASE_NAME_FIELD = "n";
    private static final String VALID_UPPERCASE_NAME_FIELD = "N";

    private static final String VALID_LOWERCASE_GENDER_FIELD = "g";
    private static final String VALID_UPPERCASE_GENDER_FIELD = "G";

    private static final String VALID_LOWERCASE_DOB_FIELD = "d";
    private static final String VALID_UPPERCASE_DOB_FIELD = "D";

    private static final String WHITESPACE = " ";
    private static final String EMPTY_STRING = "";


    private static final String LOWERCASE_NAME_DESC = WHITESPACE + PREFIX_SORT + VALID_LOWERCASE_NAME_FIELD;
    private static final String UPPERCASE_NAME_DESC = WHITESPACE + PREFIX_SORT + VALID_UPPERCASE_NAME_FIELD;

    private static final String LOWERCASE_GENDER_DESC = WHITESPACE + PREFIX_SORT + VALID_LOWERCASE_GENDER_FIELD;
    private static final String UPPERCASE_GENDER_DESC = WHITESPACE + PREFIX_SORT + VALID_UPPERCASE_GENDER_FIELD;

    private static final String LOWERCASE_DOB_DESC = WHITESPACE + PREFIX_SORT + VALID_LOWERCASE_DOB_FIELD;
    private static final String UPPERCASE_DOB_DESC = WHITESPACE + PREFIX_SORT + VALID_UPPERCASE_DOB_FIELD;


    private static final String INVALID_WHITESPACE_FIELD_DESC = WHITESPACE + PREFIX_SORT + " ";
    private static final String INVALID_SINGLE_LETTER_FIELD_DESC = WHITESPACE + PREFIX_SORT + "x";
    private static final String INVALID_MULTIPLE_LETTERS_FIELD_DESC = WHITESPACE + PREFIX_SORT + "xyz";


    private ListPersonsCommandParser parser = new ListPersonsCommandParser();

    @Test
    public void parse_validSortField_success() {
        PersonSortField expectedSortFieldName = PersonSortField.createSortField(VALID_LOWERCASE_NAME_FIELD);
        PersonSortField expectedSortFieldGender = PersonSortField.createSortField(VALID_LOWERCASE_GENDER_FIELD);
        PersonSortField expectedSortFieldDob = PersonSortField.createSortField(VALID_LOWERCASE_DOB_FIELD);

        // Name sort field
        assertParseSuccess(parser, LOWERCASE_NAME_DESC, new ListPersonsCommand(expectedSortFieldName));
        assertParseSuccess(parser, UPPERCASE_NAME_DESC, new ListPersonsCommand(expectedSortFieldName));

        // Gender sort field
        assertParseSuccess(parser, LOWERCASE_GENDER_DESC, new ListPersonsCommand(expectedSortFieldGender));
        assertParseSuccess(parser, UPPERCASE_GENDER_DESC, new ListPersonsCommand(expectedSortFieldGender));

        // DOB sort field
        assertParseSuccess(parser, LOWERCASE_DOB_DESC, new ListPersonsCommand(expectedSortFieldDob));
        assertParseSuccess(parser, UPPERCASE_DOB_DESC, new ListPersonsCommand(expectedSortFieldDob));

        // Multiple sort fields - last sort field accepted
        assertParseSuccess(parser, LOWERCASE_DOB_DESC + LOWERCASE_NAME_DESC,
                new ListPersonsCommand(expectedSortFieldName));

        // Additional whitespace preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LOWERCASE_NAME_DESC,
                new ListPersonsCommand(expectedSortFieldName));
    }

    @Test
    public void parse_noSortField_success() {
        PersonSortField expectedNoSortField = PersonSortField.sortByNoField();
        assertParseSuccess(parser, EMPTY_STRING, new ListPersonsCommand(expectedNoSortField));
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessageNonEmptyPreamble =
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListPersonsCommand.MESSAGE_USAGE);

        // Non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LOWERCASE_NAME_DESC, expectedMessageNonEmptyPreamble);

        // Invalid sort field - whitespace
        assertParseFailure(parser, INVALID_WHITESPACE_FIELD_DESC, PersonSortField.MESSAGE_CONSTRAINTS);

        // Invalid sort field - single letter
        assertParseFailure(parser, INVALID_SINGLE_LETTER_FIELD_DESC, PersonSortField.MESSAGE_CONSTRAINTS);

        // Invalid sort field - multiple letters
        assertParseFailure(parser, INVALID_MULTIPLE_LETTERS_FIELD_DESC, PersonSortField.MESSAGE_CONSTRAINTS);
    }
}
