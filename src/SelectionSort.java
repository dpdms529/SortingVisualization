import java.awt.*;

public class SelectionSort extends Sort{
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumn = -1;
	
	public SelectionSort() {
		super();
	}

	@Override
	public void reset() {
		redColumn = -1;
		blueColumn = -1;
		greenColumn = -1;		
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < size - 1; i++) {
				int currentMinIndex = i;
				redColumn = currentMinIndex;
				for (int j = i + 1; j < size; j++) {
					blueColumn = j;
					repaint();
					Thread.sleep(sleepTime);
					if (arr[currentMinIndex] > arr[j]) {
						currentMinIndex = j;
						redColumn = currentMinIndex;
						repaint();
						media(1);
						Thread.sleep(sleepTime);
					}
					count++;
					countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));
					repaint();
				}

				if (currentMinIndex != i) {
					int tmp = arr[currentMinIndex];
					arr[currentMinIndex] = arr[i];
					arr[i] = tmp;
					repaint();
					Thread.sleep(sleepTime);
				}
				greenColumn++;
				repaint();
				media(0);
				Thread.sleep(sleepTime);
			}
			greenColumn++;
			redColumn = -1;
			blueColumn = -1;
		} catch (InterruptedException e) {
			return;
		}
		repaint();
		try {
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
		if(blueColumn != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(columnWidth * blueColumn + x, getHeight() - arr[blueColumn] * columnHeight, columnWidth-1, arr[blueColumn] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * blueColumn + x, getHeight() - arr[blueColumn] * columnHeight, columnWidth-1, arr[blueColumn] * columnHeight-1);
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
