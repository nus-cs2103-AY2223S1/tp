package seedu.address.ui.popupwindow;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.UiPart;

/**
 * Represents a part of the pop-up window.
 */
public abstract class PopUpPanel extends UiPart<Region> {

    private static final String PROMPT_TEXT_STYLE = "-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"
            + " -fx-background-radius: 30px 30px 30px 30px;";
    private static final String ERROR_FOCUS_STYLE = "-fx-focus-color: red;\n"
            + "-fx-faint-focus-color: #ff000022;" + PROMPT_TEXT_STYLE;

    public PopUpPanel(String fxml) {
        super(fxml);
    }

    /**
     * Generates a {@code Command} from all the user inputs in the panel.
     *
     * @return A {@code Command}, a {@code AddCommand} to be precise.
     * @throws ParseException When parsing of the user inputs causes exception.
     */
    public abstract Command generateCommand() throws ParseException;

    /**
     * Checks whether all fields that require user inputs are filled.
     *
     * @return True if all compulsory fields are filled, false otherwise.
     */
    public abstract boolean checkAllPartsFilled();

    /**
     * Changes focus from {@code currentField} to {@code nextField} with the ENTER key in the keyboard.
     *
     * @param currentField Current field in focus.
     * @param nextField Target field to change focus to.
     */
    private void goToNextFieldWithEnter(Control currentField, Control nextField) {
        // Solution below adapted from http://www.shorturl.at/aHLX7
        currentField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                nextField.requestFocus();
            }
        });
    }

    /**
     * Generate a sequence of fields in focus with the ENTER key,
     * where the first field in {@code fields} will be the initial field in focus without the need to press ENTER,
     * while the last field in {@code fields} will be the last field in focus by pressing ENTER.
     *
     * @param fields Any number of {@code Control} objects.
     */
    public void generateInputSequence(Control... fields) {
        Control currentField = null;
        for (Control nextField : fields) {
            if (currentField != null) {
                goToNextFieldWithEnter(currentField, nextField);
            }
            currentField = nextField;
        }
    }

    /**
     * Checks all fields provided are filled with inputs.
     *
     * @param textInputFields Fields related to {@code TextInputControl}.
     * @return True if all fields are filled, false otherwise.
     */
    public boolean checkGivenFieldsAllFilled(TextInputControl... textInputFields) {
        for (TextInputControl textInputField : textInputFields) {
            if (textInputField.getText().isEmpty() || textInputField.getText().startsWith(" ")) {
                textInputField.requestFocus();
                textInputField.setStyle(ERROR_FOCUS_STYLE);
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the style of all the given fields to the style
     * that displays the prompt text even when the field is in focus.
     *
     * @param textInputFields Any number of {@code TextInputControl}.
     */
    public void setPromptTextStyle(TextInputControl... textInputFields) {
        for (TextInputControl textInputField : textInputFields) {
            textInputField.setStyle(PROMPT_TEXT_STYLE);
        }
    }

    /**
     * Generate a keyboard shortcut to the given button.
     *
     * @param button A button.
     * @param keyCombination A combination of keyboard keys.
     */
    public void generateButtonShortcut(Button button, KeyCombination keyCombination) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                button.fire();
                event.consume();
            }
        });
    }

}
