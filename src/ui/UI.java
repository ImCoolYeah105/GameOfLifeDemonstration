package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import main.Start;

@SuppressWarnings("serial")
public class UI extends JFrame implements Runnable {
	
	public UI() {		
		prepairUI();
	}
	
	private GamePanel gamePanel;
	private OptionsPanel optionsPanel;
	
	private void prepairUI() {
		this.setTitle("Conway's Game of Life");
		this.setSize(new Dimension(900,640));
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowClosing(WindowEvent arg0) {
				Start.close();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {}
			@Override
			public void windowActivated(WindowEvent arg0) {}
			@Override
			public void windowDeactivated(WindowEvent arg0) {}
			@Override
			public void windowDeiconified(WindowEvent arg0) {}
			@Override
			public void windowIconified(WindowEvent arg0) {}
			@Override
			public void windowOpened(WindowEvent arg0) {}
		});
		
		gamePanel = new GamePanel();
		this.add(gamePanel);
		optionsPanel = new OptionsPanel();
		this.add(optionsPanel);
		
		this.setVisible(true);
	}
	
	public void updateGameUI() {
		gamePanel.repaint();
		
		Options.speed = optionsPanel.speed.getValue();
		if(optionsPanel.state.getText().equals("          off          "))
			Options.active = false;
		else
			Options.active = true;
	}
	
	@Override
	public void run() {
		while(Start.active) {
			updateGameUI();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
	}
}
