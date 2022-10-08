package seedu.address.model.tag;

/**
 * Stub class to use for development of `FindContactCommand`
 */
public class NusModule {

    private String modCode;

    public NusModule(String modCode) {
        this.modCode = modCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof NusModule) {
            NusModule otherMod = (NusModule) object;
            System.out.println(this.modCode.toLowerCase());
            System.out.println(otherMod.modCode.toLowerCase());
            return this.modCode.equalsIgnoreCase(otherMod.modCode);
        }
        return false;
    }

}
