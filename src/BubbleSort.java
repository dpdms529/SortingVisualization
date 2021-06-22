import java.awt.*;

public class BubbleSort extends Sort{
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn = -1;
	
	public BubbleSort(){
		super();
	}
	
	@Override
	public void reset() {
		redColumn = -1;
		greenColumn = -1;		
	}

	@Override
	public void run() {
		try {
			for (int k = 1; k < size; k++) {
				for (int i = 0; i < size - k; i++) {
					redColumn = i;
					repaint();
					Thread.sleep(sleepTime);
					if (arr[i] > arr[i + 1]) {
						redColumn = i + 1;
						int temp = arr[i];
						arr[i] = arr[i + 1];
						arr[i + 1] = temp;
						repaint();
						media(1);
						Thread.sleep(sleepTime);
					}
					count++;
					countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));
					repaint();
				}
				greenColumn = size - k;
				media(0);
				
			}
			greenColumn = 0;
			redColumn = -1;
		} catch (InterruptedException e) {
			return;
		}
		try {
			repaint();
			Thread.sleep(200);
		}catch(Exception e) {
		}
		done = true;
		repaint();
		media(2);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < (greenColumn == -1 ? arr.length : greenColumn); i++) {
			g.setColor(Color.pink);
			g.fillRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);			
		}
		if(greenColumn != -1) {
			for (int i = greenColumn; i < arr.length; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);
				g.setColor(Color.BLACK);
				g.drawRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);			
			}
		}
		if(redColumn != -1) {
			g.setColor(Color.RED);
			g.fillRect(columnWidth * redColumn + x, getHeight() - arr[redColumn] * columnHeight, columnWidth, arr[redColumn] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * redColumn + x, getHeight() - arr[redColumn] * columnHeight, columnWidth, arr[redColumn] * columnHeight-1);
		}
		
		if(done) {
			for (int i = 0; i < size; i++) {
				int idx = arr[i];
				Color c = new Color(255,colorCode[idx],colorCode[idx]);
				g.setColor(c);
				g.fillRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);
				g.setColor(Color.black);
				g.drawRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);	
			}

		}
		
	}
 }
