package seedu.address.model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.profile.Profile;

/**
 * A Model stub that contains a single profile.
 */
public class ModelStubWithProfile extends ModelStub {
    private final Profile profile;

    /**
     * Every field must be present and not null.
     */
    public ModelStubWithProfile(Profile profile) {
        requireNonNull(profile);
        this.profile = profile;
    }

    @Override
    public boolean hasEmail(Profile profile) {
        requireNonNull(profile);
        return this.profile.isSameEmail(profile);
    }

    @Override
    public boolean hasPhone(Profile profile) {
        requireNonNull(profile);
        return this.profile.isSamePhone(profile);
    }
    @Override
    public boolean hasTelegram(Profile profile) {
        requireNonNull(profile);
        return this.profile.isSameTelegramNotEmpty(profile);
    }
}
