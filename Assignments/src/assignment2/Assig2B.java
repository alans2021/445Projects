package assignment2;

public class Assig2B {
	
	public static void main(String[]args){
		int numChar = Integer.parseInt(args[0]);
		String str = new String();
		StringBuilder build = new StringBuilder();
		MyStringBuilder myBuild = new MyStringBuilder();
		
		/***********/
		//Time the String
		long startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++)
			str = str.concat("a");
		long endTime = System.nanoTime();
		
		System.out.println("Total time for appending to String: " + (endTime - startTime) );
		double aveApp = (endTime - startTime) / numChar;
		System.out.println("Average time for appending to String: " + aveApp);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++)
			str = str.substring(1); //Essentially deletes first character for n iterations
		endTime = System.nanoTime();
		
		System.out.println("Total time for deleting(0,1) from String: " + (endTime - startTime));
		double aveDel = (endTime - startTime) / numChar;
		System.out.println("Average time for deleting from String: " + aveDel);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++){
			int middle = str.length() / 2;
			str = str.substring(0, middle) + "a" + str.substring(middle); //Insert 'a' into middle of String
		}
		endTime = System.nanoTime();
		
		System.out.println("Total time for inserting into middle of String: " + (endTime - startTime));
		double aveTime = (endTime - startTime) / numChar;
		System.out.println("Average time for inserting to String: " + aveTime);
		
		// The Extra Credit portion test. Tests time for indexOf operations
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++){
			str.indexOf("b"); //Test to see how fast String class can perform indexOf of a character that's not there
			str = str.substring(1); //Deletes first character
		}
		endTime = System.nanoTime();
		
		System.out.println("Total time for performing indexOf for String: " + (endTime - startTime));
		double aveIndex = (endTime - startTime) / numChar;
		System.out.println("Average time for indexOf for String: " + aveIndex);
		System.out.println();
		
		/*************/
		//Time the StringBuilder
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++)
			build = build.append('a');
		endTime = System.nanoTime();
		
		System.out.println("Total time for appending to StringBuilder: " + (endTime - startTime) );
		aveApp = (endTime - startTime) / numChar;
		System.out.println("Average time for appending to StringBuilder: " + aveApp);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++)
			build = build.delete(0, 1); //Deletes first character for n iterations
		endTime = System.nanoTime();
		
		System.out.println("Total time for deleting(0,1) from StringBuilder: " + (endTime - startTime));
		aveDel = (endTime - startTime) / numChar;
		System.out.println("Average time for deleting from StringBuilder: " + aveDel);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++){
			int middle = build.length() / 2;
			build = build.insert(middle, 'a');
		}
		endTime = System.nanoTime();
		
		System.out.println("Total time for inserting into middle of StringBuilder: " + (endTime - startTime));
		aveTime = (endTime - startTime) / numChar;
		System.out.println("Average time for inserting to StringBuilder: " + aveTime);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++){ //len will stay the same since same exact characters used 
			build.indexOf("b"); //Test to see how fast String class can perform indexOf
			build = build.delete(0, 1); //Deletes first character
		}
		endTime = System.nanoTime();
		
		System.out.println("Total time for performing indexOf for StringBuilder: " + (endTime - startTime));
		aveIndex = (endTime - startTime) / numChar;
		System.out.println("Average time for indexOf for StringBuilder: " + aveIndex);
		System.out.println();
		
		/*************/
		//Time the MyStringBuilder
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++)
			myBuild = myBuild.append('a');
		endTime = System.nanoTime();
		
		System.out.println("Total time for appending to MyStringBuilder: " + (endTime - startTime) );
		aveApp = (endTime - startTime) / numChar;
		System.out.println("Average time for appending to MyStringBuilder: " + aveApp);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++)
			myBuild = myBuild.delete(0, 1); //Deletes first character for n iterations
		endTime = System.nanoTime();
		
		System.out.println("Total time for deleting(0,1) from MyStringBuilder: " + (endTime - startTime));
		aveDel = (endTime - startTime) / numChar;
		System.out.println("Average time for deleting from MyStringBuilder: " + aveDel);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++){
			int middle = myBuild.length() / 2;
			myBuild = myBuild.insert(middle, 'a');
		}
		endTime = System.nanoTime();
		
		System.out.println("Total time for inserting into middle of MyStringBuilder: " + (endTime - startTime));
		aveTime = (endTime - startTime) / numChar;
		System.out.println("Average time for inserting to MyStringBuilder: " + aveTime);
		
		startTime = System.nanoTime();
		for(int i = 0; i < numChar; i++){ //len will stay the same since same exact characters used 
			myBuild.indexOf("b"); //Test to see how fast String class can perform indexOf
			myBuild = myBuild.delete(0, 1); //Deletes first character
		}
		endTime = System.nanoTime();
		
		System.out.println("Total time for performing indexOf for StringBuilder: " + (endTime - startTime));
		aveIndex = (endTime - startTime) / numChar;
		System.out.println("Average time for indexOf for StringBuilder: " + aveIndex);
	}
	
	

}
