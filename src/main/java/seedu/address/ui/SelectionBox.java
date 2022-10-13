package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class SelectionBox extends UiPart<Region> {

    private static final String FXML = "SelectionBox.fxml";

    private final MainWindow mainWindow;

    @FXML
    private Button all;
    @FXML
    private Button buyer;
    @FXML
    private Button supplier;
    @FXML
    private Button deliverer;
    @FXML
    private Button pet;
    @FXML
    private Button order;

    public SelectionBox(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
    }

    @FXML
    private void switchToAll() {
        mainWindow.showAll();
    }
    @FXML
    private void switchToBuyer() {
        mainWindow.showBuyer();
    }

    @FXML
    private void switchToSupplier() {
        mainWindow.showSupplier();
    }

    @FXML
    private void switchToDeliverer() {
        mainWindow.showDeliverer();
    }
    @FXML
    private void switchToPet() {
        mainWindow.showPet();
    }
    @FXML
    private void switchToOrder() {
        mainWindow.showOrder();
    }
}
