public interface Map<K,V>
{
    public void put(K key, V value);
    public void remove(K key);
    public V get(K key);
    public int size();
    public String toString();
}
