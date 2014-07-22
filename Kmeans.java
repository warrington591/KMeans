
//Warrington Bloomfield 
//Machine Learning Project
// K Means Algorithm

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Random;


public class Kmeans{
	
	public static double data [][]= new double[200][2];
	public static final int k = 3; // K is the number of clusters we are looking to get
	public static double total_distance=0;
	public static ArrayList<CentroPoints> cent = new ArrayList<CentroPoints>(); // This stores the randomized centroids 
	public static ArrayList<DataPoints> dataset = new ArrayList<DataPoints>();	// This stores the datapoints
	public static int repeat =1;
	public Scanner x;
	
	public void openFile(){
		try{
			x= new Scanner(new File("HW1data.txt"));
		}catch(Exception e){
			System.out.println("Could not find file");
		}
		
	}
	
	public void readFile (){
		
		int linecount = 0;
		while(x.hasNext()){
			String a =x.next();
			String b=x.next();
			
			
			
			data[linecount][0] = (double) Double.parseDouble(a);
            data[linecount][1] = (double) Double.parseDouble(b);
			
            linecount++;
		}
	}
	
	public void closeFile(){
		x.close();
	}
	
	
    public void RandomizeCentroid()
    {
        System.out.println("Starting randomized centroids are");
        
        int one, two, three;
        
        one = new Random().nextInt(200);
        two = new Random().nextInt(200);
        three = new Random().nextInt(200);
        
        cent.add(new CentroPoints(data[one][0], data[one][1])); // Random 1
        cent.add(new CentroPoints(data[two][0], data[two][1])); // Random 2
        cent.add(new CentroPoints(data[three][0], data[three][1])); // Random 3
        System.out.println("     (" + cent.get(0).centroX() + ", " + cent.get(0).centroY() + ")");
        System.out.println("     (" + cent.get(1).centroX() + ", " + cent.get(1).centroY() + ")");
        System.out.println("     (" + cent.get(2).centroX() + ", " + cent.get(2).centroY() + ")");
        System.out.print("");
        return;
    }
    
    
    //This is to manually hardcode the centroids
    public void manualCentroid(){
    	
    	cent.clear();
    	cent.add(new CentroPoints(28.4938, 51.5998)); // Highest y
        cent.add(new CentroPoints(37.0062, 36.1636)); // Mid y
        cent.add(new CentroPoints(31.5179, 15.5518)); // Lowest y 
        System.out.println("     (" + cent.get(0).centroX() + ", " + cent.get(0).centroY() + ")");
        System.out.println("     (" + cent.get(1).centroX() + ", " + cent.get(1).centroY() + ")");
        System.out.println("     (" + cent.get(2).centroX() + ", " + cent.get(2).centroY() + ")");
        System.out.print("");
        return;
    	
    }
    
    //To calculate the Euclidean Distance
    private static double EuclideanDistance(DataPoints p, CentroPoints cp){
    	double value;
    	value= Math.sqrt(Math.pow((cp.centroY() - p.yValue()), 2) + Math.pow((cp.centroX() - p.xValue()), 2));
        return value;
    }
    
    //To calculate the Euclidean Distance given two data points
    private static double EuclideanDistance2(DataPoints a, DataPoints b){
    	double value;
    	value= Math.sqrt(Math.pow((b.yValue() - a.yValue()), 2) + Math.pow((b.xValue() - a.xValue()), 2));
        return value;
    }
    
    public static double InterCluster(){
    	return total_distance;
    }
    
    public static double Extracluster(){
    	
    	double ev=0.0;
    	double d=0;
    	
    	for(int i = 0 ; i < 200; i++)
        {
            //All data points in a specific centroid
            for(int j = 1; j < 200; j++)
            {
                if(dataset.get(i).theCluster() != dataset.get(j).theCluster()){
                	//Summation d(xi,xj) if centroids are different
                     d+= EuclideanDistance2(dataset.get(i), dataset.get(j));
                }
            } 
    	
        }
    	
    	ev = d*0.005;
    	return ev;
    }
    
    private static void Clustering()
    {	boolean continued = true;
    	DataPoints new_data = null;
    	int cluster_amount = 0;	
        double maxNumber = 100000000;    // a huge number
        double min_number = maxNumber;                   // The minimum value  
        double distance = 0.0;                        // distance value initialized
        int dataCount = 0;							// data sample initialized
        		
 
        // Add in new data and recalculates the centroids after each addition 
        while(dataset.size() < 200)
        {
            new_data = new DataPoints(data[dataCount][0], data[dataCount][1]);
            dataset.add(new_data); // adds new data points to the array list "dataset"
            min_number = maxNumber;
            
            for(int i = 0; i < k; i++)
            {
                distance = EuclideanDistance(new_data, cent.get(i));
                total_distance+= distance;
                if(distance < min_number){
                	min_number = distance;
                	cluster_amount = i;
                }
            }
            new_data.theCluster(cluster_amount);
            
            // new centroid based on mean 
            for(int i = 0; i < k; i++)
            {
                int xTotal = 0;
                int yTotal = 0;
                int cTotal = 0;
                for(int j = 0; j < dataset.size(); j++)
                {
                    if(dataset.get(j).theCluster() == i){
                    	xTotal += dataset.get(j).xValue();
                    	yTotal += dataset.get(j).yValue();
                    	cTotal++;
                    }
                }
                if(cTotal > 0){
                    cent.get(i).centroX(xTotal/cTotal);
                    cent.get(i).centroY(yTotal/cTotal);
                }
            }
            
            dataCount=dataCount+1;
        }
        
        // centroid calculations continues until convergence.
        while(continued){
            for(int i = 0; i < k; i++)
            {
                int xTotal = 0;
                int yTotal = 0;
                int cTotal = 0;
                for(int j = 0; j < dataset.size(); j++)
                {
                    if(dataset.get(j).theCluster() == i){
                    	xTotal += dataset.get(j).xValue();
                    	yTotal += dataset.get(j).yValue();
                        cTotal++;
                    }
                }
                if(cTotal > 0){
                    cent.get(i).centroX(xTotal / cTotal);
                    cent.get(i).centroY(yTotal / cTotal);
                }
            }
            
            
            continued = false;
            //all data added to new centroid
            for(int i = 0; i < dataset.size(); i++)
            {
            	DataPoints temp = dataset.get(i);
            	min_number = maxNumber;
                for(int a = 0; a < k; a++)
                {
                    distance = EuclideanDistance(temp, cent.get(a));
                    if(distance < min_number){
                    	min_number = distance;
                    	cluster_amount = a;
                    }
                }
                temp.theCluster(cluster_amount);
                if(temp.theCluster() != cluster_amount){
                	temp.theCluster(cluster_amount);
                    continued = true;
                }
            }
        }
        return;
    }
    
	
	public static void main(String[] args){
	
		Kmeans kmeans = new Kmeans();
		Kmeans manual = new Kmeans();
		kmeans.openFile();
		kmeans.readFile();
		kmeans.closeFile();
		
		double iv ;
		double ev; 
		
		
		System.out.println("k is set to 3:");
		System.out.println();
		
		
		// To repeat the algorithm 5 times
		while(repeat <=5){
		
		kmeans.RandomizeCentroid();
		
		System.out.println();
		
		System.out.println("Now testing algorithm on the centroids until convergence...");
		kmeans.Clustering();
		iv =kmeans.InterCluster();
		ev = kmeans.Extracluster();
        // Print out clustering results.
        for(int i = 0 ; i < k; i++)
        {
            System.out.println("Cluster " + i + ":");
            for(int j = 0; j < 200; j++)
            {
                if(dataset.get(j).theCluster() == i){
                    System.out.println("     (" + dataset.get(j).xValue() + ", " + dataset.get(j).yValue() + ")");
                }
            } 
            System.out.println();
        } 
	
        System.out.println("The Inter Cluster Variability is: "+ iv);
        System.out.println("The Extra Cluser Variability is: "+ ev);
        System.out.println();
        
        System.out.println("K-means algorithm:" + repeat +" trial complete");
        repeat++;
		}
		
		
		//This is to perform the k-means algorithm using manually picked centroids
		
		System.out.println();
		System.out.println("Centroid have been now chosen manually....");
		manual.manualCentroid();
		System.out.println("Now testing algorithm on the centroids until convergence...");
		manual.Clustering();
		iv =manual.InterCluster();
		ev = manual.Extracluster();
        // Print out clustering results.
        for(int i = 0 ; i < k; i++)
        {
            System.out.println("Cluster " + i + ":");
            for(int j = 0; j < 200; j++)
            {
                if(dataset.get(j).theCluster() == i){
                    System.out.println("     (" + dataset.get(j).xValue() + ", " + dataset.get(j).yValue() + ")");
                }
            } 
            System.out.println();
        } 
	
        System.out.println("The Inter Cluster Variability is: "+ iv);
        System.out.println("The Extra Cluser Variability is: "+ ev);
        System.out.println();
        
    
		}
		
		
		
	}
	

