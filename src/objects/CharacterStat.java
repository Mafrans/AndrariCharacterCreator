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

    public static CharacterStat find(String name) {
        for(CharacterStat stat : values()) {
            if(stat.getName().equals(name)) {
                return stat;
            }
            if(stat.toString().equals(name)) {
                return stat;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}
