import java.awt.Color;

import javax.swing.JFrame;

public class SnakeClient extends JFrame{
	

	public SnakeClient() {
		this.setBounds(10,10,920,720);
		this.add(new SnakePanel());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SnakeClient();

	}

}
