package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.wrapper.UserReposRoute.getUserReposRoute;

import org.json.JSONArray;

import kong.unirest.Config;
import kong.unirest.UnirestInstance;

public class UserReposWrapper {
    //@@author arnav-ag

    private final UserReposRoute.UserReposRequest getUserReposRequest;
    private JSONArray reposJson;

    public UserReposWrapper(String user) {
        requireAllNonNull(user);

        UserReposRoute getUserInfoGetInfoRoute = getUserReposRoute(user);
        Config config = new Config();
        UnirestInstance unirest = new UnirestInstance(config);
        getUserReposRequest = getUserInfoGetInfoRoute.createRequest(unirest);
    }

    private void updateReposJson() {
        this.reposJson = getUserReposRequest.getJSON();
        System.out.println(this.reposJson.toString());
    }
}
