import java.util.Random;

public class BabyBorgCell extends AbstractBorgCell {
	
	public BabyBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}
	
	public AbstractCell BorgForNextGeneration() {
		// check to see if baby has matured and return mature borg if so
        this.setAge(this.getAge() + 1);
        if (this.getAge() > MATURITY) {
            AbstractCell next = new MatureBorgCell(getRow(), getColumn(), world);
            next.setAge(this.getAge());
            return next;
        } else {
            this.cellMove();
            return this.world.getCell(this.getRow(), this.getColumn(), this.world);
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
            if (this.world.isValid(r, c) && !this.world.isBorg(r, c)) {
                keepTrying = false;
                // move the cell
                AbstractCell b = new BabyBorgCell(r, c, this.world);
                b.setAge(age);
                this.world.replaceCell(b);
                AbstractCell cell = new ConwayCell(x, y, this.world);
				cell.setAge(0);
				cell.setBorg(false);
                this.world.replaceCell(cell);
				cell.setAge(0);
            }
        }
    }
		
	public boolean willBeAliveInNextGeneration() {
		return false;
	}
	
	public char displayCharacter() {
		return getIsAlive() ? '◆' : '◆';
	}

}