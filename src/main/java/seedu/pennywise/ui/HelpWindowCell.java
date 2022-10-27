package seedu.pennywise.ui;

public class HelpWindowCell {
    private final javafx.beans.property.SimpleStringProperty action = new javafx.beans.property.SimpleStringProperty("");
    private final javafx.beans.property.SimpleStringProperty format = new javafx.beans.property.SimpleStringProperty("");
    private final javafx.beans.property.SimpleStringProperty example = new javafx.beans.property.SimpleStringProperty("");

    public HelpWindowCell() {
        this("", "", "");
    }

    public HelpWindowCell(String action, String format, String example) {
        setAction(action);
        setFormat(format);
        setExample(example);
    }

    public String getAction() {
        return action.get();
    }

    public void setAction(String fName) {
        action.set(fName);
    }

    public String getFormat() {
        return format.get();
    }

    public void setFormat(String fName) {
        format.set(fName);
    }

    public String getExample() {
        return example.get();
    }

    public void setExample(String fName) {
        example.set(fName);
    }
}
