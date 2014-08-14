package knn.base.methods;

import java.io.*;
import java.util.*;

public class MainTest {
	public static  int DataLength = 100;
	public static Point pointArray [] = new Point[DataLength];	
	public static Point trainArray [] = new Point[DataLength];
//
//	/**
//	 * @param args
//	 */
	public static void main(String args[]) {  
//		// TODO Auto-generated method stub 
	   String trainFilePath = "C://Users//janet//workspace//knnClassifier//src//knn//base//methods//design-data.txt";
	   String testFilePath = "C://Users//janet//workspace//knnClassifier//src//knn//base//methods//test-data.txt";
	   MainTest test = new MainTest();
	   test.initPoints(trainFilePath,testFilePath);
	   int index = 0, errorTotal = 0,errorC1 = 0,errorC2 = 0 ;

	   while (index<pointArray.length){
	   	  pointArray[index].getKnnClassifier(1,trainArray);// classify by KNN-nearest
	   	  if(pointArray[index].getOrigClass()!=pointArray[index].getKnnClass()){
	   		 System.out.println(index+"th sample : "+pointArray[index].getX1()+"/"+pointArray[index].getY1()+"/"+pointArray[index].getOrigClass()
	   				 +" is misclassified as C"+pointArray[index].getKnnClass());
	   		errorTotal++;
	   		if(pointArray[index].getOrigClass()==1)
	   			errorC1++;
	   		if(pointArray[index].getOrigClass()==2)
	   			errorC2++;
	   	  }
	   	  
         index++;
	   }
	   System.out.println("\n"+"The error number of total misclassified samples is "+errorTotal+"\n"
			   				+"The error number of misclassified samples in C1 is "+ errorC1+"\n"
			   				+"The error number of misclassified samples in C2 is "+ errorC2);
	}
	public  void initPoints(String trainFilePath, String testFilePath){
	    //get data from txt file
		try {  
        FileInputStream fstream = new FileInputStream(testFilePath);  
        // Get the object of DataInputStream  
        DataInputStream in = new DataInputStream(fstream);  
        BufferedReader br = new BufferedReader(new InputStreamReader(in));  
        String strLine;  
        int number = 0;
        //Read File Line By Line  
        while ((strLine = br.readLine()) != null) {  
            Scanner s = new Scanner(strLine);
            Point temp =Point.getInstance();//pointArray[number++]; 
            int count=1;
            while (s.hasNext()) { // Read line word by word
                String sample = s.next(); 
                if (count==1)
                	temp.setX1(Double.valueOf(sample)) ;
                else
                if (count==2)
                	temp.setY1(Double.valueOf(sample));
                else
                if(count==3)
                	temp.setOrigClass(Integer.valueOf(sample));
                count++; 
            }
            pointArray[number] = temp ;
            number++;
        }  
        //Close the input stream
        fstream.close();
        in.close();  

    } catch (Exception e) {//Catch exception if any  
        System.err.println("Error: " + e.getMessage());  
    } 		
		
		try {  
	        FileInputStream fstream = new FileInputStream(trainFilePath);
	        // Get the object of DataInputStream  
	        DataInputStream in = new DataInputStream(fstream);  
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));  
	        String strLine;  
	        int number = 0;
	        //Read File Line By Line  
	        while ((strLine = br.readLine()) != null) {  
	            Scanner s = new Scanner(strLine);
	            Point temp =Point.getInstance();//trainArray[number++]; 
	            int count=1;
	            while (s.hasNext()) { // Read line word by word
	                String sample = s.next(); 
	                if (count==1)
	                	temp.setX1(Double.valueOf(sample)) ;
	                else
	                if (count==2)
	                	temp.setY1(Double.valueOf(sample));
	                else
	                if(count==3)
	                	temp.setOrigClass(Integer.valueOf(sample));
	                count++; 
	            }
	            trainArray[number] = temp ;
	            number++;
	        }  
	        //Close the input stream  
	        in.close();  

	    } catch (Exception e) {//Catch exception if any  
	        System.err.println("Error: " + e.getMessage());  
	    } 	
	}
}
