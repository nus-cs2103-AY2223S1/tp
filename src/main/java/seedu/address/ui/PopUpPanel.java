package seedu.address.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public abstract class PopUpPanel extends UiPart<Region> {

    private static final String PROMPT_TEXT_STYLE = "-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"
            + " -fx-background-radius: 30px 30px 30px 30px;";
    private static final String ERROR_FOCUS_STYLE = "-fx-focus-color: red;\n"
            + "-fx-faint-focus-color: #ff000022;" + PROMPT_TEXT_STYLE;

    public PopUpPanel(String fxml) {
        super(fxml);
    }

    public abstract Command generateCommand() throws ParseException;

    public abstract boolean checkAllPartsFilled();

    private void goToNextFieldWithEnter(Control currentField, Control nextField) {
        // Solution below adapted from http://www.shorturl.at/aHLX7
        currentField.setOnKeyPressed(event ->{
            if(event.getCode().equals(KeyCode.ENTER)){
                nextField.requestFocus();
            }
        });
    }

    public void generateInputSequence(Control... fields) {
        Control currentField = null;
        for (Control nextField : fields) {
            if (currentField != null) {
                goToNextFieldWithEnter(currentField, nextField);
            }
            currentField = nextField;
        }
    }

    public boolean checkGivenFieldsAllFilled(TextInputControl... textInputFields) {
        for (TextInputControl textInputField : textInputFields) {
            if (textInputField.getText().isEmpty()) {
                textInputField.requestFocus();
                textInputField.setStyle(ERROR_FOCUS_STYLE);
                return false;
            }
        }
        return true;
    }

    public void setPromptTextStyle(TextInputControl... textInputFields) {
        for (TextInputControl textInputField : textInputFields) {
            textInputField.setStyle(PROMPT_TEXT_STYLE);
        }
    }

    public void generateButtonShortcut(Button button, KeyCombination keyCombination) {
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                button.fire();
                event.consume();
            }
        });
    }

}
