import objects.ComboBoxItem;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.*;

public class StatBoxHandler {
    private ComboBoxItem[] stats = {
            new ComboBoxItem("default", " "),
            new ComboBoxItem("+2", "+2"),
            new ComboBoxItem("+1 0", "+1"),
            new ComboBoxItem("+1 1", "+1"),
            new ComboBoxItem("first +0", "+0"),
            new ComboBoxItem("second +0", "+0"),
            new ComboBoxItem("-1", "-1"),
            new ComboBoxItem("-2", "-2"),
    };

    private final Map<JComboBox, String> comboBoxMap = new HashMap<>();

    public void registerBox(JComboBox comboBox) {
        comboBoxMap.put(comboBox, "default"); // Default value
    }

    public void setBoxId(JComboBox box, String id) {
        comboBoxMap.put(box, id);
    }

    public ComboBoxItem[] getAvailableStats(JComboBox comboBox) {
        List<ComboBoxItem> statsToUse = new ArrayList<>();
        comboBox.setSelectedIndex(0);

        for (int i = 0; i < stats.length; i++) {
            String statId = stats[i].getId();
            boolean useValue = true;
            for (JComboBox box : comboBoxMap.keySet()) {
                String id = comboBoxMap.get(box);
                if (id.equals("default")) continue; // Skip value

                System.out.println(statId + " ?= " + id);
                if (id.equals(statId)) {
                    //System.out.println("Second equals triggers");
                    useValue = false;
                    break;
                }
            }

            if (useValue) {
                statsToUse.add(stats[i]);
            }
        }
        //System.out.println("--------------------------");
        return statsToUse.toArray(new ComboBoxItem[0]);
    }

    public ComboBoxItem[] getDefaults() {
        return stats;
    }

    public void setDefaults(ComboBoxItem[] defaults) {
        stats = defaults;
    }
}
