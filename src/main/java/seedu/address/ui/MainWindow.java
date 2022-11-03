package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CheckCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;
import seedu.address.ui.listpanels.BuyerListPanel;
import seedu.address.ui.listpanels.DelivererListPanel;
import seedu.address.ui.listpanels.MainListPanel;
import seedu.address.ui.listpanels.OrderListPanel;
import seedu.address.ui.listpanels.PetListPanel;
import seedu.address.ui.listpanels.SupplierListPanel;
import seedu.address.ui.popupwindow.AddCommandPopupWindow;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;

    // Independent Ui parts residing in this Ui container

    private MainListPanel mainListPanel;
    private BuyerListPanel buyerListPanel;
    private DelivererListPanel delivererListPanel;
    private SupplierListPanel supplierListPanel;
    private OrderListPanel orderListPanel;
    private PetListPanel petListPanel;

    private ResultDisplay resultDisplay;
    private final HelpWindow helpWindow;
    private AddCommandPopupWindow addCommandPopupWindow;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusBarPlaceholder;

    @FXML
    private StackPane selectionBoxPlaceHolder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // Initialise the list panels
        buyerListPanel = new BuyerListPanel(logic.getFilteredBuyerList(), logic);
        supplierListPanel = new SupplierListPanel(logic.getFilteredSupplierList(), logic);
        delivererListPanel = new DelivererListPanel(logic.getFilteredDelivererList(), logic);
        orderListPanel = new OrderListPanel(logic.getFilteredOrderList());
        petListPanel = new PetListPanel(logic.getFilteredPetList());
        mainListPanel = new MainListPanel(logic.getFilteredMainList(), logic);

        // Set the display window
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(mainListPanel.getRoot());

        // Initialise the remaining components in the main window
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        // TODO: debug this
        //        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        //        statusBarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setMaxWidth(screenBounds.getWidth() * 2);
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        if (addCommandPopupWindow != null) {
            addCommandPopupWindow.close();
        }
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Displays all contacts in the app.
     */
    public void showAll() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(mainListPanel.getRoot());
    }

    /**
     * Displays all buyers in the app.
     */
    public void showBuyer() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(buyerListPanel.getRoot());
    }

    /**
     * Displays all suppliers in the app.
     */
    public void showSupplier() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(supplierListPanel.getRoot());
    }

    /**
     * Displays all deliverers in the app.
     */
    public void showDeliverer() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(delivererListPanel.getRoot());
    }

    /**
     * Displays all pets in the app.
     */
    public void showPet() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(petListPanel.getRoot());
    }

    /**
     * Displays all orders in the app.
     */
    public void showOrder() {
        personListPanelPlaceholder.getChildren().clear();
        personListPanelPlaceholder.getChildren().add(orderListPanel.getRoot());
    }

    /**
     * Handles the window display behaviour for list command.
     */
    public void handleList(String listType) {
        listType = listType.trim().toUpperCase();
        switch (listType) {
        case ListCommand.LIST_BUYER:
            showBuyer();
            break;
        case ListCommand.LIST_SUPPLIER:
            showSupplier();
            break;
        case ListCommand.LIST_DELIVERER:
            showDeliverer();
            break;
        case ListCommand.LIST_ORDER:
            showOrder();
            break;
        case ListCommand.LIST_PET:
            showPet();
            break;
        case ListCommand.LIST_ALL:
            showAll();
            break;
        case ListCommand.LIST_EMPTY:
            show();
            break;
        default:
            //Do nothing.
        }
    }

    /**
     * Creates a pop-up window.
     *
     * @param addType Typo of person to be added.
     */
    public void handleAddByPopup(String addType) {
        addCommandPopupWindow = new AddCommandPopupWindow(logic, addType, resultDisplay);
        addCommandPopupWindow.show();
        addCommandPopupWindow.fillContentPlaceholder(addType);
    }

    /**
     * Handles the display for CheckCommand.
     * @param index The index of the item needs to be checked.
     */
    public void handleCheck(String checkType, int index) {
        checkType = checkType.trim().toUpperCase();
        switch (checkType) {
        case CheckCommand.CHECK_BUYER:
            Buyer buyer = logic.getFilteredBuyerList().get(index);
            ObservableList<Order> buyerOrderList = logic.getOrderAsObservableListFromBuyer(buyer);
            OrderListPanel newOrderList = new OrderListPanel(buyerOrderList);
            personListPanelPlaceholder.getChildren().clear();
            personListPanelPlaceholder.getChildren().add(newOrderList.getRoot());
            break;
        case CheckCommand.CHECK_SUPPLIER:
            Supplier supplier = logic.getFilteredSupplierList().get(index);
            ObservableList<Pet> supplierPetList = logic.getPetAsObservableListFromSupplier(supplier);
            PetListPanel newPetList = new PetListPanel(supplierPetList);
            personListPanelPlaceholder.getChildren().clear();
            personListPanelPlaceholder.getChildren().add(newPetList.getRoot());
            break;
        case CheckCommand.CHECK_ORDER:
            Order order = logic.getFilteredOrderList().get(index);
            Buyer buyerOfOrder = order.getBuyer();
            ObservableList<Buyer> buyerAsList = FXCollections.singletonObservableList(buyerOfOrder);
            BuyerListPanel tempBuyerPanel = new BuyerListPanel(buyerAsList, logic);
            personListPanelPlaceholder.getChildren().clear();
            personListPanelPlaceholder.getChildren().add(tempBuyerPanel.getRoot());
            break;
        case CheckCommand.CHECK_PET:
            Pet pet = logic.getFilteredPetList().get(index);
            Supplier supplierOfPet = pet.getSupplier();
            ObservableList<Supplier> supplierAsList = FXCollections.singletonObservableList(supplierOfPet);
            SupplierListPanel tempSupplierPanel = new SupplierListPanel(supplierAsList, logic);
            personListPanelPlaceholder.getChildren().clear();
            personListPanelPlaceholder.getChildren().add(tempSupplierPanel.getRoot());
            break;
        default:
            //Do nothing
        }

    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isHelpShown()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isList()) {
                handleList(commandResult.getListType());
            }

            if (commandResult.isAddedByPopup()) {
                handleAddByPopup(commandResult.getAddType());
            }
            if (commandResult.isCheck()) {
                Index index = commandResult.getIndex();
                handleCheck(commandResult.getCheckType(), index.getZeroBased());
            }

            return commandResult;

        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
