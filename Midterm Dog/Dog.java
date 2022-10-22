public class Dog {
    private int idNumber;    // dog's id at kennel
    private String breed;    // the breed of the dog
    private double age;      // the age of the dog in years

// constructor        
    public Dog(int id, String breedName, double dogAge) {
        this.idNumber = id;
        this.breed = breedName;
        this.age = dogAge;
    }

// getters
    public int getIdNumber() {
		return idNumber;
	}

    public String getBreed() {
		return breed;
	}

	public double getAge() {
		return age;
	}

// public methods
    public boolean isEqual(Dog inDog) {             // return True if both DogID's are equal
        return (this.idNumber == inDog.idNumber);
    }
        
    public boolean sameBreed(Dog inDog) {           // return True if both Breeds are euqal 
        return (this.breed == inDog.breed);
    }
        
    public boolean compatibleAges(Dog inDog) {      // return True is ages are < 1 year apart
        // check which dog is older before performing substraction!!
        if (inDog.getAge() > this.getAge()) {
            return (inDog.getAge() - this.getAge() < 1.0);
        } else {
            return (this.getAge() - inDog.getAge() < 1.0);
        }
    }
    public void printDog() {
        System.out.println(this.idNumber+": "+this.breed+" aged "+this.age+" years.");
    }
}
// end of class Dog
        