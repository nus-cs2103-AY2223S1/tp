package seedu.address.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONException;

import kong.unirest.UnirestInstance;
import seedu.address.github.exceptions.ResponseParseException;

/**
 * Class representing routes taken to get user's repo information
 */
public class UserReposRoute {
    public static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String GET_USER_BASE_PATH = "/users/";
    private static final String GET_REPO_PATH = "/repos";

    private final String path;

    private UserReposRoute(String path) {
        requireAllNonNull(path);
        this.path = path;
    }

    public static UserReposRoute getUserReposRoute(String username) {
        requireAllNonNull(username);
        return new UserReposRoute(GET_USER_BASE_PATH + username + GET_REPO_PATH);
    }

    /**
     * @param unirest Unirest instance to carry out all requests with
     * @return Request class instance pertaining to getting user repo information
     */
    public UserReposRequest createRequest(UnirestInstance unirest) {
        assert unirest != null : "Unirest instance cannot be null.";

        return new UserReposRequest(unirest, BASE_GITHUB_URL + this.path);
    }

    public String getPath() {
        return this.path;
    }

    /**
     * Class representing request needed to retrieve user repo information from GitHub
     */
    public static class UserReposRequest {

        private final UnirestInstance unirest;
        private final String url;

        UserReposRequest(UnirestInstance unirest, String url) {
            requireAllNonNull(unirest, url);
            this.unirest = unirest;
            this.url = url;
        }

        public JSONArray getJson() {
            String response = this.unirest.get(this.url).asString().getBody();

            try {
                return new JSONArray(response);
            } catch (JSONException e) {
                throw new ResponseParseException("Unable to parse API result.");
            }
        }

        @Override
        public boolean equals(Object other) {
            return other == this
                    || (other instanceof UserReposRequest)
                    && url.equals(((UserReposRequest) other).url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url);
        }
    }
}
