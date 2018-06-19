package races;

import objects.CharacterStat;
import objects.SpecialAbility;

public class Eldari extends Race {
    @Override
    public String getName() {
        return "Eldari";
    }

    @Override
    public SpecialAbility[] getAvailableSpecialAbilities() {
        return new SpecialAbility[] {
                new SpecialAbility(CharacterStat.LIST, "Luftskepp"),
                new SpecialAbility(CharacterStat.PERCEPTION, "Krutvapen"),
        };
    }

    @Override
    public SpecialAbility[] getForcedSpecialAbilities() {
        return new SpecialAbility[] {
                new SpecialAbility(CharacterStat.LIST, "Läsa/Skriva")
        };
    }

    @Override
    public CharacterStat[] getRaceBonuses() {
        return new CharacterStat[] {
                CharacterStat.PERCEPTION,
                CharacterStat.LIST
        };
    }
}
