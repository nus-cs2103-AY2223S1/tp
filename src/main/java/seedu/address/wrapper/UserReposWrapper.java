package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.wrapper.GetReposRoute.getUserReposRoute;

import java.util.Optional;

import org.json.JSONArray;

import kong.unirest.Config;
import kong.unirest.UnirestInstance;

public class UserReposWrapper {
    //@@author arnav-ag

    private final GetReposRoute.GetReposRequest getUserReposRequest;
    private JSONArray reposJson;

    private final String NAME_KEY = "name";

    public UserReposWrapper(String user) {
        requireAllNonNull(user);

        GetReposRoute getUserInfoGetInfoRoute = getUserReposRoute(user);
        Config config = new Config();
        UnirestInstance unirest = new UnirestInstance(config);
        getUserReposRequest = getUserInfoGetInfoRoute.createRequest(unirest);
    }

    private void updateReposJson() {
        this.reposJson = getUserReposRequest.getJSON();
        System.out.println(this.reposJson.toString());
    }

    public Optional<String> getName() {
        if (reposJson == null) {
            updateReposJson();
        }
        return Optional.ofNullable(null);
    }
}
