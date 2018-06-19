package objects;

public class SpecialAbility {
    private CharacterStat characterStat;
    private String ability;

    public SpecialAbility(CharacterStat _characterStat, String _ability) {
        characterStat = _characterStat;
        ability = _ability;
    }

    public CharacterStat getCharacterStat() {
        return characterStat;
    }

    public void setCharacterStat(CharacterStat characterStat) {
        this.characterStat = characterStat;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}
