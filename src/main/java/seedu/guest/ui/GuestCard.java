package seedu.guest.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.guest.model.guest.Guest;

/**
 * An UI component that displays information of a {@code Guest}.
 */
public class GuestCard extends UiPart<Region> {

    private static final String FXML = "GuestListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Guest guest;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label room;
    @FXML
    private Label dateRange;
    @FXML
    private Label numberOfGuests;
    @FXML
    private Label isRoomClean;
    @FXML
    private Label isRoomCleanStatus;
    @FXML
    private Label bill;
    @FXML
    private Label request;

    /**
     * Creates a {@code GuestCode} with the given {@code Guest} and index to display.
     */
    public GuestCard(Guest guest, int displayedIndex) {
        super(FXML);
        this.guest = guest;
        id.setText(displayedIndex + ". ");
        name.setText(guest.getName().fullName);
        name.setWrapText(true);
        Tooltip nameTT = new Tooltip("Name");
        name.setTooltip(nameTT);

        phone.setText(guest.getPhone().value);
        Image phoneImage = new Image(getClass().getResourceAsStream("/images/phone.png"));
        ImageView setPhoneImage = new ImageView(phoneImage);
        setPhoneImage.setFitWidth(16);
        setPhoneImage.setFitHeight(16);
        phone.setGraphic(setPhoneImage);
        phone.setWrapText(true);
        Tooltip phoneTT = new Tooltip("Phone Number");
        phone.setTooltip(phoneTT);

        email.setText(guest.getEmail().value);
        Image emailImage = new Image(getClass().getResourceAsStream("/images/email.png"));
        ImageView setEmailImage = new ImageView(emailImage);
        setEmailImage.setFitWidth(16);
        setEmailImage.setFitHeight(16);
        email.setGraphic(setEmailImage);
        email.setWrapText(true);
        Tooltip emailTT = new Tooltip("Email");
        email.setTooltip(emailTT);

        room.setText(guest.getRoom().value);
        Image roomImage = new Image(getClass().getResourceAsStream("/images/room.png"));
        ImageView setRoomImage = new ImageView(roomImage);
        setRoomImage.setFitWidth(16);
        setRoomImage.setFitHeight(16);
        room.setGraphic(setRoomImage);
        room.setWrapText(true);
        Tooltip roomTT = new Tooltip("Room Number");
        room.setTooltip(roomTT);

        dateRange.setText(guest.getDateRange().value);
        Image dateRangeImage = new Image(getClass().getResourceAsStream("/images/dateRange.png"));
        ImageView setDateRangeImage = new ImageView(dateRangeImage);
        setDateRangeImage.setFitWidth(16);
        setDateRangeImage.setFitHeight(16);
        dateRange.setGraphic(setDateRangeImage);
        dateRange.setWrapText(true);
        Tooltip dateRangeTT = new Tooltip("Date of Stay");
        dateRange.setTooltip(dateRangeTT);

        numberOfGuests.setText(guest.getNumberOfGuests().value);
        Image numberOfGuestsImage = new Image(getClass().getResourceAsStream("/images/group.png"));
        ImageView setNumberOfGuestsImage = new ImageView(numberOfGuestsImage);
        setNumberOfGuestsImage.setFitWidth(16);
        setNumberOfGuestsImage.setFitHeight(16);
        numberOfGuests.setGraphic(setNumberOfGuestsImage);
        Tooltip numberOfGuestsTT = new Tooltip("Number of Guests");
        numberOfGuests.setTooltip(numberOfGuestsTT);

        isRoomClean.setText("");
        isRoomCleanStatus.setText("");
        Image isRoomCleanImage = new Image(getClass().getResourceAsStream("/images/isRoomClean.png"));
        ImageView setIsRoomCleanImage = new ImageView(isRoomCleanImage);
        setIsRoomCleanImage.setFitWidth(16);
        setIsRoomCleanImage.setFitHeight(16);
        isRoomClean.setGraphic(setIsRoomCleanImage);

        // Set graphic based on status of isRoomClean
        if (guest.getIsRoomClean().value.equals("yes")) {
            Image isRoomCleanStatusImage = new Image(getClass().getResourceAsStream("/images/roomClean.png"));
            ImageView setIsRoomCleanStatusImage = new ImageView(isRoomCleanStatusImage);
            setIsRoomCleanStatusImage.setFitWidth(18);
            setIsRoomCleanStatusImage.setFitHeight(18);
            isRoomCleanStatus.setGraphic(setIsRoomCleanStatusImage);
        } else {
            Image image = new Image(getClass().getResourceAsStream("/images/roomNotClean.png"));
            ImageView setImage = new ImageView(image);
            setImage.setFitWidth(18);
            setImage.setFitHeight(18);
            isRoomCleanStatus.setGraphic(setImage);
        }

        Tooltip isRoomCleanTT = new Tooltip("Room Cleaned");
        isRoomClean.setTooltip(isRoomCleanTT);
        isRoomCleanStatus.setTooltip(isRoomCleanTT);

        bill.setText(guest.getBill().value);
        Image billImage = new Image(getClass().getResourceAsStream("/images/bill.png"));
        ImageView setBillImage = new ImageView(billImage);
        setBillImage.setFitWidth(16);
        setBillImage.setFitHeight(16);
        bill.setGraphic(setBillImage);
        bill.setWrapText(true);
        Tooltip billTT = new Tooltip("Bill");
        bill.setTooltip(billTT);

        request.setText(guest.getRequest().value);
        Image requestImage = new Image(getClass().getResourceAsStream("/images/request.png"));
        ImageView setRequestImage = new ImageView(requestImage);
        setRequestImage.setFitWidth(16);
        setRequestImage.setFitHeight(16);
        request.setGraphic(setRequestImage);
        request.setWrapText(true);
        Tooltip requestTT = new Tooltip("Request");
        request.setTooltip(requestTT);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuestCard)) {
            return false;
        }

        // state check
        GuestCard card = (GuestCard) other;
        return id.getText().equals(card.id.getText())
                && guest.equals(card.guest);
    }
}
