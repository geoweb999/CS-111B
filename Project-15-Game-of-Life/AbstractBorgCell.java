// implementation of a borg cell
// borgs have a life cycle defined by the constants below:
//     baby -- mature -- parent -- zombie -- recycled Conway cell
// Borgs are not alive for the purposes of assessing neighbors for Conway cells
// Borgs assimilate all neighboring cells that are alive after they have lived MATURITY years
// Borgs begin life as a baby borg which cannot assimlate until they mature
// After MATURITY years, a baby borg becomes a mature borg
// After PARENT years, a mature borg has a 1 in 20 chance of birthing a baby borg into an adjacent ecll
// After DEATH years, a mature borg turns into a zombie (and is now alive dor conway cells)
// After DECOMPOSE years, a zombie borg becomes a live Conway cell that cannot be assimilated for 10 years
// 
import java.util.Random;

public class AbstractBorgCell extends AbstractCell {

    static final int MATURITY = 5;
    static final int PARENT = MATURITY + 5;
    static final int DEATH = PARENT + 20;
    static final int DECOMPOSE = DEATH + 10;
    
	public AbstractBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
        this.setAge(0);
        this.setIsAlive(false);
        this.setBorg(true);
	}

	public char displayCharacter() {
		return getIsAlive() ? '■' : '■';
	}
    
    public void splitBorg() {
        int row, col;
        Random random = new Random();
        int count = 0;
        boolean split = false;
        while (count < 10 && !split) {
            row = this.getRow() + random.nextInt(3) - 1;
	        col = this.getColumn() + random.nextInt(3) - 1;
            if (this.world.isValid(row, col) && !this.world.isBorg(row, col)) {
                AbstractCell n = new BabyBorgCell(row, col, this.world);
                n.setAge(0);
                this.world.replaceCell(n);
                split = true;
            }
            count++;
        }
    }
    public void cellMove() {
		// baby borgs cannot assimilate but they can move
        int x = this.getRow();
        int y = this.getColumn();
        int count = 0;
        int r, c;
        int age = this.getAge();
        Random random = new Random();
        boolean keepTrying = true;
        // randomly look for a neighboring cell, give up after 10 tries as cell may be blocked
        while (keepTrying && count < 10) {
            count++;
            r = x + random.nextInt(3) - 1;
            c = y + random.nextInt(3) - 1;
            if (this.world.isAlive(r, c) && !this.world.isBorg(r, c)) {
                keepTrying = false;
                // move the cell
                AbstractCell b = new BabyBorgCell(r, c, this.world);
                b.setAge(age);
                this.world.replaceCell(b);
                AbstractCell cell = new ConwayCell(x, y, this.world);
                this.world.replaceCell(cell);
            }
        }
    }

	public  AbstractCell cellForNextGeneration() {
        return this;
	}	

    public void cellAssimilation() {
        // check neighboring cells for assimilation opportunities and randomly spawn a baby borg
        // mature borgs convert adjacent cells to baby borgs
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
                        this.world.replaceCell(b);
                        return;
                    }
                }
            }
        }        
    }  
    public  boolean willBeAliveInNextGeneration() {
        return this.getIsAlive();
    };
}