package seedu.waddle.logic;

import seedu.waddle.model.itinerary.Itinerary;

import static java.util.Objects.requireNonNull;

public class StageManager {
    private static StageManager instance;
    // default stage is home
    private Stages currentStage = Stages.HOME;
    // stores the currently selected Itinerary
    private Itinerary selectedItinerary;

    private StageManager(){
    }

    public static StageManager getInstance() {
        if (StageManager.instance == null) {
            StageManager.instance = new StageManager();
            return StageManager.instance;
        }
        return StageManager.instance;
    }

    public Stages getCurrentStage() {
        return this.currentStage;
    }

    public boolean isCurrentStage(Stages stage) {
        return this.currentStage == stage;
    }

    public void setHomeStage() {
        this.currentStage = Stages.HOME;
        this.selectedItinerary = null;
    }

    public void setWishStage(Itinerary selectedItinerary) throws NullPointerException {
        requireNonNull(selectedItinerary);
        this.currentStage = Stages.WISH;
        this.selectedItinerary = selectedItinerary;
    }

    public void setScheduleStage(Itinerary selectedItinerary) throws NullPointerException {
        requireNonNull(selectedItinerary);
        this.currentStage = Stages.SCHEDULE;
        this.selectedItinerary = selectedItinerary;
    }

    public void switchStage(Stages selectedStage) {
        this.currentStage = selectedStage;
    }
}
