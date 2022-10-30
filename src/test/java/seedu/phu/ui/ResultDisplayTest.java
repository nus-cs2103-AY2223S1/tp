package seedu.phu.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.control.TextArea;

public class ResultDisplayTest extends GuiUnitTest {
    private static final String CHILD_ID = "#resultDisplay"; // id to handle fxml query
    private static final String SAMPLE_FEEDBACK = "this is a feedback";
    private ResultDisplay resultDisplay;
    private TextArea childTextArea;

    @BeforeEach
    public void setUp() {
        resultDisplay = new ResultDisplay();
        childTextArea = getChildNode(resultDisplay.getRoot(), CHILD_ID);
        assert childTextArea != null; // make sure that childTextArea is not null

        uiPartExtension.setUiPart(resultDisplay);
    }

    @Test
    public void setFeedback_emptyString_success() {
        checkCurrentDisplay("");
    }

    @Test
    public void setFeedback_nonEmptyString_success() {
        robot.interact(() -> resultDisplay.setFeedbackToUser(SAMPLE_FEEDBACK));
        checkCurrentDisplay(SAMPLE_FEEDBACK);
    }

    private void checkCurrentDisplay(String expectedDisplay) {
        assertEquals(expectedDisplay, childTextArea.getText());
    }
}
