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

public abstract class AbstractBorgCell extends AbstractCell {

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
        int min = -1;
        int max = 2;
        Random random = new Random();
        int count = 0;
        boolean split = false;
        while (count < 10 && !split) {
            row = this.getRow() + random.nextInt(max - min) + min;
	        col = this.getColumn() + random.nextInt(max - min) + min;
            if (this.world.isValid(row, col) && !this.world.isBorg(row, col)) {
                AbstractCell n = new BabyBorgCell(row, col, this.world);
                n.setAge(0);
                this.world.replaceCell(n);
                split = true;
            }
            count++;
        }
    }

    public abstract void cellAssimilation();
    public abstract AbstractCell cellForNextGeneration();
    public abstract boolean willBeAliveInNextGeneration();
}