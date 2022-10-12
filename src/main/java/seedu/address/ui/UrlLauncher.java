package seedu.address.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 *  Launches a URL in the default desktop browser
 *
 * */
public class UrlLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {}

    public void launchWebPage(String url) {
        getHostServices().showDocument(url);
    }
}
