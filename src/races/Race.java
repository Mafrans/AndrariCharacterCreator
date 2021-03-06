package races;

import objects.CharacterStat;
import objects.Occupation;
import objects.SpecialAbility;

public abstract class Race {
    public String getName() {
        return "Unnamed Race";
    }

    public Occupation[] getAvailableOccupations() {
        return new Occupation[] {
                Occupation.FORHANDLARE,
                Occupation.FORKAMPE,
                Occupation.KUNSKAPARE,
                Occupation.SPECIALIST,
        };
    }

    public SpecialAbility[] getAvailableSpecialAbilities() {
        return new SpecialAbility[0];
    }

    public SpecialAbility[] getForcedSpecialAbilities() {
        return new SpecialAbility[0];
    }

    @Override
    public String toString() {
        return getName();
    }
    public CharacterStat[] getRaceBonuses() {
        return new CharacterStat[0];
    }
}
