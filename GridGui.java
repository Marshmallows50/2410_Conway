package conway;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GridGui extends JFrame {
	
	private JPanel contentPane;
	private JPanel controlPanel;
	private GridPanel gridPanel;
	
//

	public static void main(String[] args) {
//		Grid grid = new Grid(8, 30);
		//grid.randomizeCellValues();
		
		
		
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
	
	public GridGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 400);
		setTitle("Conway's Game of Life");
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		controlPanel = new JPanel();
		gridPanel = new GridPanel();
		
		
		JButton play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.play();
				
			}
		});
		controlPanel.add(play);
		
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
		
		JButton randomize = new JButton("Randomize Grid");
		randomize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gridPanel.grid.randomizeCellValues();
				
			}
		});
		controlPanel.add(randomize);
		
		contentPane.add(controlPanel, BorderLayout.NORTH);
		contentPane.add(gridPanel);
		
		
	}
	
	

}
