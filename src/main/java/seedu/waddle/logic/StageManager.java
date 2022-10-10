package seedu.waddle.logic;

import javafx.stage.Stage;

public class StageManager {
    private static StageManager instance;
    private Stages stage;

    private StageManager(){}

    public static StageManager getInstance() {
        if (StageManager.instance == null) {
            StageManager.instance = new StageManager();
            return StageManager.instance;
        }
        return StageManager.instance;
    }

    public void setStage(Stages stage) {
        this.stage = stage;
    }

    public Stages getStage() {
        return this.stage;
    }

}
