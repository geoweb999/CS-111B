
public class ConwayWorld {
	
	static final int ROWS = 15;
	static final int COLS = 20;
	
	private AbstractCell[][] grid = new AbstractCell[ROWS][COLS]; 
	
	public ConwayWorld() {
		
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				grid[r][c] = new ConwayCell(r, c, this);
			}
		}
	}

    
	// Create a string that displays the grid
	public String displayString() {
    String displayString = "";
    
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				displayString += " " + grid[r][c].displayCharacter();		
			}
			
			displayString += "\n";
		}

    return displayString;
	}
	
	// Replace a single cell
	public void replaceCell(AbstractCell cell) {
		grid[cell.getRow()][cell.getColumn()] = cell;
	}
	
	// Create the next generation
	public void advanceToNextGeneration() {
		AbstractCell[][] nextGrid = new AbstractCell[ROWS][COLS]; 

        // check to see if any Borg assimilated cells
		// this has to be done before building the next generation grid
		// because assimilation will cause preceeding rows/columns to be converted
		// and the process of getting next gen cells will thus ignore those conversions
        for (int r = 0; r < ROWS; r++) {
        	for (int c = 0; c < COLS; c++) {
                if (grid[r][c].isBorg() == true && !grid[r][c].getIsAlive()) {
					int age = grid[r][c].getAge();
                    MatureBorgCell b = new MatureBorgCell(r,c, grid[r][c].world);
					b.setAge(age);
		            b.cellAssimilation();
                }
            }
		}
        
		// Build the grid for the next generation
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				nextGrid[r][c] = grid[r][c].cellForNextGeneration();
			}
		}

		
		// Out with the old, in with the new
		grid = nextGrid;
	}
	
	// Returns true if (r, c) is a valid coordinate, and the cell is alive
	public boolean isAlive(int r, int c) {
		return this.isValid(r, c) && grid[r][c].getIsAlive();
	}
    // Returns true if (r,c) is valid and the cell contains a borg
    public boolean isBorg(int r, int c) {
		return this.isValid(r, c) && grid[r][c].isBorg();
	}
    
    // check if a cell is valid
    public boolean isValid(int r, int c) {
        return  r >= 0 && c >= 0 && r < ROWS && c < COLS;
    }
}