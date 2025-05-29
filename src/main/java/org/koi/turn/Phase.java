package org.koi.turn;


public class Phase {
    public static final PhaseType StartingPhase = PhaseType.Untap;
    public static final PhaseType FinalPhase = PhaseType.Cleanup;
    private PhaseType type = StartingPhase;

    // Can the active player cast sorcery speed stuff during this phase?
    public boolean isSorceryPhase() {
        switch (type) {
            case Main1:
            case Main2:
                return true;
            default:
                return false;
        }
    }

    // Does each player get priority at the end of this phase?
    public boolean GivesPriority() {
        switch (type) {
            case Untap:
            case Cleanup:
                return false;
            default:
                return true;
        }
    }

    public PhaseType getType() {
        return this.type;
    }

    public boolean Next() {
        if (this.type == FinalPhase) {
            this.type = StartingPhase;
            return true;
        }
        this.type = this.type.Next();
        return false;
    }

    public void reset() {
        this.type = StartingPhase;
    }

    public boolean isFinalPhase() {
        return this.type == FinalPhase;
    }
}
