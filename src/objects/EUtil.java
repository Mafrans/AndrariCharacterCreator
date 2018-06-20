package objects;

import java.util.ArrayList;
import java.util.List;

public class EUtil {
    public static Object[] combineArrays(Object[] array1, Object[] array2) {
        List<Object> outArray = new ArrayList<>();

        for(Object o1 : array1) {
            outArray.add(o1);
        }

        for(Object o2 : array2) {
            outArray.add(o2);
        }

        return outArray.toArray();
    }
    public static Object[] combineArrays(Object[] array1, Object[] array2, Object[] array3) {
        List<Object> outArray = new ArrayList<>();

        for(Object o1 : array1) {
            outArray.add(o1);
        }

        for(Object o2 : array2) {
            outArray.add(o2);
        }

        for(Object o3 : array3) {
            outArray.add(o3);
        }

        return outArray.toArray();
    }

}
