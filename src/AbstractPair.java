
/**
* Abstract class Pair Key/Value
* @author Tereshchenko Timofey
* @version 1.001
*/
public abstract class AbstractPair<K, V> {
    protected K key;
    protected V value;

    public AbstractPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}