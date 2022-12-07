
import java.util.Random;

public class MatureBorgCell extends AbstractBorgCell {

    public MatureBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}

	public  AbstractCell cellForNextGeneration() {
        this.setAge(this.getAge() + 1);
        int age = this.getAge();
        // check for death by age and return a zombie borg
        if (age > DEATH) {  
            AbstractCell n = new ZombieBorgCell(this.getRow(), this.getColumn(), this.world);
            n.setAge(age);
            n.setIsAlive(true);
            n.setBorg(true);
            return n;
        }
        AbstractCell next = new MatureBorgCell(getRow(), getColumn(), world);
        next.setAge(age);
		return next;
	}	

    public void cellAssimilation() {
        // check neighboring cells for assimilation opportunities and randomly spawn a baby borg
        Random rnd = new Random();
        int age = this.getAge();
        
        // check for giving birth
        if (age > PARENT) {
            int x = rnd.nextInt(21);
            if (x % 20 == 0) {
                this.splitBorg();
            }            
        }
        // mature borgs convert adjacent cells to baby borgs
        if (age > MATURITY && !this.getIsAlive()) {
            int x = this.getRow();
            int y = this.getColumn();
            // check each surrounding grid positions for alive cells
            // (isAlive validates grid coordinates are valid)
            // if alive, then convert to new baby borg
            // don't assimiulate borgs
            for (int i = -1; i < 2; ++i) {
                for (int j = -1; j < 2; ++j) {
                    int r = x + i;
                    int c = y + j;
                    // check all cells but current cell
                    if (!((r == x) && (c == y))) {
                        if (this.world.isAlive(r, c) && !this.world.isBorg(r, c)) {
                            // assimulate the cell
                            AbstractCell b = new BabyBorgCell(r, c, this.world);
                            b.setIsAlive(false);
                            this.world.replaceCell(b);
                        }
                    }
                }
            }
        }        
    }    
	
	public boolean willBeAliveInNextGeneration() {
		return false;
	}
	
}