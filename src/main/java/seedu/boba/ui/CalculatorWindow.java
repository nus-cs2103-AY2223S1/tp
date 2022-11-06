package seedu.boba.ui;

import static seedu.boba.commons.util.AppUtil.getImage;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.boba.commons.core.LogsCenter;

/**
 * Controller for a calculator page
 */
public class CalculatorWindow extends UiPart<Stage> {

    private static final String FXML = "CalculatorWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(CalculatorWindow.class);
    private static final String ICON_APPLICATION = "/images/calculator.png";
    private BigDecimal left;
    private String selectedOperator;
    private boolean numberInputting;

    @FXML
    private TextField display;

    /**
     * Creates a new CalculatorWindow.
     *
     * @param root Stage to use as the root of the CalculatorWindow.
     */
    public CalculatorWindow(Stage root) {
        super(FXML, root);
        this.left = BigDecimal.ZERO;
        this.selectedOperator = "";
        this.numberInputting = false;

        //Set the application icon.
        root.getIcons().add(getImage(ICON_APPLICATION));
    }

    /**
     * Creates a new CalculatorWindow.
     */
    public CalculatorWindow() {
        this(new Stage());
    }

    /**
     * Shows the calculator window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing calculator.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the calculator window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the calculator window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the calculator window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Handle the bottom clicks on calculator.
     * Reused from https://gist.github.com/argius/08834fab73b91de8d79b
     * with modifications.
     * @param evt User actions
     */
    @FXML
    protected void handleOnAnyButtonClicked(ActionEvent evt) {
        Button button = (Button) evt.getSource();
        final String buttonText = button.getText();
        if (buttonText.equals("C") || buttonText.equals("AC")) {
            if (buttonText.equals("AC")) {
                left = BigDecimal.ZERO;
            }
            selectedOperator = "";
            numberInputting = false;
            display.setText("0");;
            return;
        }
        if (buttonText.matches("[0-9\\.]")) {
            if (!numberInputting) {
                numberInputting = true;
                display.clear();
            }
            display.appendText(buttonText);
            return;
        }
        if (buttonText.matches("[＋－×÷]")) {
            left = new BigDecimal(display.getText());
            selectedOperator = buttonText;
            numberInputting = false;
            return;
        }
        if (buttonText.equals("=")) {
            final BigDecimal right = numberInputting ? new BigDecimal(display.getText()) : left;
            try {
                left = calculate(selectedOperator, left, right);
                display.setText(left.toString());
            } catch (ArithmeticException ae) {
                display.setText("∞");
            }
            numberInputting = false;
        }
    }

    static BigDecimal calculate(String operator, BigDecimal left, BigDecimal right) {
        switch (operator) {
        case "＋":
            return left.add(right);
        case "－":
            return left.subtract(right);
        case "×":
            return left.multiply(right);
        case "÷":
            return left.divide(right);
        default:
        }
        return right;
    }

}
