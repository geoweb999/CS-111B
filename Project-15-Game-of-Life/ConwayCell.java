
public class ConwayCell extends AbstractCell {

	public ConwayCell(int r, int c, ConwayWorld w) {
		super(r, c, w);
	}	

  /**
   * These are the Conway Game of Life Rules
   */
	public boolean willBeAliveInNextGeneration() {
		int nc = neighborCount();
		
		if (getIsAlive()) {
			return nc == 2 || nc == 3;
		} else {
			return nc == 3;
		}
	}
	
	public AbstractCell cellForNextGeneration() {
		ConwayCell next = new ConwayCell(getRow(), getColumn(), world);
		next.setAge(this.getAge() + 1);
		if (this.isBorg() && next.getAge() < 10) {
			next.setBorg(true);
			next.setIsAlive(true);
		} else {
			next.setBorg(false);
			next.setIsAlive(willBeAliveInNextGeneration());
		}
		return next;
	}
	
	public AbstractCell BorgForNextGeneration() {
		return this;
	}
}