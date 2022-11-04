package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE_PATH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TestUtil.getFilePathInSandboxFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.testutil.PersonBuilder;

public class ImportCommandParserTest {

    private ImportCommandParser parser = new ImportCommandParser();

    private Path dummyPath = getFilePathInSandboxFolder("foobar.json");
    private File dummyFile = new File(dummyPath.toUri());

    @Test
    public void parse_validArgs_returnsImportCommand() {
        Model validModel = new ModelManager(new AddressBook(), new UserPrefs());
        Person validPerson = new PersonBuilder().build();
        validModel.addPerson(validPerson);
        JsonAddressBookStorage storage = new JsonAddressBookStorage(dummyPath);
        try {
            dummyFile.createNewFile();
            storage.saveAddressBook(validModel.getAddressBook());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertParseSuccess(parser, "src/test/data/sandbox/foobar.json", new ImportCommand(dummyPath));
        dummyFile.delete();
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_FILE_PATH, ImportCommand.MESSAGE_USAGE));
    }
}
