package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import seedu.address.MainApp;
import seedu.address.model.calendar.CalendarEvent;

/**
 * The Popup that is displayed when a {@code CalendarButton} is
 * interacted with.
 */
public class CalendarPopup extends UiPart<Tooltip> {
    private static final String FXML = "CalendarPopup.fxml";
    private static final int POPUP_DELAY = 10;
    private static final int POPUP_DURATION = 1000;
    private static final String LABEL_STYLE = "-fx-text-fill: black;";
    private static final String LEFT_SQUARE_BUBBLE =
            "-fx-shape: \" M24 1h-24v16.981h4v5.019l7-5.019h13z \";";
    private static final String RIGHT_SQUARE_BUBBLE =
            "M24 1h-24v16.981h13l7+5.019v-5.019h4z";
    private static final String POPUP_STYLE = "-fx-font-size: 16px; -fx-background-color: "
            + "white;";
    private static final Image DATE_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/calendar.png"));
    private static final Image TIME_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/clock.png"));
    private static final Image LOCATION_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/location.png"));
    private static final int LABEL_PREF_WIDTH = 200;
    private final Node owner;
    @FXML
    private Tooltip popup;

    /**
     * Creates a {@code CalendarPopup} with the given Appointment details.
     */
    public CalendarPopup(CalendarEvent calendarEvent, Node owner) {
        super(FXML);
        this.owner = owner;
        initialiseCalendarPopup(calendarEvent);
    }

    private void initialiseCalendarPopup(CalendarEvent calendarEvent) {
        popup.setStyle(POPUP_STYLE + LEFT_SQUARE_BUBBLE);
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        popup.setShowDelay(new Duration(POPUP_DELAY));
        popup.setShowDuration(new Duration(POPUP_DURATION));
        popup.setAutoHide(false);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        Label f = new Label(calendarEvent.getName().toString());
        f.setStyle(LABEL_STYLE);
        FlowPane datePane = createFlowPane(DATE_IMAGE, calendarEvent.getDate());
        FlowPane timePane = createFlowPane(TIME_IMAGE, calendarEvent.getTimeFormat());
        FlowPane locationPane = createFlowPane(LOCATION_IMAGE, calendarEvent.getLocation().toString());
        vBox.getChildren().addAll(f, datePane, timePane, locationPane);
        popup.setGraphic(vBox);
        popup.setContentDisplay(ContentDisplay.BOTTOM);
    }

    private FlowPane createFlowPane(Image image, String content) {
        FlowPane flowPane = new FlowPane();
        ImageView icon = new ImageView(image);
        Label label = new Label(content);
        label.setPrefWidth(LABEL_PREF_WIDTH);
        label.setStyle(LABEL_STYLE);
        label.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(icon, label);
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }
}
