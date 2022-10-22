import java.util.Scanner;

// NOTE: EXAM ASKS FOR METHOD "ISEQUALTO", WE ASSUME THIS REFERS TO "EQUALS TO" WHICH ONLY CHECKS ID
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter id: ");
        int id         = scnr.nextInt();
        String temp = scnr.nextLine();
        System.out.print("Breed: ");
        String breed   = scnr.nextLine();
        System.out.print("Age (years): ");
        double age     = scnr.nextDouble();

        
        Dog d1 = new Dog(id, breed, age);
        Dog d2 = new Dog(128,"Pug", 2.0);
        d1.printDog();
        d2.printDog();

        if (d1.isEqual(d2)) {
            System.out.println("equal");
        }

        if (d1.sameBreed(d2)) {
            System.out.println("same breed");
        }
        
        if (d1.compatibleAges(d2)) {
            System.out.println("compatible");
        }
       
    }    
}