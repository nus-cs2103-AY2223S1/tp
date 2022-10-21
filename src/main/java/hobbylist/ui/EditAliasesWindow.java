package hobbylist.ui;

import java.util.logging.Logger;

import hobbylist.commons.core.AliasSettings;
import hobbylist.commons.core.LogsCenter;
import hobbylist.logic.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class EditAliasesWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(EditAliasesWindow.class);
    private static final String FXML = "EditAliasesWindow.fxml";

    private final Logic logic;

    @FXML
    private TextField add;

    @FXML
    private TextField clear;

    @FXML
    private TextField delete;

    @FXML
    private TextField edit;

    @FXML
    private TextField exit;

    @FXML
    private TextField filter;

    @FXML
    private TextField find;

    @FXML
    private TextField list;

    @FXML
    private TextField help;

    @FXML
    private TextField rate;

    /**
     * Creates a new EditAliasesWindow.
     *
     * @param root Stage to use as the root of the EditAliasesWindow.
     */
    public EditAliasesWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
        setTextFields();
    }

    /**
     * Creates a new EditAliasesWindow.
     */
    public EditAliasesWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the EditAliases window.
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
        logger.fine("Showing edit aliases window of the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the edit aliases window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the edit aliases window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the edit aliases window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Handles saving of the aliases
     */
    public void handleSave() {
        if (!isValidAliases()) {
            new Alert(Alert.AlertType.INFORMATION, "Invalid aliases detected.").show();
            setTextFields();
            return;
        }
        AliasSettings newSettings = new AliasSettings(add.getText(), clear.getText(), delete.getText(), edit.getText(),
                exit.getText(), filter.getText(), find.getText(), list.getText(), help.getText(), rate.getText());
        logic.setAliasSettings(newSettings);
        this.hide();
    }

    private void setTextFields() {
        AliasSettings settings = logic.getAliasSettings();
        add.setText(settings.getAdd());
        clear.setText(settings.getClear());
        delete.setText(settings.getDelete());
        edit.setText(settings.getEdit());
        exit.setText(settings.getExit());
        filter.setText(settings.getFilter());
        find.setText(settings.getFind());
        list.setText(settings.getList());
        help.setText(settings.getHelp());
        rate.setText(settings.getRate());
    }

    private boolean isValidAliases() {
        String[] arr = {add.getText(), clear.getText(), delete.getText(), edit.getText(),
                exit.getText(), filter.getText(), find.getText(), list.getText(), help.getText(), rate.getText()};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("")) {
                return false;
            }
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].equals(arr[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
