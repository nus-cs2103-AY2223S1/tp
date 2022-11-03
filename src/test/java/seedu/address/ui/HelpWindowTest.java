package seedu.address.ui;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;
import static org.testfx.util.NodeQueryUtils.isVisible;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import javafx.scene.control.ChoiceBox;
import seedu.address.logic.commands.AddCommand;

public class HelpWindowTest {
    @BeforeAll
    public static void setupSpec() throws Exception {
        setHeadless();
        FxToolkit.registerPrimaryStage();
    }

    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.setupStage(s -> {
            HelpWindow helpWindow = new HelpWindow();
            helpWindow.show();
        });
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.cleanupStages();
    }

    @Test
    public void assertHelpWindowIsShowing() {
        verifyThat("#helpMessageContainer", isVisible());
    }

    @Test
    public void assertChoiceBoxHasItems() {
        verifyThat("#choiceBox", (ChoiceBox<String> choiceBox) -> choiceBox.getItems().size() == 9);
    }

    @Test
    public void assertDefaultChoiceBox() {
        verifyThat("#choiceBox", (ChoiceBox<String> choiceBox) -> choiceBox.getValue().equals("Add"));
    }

    @Test
    public void assertDefaultDesc() {
        verifyThat("#desc", hasText(AddCommand.DESCRIPTION));
    }

    @Test
    public void assertDefaultParam() {
        verifyThat("#param", hasText(AddCommand.PARAMETER));
    }

    @Test
    public void assertDefaultEx() {
        verifyThat("#ex", hasText(AddCommand.EXAMPLE));
    }

    private static void setHeadless() {
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            System.loadLibrary("WindowsCodecs");
        }
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }
}
