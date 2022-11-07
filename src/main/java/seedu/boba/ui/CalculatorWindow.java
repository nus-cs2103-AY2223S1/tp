package seedu.boba.ui;

import static seedu.boba.commons.util.AppUtil.getImage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    private BigDecimal numberDisplaying;
    private String selectedOperator;
    private boolean isInputtingNumber;

    @FXML
    private TextField display;

    /**
     * Creates a new CalculatorWindow.
     *
     * @param root Stage to use as the root of the CalculatorWindow.
     */
    public CalculatorWindow(Stage root) {
        super(FXML, root);
        this.numberDisplaying = BigDecimal.ZERO;
        this.selectedOperator = "";
        this.isInputtingNumber = false;

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
     * Calculate +, -, *, / operations
     * @param operator Operator to be used to perform calculation
     * @param leftOperand Left operand (number)
     * @param rightOperand Right operand (number)
     * @return Result of calculation
     */
    static BigDecimal calculate(String operator, BigDecimal leftOperand, BigDecimal rightOperand) {
        switch (operator) {
        case "＋":
            return leftOperand.add(rightOperand);
        case "－":
            return leftOperand.subtract(rightOperand);
        case "×":
            return leftOperand.multiply(rightOperand);
        case "÷":
            return leftOperand.divide(rightOperand, 2, RoundingMode.HALF_UP);
        default:
        }
        // Trivial. Will not reach here
        return leftOperand;
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
     * with modifications and bug fixes
     * @param event User actions (Clicks on buttons)
     */
    @FXML
    protected void handleOnAnyButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        final String buttonText = button.getText();
        // The button clicked is a number
        if (buttonText.matches("[0-9\\.]")) {
            if (!isInputtingNumber) {
                isInputtingNumber = true;
                display.clear();
            }
            display.appendText(buttonText);
            return;
        }
        // The button clicked is an operator
        if (buttonText.matches("[＋－×÷]")) {
            numberDisplaying = new BigDecimal(display.getText());
            selectedOperator = buttonText;
            isInputtingNumber = false;
            return;
        }
        if (buttonText.equals("=")) {
            final BigDecimal rightOperand = isInputtingNumber
                    ? new BigDecimal(display.getText())
                    : numberDisplaying;
            final BigDecimal leftOperand = numberDisplaying;
            try {
                numberDisplaying = calculate(selectedOperator, leftOperand, rightOperand);
                DecimalFormat df = new DecimalFormat("#.##");
                // May round 5 to 0 sometimes without this line
                df.setRoundingMode(RoundingMode.HALF_UP);
                String resultToDisplay = df.format(numberDisplaying);
                display.setText(resultToDisplay);
            } catch (ArithmeticException ae) {
                // Division by zero
                display.setText("∞");
            }
            isInputtingNumber = false;
            return;
        }
        // The button clicked is clear/all-clear
        if (buttonText.equals("C") || buttonText.equals("AC")) {
            if (buttonText.equals("AC")) {
                numberDisplaying = BigDecimal.ZERO;
            }
            selectedOperator = "";
            isInputtingNumber = false;
            display.setText("0");
        }
    }
}
