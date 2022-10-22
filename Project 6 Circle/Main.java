public class Main {

  public static void main(String []args)
  {
    Circle c1 = new Circle();
    Circle c2 = new Circle();
    Circle c3 = new Circle();

    c1.setX(0);
    c1.setY(0);
    c1.setRadius(1);

    c2.setX(1);
    c2.setY(1);
    c2.setRadius(0.5);

    c3.setX(10);
    c3.setY(10);
    c3.setRadius(2);

    System.out.println(c2.getRadius());

    // Test getArea
    System.out.println(c1.getArea());
    System.out.println(c2.getArea());
    System.out.println(c3.getArea());
 
    // Test doesOverlap
    if (c1.doesOverlap(c2)) {
      System.out.println("c1 and c2 overlap! (good)");
    } else {
      System.out.println("c1 and c2 don't overlap.(no good)");
    }

    if (c2.doesOverlap(c3)) {
      System.out.println("c2 and c3 overlap! (no good)");
    } else {
      System.out.println("c2 and c3 don't overlap. (good)");
    }

    // Test Equals
    if (c1.isEqualTo(c2)) {
      System.out.println("c1 and c2 are equals! (no good)");
    } else {
      System.out.println("c1 and c2 are not equal. (good)");
    }

    if (c2.isEqualTo(c2)) {
      System.out.println("c2 is equal to itself. (good)");
    } else {
      System.out.println("c2 is not equal to itself. (no good)");
    }
  }
}