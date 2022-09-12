package conway;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	// ******************Fields******************
	// TODO: move isPaused to main program.
	//private boolean isPaused;
	
	private ArrayList<ArrayList<Cell>> columns;
	
	public int width;
	public int height;
	
	// ******************Constructor******************
	/**
	 * Creates a Grid object of provided height and width. The Grid is populated with Cells.
	 * @param height
	 * @param width
	 */
	public Grid(int height, int width) {
		this.width = width;
		this.height = height;
		
		columns = new ArrayList<ArrayList<Cell>>();
		
		for (int i=0; i < height; i++) { // columns
			ArrayList<Cell> row = new ArrayList<Cell>();
			
			for(int k=0; k < width; k++) { // rows
				row.add(new Cell(i, k));
			}
			columns.add(row);
		}
	}
	
	// ******************Class Methods******************
	/**
	 * Loops through each Cell in the grid and randomly sets it to be either dead or alive.
	 */
	public void randomizeCellValues() {
		Random rand = new Random();
		
		for(ArrayList<Cell> i:columns) {
			for(Cell cell: i) {
				
				if(rand.nextInt(2) == 1) {
					cell.setAlive();
				} else cell.setDead();
			}
		}
	}
	
	/**
	 * Returns an ArrayList with all of the Neighbors of the cell on the provided coordinates.
	 * @param row
	 * @param column
	 * @return ArrayList<Cell>
	 */
	public ArrayList<Cell> getNeighbors(int row, int column) {
		// TODO: change method to private after we get rid of TestClient
		ArrayList<Cell> neighborCells = new ArrayList<Cell>();
		
		
		int rowVal = row -1;
		int colVal = column -1;
		
		for (int i = 0; i < 3; i++){
			for (int k = 0; k < 3; k++){
				if (rowVal + i == row && colVal + k == column){
					continue;
				}
				if(isValidCoordinate(rowVal + i, colVal + k)) {
					neighborCells.add(this.getCell(rowVal + i, colVal + k));
				}
			}
		}
		
		return neighborCells;
	}
	
	/**
	 * Returns an ArrayList with all of the Neighbors of the cell provided.
	 * @param row
	 * @param column
	 * @return ArrayList<Cell>
	 */
	public ArrayList<Cell> getNeighbors(Cell cell) {
		// TODO: change method to private after we get rid of TestClient
		ArrayList<Cell> neighborCells = new ArrayList<Cell>();
		int row = cell.getRowCoordinate();
		int column = cell.getColumnCoordinate();
		
		int rowVal = cell.getRowCoordinate() -1;
		int colVal = cell.getColumnCoordinate() -1;
		
		for (int i = 0; i < 3; i++){
			for (int k = 0; k < 3; k++){
				if (rowVal + i == row && colVal + k == column){
					continue;
				}
				if(isValidCoordinate(rowVal + i, colVal + k)) {
					neighborCells.add(this.getCell(rowVal + i, colVal + k));
				}
			}
		}
		
		return neighborCells;
	}
	
	/**
	 * Checks if a cell has a valid coordinate
	 * @param rowVal
	 * @param colVal
	 * @return boolean
	 */
	private boolean isValidCoordinate(int rowVal, int colVal) {
		if( (rowVal >= 0 && rowVal < this.height) &&
			(colVal >= 0 && colVal < this.width) ) {
			return true;
		} else return false;
		
	}
	
	/**
	 * generate next Iterations.
	 */
	public void nextIteration() {
		ArrayList<Cell> toKill = new ArrayList<Cell>();
		ArrayList<Cell> toLive = new ArrayList<Cell>();
		
		for(ArrayList<Cell> rows: this.columns) {
			for(Cell cell: rows) {
				
				int liveNeighbors = 0;
				for(Cell neighbor: this.getNeighbors(cell)) {
					if(neighbor.getIsAlive()) {
						liveNeighbors++;
					}
				}
				
				if(cell.getIsAlive() && (liveNeighbors == 2 || liveNeighbors == 3)) {
					toLive.add(cell);
				}
				else if (!cell.getIsAlive() && liveNeighbors == 3) {
					toLive.add(cell);
				}
				else {
					toKill.add(cell);
				}
			}
		}
		
		for(Cell cell:toLive) {
			cell.setAlive();
		}
		for(Cell cell:toKill) {
			cell.setDead();
		}
	}
	
	/**
	 * saves current Grid to a file
	 */
	public void saveGrid() {
		// TODO: implement
	}
	
	/**
	 * Prints the Grid to the Console.
	 */
	public void printGrid() {
		for(ArrayList<Cell> i:columns) {
			for(Cell cell: i) {
				System.out.print(cell.getStringValue() + " ");
			}
			System.out.println();
		}
	}
	
	
	// ******************Getters and Setters******************
	/**
	 * Returns the cell on the provided Grid coordinate.
	 * @param row
	 * @param column
	 * @return Cell at coordinate (row, column)
	 */
	public Cell getCell(int row, int column) {
		return columns.get(row).get(column);
	}
}