package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import org.json.JSONArray;
import org.json.JSONException;

import kong.unirest.UnirestInstance;
import seedu.address.wrapper.exceptions.ResponseParseException;

public class UserReposRoute {
    //@@author arnav-ag

    public static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String GET_USER_BASE_PATH = "/users/";
    private static final String GET_REPO_PATH = "/repos";

    private final String path;

    private UserReposRoute(String path) {
        requireAllNonNull(path);
        this.path = path;
    }

    public static UserReposRoute getUserReposRoute(String user) {
        requireAllNonNull(user);
        return new UserReposRoute(GET_USER_BASE_PATH + user + GET_REPO_PATH);
    }

    public UserReposRequest createRequest(UnirestInstance unirest) {
        requireAllNonNull(unirest);

        return new UserReposRequest(unirest, BASE_GITHUB_URL + this.path);
    }

    public String getPath() {
        return this.path;
    }

    public static class UserReposRequest {

        private final UnirestInstance unirest;
        private final String url;

        UserReposRequest(UnirestInstance unirest, String url) {
            requireAllNonNull(unirest, url);
            this.unirest = unirest;
            this.url = url;
        }

        public JSONArray getJSON() {
            String response = this.unirest.get(this.url).asString().getBody();

            try {
                return new JSONArray(response);
            } catch (JSONException e) {
                throw new ResponseParseException("Unable to parse API result.");
            }
        }
    }
}
