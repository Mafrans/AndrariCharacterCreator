package objects;

public enum Occupation {
    FORHANDLARE("Förhandlare", new String[] {"Intimidera", "Inspirera", "Infiltration", "Hållhake"}, new SpecialAbility[] {}),
    FORKAMPE("Förkämpe", new String[] {"Stark Röst", "Knocka", "Utmana"}, new SpecialAbility[] {}),
    KUNSKAPARE("Kunskapare", new String[] {"Innovatör", "Obskyr Kunskap", "Sjätte Sinne"}, new SpecialAbility[] {}),
    SPECIALIST("Specialist", new String[] {}, new SpecialAbility[] {}),

    ;

    private String name;
    private String[] abilities;
    private SpecialAbility[] specialAbilities;
    Occupation(String _name, String[] _abilities, SpecialAbility[] _specialAbilities) {
        name = _name;
        abilities = _abilities;
        specialAbilities = _specialAbilities;
    }

    public SpecialAbility[] getSpecialAbilities() {
        return specialAbilities;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String[] getAbilities() {
        return abilities;
    }

    public static SpecialAbility[] getDefaultAbilities() {
        return new SpecialAbility[] {
                // Kommunikation
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Förhandla"),
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Bedrägeri"),
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Övertala"),
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Charma"),
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Framföra"),
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Kommendera"),
                new SpecialAbility(CharacterStat.KOMMUNIKATION, "Djurhantering"),

                // Styrka
                new SpecialAbility(CharacterStat.STYRKA, "Kraftprov"),
                new SpecialAbility(CharacterStat.STYRKA, "Tvåhandsvapen (Kross)"),
                new SpecialAbility(CharacterStat.STYRKA, "Tvåhandsvapen (Klinga)"),
                new SpecialAbility(CharacterStat.STYRKA, "Enhandsvapen (Kross)"),
                new SpecialAbility(CharacterStat.STYRKA, "Slagsmål"),

                // Fysik
                new SpecialAbility(CharacterStat.FYSIK, "Klättra"),
                new SpecialAbility(CharacterStat.FYSIK, "Hoppa"),
                new SpecialAbility(CharacterStat.FYSIK, "Simma"),
                new SpecialAbility(CharacterStat.FYSIK, "Rida"),
                new SpecialAbility(CharacterStat.FYSIK, "Gift Motstånd"),
                new SpecialAbility(CharacterStat.FYSIK, "Starkt Immunförsvar"),
                new SpecialAbility(CharacterStat.FYSIK, "Springa"),

                // Smidighet
                new SpecialAbility(CharacterStat.SMIDIGHET,"Enhandsvapen (Klinga)"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Enhandsvapen (Stick)"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Tvåhandsvapen (Stick)"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Smyga"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Fällor"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Låsdyrkning"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Fingerfärdighet"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Bågar/Armborst"),
                new SpecialAbility(CharacterStat.SMIDIGHET,"Kasta"),

                // Perception
                new SpecialAbility(CharacterStat.PERCEPTION, "Kanoner"),
                new SpecialAbility(CharacterStat.PERCEPTION, "Skarpa Sinnen"),
                new SpecialAbility(CharacterStat.PERCEPTION, "Spåra"),
                new SpecialAbility(CharacterStat.PERCEPTION, "Bågar/Armborst"),

                // Viljestyrka
                new SpecialAbility(CharacterStat.VILJESTYRKA, "Mod"),
                new SpecialAbility(CharacterStat.VILJESTYRKA, "Sköldat Sinne"),
                new SpecialAbility(CharacterStat.VILJESTYRKA, "Självdisciplin"),
                new SpecialAbility(CharacterStat.VILJESTYRKA, "Magiska Ritualer"),
                new SpecialAbility(CharacterStat.VILJESTYRKA, "Egen Magisk Förmåga"),
        };
    }
}
