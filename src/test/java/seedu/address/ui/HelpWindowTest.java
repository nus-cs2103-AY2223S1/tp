package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static seedu.address.ui.HelpWindow.USERGUIDE_URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import guitests.guihandles.HelpWindowHandle;
import javafx.stage.Stage;

// @@author pyokagan-reused
// Test code adapted from AddressBook Level 4 https://se-education.org/addressbook-level4/ with modifications
public class HelpWindowTest extends GuiUnitTest {

    private HelpWindow helpWindow;
    private HelpWindowHandle helpWindowHandle;

    @BeforeEach
    public void setUp() throws Exception {
        guiRobot.interact(() -> helpWindow = new HelpWindow());
        FxToolkit.registerStage(helpWindow::getRoot);
        helpWindowHandle = new HelpWindowHandle(helpWindow.getRoot());
    }

    @Test
    public void display() throws Exception {
        FxToolkit.showStage();
        assertEquals(HelpWindow.USERGUIDE_URL, USERGUIDE_URL);
    }

    @Test
    public void isShowing_helpWindowIsShowing_returnsTrue() {
        guiRobot.interact(helpWindow::show);
        assertTrue(helpWindow.isShowing());
    }

    @Test
    public void isShowing_helpWindowIsHiding_returnsFalse() {
        assertFalse(helpWindow.isShowing());
    }

    @Test
    public void focus_helpWindowNotFocused_focused() throws Exception {
        // TODO: This test skip can be removed once this bug is fixed:
        // https://github.com/javafxports/openjdk-jfx/issues/50
        //
        // When there are two stages (stage1 and stage2) shown,
        // stage1 is in focus and stage2.requestFocus() is called,
        // we expect that stage1.isFocused() will return false while
        // stage2.isFocused() returns true. However, as reported in the bug report,
        // both stage1.isFocused() and stage2.isFocused() returns true,
        // which fails the test.
        assumeFalse(guiRobot.isHeadlessMode(), "Test skipped in headless mode: Window focus behavior is buggy.");
        guiRobot.interact(helpWindow::show);

        // Focus on another stage to remove focus from the helpWindow
        guiRobot.interact(() -> {
            Stage temporaryStage = new Stage();
            temporaryStage.show();
            temporaryStage.requestFocus();
        });
        assertFalse(helpWindow.getRoot().isFocused());

        guiRobot.interact(helpWindow::focus);
        assertTrue(helpWindow.getRoot().isFocused());
    }
}
// @@author
