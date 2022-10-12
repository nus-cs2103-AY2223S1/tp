package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import seedu.address.OldMainApp;

/**
 * Represents a distinct part of the UI. e.g. Windows, dialogs, panels, status bars, etc.
 * It contains a scene graph with a root node of type {@code T}.
 */
public abstract class OldUiPart<T> {

    /** Resource folder where FXML files are stored. */
    public static final String FXML_FILE_FOLDER = "/view/";

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    /**
     * Constructs a OldUiPart with the specified FXML file URL.
     * The FXML file must not specify the {@code fx:controller} attribute.
     */
    public OldUiPart(URL fxmlFileUrl) {
        loadFxmlFile(fxmlFileUrl, null);
    }

    /**
     * Constructs a OldUiPart using the specified FXML file within {@link #FXML_FILE_FOLDER}.
     * @see #OldUiPart(URL)
     */
    public OldUiPart(String fxmlFileName) {
        this(getFxmlFileUrl(fxmlFileName));
    }

    /**
     * Constructs a OldUiPart with the specified FXML file URL and root object.
     * The FXML file must not specify the {@code fx:controller} attribute.
     */
    public OldUiPart(URL fxmlFileUrl, T root) {
        loadFxmlFile(fxmlFileUrl, root);
    }

    /**
     * Constructs a OldUiPart with the specified FXML file within {@link #FXML_FILE_FOLDER} and root object.
     * @see #OldUiPart(URL, T)
     */
    public OldUiPart(String fxmlFileName, T root) {
        this(getFxmlFileUrl(fxmlFileName), root);
    }

    /**
     * Returns the root object of the scene graph of this OldUiPart.
     */
    public T getRoot() {
        return fxmlLoader.getRoot();
    }

    /**
     * Loads the object hierarchy from a FXML document.
     * @param location Location of the FXML document.
     * @param root Specifies the root of the object hierarchy.
     */
    private void loadFxmlFile(URL location, T root) {
        requireNonNull(location);
        fxmlLoader.setLocation(location);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(root);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns the FXML file URL for the specified FXML file name within {@link #FXML_FILE_FOLDER}.
     */
    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        String fxmlFileNameWithFolder = FXML_FILE_FOLDER + fxmlFileName;
        URL fxmlFileUrl = OldMainApp.class.getResource(fxmlFileNameWithFolder);
        return requireNonNull(fxmlFileUrl);
    }

}
