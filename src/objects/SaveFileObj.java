package objects;

import races.Race;

import java.util.Map;

public class SaveFileObj {
    public String name;                         // Namn
    public String title;                        // Titel
    public String age;                          // Ålder
    public String gender;                       // Kön
    public Occupation occupation;               // Yrke
    public Race race;                           // Ras
    public CharacterStat cultureBonus;          // KulturBonus
    public String ability;                      // Förmåga
    public ComboBoxItem[] specialAbilities;   // Specialiseringar
    public Map<CharacterStat, Integer> stats;   // Grundegenskaper
    public String height;                       // Height
    public String weight;                       // Weight

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public CharacterStat getCultureBonus() {
        return cultureBonus;
    }

    public void setCultureBonus(CharacterStat cultureBonus) {
        this.cultureBonus = cultureBonus;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public ComboBoxItem[] getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(ComboBoxItem[] specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    public Map<CharacterStat, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<CharacterStat, Integer> stats) {
        this.stats = stats;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public SaveFileObj(String name, String title, String age, String gender, Occupation occupation, Race race, CharacterStat cultureBonus, String ability, ComboBoxItem[] specialAbilities, Map<CharacterStat, Integer> stats, String height, String weight) {
        this.name = name;
        this.title = title;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.race = race;
        this.cultureBonus = cultureBonus;
        this.ability = ability;
        this.specialAbilities = specialAbilities;
        this.stats = stats;
        this.height = height;
        this.weight = weight;
    }

    public SaveFileObj() {}
}
