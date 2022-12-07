
public class BabyBorgCell extends AbstractBorgCell {
	
	public BabyBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}
	
	public  AbstractCell cellForNextGeneration() {
		// check to see if baby has matured and return mature borg if so
        this.setAge(this.getAge() + 1);
        if (this.getAge() > MATURITY) {
            AbstractCell next = new MatureBorgCell(getRow(), getColumn(), world);
            next.setAge(this.getAge());
            return next;
        } else {
            return this;
        }
	}	
    public void cellAssimilation() {
		// baby borgs cannot assimilate
    }
		
	public boolean willBeAliveInNextGeneration() {
		return false;
	}
	
	public char displayCharacter() {
		return getIsAlive() ? '◆' : '◆';
	}
}