class Main {
    public static void main(String[] args) {
         
        Episode e = new Episode("Star Trek", 1, 1);
        Episode f = new Episode("Star Trek", 2, 2);
        Episode g = new Episode("Star Trek", 3, 3);
        Episode h = new Episode("Columbo");
        if (e.equals(f)) {
             System.out.println("same!");
        } else {
            System.out.println("not the same!");
        }        
        if (e.comesBefore(f)) {
            System.out.println("yes!");
        } else {
            System.out.println("no!");
        }
    }
}