package Rec1;

public class PrimQ2<T> implements Moves, SimpleQueue {
	private int moves;
	private int size;
	private T[] data;

	public PrimQ2(int i) {
		size = 0;
		data = (T[]) new Object[i];
	}

	public boolean offer(Object element) {
		T info = (T) element;
		if(size >= data.length)
			data = resize(data);
		data[size] = info;
		size++;
		moves++;
		return true;
	}

	public Object poll() {
		T info = (T) data[0];
		for(int i = 0; i < size; i++){
			data[i] = data[i + 1];
			moves++;
		}
		size--;
		return info;
	}

	public Object peek() {
		if(this.isEmpty())
			return null;
		else
			return data[0];
	}

	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	public void clear() {
		for(int i = 0; i < data.length; i++)
			data[i] = null;
		size = 0;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int val) {
		moves = val;	
	}
	
	private T[] resize( T[] oldAr){
		T[] newAr = (T[]) new Object[oldAr.length * 2];
		for(int i = 0; i < oldAr.length; i++)
			newAr[i] = oldAr[i];
		return newAr;
	}

}
