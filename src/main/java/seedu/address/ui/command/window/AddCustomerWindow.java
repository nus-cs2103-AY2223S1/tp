package seedu.address.ui.command.window;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.function.Consumer;

import javafx.stage.Stage;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.ui.CommandBox;
import seedu.address.ui.UiPart;

/**
 * Controller for to add a Customer by the GUI.
 */
public class AddCustomerWindow extends CustomerWindow {

    private static final String ADD_CUSTOMER_WINDOW_NAME = "Add Customer";

    /**
     * Creates a new AddCustomerWindow.
     */
    public AddCustomerWindow(Consumer<UiPart<Stage>> addChildWindow,
                             CommandBox.CommandExecutor commandExecutor,
                             Stage stage) {
        super(addChildWindow, commandExecutor, stage, ADD_CUSTOMER_WINDOW_NAME);
    }

    /**
     * Tries to create a Customer with the given user inputs.
     */
    @Override
    public void handleCustomerCommand() {
        String addCustomerCommand = getAddCustomerCommand();
        executeCustomerCommand(addCustomerCommand);
    }

    private String getAddCustomerCommand() {
        String addCustomerCommandInput = AddCustomerCommand.COMMAND_WORD + " ";
        addCustomerCommandInput += PREFIX_NAME + getCustomerNameInput() + " ";
        addCustomerCommandInput += PREFIX_PHONE + getCustomerPhoneInput() + " ";
        addCustomerCommandInput += PREFIX_EMAIL + getCustomerEmailInput() + " ";

        HashSet<String> uniqueTags = getCustomerTagsInput();
        for (String tagName : uniqueTags) {
            addCustomerCommandInput += PREFIX_TAG + tagName + " ";
        }

        if (!getCustomerAddressInput().isBlank()) {
            addCustomerCommandInput += PREFIX_ADDRESS + getCustomerAddressInput();
        }

        return addCustomerCommandInput;
    }
}
