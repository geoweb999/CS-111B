/* 
Class: Circle (Geoffrey Weber CS-111B October 2022)
A class that describes a cicle in cartesian space (2 dimensions)

double x: the x-axis coordinate of the center of the circle
double y: the y-axis coordinate of the center of the circle
double radius: the length of the radius of the cicle

Methods (all public):
  setX: sets the value of x, no pre-conditions
  setY: sets the value of y, no pre-conditions
  setRadius: sets the value of radius, no pre-conditions
  getX: returns double, the value of x
  getY: returns double, the value of y
  getRadius: returns double the value of radius
  getArea: returns double, the area of the circle using pi * radius * radius
  doesOverlap(Circle c2): returns boolean if circle and c2 overlap
  isEqualTo(Circle c2): returns boolean if circle and c2 are the same area
*/






public class Circle {                        
  private double x;
  private double y;
  private double radius;





  
  public void setX (double val) {
    // sets the x-axis to value val, any valid double 
    x = val;
  }
  
  
  
  
  
  
  public double getX() {
    // returns the x-axis coordinate (double) 
    return x;
  }
  
  
  
  
  
  
  public void setY (double val) {
    // sets the y-axis coordinate to val, any valid double
    y = val;
  }
  
  
  
  
  
  
  public double getY() {
    // returns the y-axis coordinate (double)
    return y;
  }
  
  
  
  
  
  
  public void setRadius(double val) {
    // sets the radiius to val, any valid double
    radius = val;
  } 
  
  
  
  
  
  
  public double getRadius() {
    // returns the radius (double)
    return radius;
  }
  
  
  
  
  
  
  public double getArea() {
    // calculate the area of circle as PI * radius * radius, returns double
    return Math.PI * radius * radius;
  }
  
  
  
  
  
  
  public boolean doesOverlap(Circle c2) {
    // calculates if c2 overlaps Circle by calculating distance between two
    // centers of circle and c2
    // the circle overlap if the sum of the two radii is >= distance between centers
    double dist;
    dist = Math.sqrt( ((c2.x - x) * (c2.x - x)) + ((c2.y - y) * (c2.y - y)) );
    return (radius + c2.radius >= dist);
  }
  
  
  
  
  
  
  public boolean isEqualTo(Circle c2) {
    // returns true if two cicles are same area
    return (this.getArea() == c2.getArea());
  }
}
