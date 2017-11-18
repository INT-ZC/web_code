public class BSTSet<T> implements Set<T> {
	BSTree tree;
	int elements;
	
	class BSTree {
		T element;
		BSTree left, right;
		
		BSTree add(T element) {
			if (this.element == null)
			{
				this.element = element;
				return this;
			}
			else if (this.element == element)
				return  null;
			else if (element.hashCode() < this.element.hashCode())
			{
				if (left == null)
					left = new BSTree();
				return left.add(element);
			}
			else
			{
				if (right == null)
					right = new BSTree();
				return right.add(element);
			}
		}
		
		void add(BSTree tree) {
			if (tree != null && tree.element != null) {
				add(tree.element);
				add(tree.left);
				add(tree.right);
			}
		}
		
		BSTree find(T element, boolean detatch) {
			if (element == null || this.element == null)
				return null;
			else if (element.equals(this.element))
				return this; // found it!
			else if (element.hashCode() < this.element.hashCode()) {
				if (left == null)
					return null;
				else {
					BSTree t = left.find(element, detatch);
					if (detatch && t == left)
						left = null;
					return t;
				}
			} else {
				if (right == null)
					return null;
				else {
					BSTree t = right.find(element, detatch);
					if (detatch && t == right)
						right = null;
					return t;
				}
			}
		}
		
		public String toString() {
			if (element == null)
				return "";
			return ((left == null || left.element == null) ? "" : (left + ", ")) +
					element.toString() +
					((right == null || right.element == null) ? "" : (", "+ right));
		}
		
	}
	
	
	@Override
	public void add(T element) {
		if (tree == null) {
			tree = new BSTree();
		}
		if (tree.add(element) != null)
			elements++;
	}
	
	@Override
	public void remove(T element) {
		BSTree root = tree.find(element, true);
		if (root != null) {
			tree.add(root.left);
			tree.add(root.right);
			elements--;
		}
	}
	
	@Override
	public boolean contains(T element) {
		return tree != null && tree.find(element, false) != null;
	}
	
	@Override
	public int size() {
		return elements;
	}
	
	@Override
	public String toString() {
		return (tree == null) ? "" : tree.toString();
	}
	
	
	/* The following method only exists to allow C05.Complexity to operate */
	@Override
	public Set<T> newInstance() {
		return new BSTSet<T>();
	}
	
}
