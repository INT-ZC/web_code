import java.util.Arrays;

public class ArrayList<T> implements List<T>
{
	static final int INITIAL_SIZE = 2;
	static final double GROWTH_FACTOR = 1.5;
	
	T[] values = (T[]) new Object[INITIAL_SIZE];
	int elements = 0;
	
	@Override
	public void add(T value)
	{
		if (elements == values.length)
		{
			T[] tmp = Arrays.copyOf(values,(int)(elements*GROWTH_FACTOR));
			values = tmp;
		}
		values[elements++] = value;
		
	}
	
	@Override
	public void remove(int index)
	{
		if (index < 0 || index >= elements)
			throw new IndexOutOfBoundsException();
		--elements;
		for (int i = index; i < elements; ++i)
			values[i] = values[i+1];
	}
	
	@Override
	public T get(int index)
	{
		if (index < 0 || index >= elements)
			throw new IndexOutOfBoundsException();
		return values[index];
	}
	
	@Override
	public int size()
	{
		return elements;
	}
	
	@Override
	public void reverse()
	{
		T[] tmp = Arrays.copyOf(values,elements);
		for (int i = 0; i < elements; ++i)
			values[i] = tmp[elements-i];
	}
	
	@Override
	public String toString() {
		String rtn = "";
		for (int i = 0; i < elements; ++i) {
			rtn += ((i != 0) ? " " : "")+values[i];
		}
		return rtn;
	}
}
