package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

public class CommandFeaturesTest {

    private static final CommandFeatures ADDPERSON_COMMAND_FEATURES = new CommandFeatures(
            "addperson",
            "Adds a person to the address book.",
            "addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]",
            "addperson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01"
    );

    @Test
    public void constructor_commandFeatures_success() {
        CommandFeatures feature = new CommandFeatures(
                "testcommand",
                "command for testing",
                "testcommand format",
                "testcommand example");

        String command = "testcommand";
        String description = "command for testing\nFormat : testcommand format\n";

        assertTrue(feature instanceof CommandFeatures);
        assertEquals(feature.getCommand(), command);
        assertEquals(feature.getDescription(), description);
    }

    @Test
    public void getCommandFeaturesList_success() {
        ObservableList<CommandFeatures> featureList = CommandFeatures.getCommandFeaturesList();

        assertEquals(featureList.get(0).getCommand(), ADDPERSON_COMMAND_FEATURES.getCommand());
        assertEquals(featureList.get(0).getDescription(), ADDPERSON_COMMAND_FEATURES.getDescription());
    }

}
