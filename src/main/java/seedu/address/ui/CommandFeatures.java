package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Type class for tableview
 */
public class CommandFeatures {

    private static final CommandFeatures ADDPERSON_COMMAND_FEATURES = new CommandFeatures(
            "addperson",
            "Adds a person to the address book.",
            "addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]",
            "addperson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01"
            );

    private static final CommandFeatures LISTPERSONS_COMMAND_FEATURES = new CommandFeatures(
            "listpersons",
            "Shows a list of all persons in the address book.",
            "listpersons",
            "listpersons"
    );

    private static final CommandFeatures EDITPERSON_COMMAND_FEATURES = new CommandFeatures(
            "editperson",
            "Edits an existing person in the address book.",
            "editperson NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] ...",
            "editperson John Doe p/91234567 e/johndoe@example.com"
    );

    private static final CommandFeatures FINDPERSON_COMMAND_FEATURES = new CommandFeatures(
            "findperson",
            "Finds persons whose names contain any of the given keywords.",
            "findperson KEYWORD [MORE_KEYWORDS]",
            "findperson Alex David"
    );

    private static final CommandFeatures DELETEPERSON_COMMAND_FEATURES = new CommandFeatures(
            "deleteperson",
            "Deletes the specified person from the address book.",
            "deleteperson NAME",
            "deleteperson Betsy"
    );

    private static final CommandFeatures ADDGROUP_COMMAND_FEATURES = new CommandFeatures(
            "addgroup",
            "Creates a new group with no members.",
            "addgroup GROUP",
            "addgroup CS2103T"
    );

    private static final CommandFeatures ADDMEMBER_COMMAND_FEATURES = new CommandFeatures(
            "addmember",
            "Adds an existing person to an existing group in TABS.",
            "addmember GROUP NAME",
            "addmember CS2103T UserName"
    );

    private static final CommandFeatures ASSIGNTASK_COMMAND_FEATURES = new CommandFeatures(
            "assigntask",
            "Assigns a task to a person with a group in TABS.",
            "assigntask NAME g/GROUP task/TASK",
            "assigntask John g/CS2103T task/TeamProject"
    );

    private static final CommandFeatures DELETETASK_COMMAND_FEATURES = new CommandFeatures(
            "deletetask",
            "Deletes a task from a person in a group in TABS.",
            "deletetask NAME g/GROUP task/TASK",
            "deletetask John g/CS2103T task/TeamProject"
    );

    private static final CommandFeatures DISPLAY_COMMAND_FEATURES = new CommandFeatures(
            "display",
            "Displays the group members allocated to the specified group. "
                    + "Instead of details, assigned tasks will be listed.",
            "display GROUP",
            "display CS2103T"
    );

    private static final CommandFeatures DELETEGROUP_COMMAND_FEATURES = new CommandFeatures(
            "deletegroup",
            "Deletes the specified group from the address book.",
            "deletegroup GROUP",
            "deletegroup CS2103T"
    );

    private static final CommandFeatures EXIT_COMMAND_FEATURES = new CommandFeatures(
            "exit",
            "Exits the program.",
            "exit",
            "exit"
    );

    private SimpleStringProperty command;
    private SimpleStringProperty description;

    /**
     * Constructor to build a CommandFeatures with details of existing commands
     */
    public CommandFeatures(String command, String description, String format, String example) {
        this.command = new SimpleStringProperty(command);
        this.description = new SimpleStringProperty(String.format((
                "%s\nFormat : %s\n"),
                description, format));
    }
    public static ObservableList<CommandFeatures> getCommandFeaturesList() {
        return FXCollections.observableArrayList(
                ADDPERSON_COMMAND_FEATURES,
                LISTPERSONS_COMMAND_FEATURES,
                EDITPERSON_COMMAND_FEATURES,
                FINDPERSON_COMMAND_FEATURES,
                DELETEPERSON_COMMAND_FEATURES,
                ADDGROUP_COMMAND_FEATURES,
                ADDMEMBER_COMMAND_FEATURES,
                ASSIGNTASK_COMMAND_FEATURES,
                DELETETASK_COMMAND_FEATURES,
                DISPLAY_COMMAND_FEATURES,
                DELETEGROUP_COMMAND_FEATURES,
                EXIT_COMMAND_FEATURES
        );
    }

    public String getCommand() {
        return this.command.get();
    }

    public String getDescription() {
        return this.description.get();
    }
}
