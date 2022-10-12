package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.testutil.TypicalPersons.CARL;
import static seedu.address.model.person.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGroupMemberCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class AddGroupMemberCommandParserTest {
    private AddUserCommandParser parser = new AddUserCommandParser();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void parse_validArgs_returnsAddUserCommandCommand() {
        Person personToAddGroup = model.getFilteredPersonList().get(CARL);
        assertParseSuccess(parser, "g/Alpha n/",
                new AddGroupMemberCommand("Alpha", personToAddGroup.getName().fullName));
    }
}
