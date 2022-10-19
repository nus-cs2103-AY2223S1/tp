package seedu.address.ui;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
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
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;

/**
 * The Popup that is displayed when a {@code CalendarButton} is
 * interacted with.
 */
public class CalendarPopup extends Tooltip {
    private static final String SQUARE_BUBBLE =
            "M24 1h-24v16.981h4v5.019l7-5.019h13z";
    private static final Image DATE_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/calendar.png"));
    private static final Image TIME_IMAGE = new Image(MainApp.class.getResourceAsStream("/images/clock.png"));
    private final Node owner;
    private final Point2D ownerCoordinates;

    /**
     * Creates a {@code CalendarPopup} with the given {@code Name}, {@code String}
     * {@code Location} and {@code Node}.
     */
    public CalendarPopup(Name name, String time, Location location, String date, Node owner) {

        this.owner = owner;
        this.ownerCoordinates = owner.localToScene(0.0, 0.0);
        setStyle("-fx-font-size: 16px; -fx-background-color: white; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
        setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        //setMinSize(50, 100);
        setShowDelay(new Duration(10));
        setShowDuration(new Duration(1000));
        setAutoHide(false);
        VBox vBox = new VBox();
        vBox.setMinWidth(10);
        vBox.setAlignment(Pos.TOP_CENTER);

        Label f = new Label(name.toString());
        FlowPane day = new FlowPane();
        FlowPane timePane = new FlowPane();
        day.setAlignment(Pos.CENTER);
        timePane.setAlignment(Pos.CENTER);
        ImageView date_icon = new ImageView(DATE_IMAGE);
        ImageView time_icon = new ImageView(TIME_IMAGE);
        Label dayLabel = new Label(date);
        dayLabel.setStyle("-fx-text-fill: black;");
        Label timeLabel = new Label(time);
        timeLabel.setStyle("-fx-text-fill: black;");
        day.getChildren().addAll(date_icon, dayLabel);
        timePane.getChildren().addAll(time_icon, timeLabel);
        Label s = new Label(location.toString());
        f.setStyle("-fx-text-fill: black;");
        s.setStyle("-fx-text-fill: black;");
        vBox.getChildren().addAll(f, day, timePane, s);
        //flowPane.setMinSize(10, 10);
        //flowPane.setStyle("-fx-background-color: black;");
        setGraphicTextGap(0);
        setGraphic(vBox);
        setContentDisplay(ContentDisplay.TOP);
    }
}
