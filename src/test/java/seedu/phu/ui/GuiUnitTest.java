package seedu.phu.ui;

import java.util.Optional;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.testfx.api.FxRobot;

import javafx.scene.Node;

/**
 * A GUI unit test class for InternshipBook.
 *
 * Reused from https://github.com/se-edu/addressbook-level4/blob/master/src/test/java/seedu/address/ui/GuiUnitTest.java
 * with minor modifications.
 *
 * @@author sugiyem-reused
 */
public abstract class GuiUnitTest {
    // TODO: Remove this workaround after using JavaFX version 13 or above
    // This is a workaround to solve headless test failure on Windows OS
    // Refer to https://github.com/javafxports/openjdk-jfx/issues/66 for more details.
    static {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            System.loadLibrary("WindowsCodecs");
        }

        setHeadlessModeToTrue();
    }

    @RegisterExtension
    public final UiPartExtension uiPartExtension = new UiPartExtension();

    protected final FxRobot robot = new FxRobot();

    /**
     * Retrieves the {@code query} node owned by the {@code rootNode}.
     *
     * @param query name of the CSS selector of the node to retrieve.
     */
    protected <T extends Node> T getChildNode(Node rootNode, String query) {
        Optional<T> node = robot.from(rootNode).lookup(query).tryQuery();
        return node.orElse(null);
    }

    private static void setHeadlessModeToTrue() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");
    }
}
