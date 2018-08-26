import java.io.*;
import java.util.*;

public class ReplaceLines {
	
	public static void main(String[]args) throws IOException{
		int number = (int) ( 8 * Math.random() + 1); //Random order number to be processing
		String num = Integer.toString(number); //Changes number to String
		System.out.println(num); //Prints out the Order number that will turn from received to processing
		//Line 7-8 is for me. You don't need those lines
		
		File status = new File("status.txt");
		Scanner readFile = new Scanner(status);
		String[] text = new String[10]; //Array of text output. Maximum of 10 since maximum of 10 orders allowed
		
		int i = 0; //counter for index of String[] text array
		while(readFile.hasNextLine()){
			text[i] = readFile.nextLine(); //Put the line in the text file to the String array
			i++; //Increment index of text array to be edited
		}
		int length = i; // 'Real' length of String array represented by i, put into length variable
		
		PrintWriter pw = new PrintWriter("status.txt");
		for(int j = 0; j < length; j++){
			if(text[j].indexOf(num) != -1)
				pw.println("Order " + num + " is being processed");
			else
				pw.println(text[j]);
		}
		pw.close();
		
	}

}
