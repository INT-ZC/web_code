import java.util.LinkedList;

public class HashSet<T> implements Set<T> {
	
	final static int DEFAULT_SIZE = 2;
	LinkedList<T>[] table;
	int elements = 0;
	
	public HashSet() {
		table = (LinkedList<T>[]) new LinkedList[DEFAULT_SIZE];
	}
	public HashSet(int size) { table = (LinkedList<T>[]) new LinkedList[size]; }
	
	private int hash(T element) {
		return Math.abs(element.hashCode()) % table.length;
	}
	
	@Override
	public void add(T element)
	{
		int bkt = hash(element);
		if (table[bkt] == null)
			table[bkt] = new LinkedList<>();
		LinkedList<T> ll = table[bkt];
		if (!ll.contains(element))
		{
			ll.add(element);
			++elements;
		}
	}
	
	@Override
	public void remove(T element)
	{
		int bkt = hash(element);
		LinkedList<T> ll = table[bkt];
		if (element != null && ll.contains(element))
		{
			ll.remove(element);
			--elements;
		}
	}
	
	@Override
	public boolean contains(T element)
	{
		int bkt = hash(element);
		LinkedList<T> ll = table[bkt];
		if (element != null && ll.contains(element))
		{
			return true;
		}
		return false;
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
				LinkedList<T> ll = table[bkt];
				for (T element : ll) {
					if (!rtn.equals(""))
						rtn += ", ";
					rtn += element.toString();
				}
			}
		}
		
		return rtn;
	}
	
	/* The following method only exists to allow C05.Complexity to operate */
	@Override
	public Set<T> newInstance()
	{
		return new HashSet<T>(this.table.length);
	}
}
