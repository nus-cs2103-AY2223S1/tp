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
    private static final double IMAGE_WIDTH = 16;
    private static final double IMAGE_HEIGHT = 16;
    private static final double IS_ROOM_CLEAN_STATUS_WIDTH = 18;
    private static final double IS_ROOM_CLEAN_STATUS_HEIGHT = 18;
    private static final double TEXT_GRAPHIC_GAP = 7;
    private static final String NAME_TOOLTIP = "Name";
    private static final String PHONE_TOOLTIP = "Phone Number";
    private static final String EMAIL_TOOLTIP = "Email";
    private static final String ROOM_TOOLTIP = "Room";
    private static final String DATE_RANGE_TOOLTIP = "Date of Stay";
    private static final String NUMBER_OF_GUESTS_TOOLTIP = "Number of Guests";
    private static final String IS_ROOM_CLEAN_TOOLTIP = "Room Cleaned";
    private static final String BILL_TOOLTIP = "Bill";
    private static final String REQUEST_TOOLTIP = "Request";


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
        Tooltip nameTooltip = new Tooltip(NAME_TOOLTIP);
        name.setTooltip(nameTooltip);

        phone.setText(guest.getPhone().value);
        Image phoneImage = new Image(getClass().getResourceAsStream("/images/phone.png"));
        setLabel(phone, phoneImage, PHONE_TOOLTIP);

        email.setText(guest.getEmail().value);
        Image emailImage = new Image(getClass().getResourceAsStream("/images/email.png"));
        setLabel(email, emailImage, EMAIL_TOOLTIP);

        room.setText(guest.getRoom().value);
        Image roomImage = new Image(getClass().getResourceAsStream("/images/room.png"));
        setLabel(room, roomImage, ROOM_TOOLTIP);

        dateRange.setText(guest.getDateRange().value);
        Image dateRangeImage = new Image(getClass().getResourceAsStream("/images/dateRange.png"));
        setLabel(dateRange, dateRangeImage, DATE_RANGE_TOOLTIP);

        numberOfGuests.setText(guest.getNumberOfGuests().value);
        Image numberOfGuestsImage = new Image(getClass().getResourceAsStream("/images/group.png"));
        setLabel(numberOfGuests, numberOfGuestsImage, NUMBER_OF_GUESTS_TOOLTIP);

        isRoomClean.setText("");
        isRoomCleanStatus.setText("");
        Image isRoomCleanImage = new Image(getClass().getResourceAsStream("/images/isRoomClean.png"));
        setLabel(isRoomClean, isRoomCleanImage, IS_ROOM_CLEAN_TOOLTIP);

        // Set graphic based on status of isRoomClean
        if (guest.getIsRoomClean().value.equals("yes")) {
            Image isRoomCleanStatusImage = new Image(getClass().getResourceAsStream("/images/roomClean.png"));
            ImageView setIsRoomCleanStatusImage = new ImageView(isRoomCleanStatusImage);
            setIsRoomCleanStatusImage.setFitWidth(IS_ROOM_CLEAN_STATUS_WIDTH);
            setIsRoomCleanStatusImage.setFitHeight(IS_ROOM_CLEAN_STATUS_HEIGHT);
            isRoomCleanStatus.setGraphic(setIsRoomCleanStatusImage);
        } else {
            Image image = new Image(getClass().getResourceAsStream("/images/roomUnclean.png"));
            ImageView setImage = new ImageView(image);
            setImage.setFitWidth(IS_ROOM_CLEAN_STATUS_WIDTH);
            setImage.setFitHeight(IS_ROOM_CLEAN_STATUS_HEIGHT);
            isRoomCleanStatus.setGraphic(setImage);
        }

        Tooltip isRoomCleanStatusTooltip = new Tooltip(IS_ROOM_CLEAN_TOOLTIP);
        isRoomCleanStatus.setTooltip(isRoomCleanStatusTooltip);

        bill.setText(guest.getBill().value);
        Image billImage = new Image(getClass().getResourceAsStream("/images/bill.png"));
        setLabel(bill, billImage, BILL_TOOLTIP);

        request.setText(guest.getRequest().value);
        Image requestImage = new Image(getClass().getResourceAsStream("/images/request.png"));
        setLabel(request, requestImage, REQUEST_TOOLTIP);
    }

    private void setLabel(Label label, Image image, String text) {
        ImageView setImage = new ImageView(image);
        setImage.setFitWidth(IMAGE_WIDTH);
        setImage.setFitHeight(IMAGE_HEIGHT);
        label.setGraphic(setImage);
        label.setGraphicTextGap(TEXT_GRAPHIC_GAP);
        label.setWrapText(true);
        Tooltip tooltip = new Tooltip(text);
        label.setTooltip(tooltip);
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
