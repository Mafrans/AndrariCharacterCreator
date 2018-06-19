package races;

import objects.CharacterStat;
import objects.SpecialAbility;

public class Kandra extends Race {
    @Override
    public String getName() {
        return "Kandra";
    }

    @Override
    public SpecialAbility[] getForcedSpecialAbilities() {
        return new SpecialAbility[] {
                new SpecialAbility(CharacterStat.LIST, "Örtkunskap")
        };
    }
}
