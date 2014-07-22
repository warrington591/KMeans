
//Warrington Bloomfield 
//Machine Learning Project
// K Means Algorithm

public class CentroPoints {

	 public double centro_x = 0.0;
     public double cenro_y = 0.0;
     
     public CentroPoints(){
         return;}
     
     public CentroPoints(double x, double y){
         this.centro_x = x;
         this.cenro_y = y;
         return;}
     
     public void centroX(double x){
         this.centro_x = x;
         return;}
     
     public double centroX(){
         return this.centro_x;}
     
     public void centroY(double y){
         this.cenro_y = y;
         return; }
     
     public double centroY(){
         return this.cenro_y;}
	
	
}
