
public class ZombieBorgCell extends AbstractBorgCell {
	
	public ZombieBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
        this.setIsAlive(true);
	}
	
	public  AbstractCell cellForNextGeneration() {
        // check to see if zombie has decomposed and replace with protected zombie cell (via isBorg)
        this.setAge(this.getAge() + 1);
        if (this.getAge() > DECOMPOSE ) {
            AbstractCell d = new ConwayCell(this.getRow(), this.getColumn(), this.world);
            d.setIsAlive(true);
            d.setBorg(true);
            return d;
        }
        return this;
	}	
		
	public boolean willBeAliveInNextGeneration() {
		return true;
	}

    public void cellAssimilation() {
        // zombies cannot assimilate 
    
    }
        
	public char displayCharacter() {
		return getIsAlive() ? '◘' : '◇';
	}
}