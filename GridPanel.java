package conway;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridPanel extends JPanel{
	public JPanel contentPane;
	public JPanel controlPanel;
	public JPanel gridPanel;
	
	private AtomicBoolean isIterating = new AtomicBoolean(false);
	
	public Grid grid = new Grid(10, 30);
	public ArrayList<ArrayList<JButton>> buttonColumns;
	
	private Thread loop;
	

	public GridPanel() {
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
		
		buttonColumns = new ArrayList<ArrayList<JButton>>();
		setLayout(new GridLayout(grid.height, grid.width));
		for(ArrayList<Cell> rows: grid.columns) {
			ArrayList<JButton> buttonRows = new ArrayList<JButton>();
			for(Cell cell: rows) {
				JButton button = new JButton();
				if (cell.getIsAlive()) {
					button.setBackground(Color.black);
				} 
				else {
					button.setBackground(Color.white);
				}
				
				button.setBorder(new LineBorder(Color.BLACK));
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (cell.getIsAlive()) {
							cell.setDead();
							button.setBackground(Color.white);
						} 
						else {
							cell.setAlive();
							button.setBackground(Color.black);
						}
					}
				});
				button.setFocusPainted(false);
				button.setContentAreaFilled(false);
				button.setOpaque(true);
				buttonRows.add(button);
				add(button);
				
			}
			buttonColumns.add(buttonRows);
		}
	}
	
	public void play() {
		
		isIterating.set(true);
		loop = new Thread(new iterate());
		loop.start();
		
	}
	
	public void stop() throws InterruptedException{
		isIterating.set(false);
		loop.join();
	}
	
	
	public class iterate implements Runnable {
		public void run() {
			while(isIterating.get()) {
				grid.nextIteration();
				for(int i =0; i<grid.columns.size(); i++) {
					for(int k=0; k<grid.columns.get(i).size(); k++) {
						if (grid.columns.get(i).get(k).getIsAlive()) {
							buttonColumns.get(i).get(k).setBackground(Color.black);
						} else buttonColumns.get(i).get(k).setBackground(Color.white);
					}
				}
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		
	}
		
}