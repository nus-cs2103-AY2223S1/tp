package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Person;

public class DeletePersonCommandParserTest {
    private DeletePersonCommandParser parser = new DeletePersonCommandParser();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void parse_validArgs_returnsDeletePersonCommand() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FullNamePredicate predicate = new FullNamePredicate(personToDelete.getName().toString());
        assertParseSuccess(parser, personToDelete.getName().toString(), new DeletePersonCommand(predicate));
    }


}
