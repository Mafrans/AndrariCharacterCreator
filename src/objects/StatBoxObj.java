package objects;

import java.util.ArrayList;
import java.util.Arrays;

public class StatBoxObj {

    public ArrayList<String> options;

    public void setInitialOptions() {
        String[] initialOptions = {null,"+3","+2","+1","+1","+1","+1","+0","-1"};
        this.options = new ArrayList<>(Arrays.asList(initialOptions));
    }


}
