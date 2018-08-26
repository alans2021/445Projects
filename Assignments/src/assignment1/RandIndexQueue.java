package assignment1;

import java.util.Random;

public class RandIndexQueue<T> implements MyQ<T>, Indexable<T>, Shufflable{
	private T[] data;
	private int write;
	private int next;
	private int size;
	private int moves;
	
	@SuppressWarnings("unchecked")
	public RandIndexQueue(int i){
		data = (T[]) new Object[i];
		size = 0;
		write = 0;
		next = 0;
	}
	@SuppressWarnings("unchecked")
	public RandIndexQueue(){
		data = (T[]) new Object[1];
		size = 0;
		write = 0;
		next = 0;
	}

	public boolean offer(T item) {
		if(write >= data.length - 1 || size == 0)
			write = 0;
		else
			write++;
		
		if(write == next && size != 0)
			data = resize(data);
		data[write] = (T) item;

		size++;
		moves++;
		return true;
	}

	public T poll() {
		if(size > 0){
			T element = (T) data[next];
			size--;
			moves++;
			
			if(next == data.length - 1)
				next = 0;
			else
				next++;
			
			return element;
		}
		else
			return null;
	}

	public T peek() {
		return data[next];
	}

	public boolean isFull() {
		return false;
	}

	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	public int size() {
		return size;
	}
	
	public void clear() {
		for(int i = 0; i < size; i++)
			data[i] = null;
		size = 0;
		write = 0;
		next = 0;
		moves = 0;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public void shuffle() {
		Random r = new Random();
		for(int i = 0; i < size; i++){
			int ind = r.nextInt(size); //Switch two cards at i and a random index; do for each index
			T temp = data[i];
			data[i] = data[ind];
			data[ind] = temp;
		}
	}

	public T get(int i) {
		if(i >= size)
			throw new IndexOutOfBoundsException();
		
		int index = next + i;
		if(index > data.length){ //If logical index is larger than array length, index wraps around to beginning
			index = index - data.length;
		}
			
		return data[index];
	}

	public void set(int i, T item) {
		if(i >= size)
			throw new IndexOutOfBoundsException();
		
		int index = next + i;
		if(index > data.length) //If logical index is larger than array length, index wraps around to beginning
			index = index - data.length;
			
		data[index] = item;
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder("Contents: ");
		if(write > next){ //That means no wrapping around occurs, so just go through sequentially
			for(int i = next; i < write + 1; i++)
				b.append(data[i] + " ");
		}
		else{
			for(int i = next; i < data.length; i++) //Print contents from next to end of physical array
				b.append(data[i] + " ");
			for(int i = 0; i < write + 1; i++) //Then print from beginning to write
				b.append(data[i] + " ");
		}
		return b.toString();
	}
	
	@SuppressWarnings("unchecked")
	private T[] resize( T[] oldAr){
		int index = 0; //Represents index of new array to be filled
		T[] newAr = (T[]) new Object[oldAr.length * 2];
		
		for(int i = write; i < oldAr.length; i++){
			newAr[index] = oldAr[i];
			index++;
		}
		
		for(int i = 0; i < write; i++){
			newAr[index] = oldAr[i];
			index++;
		}
		next = 0;
		write = index;
		return newAr;
	}

}
