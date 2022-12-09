
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
        if (age > MATURITY) {
            this.splitBorg();
            return this;
        }       
        this.cellAssimilation();
        return this;
    }
	public boolean willBeAliveInNextGeneration() {
		return false;
	}
	
}