package seedu.address.model.person;

public class Mod {
    String modCode;
    public Mod(String modCode) {
        this.modCode = modCode;
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof Mod) {
            Mod otherMod = (Mod) object;
            return this.modCode.equals(otherMod.modCode);
        }
        return false;
    }
}
