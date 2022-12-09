import java.util.Random;

public class MatureBorgCell extends AbstractBorgCell {

    public MatureBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}
    
    public AbstractCell BorgForNextGeneration() {
        int age = getAge() + 1;
        setAge(age);
        if (age > DEATH) {
            AbstractCell n = new ZombieBorgCell(this.getRow(), this.getColumn(), this.world);
            n.setAge(age);
            n.setIsAlive(true);
            n.setBorg(true);
            return n;
        }           
        if (age > PARENT) {
            this.splitBorg(BIRTHRATE);
            return this;
        } else {      
            this.cellAssimilation();
            return this;
        }
    }

    public void splitBorg(int bound) {
        int row, col;
        Random random = new Random();
        int count = 0;
        boolean split = false;
        if (random.nextInt(bound + 1) % bound == 0) {
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

	public boolean willBeAliveInNextGeneration() {
		return false;
	}
	
    public char displayCharacter() {
		return getIsAlive() ? '■' : '■';
	}
}