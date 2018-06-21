package objects;

import org.json.JSONArray;
import org.json.JSONObject;
import races.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveFileObj {
    public String name = null;                         // Namn
    public String title = null;                        // Titel
    public String age = null;                          // Ålder
    public String gender = null;                       // Kön
    public Occupation occupation = null;               // Yrke
    public Race race = null;                           // Ras
    public CharacterStat cultureBonus = null;          // KulturBonus
    public String ability = null;                      // Förmåga
    public ComboBoxItem[] specialAbilities = null;     // Specialiseringar
    public Map<CharacterStat, String> stats = null;   // Grundegenskaper
    public String height = null;                       // Height
    public String weight = null;                       // Weight

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

    public Map<CharacterStat, String> getStats() {
        return stats;
    }

    public void setStats(Map<CharacterStat, String> stats) {
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

    public SaveFileObj(String name, String title, String age, String gender, Occupation occupation, Race race, CharacterStat cultureBonus, String ability, ComboBoxItem[] specialAbilities, Map<CharacterStat, String> stats, String height, String weight) {
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

    public JSONObject toJSON() {
        JSONArray specialAbilityArray = new JSONArray();
        for(ComboBoxItem item : specialAbilities) {
            String value = item.getValue();
            String id = item.getId();
            if(id.replace(" ", "").isEmpty()) continue;
            if(value.replace(" ", "").isEmpty()) continue;

            specialAbilityArray.put(new JSONObject().put("name", value).put("id", id));
        }

        JSONArray statArray = new JSONArray();
        for(CharacterStat stat : stats.keySet()) {
            String id = stats.get(stat);
            if(id == null || id.isEmpty()) continue;

            statArray.put(new JSONObject().put("stat", stat.toString()).put("id", id));
        }

        Map<String, Object> outMap = new HashMap<>();

        outMap.put("name", name);
        outMap.put("title", title);
        outMap.put("age", age);
        outMap.put("gender", gender);
        outMap.put("occupation", occupation.toString());
        outMap.put("race", race.getClass().getName());
        outMap.put("culture_bonus", cultureBonus.toString());
        outMap.put("ability", ability);
        outMap.put("special_abilities", specialAbilityArray);
        outMap.put("base_stats", statArray);
        outMap.put("height", height);
        outMap.put("weight", weight);


        JSONObject outJSON = new JSONObject();
        for(String key : outMap.keySet()) {
            Object value = outMap.get(key);

            if(key == null || value == null) continue;
            if(value instanceof String && ((String)value).isEmpty()) continue;

            outJSON.put(key, value);
        }

        System.out.println(outJSON);
        return outJSON;
    }

    public static SaveFileObj fromJSON(JSONObject json) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SaveFileObj saveFileObj = new SaveFileObj();
        saveFileObj.setName((String) EUtil.getJSONSafe(json, "name"));
        saveFileObj.setTitle((String) EUtil.getJSONSafe(json, "title"));
        saveFileObj.setAge((String) EUtil.getJSONSafe(json, "age"));
        saveFileObj.setGender((String) EUtil.getJSONSafe(json, "gender"));
        saveFileObj.setOccupation(Occupation.find((String) EUtil.getJSONSafe(json, "occupation")));
        saveFileObj.setRace((Race) Class.forName((String)EUtil.getJSONSafe(json, "race")).newInstance());
        saveFileObj.setCultureBonus(CharacterStat.find((String)EUtil.getJSONSafe(json, "culture_bonus")));
        saveFileObj.setAbility((String)EUtil.getJSONSafe(json, "ability"));

        JSONArray specialAbilityJSONArray = (JSONArray) EUtil.getJSONSafe(json, "special_abilities");
        List<ComboBoxItem> specialAbilities = new ArrayList<>();
        if(specialAbilityJSONArray != null) {
            for (int i = 0; i < specialAbilityJSONArray.length(); i++) {
                JSONObject abilityJSON = specialAbilityJSONArray.getJSONObject(i);
                String id = (String) EUtil.getJSONSafe(abilityJSON, "id");
                String name = (String) EUtil.getJSONSafe(abilityJSON, "name");

                ComboBoxItem comboBoxItem = new ComboBoxItem(id, name);
                specialAbilities.add(comboBoxItem);
            }
        }
        saveFileObj.setSpecialAbilities(specialAbilities.toArray(new ComboBoxItem[0]));

        JSONArray baseStatsJSONArray = (JSONArray) EUtil.getJSONSafe(json, "base_stats");
        Map<CharacterStat, String> baseStats = new HashMap<>();
        if(baseStatsJSONArray != null) {
            for (int i = 0; i < baseStatsJSONArray.length(); i++) {
                JSONObject statJSON = baseStatsJSONArray.getJSONObject(i);
                CharacterStat stat = CharacterStat.find((String) EUtil.getJSONSafe(statJSON, "stat"));
                String id = (String) EUtil.getJSONSafe(statJSON, "id");

                baseStats.put(stat, id);
            }
        }

        saveFileObj.setStats(baseStats);
        saveFileObj.setHeight((String) EUtil.getJSONSafe(json, "height"));
        saveFileObj.setWeight((String) EUtil.getJSONSafe(json, "weight"));

        return saveFileObj;
    }
}
