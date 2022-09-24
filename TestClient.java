package conway;



public class TestClient {

	public static void main(String[] args) {
		//Grid grid = new Grid(7, 5);
		Grid grid = new Grid(8, 30);
		
		//grid.randomizeCellValues();
		
		/*
		grid.getCell(0, 0).setAlive();
		grid.getCell(3, 3).setAlive();
		grid.getCell(3, 4).setAlive();
		grid.getCell(4, 4).setAlive();
		grid.getCell(3, 2).setAlive();
		
		grid.printGrid();
		
		System.out.println();
		
		System.out.println(grid.getCell(3, 3).getStringValue());
		
		for(Cell cell: grid.getNeighbors(3, 3)) {
			System.out.print(cell.getStringValue());
		}
		
		System.out.println();
		
		System.out.println(grid.getCell(2, 4).getStringValue());
		for(Cell cell: grid.getNeighbors(2, 4)) {
			System.out.print(cell.getStringValue());
		}
		
		*/
		
		//PATTERN: COPPERHEAD
		
		grid.getCell(3, 0).setAlive();
		grid.getCell(4, 0).setAlive();
		grid.getCell(3, 1).setAlive();
		grid.getCell(4, 1).setAlive();
		
		grid.getCell(2, 3).setAlive();
		grid.getCell(3, 3).setAlive();
		grid.getCell(4, 3).setAlive();
		grid.getCell(5, 3).setAlive();
		
		grid.getCell(1, 4).setAlive();
		grid.getCell(2, 4).setAlive();
		grid.getCell(5, 4).setAlive();
		grid.getCell(6, 4).setAlive();
		
		grid.getCell(0, 5).setAlive();
		grid.getCell(7, 5).setAlive();
		
		grid.getCell(0, 7).setAlive();
		grid.getCell(7, 7).setAlive();
		
		grid.getCell(0, 8).setAlive();
		grid.getCell(2, 8).setAlive();
		grid.getCell(5, 8).setAlive();
		grid.getCell(7, 8).setAlive();
		
		grid.getCell(3, 9).setAlive();
		grid.getCell(4, 9).setAlive();
		grid.getCell(3, 10).setAlive();
		grid.getCell(4, 10).setAlive();
		
		grid.getCell(1, 11).setAlive();
		grid.getCell(2, 11).setAlive();
		grid.getCell(5, 11).setAlive();
		grid.getCell(6, 11).setAlive();
		
		
//		while(true) {
//			grid.printGrid();
//			System.out.println();
//			System.out.println();
//			System.out.println();
//			
//			grid.nextIteration();
//			
//			try {
//				Thread.sleep(250);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} 
		
		grid.printGrid();
		//grid.serialize();
		grid.randomizeCellValues();
		
		grid.printGrid();
		
	//	grid.deserialize();
		
		grid.printGrid();
		
		
		
		
		
	}
}
