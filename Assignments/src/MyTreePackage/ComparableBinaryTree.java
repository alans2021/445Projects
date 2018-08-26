package MyTreePackage;

public class ComparableBinaryTree <T extends Comparable<?super T>> extends BinaryTree<T> implements ComparableTreeInterface<T>
{
	public ComparableBinaryTree()
	{
		super();
	}
	public ComparableBinaryTree(T rootData)
	{
		super(rootData);
		setRootNode(new BinaryNode<T>(rootData));
	}
	
	@Override
	public T getMax() {
		BinaryNode<T> max = this.getRootNode();
		if(max == null)
			return null;
		
		T data = recMax(max.getLeftChild(), max.getData());
		data = recMax(max.getRightChild(), data);
		return data;
	}
	
	private T recMax(BinaryNode<T> node, T max)
	{
		if(node != null)
		{
			if(node.getData().compareTo(max) > 0) //If node currently at is greater, reset max
				max = node.getData();
			max = recMax(node.getLeftChild(), max); //Check left child
			max = recMax(node.getRightChild(), max); //Check right child
			return max;
		}
		
		else
			return max;
	}

	@Override
	public T getMin() {
		BinaryNode<T> min = this.getRootNode();
		if(min == null)
			return null;
		
		T data = recMin(min.getLeftChild(), min.getData());
		data = recMin(min.getRightChild(), data);
		return data;
	}
	
	private T recMin(BinaryNode<T> node, T min)
	{
		if(node != null)
		{
			if(node.getData().compareTo(min) < 0) //If current node smaller, reset min
				min = node.getData();
			min = recMin(node.getLeftChild(), min); //Check left child
			min = recMin(node.getRightChild(), min); //Check right child
			return min;
		}
		
		else
			return min;
	}

	@Override
	public boolean isBST() 
	{
		BinaryNode<T> node = this.getRootNode();
		return recBST(node);
	}
	
	private boolean recBST(BinaryNode<T> node)
	{
		if(node != null)
		{
			BinaryNode<T> leftC = node.getLeftChild(); //Look at both children
			BinaryNode<T> rightC = node.getRightChild();
			if(leftC == null)
			{
				if(rightC == null) //Single node is by itself a BST
					return true;
				else if(rightC.getData().compareTo(node.getData()) > 0) //If right child is greater, keep going 
					return recBST(rightC);
				else
					return false; //Right child is not greater than parent, so not BST
			}
			else if(rightC == null)
			{
				if(leftC.getData().compareTo(node.getData()) < 0) //Left child is smaller, keep going
					return recBST(leftC);
				else
					return false; //Left child is not smaller, return false
			}
			else if( (leftC.getData().compareTo(node.getData()) < 0) && 
				(rightC.getData().compareTo(node.getData()) > 0) ) //When both children present and are right relation to parent, check left and right subtrees
				return ( recBST(leftC) && recBST(rightC) );
			else
				return false;
			
		}
		else
			return true;
	}

	@Override
	public T get(int i) {
		if(i < 0 || i >= this.getNumberOfNodes())
			throw new IndexOutOfBoundsException();
		else
		{
			Object[] data = new Object[this.getNumberOfNodes()];
			recTraverse(getRootNode(), data, 0); //Put nodes of tree into Object array
			data = insertSort(data); //Sort the data in array

			return (T) data[i];
		}
		
	}

	@Override
	public int rank(T data) {
		Object[] dat = new Object[this.getNumberOfNodes()];
		recTraverse(getRootNode(), dat, 0); //Put nodes of tree into array
		dat = insertSort(dat); //Sort array
		for(int i = 0; i < dat.length; i++) //Once right object reached, return index
		{
			T node = (T) dat[i];
			if(data.compareTo(node) <= 0) //Once the data in array becomes smaller or equal to data in parameter, return
				return i;
		}
		return -1;
		
	}
	
	private int recTraverse(BinaryNode<T> node, Object[] data, int index)
	{
		if(node != null)
		{
			index = recTraverse(node.getLeftChild(), data, index); //Keep going to left children
			data[index] = node.getData(); //Put the node into array
			index = index + 1; //Update index
			index = recTraverse(node.getRightChild(), data, index); //Go through right children
		}
		return index;
	}
	private Object[] insertSort(Object[] data)
	{
		Object temp = null;
		for(int i = 1; i < data.length; i++)
		{
			for(int j = i; j > 0; j--)
			{
				T obj = (T) data[j - 1];
				T obj2 = (T) data[j];
				if(obj.compareTo(obj2) > 0) //If one before is less than one after
				{
					temp = data[j - 1]; //Switch data
					data[j - 1] = data[j];
					data[j] = temp;
					
				}
			}
			
		}
		return data;
	}


}
