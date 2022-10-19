package seedu.address.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.address.model.person.Location;
import seedu.address.model.person.Name;

import java.lang.reflect.Field;


public class CalendarButton extends Button {
    private Stage primaryStage;
    private CalendarPopup calendarPopup;

    public CalendarButton(Name name, String time, Location location, Stage primaryStage) {
        super(time + " " + name);
        this.setStyle("-fx-font-size: 8pt; -fx-border-color: grey; -fx-border-radius: 5;" +
                "-fx-min-width: 100;" );
        this.setOnAction(x -> displayToolTip(x));
        this.setOnKeyPressed(x -> handleEnter(x));
        this.primaryStage = primaryStage;
        this.focusedProperty().addListener(x -> displayToolTip(x));
//        this.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
//            @Override
//            public void handle(javafx.event.ActionEvent event) {
//                displayToolTip(event);
//            }
//        });
        this.calendarPopup = new CalendarPopup(getText(), location, this);
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            if (calendarPopup.isShowing()) {
                calendarPopup.hide();
            }
        };
        primaryStage.heightProperty().addListener(stageSizeListener);
        primaryStage.widthProperty().addListener(stageSizeListener);
    }

    @FXML
    private void displayToolTip(ActionEvent event) {
        System.out.println("Hello");
        Point2D p = localToScene(0.0, 0.0);
        //tooltip.setStyle("-fx-font-size: 12pt; -fx-background-color: white; -fx-shape: \\\"\" + SQUARE_BUBBLE + \"\\\";");

        //setTiming(tooltip);

        //tooltip.show(mainWindow.getPrimaryStage(), 100, 100);
        if (!calendarPopup.isShowing()) {
            calendarPopup.show(this, p.getX()
                    + this.getScene().getX() + this.getScene().getWindow().getX(), p.getY()
                    + this.getScene().getY() + this.getScene().getWindow().getY());
        }
    }

    @FXML
    private void displayToolTip(Observable observable) {
        System.out.println("Hello");
        Point2D p = localToScene(0.0, 0.0);
        //tooltip.setStyle("-fx-font-size: 12pt; -fx-background-color: white; -fx-shape: \\\"\" + SQUARE_BUBBLE + \"\\\";");

        //setTiming(tooltip);

        //tooltip.show(mainWindow.getPrimaryStage(), 100, 100);
        if (!calendarPopup.isShowing() && isFocused()) {
            calendarPopup.show(this, p.getX()
                    + this.getScene().getX() + this.getScene().getWindow().getX(), p.getY()
                    + this.getScene().getY() + this.getScene().getWindow().getY());
        }
        if (!isFocused()) {
            calendarPopup.hide();
        }
    }
    @FXML
    private void handleEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            System.out.println("Hello");
        } else if (event.getCode().equals(KeyCode.ESCAPE)) {
        }
    }
}
