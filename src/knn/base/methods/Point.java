package knn.base.methods;

public class Point {
		
	private  double x1 ;	
	
	private  double y1 ;
	
	private int origClass ;
	
	private int knnClass ;
	
	private boolean isMin = false; //marked as true if it has been chosen as one of k nearest point
	
	public double getX1(){
		return x1;
	}
	public double getY1(){
		return y1;
	}
	public int getOrigClass(){
		return origClass;
	}
	public int getKnnClass(){
		return knnClass;
	}
	public boolean getIsMin(){
		return isMin;
	}
	public void setX1(double x1){
		this.x1 = x1;
	}
	public void setY1(double y1){
		this.y1 = y1;
	}
	public void setOrigClass(int origClass){
		this.origClass = origClass;
	}
	public void setKnnClass(int knnClass){
		this.knnClass = knnClass;
	}	
	public void setIsMin(boolean ismin){
		this.isMin = ismin;
	}

	public static Point getInstance(){
		return new Point();
	}
	private double getDistance(double x2, double y2){
		double distance = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
		return distance;
	}
	/**
	 * return the k nearest points
	 * @param Knearest
	 * @param tempArray  as training data
	 * @return the k nearest points classified pattern
	 */
	private int[] getKnnPoints(int Knearest,Point tempArray []) {
		//Point tempArray [] = new Point[DataLength];
		//tempArray = pointArray;
		int knearestArray [] = new int[Knearest];
		int knearestClassArray [] = new int[Knearest];
		int index = 0,minIndex = 0, minOrgClass = 1, temporigClass;
		double minDis = 100, tempDis = 0; 
		//System.out.println("The current point : "+this.getX1()+"/"+this.getY1()+"/"+this.getOrigClass());
		for (int i=0; i<Knearest;i++){  //Choose the k nearest point one by one
			while (index<tempArray.length){ //Every time choose the minimum distance point
				tempDis = this.getDistance(tempArray[index].getX1(), tempArray[index].getY1());
				temporigClass = tempArray[index].getOrigClass();
				if(tempDis < minDis & tempDis !=0 & tempArray[index].getIsMin()!=true){//except self, chosen one of k point
					minDis = tempDis;
					minIndex = index;
					minOrgClass = temporigClass;
				}
				index++;
			}
			index=0; //reset
			tempArray[minIndex].setIsMin(true); //marked as true if it has been chosen as one of k nearest point, then it would not be compared again in the while cycle 
			knearestArray [i] = minIndex;//remember this point index
			knearestClassArray[i] = minOrgClass; //remember original class
			//System.out.println("For "+i+"th nearest points"+tempArray[minIndex].getX1()+"/"+tempArray[minIndex].getY1()+"/"+tempArray[minIndex].getOrigClass()+"  minIndex "+minIndex+" / Mindistance "+minDis+ " / minOrgClass "+minOrgClass);
			minDis = 100; //reset
		}
	    index=0;	
		while (index<tempArray.length){
				tempArray[index].setIsMin(false); //reset all isMin 
				index++;
		}
		
		return knearestClassArray;
	}
	/**
	 * classify base on the majority pattern
	 * @param Knearest
	 * @param trainArray as training data
	 * @return  knn classified pattern
	 */
	public int getKnnClassifier(int kNearest,Point trainArray [])
	{
		int kClassArray [] = getKnnPoints(kNearest,trainArray);
		int C1=0, C2=0, knnClassifer;
		if(kClassArray!=null){
			for (int j=0;j<kClassArray.length;j++){
				if(kClassArray[j]==1)
					C1++;
				if(kClassArray[j]==2)
					C2++;
			}
		}

		knnClassifer = C1>C2?1:2;
		//	System.out.println(this.getX1()+"/"+this.getY1()+"/"+this.getOrigClass()+" is classified as C"+knnClassifer);
		setKnnClass(knnClassifer);
		return knnClassifer;
	}
}
