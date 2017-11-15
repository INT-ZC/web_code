public class LinkedList<T> implements List<T>
{
	int elements;
	LL first, last;
	
	class LL
	{
		T value;
		LL next;
		
		public LL(T value, LL next)
		{
			this.value = value;
			this.next  = next;
		}
		@Override
		public String toString()
		{
			return value + ((next == null) ? "" : " " + next.toString());
		}
	}
	
	@Override
	public void add(T value)
	{
		if (last == null)
			first = last = new LL(value, null);
		else
		{
			last.next = new LL(value, null);
			last = last.next;
		}
		++elements;
	}
	
	@Override
	public void remove(int index)
	{
		if (index < 0 || index == elements)
			throw new IndexOutOfBoundsException();
		else if (index == 0)
			first = first.next;
		else
		{
			LL prev = getNode(first, index-1);
			prev.next = prev.next.next;
			if (prev.next == null)
				last = prev;
		}
		--elements;
	}
	private LL getNode (LL start, int index) {return (index == 0) ? start : getNode(start.next,index-1);}
	
	
	@Override
	public T get(int index) {
		if (index >= elements || index < 0)
			throw new IndexOutOfBoundsException();
		return getNode(first, index).value;
	}
	
	@Override
	public int size()
	{
		return elements;
	}
	
	@Override
	public void reverse()
	{
		LL prev = null;
		LL curr = first;
		while (curr != null)
		{
			LL tmp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = tmp;
		}
		first = prev;
	}
}
