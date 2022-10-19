package seedu.address.ui;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import seedu.address.model.person.Location;

public class CalendarPopup extends Tooltip {
    private static final String SQUARE_BUBBLE =
            "M24 1h-24v16.981h4v5.019l7-5.019h13z";
    private final Node owner;
    private final Point2D ownerCoordinates;
    public CalendarPopup(String content, Location location, Node owner) {
        super(content);
        this.owner = owner;
        this.ownerCoordinates = owner.localToScene(0.0, 0.0);
        setStyle("-fx-font-size: 16px; -fx-background-color: white; -fx-shape: \"" + SQUARE_BUBBLE + "\";");
        setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_BOTTOM_LEFT);
        setMinSize(100, 100);
        setShowDelay(new Duration(10));
        setShowDuration(new Duration(1000));
        setAutoHide(false);
        VBox vBox = new VBox();
        Label f = new Label(content);
        Label s = new Label(location.toString());
        f.setStyle("-fx-color: black;");
        vBox.getChildren().addAll(f, s);
        //flowPane.setMinSize(10, 10);
        //flowPane.setStyle("-fx-background-color: black;");
        setGraphicTextGap(0);
        setGraphic(vBox);
//
        setContentDisplay(ContentDisplay.TOP);
    }

//    public void show() {
////        tooltip.show(this, p.getX()
////                + this.getScene().getX() + this.getScene().getWindow().getX(), p.getY()
////                + this.getScene().getY() + this.getScene().getWindow().getY());
////        show(owner, ownerCoordinates.getX()
////                        + owner.getScene().getX() + owner.getScene().getWindow().getX(),
////                ownerCoordinates.getY() + owner.getScene().getY()
////                        + owner.getScene().getWindow().getY());
//    }
}
