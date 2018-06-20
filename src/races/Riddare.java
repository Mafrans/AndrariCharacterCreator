package races;

import objects.CharacterStat;
import objects.SpecialAbility;

public class Riddare extends Race {
    @Override
    public String getName() {
        return "Riddare";
    }

    @Override
    public SpecialAbility[] getForcedSpecialAbilities() {
        return new SpecialAbility[] {
                new SpecialAbility(CharacterStat.FYSIK, "Rida")
        };
    }

    @Override
    public CharacterStat[] getRaceBonuses() {
        return new CharacterStat[] {
                CharacterStat.FYSIK,
                CharacterStat.VILJESTYRKA
        };
    }
}
