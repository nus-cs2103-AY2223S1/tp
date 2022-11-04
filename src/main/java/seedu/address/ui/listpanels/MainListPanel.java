package seedu.address.ui.listpanels;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;
import seedu.address.ui.UiPart;
import seedu.address.ui.displaycards.BuyerCard;
import seedu.address.ui.displaycards.DelivererCard;
import seedu.address.ui.displaycards.OrderCard;
import seedu.address.ui.displaycards.PetCard;
import seedu.address.ui.displaycards.SupplierCard;

/**
 * Panel containing the list of all contacts.
 */
public class MainListPanel extends UiPart<Region> {
    private static final String FXML = "MainListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(DelivererListPanel.class);

    @FXML
    private ListView<Object> mainListView;


    /**
     * Creates a {@code MainListPanel} with the given {@code ObservableList}.
     */
    public MainListPanel(ObservableList<Object> mainList, Logic logic) {
        super(FXML);
        mainListView.setItems(mainList);
        mainListView.setCellFactory(listView -> new MainListViewCell(logic));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Deliverer} using a {@code DelivererCard}.
     */
    private static class MainListViewCell extends ListCell<Object> {
        private final Logic logic;

        public MainListViewCell(Logic logic) {
            this.logic = logic;
        }

        @Override
        protected void updateItem(Object object, boolean empty) {
            super.updateItem(object, empty);

            if (empty || object == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (object instanceof Buyer) {
                    Buyer buyer = (Buyer) object;
                    setGraphic(new BuyerCard(buyer, getIndex() + 1, logic).getRoot());
                }
                if (object instanceof Supplier) {
                    Supplier supplier = (Supplier) object;
                    setGraphic(new SupplierCard(supplier, getIndex() + 1, logic).getRoot());
                }
                if (object instanceof Deliverer) {
                    Deliverer deliverer = (Deliverer) object;
                    setGraphic(new DelivererCard(deliverer, getIndex() + 1, logic).getRoot());
                }
                if (object instanceof Pet) {
                    Pet pet = (Pet) object;
                    setGraphic(new PetCard(pet, getIndex() + 1, PetCard.SHOULD_DISPLAY_SUPPLIER_NAME)
                            .getRoot());
                }
                if (object instanceof Order) {
                    Order order = (Order) object;
                    setGraphic(new OrderCard(order, getIndex() + 1, OrderCard.SHOULD_DISPLAY_BUYER_NAME)
                            .getRoot());
                }

            }
        }
    }

}
