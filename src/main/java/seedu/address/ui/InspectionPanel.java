package seedu.address.ui;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class InspectionPanel extends UiPart<Region> {
    private static final String PERSON_IMAGE_PATH = "/images/person.png";
    private static final String PHONE_IMAGE_PATH = "/images/phone.png";
    private static final String EMAIL_IMAGE_PATH = "/images/mail.png";
    private static final String ADDRESS_IMAGE_PATH = "/images/home.png";
    private static final String BIRTHDAY_IMAGE_PATH = "/images/birthday.png";
    private static final String LOAN_IMAGE_PATH = "/images/loan.png";
    private static final String NO_RECORDS_IMAGE_PATH = "/images/no_records.png";

    private static final String FXML = "InspectionPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(InspectionPanel.class);

    @FXML
    private ListView<Pair<Loan, LoanHistory>> historyListView;

    @FXML
    private Label name;
    @FXML
    private ImageView nameImage;

    @FXML
    private Label phone;
    @FXML
    private ImageView phoneImage;

    @FXML
    private Label address;
    @FXML
    private ImageView addressImage;

    @FXML
    private Label email;
    @FXML
    private ImageView emailImage;

    @FXML
    private Label birthday;
    @FXML
    private ImageView birthdayImage;

    @FXML
    private VBox basicInformation;

    @FXML
    private ImageView loanIndicator;

    @FXML
    private ImageView noRecordsImage;

    @FXML
    private Label summaryText;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public InspectionPanel(ListView<Person> personListView) {
        super(FXML);
        setImageViews();
        personListView.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> Optional.ofNullable(n).ifPresent(this::setInspectParameters));

        basicInformation.maxWidthProperty().bind(getRoot().widthProperty().multiply(0.33));
        basicInformation.prefWidthProperty().bind(basicInformation.maxWidthProperty());

        personListView.getSelectionModel().select(0);
    }

    private void setInspectParameters(Person n) {
        String fullName = n.getName().fullName;
        name.setText(fullName);
        email.setText(n.getEmail().value);
        phone.setText(n.getPhone().value);
        address.setText(n.getAddress().value);
        birthday.setText(n.getBirthday().value);

        double loanAmount = n.getLoan().getAmount();
        if (loanAmount >= 0) {
            summaryText.setText(String.format("%s is due to pay $%.2f", fullName, loanAmount));
        } else {
            summaryText.setText(String.format("%s is to be paid $%.2f", fullName, -loanAmount));
        }

        historyListView.setItems(FXCollections.observableList(n.getHistoryWithTotal()));
        historyListView.setCellFactory(listView -> new HistoryListViewCell());

        if (historyListView.getItems().size() == 0) {
            noRecordsImage.setOpacity(1);
            loanIndicator.setOpacity(0);
            summaryText.setOpacity(0);
        } else {
            noRecordsImage.setOpacity(0);
            loanIndicator.setOpacity(1);
            summaryText.setOpacity(1);
        }
    }

    private void setImageViews() {
        nameImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(PERSON_IMAGE_PATH))));
        phoneImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(PHONE_IMAGE_PATH))));
        emailImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(EMAIL_IMAGE_PATH))));
        addressImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(ADDRESS_IMAGE_PATH))));
        birthdayImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(BIRTHDAY_IMAGE_PATH))));
        loanIndicator.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(LOAN_IMAGE_PATH))));
        noRecordsImage.setImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream(NO_RECORDS_IMAGE_PATH))));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code LoanHistory} using a {@code LoanHistory}.
     */
    class HistoryListViewCell extends ListCell<Pair<Loan, LoanHistory>> {
        @Override
        protected void updateItem(Pair<Loan, LoanHistory> totalAndHistory, boolean empty) {
            super.updateItem(totalAndHistory, empty);

            if (empty || totalAndHistory == null) {
                setGraphic(null);
                setText(null);
            } else {
                LoanHistoryCard loanHistoryCard = new LoanHistoryCard(totalAndHistory.getValue(),
                        totalAndHistory.getKey().toString());

                loanHistoryCard.bindWidth(historyListView, 15);
                setGraphic(loanHistoryCard.getRoot());
            }
        }
    }

}
