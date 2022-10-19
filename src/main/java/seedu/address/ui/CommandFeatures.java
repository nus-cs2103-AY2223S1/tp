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
            "Shows a list of all persons in the address book.",
            "addperson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]",
            "addperson n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01"
            );

    private static final CommandFeatures LISTPERSONS_COMMAND_FEATURES = new CommandFeatures(
            "listpersons",
            "Adds a person to the address book.",
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

    private SimpleStringProperty command;
    private SimpleStringProperty description;

    /**
     * Constructor to build a CommandFeatures with details of existing commadns
     */
    public CommandFeatures(String command, String description, String format, String example) {
        this.command = new SimpleStringProperty(command);
        this.description = new SimpleStringProperty(String.format((
                "%s\nFormat : %s\nExample : %s"),
                description, format, example));
    }
    
    public static ObservableList<CommandFeatures> getCommandFeaturesList() {
        return FXCollections.observableArrayList(
                ADDPERSON_COMMAND_FEATURES,
                LISTPERSONS_COMMAND_FEATURES,
                LISTPERSONS_COMMAND_FEATURES,
                FINDPERSON_COMMAND_FEATURES,
                DELETEPERSON_COMMAND_FEATURES
        );
    }

    public String getCommand() {
        return this.command.get();
    }

    public String getDescription() {
        return this.description.get();
    }
}
