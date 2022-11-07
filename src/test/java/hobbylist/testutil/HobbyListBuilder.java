package hobbylist.testutil;

import hobbylist.model.HobbyList;
import hobbylist.model.activity.Activity;

/**
 * A utility class to help with building HobbyList objects.
 * Example usage: <br>
 *     {@code HobbyList ab = new HobbyListBuilder().withActivity("John", "Doe").build();}
 */
public class HobbyListBuilder {

    private HobbyList hobbyList;

    public HobbyListBuilder() {
        hobbyList = new HobbyList();
    }

    public HobbyListBuilder(HobbyList hobbyList) {
        this.hobbyList = hobbyList;
    }

    /**
     * Adds a new {@code Activity} to the {@code HobbyList} that we are building.
     */
    public HobbyListBuilder withActivity(Activity activity) {
        hobbyList.addActivity(activity);
        return this;
    }

    public HobbyList build() {
        return hobbyList;
    }
}
