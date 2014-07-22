
//Warrington Bloomfield 
//Machine Learning Project
// K Means Algorithm

public class DataPoints {

	public double local_x = 0;
    public double local_y = 0;
    public int cluster = 0;
    
    public DataPoints(){return;}
    
    public DataPoints(double x, double y){
        this.xValue(x);
        this.yValue(y);
        return;}
    
    public void theCluster(int num){
        this.cluster = num;
        return;}
    
    public int theCluster(){
        return this.cluster;}
    
    public void xValue(double x){
        this.local_x = x;
        return;}
    
    public double xValue(){
        return this.local_x;}
    
    public void yValue(double y){
        this.local_y = y;
        return;}
    
    public double yValue(){
        return this.local_y;}
    
    
	
}
