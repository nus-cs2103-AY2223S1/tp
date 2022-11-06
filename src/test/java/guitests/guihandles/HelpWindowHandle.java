package guitests.guihandles;

import guitests.GuiRobot;
import javafx.stage.Stage;

// @@author pyokagan-reused
// Test code adapted from AddressBook Level 4 https://se-education.org/addressbook-level4/ with modifications
/**
 * A handle to the {@code HelpWindow} of the application.
 */
public class HelpWindowHandle extends StageHandle {

    public static final String HELP_WINDOW_TITLE = "Help";

    private static final String HELP_WINDOW_BROWSER_ID = "#browser";

    public HelpWindowHandle(Stage helpWindowStage) {
        super(helpWindowStage);
    }

    /**
     * Returns true if a help window is currently present in the application.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(HELP_WINDOW_TITLE);
    }
}
// @@author
