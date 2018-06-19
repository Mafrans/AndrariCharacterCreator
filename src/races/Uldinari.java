package races;

import objects.CharacterStat;
import objects.SpecialAbility;

public class Uldinari extends Race {
    @Override
    public String getName() {
        return "Uldinari";
    }

    @Override
    public SpecialAbility[] getForcedSpecialAbilities() {
        return new SpecialAbility[] {
                new SpecialAbility(CharacterStat.SMIDIGHET, "Ormkropp")
        };
    }
}
