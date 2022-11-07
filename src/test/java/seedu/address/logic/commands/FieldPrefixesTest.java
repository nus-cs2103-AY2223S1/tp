package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.FieldPrefixes;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FieldPrefixesTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void prefix_test() throws ParseException {
        FieldPrefixes fieldPrefixesStub = new FieldPrefixes();
        Prefix prefixStub = new Prefix("/v");

        fieldPrefixesStub.addPrefix(prefixStub, "Vaccination", model);
        assertTrue(fieldPrefixesStub.contains(prefixStub));

        fieldPrefixesStub.removePrefix(prefixStub);
        assertFalse(fieldPrefixesStub.contains(prefixStub));
    }

    @Test
    public void matchesDefaultPrefixes_test() {
        FieldPrefixes fieldPrefixesStub = new FieldPrefixes();

        assertTrue(fieldPrefixesStub.matchesDefaultPrefixes(PREFIX_ADDRESS));
        assertTrue(fieldPrefixesStub.matchesDefaultPrefixes(PREFIX_NAME));
        assertTrue(fieldPrefixesStub.matchesDefaultPrefixes(PREFIX_EMAIL));
        assertTrue(fieldPrefixesStub.matchesDefaultPrefixes(PREFIX_PHONE));
        assertTrue(fieldPrefixesStub.matchesDefaultPrefixes(PREFIX_TAG));

        assertFalse(fieldPrefixesStub.matchesDefaultPrefixes(new Prefix("v/")));
    }
}
