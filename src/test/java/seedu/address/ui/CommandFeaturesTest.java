package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

public class CommandFeaturesTest {

    private static final CommandFeatures ADDPERSON_COMMAND_FEATURES = new CommandFeatures(
            "addperson",
            "Adds a person to the address book.",
            "addperson n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]",
            "addperson n/John Doe p/98765432 e/john@example.com a/31, Clementi Ave 2, #025 t/friend t/owesMoney"
    );

    private static final CommandFeatures ADDGROUP_COMMAND_FEATURES = new CommandFeatures(
            "addgroup",
            "Adds an empty group to the address book.",
            "addgroup g/GROUP",
            " addgroup g/CS2103T Team Project"
    );

    private static final CommandFeatures ASSIGNTASK_COMMAND_FEATURES = new CommandFeatures(
            "assigntask",
            "Assigns a task to a person with the given name in a group.",
            "assigntask NAME g/GROUP task/TASK w/WORKLOAD [d/DEADLINE]",
            "assigntask Alice g/Group Alpha task/Coursework 0 w/High d/2022-01-01 23:59"
    );

    private static final CommandFeatures CLEAR_COMMAND_FEATURES = new CommandFeatures(
            "clear",
            "Clears all entries from the address book.",
            "clear",
            "clear"
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
        ObservableList<CommandFeatures> commandFeatureList = CommandFeatures.getCommandFeaturesList();
        assertEquals(commandFeatureList.get(0).getCommand(), ADDPERSON_COMMAND_FEATURES.getCommand());
        assertEquals(commandFeatureList.get(0).getDescription(), ADDPERSON_COMMAND_FEATURES.getDescription());

        ObservableList<CommandFeatures> personFeatureList = CommandFeatures.getPersonFeaturesList();

        assertEquals(personFeatureList.get(0).getCommand(), ADDPERSON_COMMAND_FEATURES.getCommand());
        assertEquals(personFeatureList.get(0).getDescription(), ADDPERSON_COMMAND_FEATURES.getDescription());

        ObservableList<CommandFeatures> groupFeatureList = CommandFeatures.getGroupFeaturesList();

        assertEquals(groupFeatureList.get(0).getCommand(), ADDGROUP_COMMAND_FEATURES.getCommand());
        assertEquals(groupFeatureList.get(0).getDescription(), ADDGROUP_COMMAND_FEATURES.getDescription());

        ObservableList<CommandFeatures> taskFeatureList = CommandFeatures.getTaskFeaturesList();

        assertEquals(taskFeatureList.get(0).getCommand(), ASSIGNTASK_COMMAND_FEATURES.getCommand());
        assertEquals(taskFeatureList.get(0).getDescription(), ASSIGNTASK_COMMAND_FEATURES.getDescription());

        ObservableList<CommandFeatures> utilityFeatureList = CommandFeatures.getUtilityFeaturesList();

        assertEquals(utilityFeatureList.get(0).getCommand(), CLEAR_COMMAND_FEATURES.getCommand());
        assertEquals(utilityFeatureList.get(0).getDescription(), CLEAR_COMMAND_FEATURES.getDescription());
    }

}
