    public class BSTMap<K, V> implements Map<K, V> {
    BSTree tree;
    int elements;


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


    class BSTree {
        KVPair<K,V> kv;
        BSTree left, right;

        BSTree add(KVPair<K,V> kv) {
            if (this.kv == null) {
                this.kv = kv;
                return this;
            } else if (this.kv.equals(kv)) {
                return null;
            } else if (kv.key.hashCode() < this.kv.key.hashCode()) {  // add to left
                if (left == null)
                    left = new BSTree();
                return left.add(kv);
            } else { // add to right
                if (right == null)
                    right = new BSTree();
                return right.add(kv);
            }
        }

        void add(BSTree tree) {
            if (tree != null && tree.kv != null) {
                add(tree.kv);
                add(tree.left);
                add(tree.right);
            }
        }

        BSTree find(K key, boolean detatch) {
            if (key == null || this.kv == null || this.kv.key == null)
                return null;
            else if (key.equals(this.kv.key))
                return this; // found it!
            else if (key.hashCode() < this.kv.key.hashCode()) {
                if (left == null)
                    return null;
                else {
                    BSTree t = left.find(key, detatch);
                    if (detatch && t == left)
                        left = null;
                    return t;
                }
            } else {
                if (right == null)
                    return null;
                else {
                    BSTree t = right.find(key, detatch);
                    if (detatch && t == right)
                        right = null;
                    return t;
                }
            }
        }

        public String toString() {
            if (kv == null)
                return "";
            return ((left == null || left.kv == null) ? "" : (left + ", ")) +
                    kv.toString() +
                    ((right == null || right.kv == null) ? "" : (", "+ right));
        }

    }


    @Override
    public void put(K key, V value) {
        if (tree == null) {
            tree = new BSTree();
        }
        BSTree t = tree.find(key, false);
        if (t == null) {
            tree.add(new KVPair<K,V>(key,value));
            elements++;
        } else {
            t.kv.value = value;
        }
    }

    @Override
    public void remove(K key) {
        if (tree != null) {
            BSTree t = tree.find(key, true);
            if (t != null) {
                tree.add(t.right);
                tree.add(t.left);
                elements--;
            }
        }
    }

    @Override
    public V get(K key) {
        if (tree == null)
            return null;
        else {
            BSTree t = tree.find(key, false);
            if (t == null)
                return null;  // not found
            else
                return t.kv.value;
        }
    }

    @Override
    public int size() {
        return elements;
    }


    @Override
    public String toString() {
        return (tree == null) ? "" : tree.toString();
    }
}
