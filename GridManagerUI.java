package conway;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

public class GridManagerUI extends JFrame {

	private JPanel contentPane;
	private int row = 50;
	private int col = 50;
	Grid grid = new Grid(row, col);
	private ArrayList<ArrayList<JButton>> cellBtnList = new ArrayList<ArrayList<JButton>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridManagerUI frame = new GridManagerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GridManagerUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel gridPanel = new JPanel();
		contentPane.add(gridPanel, BorderLayout.CENTER);
		gridPanel.setLayout(new GridLayout(row, col, 0, 0));

		for (int i = 0; i < row; i++) {
			ArrayList<JButton> cellBtnRow = new ArrayList<>();
			for (int j = 0; j < col; j++) {
				JButton cellBtn = createCellButton(i, j);
				cellBtnRow.add(cellBtn);
				gridPanel.add(cellBtn);
			}
			cellBtnList.add(cellBtnRow);
		}
		
		JPanel controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.NORTH);
		
		JButton start = createStartButton();
		controlPanel.add(start);
		
	}

	private JButton createStartButton() {
		JButton start = new JButton();
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ArrayList<Cell>> change = grid.nextIteration();
				for (int i = 0; i < 2; i++) {
					ArrayList<Cell> change1 = change.get(i);
					for (Cell cell: change1) {
						if (i == 0) {
							cellBtnList.get(cell.getRowCoordinate()).get(cell.getColumnCoordinate())
									.setBackground(Color.GREEN);
						} else
							cellBtnList.get(cell.getRowCoordinate()).get(cell.getColumnCoordinate())
									.setBackground(Color.BLACK);
					}
				}
			}
		});
		return start;
	}

	private JButton createCellButton(int i, int j) {
		JButton gridBtn = new JButton("");
		gridBtn.putClientProperty("cell", grid.getCell(i, j));
		gridBtn.setBackground(Color.black);
		
		gridBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((Cell) gridBtn.getClientProperty("cell")).getIsAlive()) {
					gridBtn.setBackground(Color.black);
					((Cell) gridBtn.getClientProperty("cell")).setDead();
				} else {
					gridBtn.setBackground(Color.GREEN);
					((Cell) gridBtn.getClientProperty("cell")).setAlive();
				}
			}
		});
		
		gridBtn.setBorder(new LineBorder(Color.gray));
		gridBtn.setBorderPainted(true);  
        gridBtn.setFocusPainted(false); 
		return gridBtn;
	}

}
