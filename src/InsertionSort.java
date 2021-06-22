import java.awt.*;

public class InsertionSort extends Sort{
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn = -1;
	
	public InsertionSort() {
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
			for (int i = 1; i < size; i++) {
				greenColumn = i;
				media(0);
				redColumn = greenColumn;
				int k;
				for (k = i - 1; k >= 0 && arr[k] > arr[k+1]; k--) {
					redColumn = k + 1;
					repaint();
					Thread.sleep(sleepTime);
					int tmp = arr[k + 1]; 
					arr[k + 1] = arr[k];
					arr[k] = tmp;
					media(1);
					if(k!=0) {
						count++;
						countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));	
					}
					repaint();
				}
				Thread.sleep(sleepTime);
				count++;
				countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));	
				redColumn = k + 1;
				repaint();
			}
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
		
		for (int i = (greenColumn == -1 ? 0 : greenColumn); i < arr.length; i++) {
			g.setColor(Color.pink);
			g.fillRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);			
		}
		for (int i = 0; i <= greenColumn; i++) {
			g.setColor(Color.GREEN);
			g.fillRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);			
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
