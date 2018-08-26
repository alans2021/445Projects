package assignment2;
//Alan Shen, CS 0445, 
//Dr. Ramirez
public class MyStringBuilder
{
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		firstC = new CNode(s[0]); //First character is first node
		length++;
		CNode currNode = firstC;  //Keeps track of where list currently is
		for(int i = 1; i < s.length; i++){
			CNode newNode = new CNode(s[i]);
			currNode.next = newNode; //currNode's references the newNode specified in line before
			currNode = newNode; //current Node reference now to the new node
			length++;
		}
		lastC = currNode;
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		length = 0;
		firstC = null;
		lastC = null;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		char[] newchars = b.toString().toCharArray();
		if(this.length == 0){ //If this MyStringBuilder is at first nothing
			firstC = new CNode(newchars[0]); //Have first node reference first character of the argument
			CNode currNode = firstC;
			length++;
			for(int i = 1; i < newchars.length; i++){ //Same mechanism of updating current node and referencing new node
				CNode newNode = new CNode(newchars[i]); 
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode; //last node references the current node
			return this;
		}
		else{
			CNode currNode = lastC; //current node is original last node
			for(int i = 0; i < newchars.length; i++){
				CNode newNode = new CNode(newchars[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
			return this;
		}
		
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		char[] newchars = s.toCharArray();
		if(this.length == 0){
			firstC = new CNode(newchars[0]);
			CNode currNode = firstC;
			length++;
			for(int i = 1; i < newchars.length; i++){
				CNode newNode = new CNode(newchars[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
			return this;
		}
		else{
			CNode currNode = lastC;
			for(int i = 0; i < newchars.length; i++){
				CNode newNode = new CNode(newchars[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
			return this;
		}
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if(this.length == 0){
			firstC = new CNode(c[0]);
			CNode currNode = firstC;
			length++;
			for(int i = 1; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
			return this;
		}
		else{
			CNode currNode = lastC;
			for(int i = 0; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
			return this;
		}
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		CNode newNode = new CNode(c);
		if(length == 0){
			length++;
			firstC = newNode;
			lastC = newNode;
			return this;
		}
		else{
			CNode currNode = lastC;
			currNode.next = newNode;
			lastC = newNode;
			length++;
			return this;
		}
			
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index >= length)
			throw new IndexOutOfBoundsException();

		int current = 0; //Keeps track of logical index
		CNode currNode = firstC;
		while(current < index){
			currNode = currNode.next; //Keep going to next Node when requested index has not been reached
			current++;
		}
		
		return currNode.data;
	}
	
	public int length() {
		return length;
	}
	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		if(start < 0 || start >= end || start >= length)
			return this;
		
		CNode prevNode = firstC;
		int current = 0;
		while(current < start - 1){ //Change prevNode to next reference until index right before start is reached
			current++;
			prevNode = prevNode.next;
		}
		if(end >= length){ //Deletes everything after prevNode if end is greater than length
			if(start == 0){
				firstC = null;
				lastC = null;
				length = 0;
			}
			else{	
				lastC = prevNode;
				lastC.next = null;
				length = start;
			}
			return this;
		}
		if(start == 0){ //Updates first node 
			while(current < end){
				firstC = firstC.next;
				length--;
				current++;
			}
			return this;
		}
		else{ //Normal case of deleting. 
			CNode nextNode = prevNode.next; //original nextnode
			while(current < end - 1){
				nextNode = nextNode.next; //keep changing the nextnode until index end - 1 is reached
				current++;
				length--;
			}
			prevNode.next = nextNode; //prevNode references nextNode
			return this;
		}
		
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index < 0 || index >= length)
			return this;
		CNode currNode = firstC;
		int current = 0;
		while(current < index - 1){
			currNode = currNode.next;
			current++;
		}
		if(current == 0)
			firstC = firstC.next;
		else if(current == length - 1)
			lastC = currNode;
		else
			currNode.next = currNode.next.next;
		length--;
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		char[] string = str.toCharArray();
		int i = 0; //Represents index of array that is char[] string
		int index = -1; //Represents what index the String is found is found
		int current = 0; //Represents logical index of the LinkedList
		CNode currNode = firstC;
		while(current < length){
			if(string[i] == currNode.data){
				if(i == string.length - 1){ //Means that str argument has been found in List
					index = current - i; //index is equal to current minus index i (which is length of str)
					break; //break out of loop
				}
				else //Check to see if next character matches
					i++;
			}
			currNode = currNode.next; //Update currNode to the nextOne
			current++;
		}
		return index;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		if(offset < 0 || offset > length)
			return this;
		char[] newchars = str.toCharArray();
		
		if(offset == 0){		
			CNode firstNode = new CNode(newchars[0]); //firstC will eventually reference firstnode
			CNode newNode = firstNode;
			for(int i = 1; i < newchars.length; i++){
				newNode.next = new CNode(newchars[i]); //Make sure each character references next one
				newNode = newNode.next;
			}
			if(length == 0){
				lastC = newNode;
				length = newchars.length;
			}
			else
				length += newchars.length;
			newNode.next = firstC; //The last of inserted references the original firstnode
			firstC = firstNode; //firstnode is now the first of the inserted
		}
		
		else{
			CNode currNode = firstC;
			for(int current = 0; current < offset - 1; current++)
				currNode = currNode.next; //Find the Node right before the place where new String inserted
			CNode nextNode = currNode.next;
			for(int i = 0; i < newchars.length; i++){ //Make the reference to the new nodes
				currNode.next = new CNode(newchars[i]);
				currNode = currNode.next;
				length++;
			}
			currNode.next = nextNode;
			if(nextNode == null) //Happens if inserted at the end, lastNode is the last character of inserted String
				lastC = currNode;
		}
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if(offset < 0 || offset > length)
			return this;
		CNode newNode = new CNode(c);
		CNode currNode = firstC;
		if(offset == length){
			if(length == 0){
				firstC = newNode;
				lastC = newNode;
			}
			else{
				lastC.next = newNode;
				lastC = newNode;
			}
		}
		else if(offset == 0){
			firstC = newNode;
			firstC.next = currNode;
		}
		else{
			for(int i = 0; i < offset - 1; i++)
				currNode = currNode.next;
			newNode.next = currNode.next;
			currNode.next = newNode;
		}
		length++;
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		if(offset < 0 || offset > length)
			return this;
		char[] newchars = c;
		
		if(offset == 0){		
			CNode firstNode = new CNode(newchars[0]);
			CNode newNode = firstNode;
			for(int i = 1; i < newchars.length; i++){
				newNode.next = new CNode(newchars[i]);
				newNode = newNode.next;
			}
			if(length == 0){
				lastC = newNode;
				length = newchars.length;
			}
			else
				length += newchars.length;
			newNode.next = firstC;
			firstC = firstNode;
		}
		
		else{
			CNode currNode = firstC;
			for(int current = 0; current < offset - 1; current++)
				currNode = currNode.next;
			CNode nextNode = currNode.next;
			for(int i = 0; i < newchars.length; i++){
				currNode.next = new CNode(newchars[i]);
				currNode = currNode.next;
				length++;
			}
			currNode.next = nextNode;
			if(nextNode == null)
				lastC = currNode;
		}
		return this;
	}

	
	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{
		char[] newchars = str.toCharArray();
		if(start < 0 || end <= start)
			return this;
		if(end > length) //If end greater than length of string, just change end to equal length
			end = length;
		
		CNode currNode;
		if(start == 0) //If start == 0, make an arbitrary node that references the first node; let that be currNode
			currNode = new CNode('a', firstC); 
		else	
			currNode = firstC;
		
		for(int i = 0; i < start - 1; i++) //Find the previous Node before the nodes to be deleted
			currNode = currNode.next;
		for(int i = start; i < end; i++){ //Delete nodes from start to end - 1
			currNode.next = currNode.next.next;
			length--;
		}
		
		CNode nextNode = currNode.next; //Find the Node right after the insertion
		for(int i = 0; i < newchars.length; i++){
			currNode.next = new CNode(newchars[i]);	
			 						
			//If start == 0 and the first character of array added, firstC is reference
			if(i == 0 && start == 0)//to the arbitrary node's "next" reference
				firstC = currNode.next;
			
			currNode = currNode.next;
			length++;
		}
		currNode.next = nextNode; //Reference the last new node added to the nextNode, which is right after insertion
		
		if(end == length) //Reference lastC to the currNode
			lastC = currNode;
		return this;
			
	}

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.
	public MyStringBuilder reverse()
	{
		char[] original = this.toString().toCharArray(); //Takes characters and puts in an array
		for(int i = 0; i < original.length / 2; i++){	//Reverses order of characters
			char temp = original[length - i - 1];
			original[original.length - i - 1] = original[i];
			original[i] = temp;
		}
		
		firstC = new CNode(original[0]); //Have nodes reference characters in the new order
		CNode currNode = firstC;
		for(int i = 1; i < length; i++){
			CNode newNode = new CNode(original[i]);
			currNode.next = newNode;
			currNode = newNode;
		}
		lastC = currNode;
		return this;
		
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		if(start < 0 || end <= start)
			return this.toString();
		if(end >= length)
			end = length;
		
		int size = end - start; //represents size of char array
		char[] sub = new char[size];
		
		CNode currNode;
		if(start == 0) //If start == 0, make an arbitrary node that references the first node; let that be currNode
			currNode = new CNode('a', firstC); 
		else	
			currNode = firstC;
		
		for(int i = 0; i < start - 1; i++) //Find the previous Node before the nodes to be put in char[] array
			currNode = currNode.next;
		
		for(int i = 0; i < sub.length; i++){
			sub[i] = currNode.next.data; //Add new CNode data into char array
			currNode = currNode.next;	 //Adjust currNode reference
		}
		
		return new String(sub);
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		char[] c = new char[length];
		CNode currNode = firstC;
		int i = 0;
		while(currNode != null){
			c[i] = currNode.data;
			i++;
			currNode = currNode.next;
		}
		return new String(c);
	}

	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}

}
