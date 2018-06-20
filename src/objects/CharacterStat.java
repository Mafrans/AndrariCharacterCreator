package objects;

public enum CharacterStat {
    KOMMUNIKATION("Kommunikation"),
    FYSIK("Fysik"),
    LIST("List"),
    SMIDIGHET("Smidighet"),
    PERCEPTION("Perception"),
    STYRKA("Styrka"),
    VILJESTYRKA("Viljestyrka"),
    ;

    private String name;
    CharacterStat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
