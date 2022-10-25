package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Type class for tableview
 */
public class CommandFeatures {

    /**
     * PERSON COMMANDS
     */
    private static final CommandFeatures ADDPERSON_COMMAND_FEATURES = new CommandFeatures(
            "addperson",
            "Adds a person to the address book.",
            "addperson n/NAME p/PHONE e/EMAIL a/ADDRESS [t/TAG]",
            "addperson n/John Doe p/98765432 e/john@example.com a/31, Clementi Ave 2, #025 t/friend t/owesMoney"
    );

    private static final CommandFeatures DELETEPERSON_COMMAND_FEATURES = new CommandFeatures(
            "deleteperson",
            "Deletes the specified person from the address book.",
            "deleteperson NAME",
            "deleteperson John Doe"
    );

    private static final CommandFeatures EDITPERSON_COMMAND_FEATURES = new CommandFeatures(
            "editperson",
            "Edits an existing person in the address book.",
            "editperson NAME [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] ...",
            "editperson John n/Alice p/91234567 e/alice@example.com"
    );

    private static final CommandFeatures FINDPERSON_COMMAND_FEATURES = new CommandFeatures(
            "findperson",
            "Finds all persons whose names contain any of the specified keywords (case-insensitive) "
                    + "and displays them as\n" + "a list with index numbers.",
            "findperson KEYWORD [MORE_KEYWORDS]",
            "findperson Alex David"
    );

    private static final CommandFeatures LISTPERSONS_COMMAND_FEATURES = new CommandFeatures(
            "listpersons",
            "Shows a list of all persons in the address book.",
            "listpersons",
            "listpersons"
    );

    /**
     * GROUP COMMANDS
     */
    private static final CommandFeatures ADDGROUP_COMMAND_FEATURES = new CommandFeatures(
            "addgroup",
            "Adds an empty group to the address book.",
            "addgroup g/GROUP",
            " addgroup g/CS2103T Team Project"
    );

    private static final CommandFeatures DELETEGROUP_COMMAND_FEATURES = new CommandFeatures(
            "deletegroup",
            "Deletes the specified group from the address book.",
            "deletegroup g/GROUP",
            "deletegroup g/CS2103T Team Project"
    );

    private static final CommandFeatures ADDMEMBER_COMMAND_FEATURES = new CommandFeatures(
            "addmember",
            "Adds a member to a specified group.",
            "addmember g/GROUP n/NAME",
            "addmember g/Group Alpha n/Alice Chee"
    );

    private static final CommandFeatures DELETEMEMBER_COMMAND_FEATURES = new CommandFeatures(
            "deletemember",
            "Deletes a member from the specified group.",
            "deletemember g/GROUP n/NAME",
            "deletemember g/Group 1 n/Bobby Chua"
    );

    private static final CommandFeatures DISPLAYGROUP_COMMAND_FEATURES = new CommandFeatures(
            "displaygroup",
            "Shows the group identified by its name in the displayed group list.",
            "displaygroup GROUP",
            "displaygroup CS2103T Team Project"
    );

    private static final CommandFeatures LISTGROUPS_COMMAND_FEATURES = new CommandFeatures(
            "listgroups",
            "Lists all existing groups in TABS.",
            "listgroups",
            "listgroups"
    );

    /**
     * TASK COMMANDS
     */
    private static final CommandFeatures ASSIGNTASK_COMMAND_FEATURES = new CommandFeatures(
            "assigntask",
            "Assigns a task to a person with the given name in a group.",
            "assigntask NAME g/GROUP task/TASK w/WORKLOAD [d/DEADLINE]",
            "assigntask Alice g/Group Alpha task/Coursework 0 w/High d/2022-01-01 23:59"
    );

    private static final CommandFeatures DELETETASK_COMMAND_FEATURES = new CommandFeatures(
            "deletetask",
            "Deletes a task from a person with the given name in a group.",
            "deletetask NAME g/GROUP task/TASK",
            "deletetask Alice g/Group Alpha task/Coursework 0"
    );

    private static final CommandFeatures ASSIGNTASKALL_COMMAND_FEATURES = new CommandFeatures(
            "assigntaskall",
            "Assigns a task to all persons in a group. Members with the same task are ignored.",
            "assigntaskall g/GROUP task/TASKw/WORKLOAD d/DEADLINE",
            "assigntaskall g/Group Alpha task/Coursework 0 w/High d/2022-01-01 23:59"
    );

    private static final CommandFeatures DELETETASKALL_COMMAND_FEATURES = new CommandFeatures(
            "deletetaskall",
            "Deletes a task from all persons in a group. Members without this task are ignored.",
            "deletetaskall g/GROUP task/TASK",
            "deletetaskall g/Group Alpha task/Coursework 0"
    );

    /**
     * UTILITY COMMANDS
     */

    private static final CommandFeatures CLEAR_COMMAND_FEATURES = new CommandFeatures(
            "clear",
            "Clears all entries from the address book.",
            "clear",
            "clear"
    );

    private static final CommandFeatures EXIT_COMMAND_FEATURES = new CommandFeatures(
            "exit",
            "Exits the program.",
            "exit",
            "exit"
    );

    private static final CommandFeatures HELP_COMMAND_FEATURES = new CommandFeatures(
            "help",
            "Shows a list of commands explaining how to use the app and a message to the user guide.",
            "help",
            "help"
    );

    private static final CommandFeatures _COMMAND_FEATURES = new CommandFeatures(
            "",
            "",
            "",
            ""
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
        ObservableList<CommandFeatures> obsList = getPersonFeaturesList();
        obsList.addAll(getGroupFeaturesList());
        obsList.addAll(getTaskFeaturesList());
        obsList.addAll(getUtilityFeaturesList());

        return obsList;
    }
    public static ObservableList<CommandFeatures> getPersonFeaturesList() {
        return FXCollections.observableArrayList(
                ADDPERSON_COMMAND_FEATURES,
                DELETEPERSON_COMMAND_FEATURES,
                EDITPERSON_COMMAND_FEATURES,
                FINDPERSON_COMMAND_FEATURES,
                LISTPERSONS_COMMAND_FEATURES
        );
    }

    public static ObservableList<CommandFeatures> getGroupFeaturesList() {
        return FXCollections.observableArrayList(
                ADDGROUP_COMMAND_FEATURES,
                DELETEGROUP_COMMAND_FEATURES,
                ADDMEMBER_COMMAND_FEATURES,
                DELETEMEMBER_COMMAND_FEATURES,
                DISPLAYGROUP_COMMAND_FEATURES,
                LISTGROUPS_COMMAND_FEATURES
        );
    }

    public static ObservableList<CommandFeatures> getTaskFeaturesList() {
        return FXCollections.observableArrayList(
                ASSIGNTASK_COMMAND_FEATURES,
                DELETETASK_COMMAND_FEATURES,
                ASSIGNTASKALL_COMMAND_FEATURES,
                DELETETASKALL_COMMAND_FEATURES
        );
    }

    public static ObservableList<CommandFeatures> getUtilityFeaturesList() {
        return FXCollections.observableArrayList(
                CLEAR_COMMAND_FEATURES,
                EXIT_COMMAND_FEATURES,
                HELP_COMMAND_FEATURES
        );
    }

    public String getCommand() {
        return this.command.get();
    }

    public String getDescription() {
        return this.description.get();
    }
}
