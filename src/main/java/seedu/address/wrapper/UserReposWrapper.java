package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.wrapper.UserReposRoute.getUserReposRoute;

import org.json.JSONArray;

import kong.unirest.UnirestInstance;

public class UserReposWrapper {
    //@@author arnav-ag

    private final UserReposRoute.UserReposRequest getUserReposRequest;
    private JSONArray reposJson;

    public UserReposWrapper(String user, UnirestInstance unirest) {
        requireAllNonNull(user);

        UserReposRoute getUserInfoGetInfoRoute = getUserReposRoute(user);
        getUserReposRequest = getUserInfoGetInfoRoute.createRequest(unirest);
    }

    private void updateReposJson() {
        this.reposJson = getUserReposRequest.getJSON();
        System.out.println(this.reposJson.toString());
    }
}
