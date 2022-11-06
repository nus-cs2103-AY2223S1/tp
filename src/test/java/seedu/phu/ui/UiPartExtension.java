package seedu.phu.ui;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testfx.api.FxToolkit;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Ui Part extension to handle GUI test cases.
 *
 * Reused from
 * https://github.com/se-edu/addressbook-level4/blob/master/src/test/java/seedu/address/ui/testutil/UiPartExtension.java
 * with minor modifications.
 *
 * @@author sugiyem-reused
 */
public class UiPartExtension implements AfterEachCallback, BeforeEachCallback {
    private static final String[] CSS_FILES = {"view/DarkTheme.css", "view/Extensions.css"};

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        FxToolkit.cleanupStages();
    }

    public void setUiPart(final UiPart<? extends Parent> uiPart) {
        try {
            FxToolkit.setupScene(() -> {
                Scene scene = new Scene(uiPart.getRoot());
                scene.getStylesheets().setAll(CSS_FILES);
                return scene;
            });
            FxToolkit.showStage();
        } catch (TimeoutException error) {
            throw new AssertionError("Timeout should not happen.", error);
        }
    }
}
