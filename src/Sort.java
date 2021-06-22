import java.awt.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;

import java.io.*;

public class Sort extends JPanel implements Runnable{
	String str;
	private static final long serialVersionUID = 1L;
	protected static final int BORDER_WIDTH = 10;
	protected int size;
	protected int[] arr;
	protected int sleepTime = 10;
	public static File f = null;
	FileInputStream file;
	Boolean done;
	int colorCode[] = new int[100];
	int columnWidth, columnHeight, x, g, b, count;
	JLabel countLabel;
	
	public Sort() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1100,650));
		this.setBackground(Color.white);
		this.setArray();
		this.count = 0;
		countLabel = new JLabel("ºñ±³È½¼ö : " + String.valueOf(count));
		countLabel.setFont(new Font(countLabel.getFont().getName(),countLabel.getFont().getStyle(),20));
		countLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		this.add(countLabel,BorderLayout.NORTH);
		this.done = false;
		for(int i = 0;i<100;i++) {
			colorCode[i] = 250-(i*2);
		}
	}
	
	public void setArray() {
		reset();
		try {
			if(f == null) {
				file = new FileInputStream("./Input.txt");
			}else {
				file = new FileInputStream(f);
			}
			byte readBuffer[] = new byte[file.available()];
			int i = 0;
			while(file.read(readBuffer) != -1);
			str = new String(readBuffer);
			file.close();
			
		}catch(Exception e) {
		}
		
		String temp[] = str.split(" ");
		arr = new int[temp.length];
		for(int i =0;i<temp.length;i++) {
			arr[i] = Integer.parseInt(temp[i]);
		}
		size = arr.length;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
		columnWidth = getWidth() / size - 1;
		x = (getWidth() - (columnWidth*size))/2;
		columnHeight = (getHeight() - columnWidth) / 100;
		
		for(int i = 0; i < size; i++) {
			Font num = new Font(Font.MONOSPACED, Font.BOLD, columnWidth-2);		
			FontMetrics fontMetrix = getFontMetrics(num);
			g.setColor(Color.BLACK);
			g.setFont(num);
			if(arr[i]<10) {
				g.drawString(String.valueOf(arr[i]), (columnWidth * i + fontMetrix.stringWidth(String.valueOf(arr[i]))/2) + x, getHeight() - arr[i] * columnHeight);
			}else {
				g.drawString(String.valueOf(arr[i]), columnWidth * i + x, getHeight() - arr[i] * columnHeight);
			}
			
		}
		
	}
	
	public void media(int choice) {
		File media = null;
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;
		Clip clip;
		
		switch(choice) {
		case 0:
			media = new File("./jumping.wav");
			break;
		case 1:
			media = new File("./blop.wav");
			break;
		case 2:
			media = new File("./tada.wav");
			break;
		}
		
		try {
			stream = AudioSystem.getAudioInputStream(media);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip)AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();
		}catch(Exception e) {
		}
	}
	
	@Override
	public void run() {};

	public void reset() {};
}
