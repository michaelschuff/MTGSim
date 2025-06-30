package org.koi.game.turn;

public enum PhaseType {
    Untap,
    Upkeep,
    Draw,
    Main1,
    CombatStart,
    CombatAttackers,
    CombatBlockers,
    CombatDamage,
    CombatEnd,
    Main2,
    End,
    Cleanup;

    public PhaseType Next() {
        return PhaseType.values()[this.ordinal() + 1];
    }
}
