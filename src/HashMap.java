import java.util.LinkedList;

public class HashMap<K, V> implements Map<K,V> {
    final static int DEFAULT_SIZE = 2;
    LinkedList<KVPair<K,V>>[] table;
    int elements = 0;

    public HashMap() {
        table = (LinkedList<KVPair<K,V>>[]) new LinkedList[DEFAULT_SIZE];
    }


    class KVPair<K, V> {
        K key;
        V value;
        KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "("+key+", "+value+")";
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }


    private KVPair<K,V> find(LinkedList<KVPair<K,V>> list, K key) {
        for (KVPair<K,V> kv : list) {
            if (kv.key.equals(key)) {
                return kv;
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int bkt = hash(key);
        if (table[bkt] == null)
            table[bkt] = new LinkedList<>();
        LinkedList<KVPair<K,V>> ll = table[bkt];
        KVPair<K, V> kv = find(ll ,key);
        if (kv == null) {
            kv = new KVPair(key, value);
            ll.add(kv);
            elements++;
        } else {
            kv.value = value;
        }
    }

    @Override
    public void remove(K key) {
        int bkt =  hash(key);
        LinkedList<KVPair<K,V>> ll = table[bkt];
        if (ll != null) {
            KVPair<K, V> kv = find(ll, key);
            if (kv != null) {
                ll.remove(kv);
                elements--;
            }
        }
    }

    @Override
    public V get(K key) {
        int bkt = hash(key);
        LinkedList<KVPair<K, V>> ll = table[bkt];
        if (ll != null) {
            KVPair<K, V> kv = find(ll, key);
            if (kv != null) {
                return kv.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return elements;
    }

    @Override
    public String toString() {
        String rtn = "";

        for (int bkt = 0; bkt < table.length; bkt++) {
            if (table[bkt] != null) {
                LinkedList<KVPair<K,V>> ll = table[bkt];
                for (KVPair<K,V> kv : ll) {
                    if (!rtn.equals(""))
                        rtn += ", ";
                    rtn += kv.toString();
                }
            }
        }

        return rtn;
    }

}
