package seedu.address.ui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import seedu.address.MainApp;
import seedu.address.model.calendar.CalendarEvent;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;

/**
 * The Popup that is displayed when a {@code CalendarButton} is
 * interacted with.
 */
public class CalendarPopup extends Tooltip {
    public static final int POPUP_DELAY = 10;
    public static final int POPUP_DURATION = 1000;
    public static final String LABEL_STYLE = "-fx-text-fill: black;";
    private static final String SQUARE_BUBBLE =
            "M24 1h-24v16.981h4v5.019l7-5.019h13z";
    private static final String POPUP_STYLE = "-fx-font-size: 16px; -fx-background-color: "
            + "white; -fx-shape: \"" + SQUARE_BUBBLE + "\";";
    private static final Image DATE_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/calendar.png"));
    private static final Image TIME_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/clock.png"));
    private static final Image LOCATION_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/location.png"));

    private final Node owner;

    /**
     * Creates a {@code CalendarPopup} with the given Appointment details.
     */
    public CalendarPopup(CalendarEvent calendarEvent, Node owner) {
        this.owner = owner;
        setStyle(POPUP_STYLE);
        setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        setShowDelay(new Duration(POPUP_DELAY));
        setShowDuration(new Duration(POPUP_DURATION));
        setAutoHide(false);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        Label f = new Label(calendarEvent.getName().toString());
        f.setStyle(LABEL_STYLE);
        FlowPane datePane = createFlowPane(DATE_IMAGE, calendarEvent.getDate());
        FlowPane timePane = createFlowPane(TIME_IMAGE, calendarEvent.getTimeFormat());
        FlowPane locationPane = createFlowPane(LOCATION_IMAGE, calendarEvent.getLocation().toString());
        vBox.getChildren().addAll(f, datePane, timePane, locationPane);
        setGraphicTextGap(0);
        setGraphic(vBox);
        setContentDisplay(ContentDisplay.TOP);
    }

    private FlowPane createFlowPane(Image image, String content) {
        FlowPane flowPane = new FlowPane();
        ImageView icon = new ImageView(image);
        Label label = new Label(content);
        label.setPrefWidth(200);
        label.setStyle(LABEL_STYLE);
        label.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(icon, label);
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }
}
