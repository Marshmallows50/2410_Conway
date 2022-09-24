package conway;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * JPanel that contains buttons representing a grid in Conways Game of Life
 * @author everybody
 *
 */
public class GridPanel extends JPanel{
	public JPanel contentPane;
	public JPanel controlPanel;
	public JPanel gridPanel;
	
	private AtomicBoolean isIterating = new AtomicBoolean(false);
	
	public Grid grid;
	public ArrayList<ArrayList<JButton>> buttonColumns;
	
	public Thread loop;
	
	
	/**
	 * Constructor. Creates a new Grid, creates a new JButton for each Cell in the grid, an
	 * adds those buttons to the panel.
	 */
	public GridPanel() {
		grid = new Grid(10, 30);
		
		buttonColumns = new ArrayList<ArrayList<JButton>>();
		setLayout(new GridLayout(grid.height, grid.width));
		
		
		for(ArrayList<Cell> rows: grid.columns) {
			ArrayList<JButton> buttonRows = new ArrayList<JButton>();
			for(Cell cell: rows) {
				JButton button = createCellButton(buttonRows, cell);
				buttonRows.add(button);
				add(button);
				
				
			}
			buttonColumns.add(buttonRows);
		}
	}
	
	public GridPanel(int height, int width) {
		grid = new Grid(height, width);
		
		buttonColumns = new ArrayList<ArrayList<JButton>>();
		setLayout(new GridLayout(grid.height, grid.width));
		
		
		for(ArrayList<Cell> rows: grid.columns) {
			ArrayList<JButton> buttonRows = new ArrayList<JButton>();
			for(Cell cell: rows) {
				JButton button = createCellButton(buttonRows, cell);
				buttonRows.add(button);
				add(button);
				
				
			}
			buttonColumns.add(buttonRows);
		}
	}

	/**
	 * Method that creates a new JButton to be added to the grid, adds click functionality, and styles it.
	 * @param buttonRows
	 * @param cell
	 * @return a JButton for the Grid.
	 */
	private JButton createCellButton(ArrayList<JButton> buttonRows, Cell cell) {
		JButton button = new JButton();
		if (cell.getIsAlive()) {
			button.setBackground(Color.black);
		} 
		else {
			button.setBackground(Color.white);
		}
		button.putClientProperty("c", cell);
		
		
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
		
		
		button.setBorder(new LineBorder(Color.BLACK));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(true);
		return button;
	}
	
	/**
	 * Continually generates the next iterations and updates the button on the grid accordingly.
	 */
	public void play() {
		isIterating.set(true);
		loop = new Thread(new iterate());
		loop.start();
		
	}
	
	/**
	 * Stops generating next iterations.
	 * @throws InterruptedException
	 */
	public void stop() throws InterruptedException{
		isIterating.set(false);
		loop.join();
	}
	
	/**
	 * Refreshes the Buttons on the grid.
	 */
	public void refresh() {
		for(ArrayList<JButton> buttonRow:buttonColumns) {
			for(JButton button:buttonRow) {
				if (((Cell) button.getClientProperty("c")).getIsAlive()) {
					button.setBackground(Color.black);
				} else button.setBackground(Color.white);
			}
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	/**
	 * Class that contains a run method that is meant to run in a separate thread.
	 * Continually generates the next iterations and updates the button on the grid accordingly.
	 * @author everybody
	 */
	
	public class iterate implements Runnable {
		public void run() {
			while(isIterating.get()) {
				grid.nextIteration();
				
				for(ArrayList<JButton> buttonRow:buttonColumns) {
					for(JButton button:buttonRow) {
						if (((Cell) button.getClientProperty("c")).getIsAlive()) {
							button.setBackground(Color.black);
						} else button.setBackground(Color.white);
					}
				}
				Toolkit.getDefaultToolkit().sync();
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}	
}