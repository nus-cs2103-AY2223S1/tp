package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Company;

/**
 * Panel containing the list of companies.
 */
public class CompanyListPanel extends UiPart<Region> {
    private static final String FXML = "CompanyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CompanyListPanel.class);

    @FXML
    private ListView<Company> companyListView;

    /**
     * Creates a {@code CompanyListPanel} with the given {@code ObservableList}.
     */
    public CompanyListPanel(ObservableList<Company> companyList) {
        super(FXML);
        companyListView.setItems(companyList);
        companyListView.setCellFactory(listView -> new CompanyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Company} using a {@code CompanyCard}.
     */
    class CompanyListViewCell extends ListCell<Company> {
        @Override
        protected void updateItem(Company company, boolean empty) {
            super.updateItem(company, empty);

            if (empty || company == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CompanyCard(company, getIndex() + 1).getRoot());
            }
        }
    }
}
