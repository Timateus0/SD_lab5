

import java.util.Arrays;
import java.util.Comparator;
/**
* Class containing various sorting methods  
* @author Tereshchenko Timofey
* @version 1.001
*/

public class SortUtils {

   
	/**
	* Shell sorting
	* @author Tereshchenko Timofey
	* @param pairs - array of Pair
	* @version 1.001
	*/
    public static <K extends Comparable<K>, V> void shellSort(Pair<K, V>[] pairs) {
        int n = pairs.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Pair<K, V> temp = pairs[i];
                int j;
                for (j = i; j >= gap && pairs[j - gap].getKey().compareTo(temp.getKey()) > 0; j -= gap) {
                    pairs[j] = pairs[j - gap];
                }
                pairs[j] = temp;
            }
        }
    }

    
    /**
	* Introspective sorting
	* @author Tereshchenko Timofey
	* @param pairs - array of Pair
	* @version 1.001
	*/
    
    public static <K extends Comparable<K>, V> void introspectiveSort(Pair<K, V>[] pairs) {
        introspectiveSort(pairs, 0, pairs.length - 1, 2 * (int) Math.log(pairs.length));
    }

    private static <K extends Comparable<K>, V> void introspectiveSort(Pair<K, V>[] pairs, int left, int right, int depthLimit) {
        if (right - left < 16) {
            insertionSort(pairs, left, right);
            return;
        }
        if (depthLimit == 0) {
            Arrays.sort(pairs, left, right + 1, Comparator.comparing(Pair::getKey));
            return;
        }
        int pivotIndex = partition(pairs, left, right);
        introspectiveSort(pairs, left, pivotIndex - 1, depthLimit - 1);
        introspectiveSort(pairs, pivotIndex + 1, right, depthLimit - 1);
    }

    private static <K extends Comparable<K>, V> int partition(Pair<K, V>[] pairs, int left, int right) {
        Pair<K, V> pivot = pairs[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (pairs[j].getKey().compareTo(pivot.getKey()) <= 0) {
                i++;
                swap(pairs, i, j);
            }
        }
        swap(pairs, i + 1, right);
        return i + 1;
    }

    private static <K extends Comparable<K>, V> void insertionSort(Pair<K, V>[] pairs, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Pair<K, V> key = pairs[i];
            int j = i - 1;
            while (j >= left && pairs[j].getKey().compareTo(key.getKey()) > 0) {
                pairs[j + 1] = pairs[j];
                j--;
            }
            pairs[j + 1] = key;
        }
    }
    
   
    private static <K extends Comparable<K>, V> void swap(Pair<K, V>[] pairs, int i, int j) {
        Pair<K, V> temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }

    
}
