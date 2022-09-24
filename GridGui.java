package conway;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel controlPanel;
	private GridPanel gridPanel;
	

	/**
	 * Main method that runs the program.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridGui frame = new GridGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructs the JFrame and creates panels.
	 */
	public GridGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 400);
		setTitle("Conway's Game of Life");
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		controlPanel = new JPanel();
		gridPanel = new GridPanel();
		
		
		
		createNewGrid();
		createNewLargeGrid();
		createPlay();
		createNext();
		createStop();
		createRandomize();
		createSaveGrid();
		createImportGrid();
		
		
		contentPane.add(gridPanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.NORTH);
	}

	private void createNewLargeGrid() {
		JButton newLargeGrid = new JButton("New Large Grid");
		newLargeGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(gridPanel);
				gridPanel = new GridPanel(20, 50);
				setBounds(100, 100, 1500, 650);
				contentPane.add(gridPanel);
				gridPanel.refresh();
				contentPane.repaint();
			}
		});
		controlPanel.add(newLargeGrid);
	}

	private void createNewGrid() {
		JButton newGrid = new JButton("New Grid");
		newGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(gridPanel);
				gridPanel = new GridPanel(10, 30);
				setBounds(100, 100, 1200, 400);
				contentPane.add(gridPanel);
				gridPanel.refresh();
				contentPane.repaint();
				
			}
		});
		controlPanel.add(newGrid);
	}

	private void createNext() {
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.grid.nextIteration();
				gridPanel.refresh();
			}
		});
		controlPanel.add(next);
	}

	/**
	 * Creates the import button
	 */
	private void createImportGrid() {
		JButton importGrid = new JButton("Import Grid");
		importGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(gridPanel);
				deserializeGrid();
				contentPane.add(gridPanel);
				gridPanel.refresh();
				contentPane.repaint();
			}
		});
		controlPanel.add(importGrid);
	}

	/**
	 * Creates the Save Button
	 */
	private void createSaveGrid() {
		JButton saveGrid = new JButton("Save Grid");
		saveGrid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serializeGrid();
			}
		});
		controlPanel.add(saveGrid);
	}

	/**
	 * Creates the randomize Button
	 */
	private void createRandomize() {
		JButton randomize = new JButton("Randomize Grid");
		randomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.grid.randomizeCellValues();
				gridPanel.refresh();
			}
		});
		controlPanel.add(randomize);
	}

	/**
	 * Creates the stop Button
	 */
	private void createStop() {
		JButton stop = new JButton("Stop");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gridPanel.stop();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		controlPanel.add(stop);
	}

	/**
	 * Creates the play Button.
	 */
	private void createPlay() {
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.play();
				//gridPanel.repaint();
				
			}
		});
		controlPanel.add(play);
	}
	
	/**
	 * Lets us choose a saved Grid to import into the game.
	 */
	public void deserializeGrid() {
		gridPanel = null;
	      try {
	    	  JFileChooser j = new JFileChooser();
	    	  int response = j.showOpenDialog(null);
	    	  if (response == JFileChooser.APPROVE_OPTION) {
	    		  FileInputStream fileIn = new FileInputStream(j.getSelectedFile().getAbsolutePath());
	    		  ObjectInputStream streamIn = new ObjectInputStream(fileIn);
	    		  gridPanel = (GridPanel) streamIn.readObject();
		          streamIn.close();
		          fileIn.close();
	    	  }

	       } catch (IOException e) {
	          e.printStackTrace();
	          return;
	       } catch (ClassNotFoundException e) {
	          e.printStackTrace();
	          return;
	       }
	}
	
/**
 * Creates our Game Directory if it doesn't already exist.
 * Then saves our current Grid as a time stamped Grid.ser
 */
	public void serializeGrid() {
		String directory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Conway";
		File resources = new File(directory);
		
		if (resources.exists() == false) {
			resources.mkdirs();
		}
          try {
              FileOutputStream fileOut = new FileOutputStream(directory + File.separator + new Date().toString() + "Grid.ser");
              ObjectOutputStream streamOut = new ObjectOutputStream(fileOut);
              gridPanel.loop = null;
              streamOut.writeObject(gridPanel);
              streamOut.close();
              fileOut.close();
           } catch (IOException e) {
              e.printStackTrace();
           }
    }
}
