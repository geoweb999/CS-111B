
public class ZombieBorgCell extends AbstractBorgCell {
	
	public ZombieBorgCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
        this.setIsAlive(true);
	}
	
    public AbstractCell BorgForNextGeneration() {
        int age = getAge() + 1;
        setAge(age);
        if (age > DECOMPOSE) {
            AbstractCell n = new ConwayCell(this.getRow(), this.getColumn(), this.world);
            n.setAge(0);
            n.setIsAlive(true);
            n.setBorg(true);
            return n;
        }           
        return this;
    } 
		
	public boolean willBeAliveInNextGeneration() {
		return true;
	}
        
	public char displayCharacter() {
		return getIsAlive() ? '◘' : '◇';
	}
}