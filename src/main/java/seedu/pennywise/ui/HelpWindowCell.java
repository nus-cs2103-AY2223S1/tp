package seedu.pennywise.ui;

import javafx.scene.text.Text;

/**
 * Table cell for help window.
 */
public class HelpWindowCell {
    private final Text action = new Text();
    private final Text format = new Text();
    private final Text example = new Text();

    public HelpWindowCell() {
        this("", "", "");
    }

    /**
     * Constructor for HelpWindowCell
     */
    public HelpWindowCell(String action, String format, String example) {
        setAction(action);
        setFormat(format);
        setExample(example);
    }

    public String getAction() {
        return action.getText();
    }

    public void setAction(String fName) {
        action.setText(fName);
    }

    public String getFormat() {
        return format.getText();
    }

    public void setFormat(String fName) {
        format.setText(fName);
    }

    public String getExample() {
        return example.getText();
    }

    public void setExample(String fName) {
        example.setText(fName);
    }
}
