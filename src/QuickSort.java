import java.awt.*;

public class QuickSort extends Sort{
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int yellowColumn = -1;
	private int greenColumn = -1;
	
	public QuickSort() {
		super();
	}

	@Override
	public void reset() {
		redColumn = -1;
		blueColumn = -1;
		greenColumn = -1;
		yellowColumn = -1;
	}

	@Override
	public void run() {
		try {
			quicksort(0, size - 1);	
		} catch (InterruptedException e) {
			return;
		}
		redColumn = -1;
		blueColumn = -1;
		yellowColumn = -1;
		greenColumn = size - 1;
		repaint();
		try {
			Thread.sleep(200);
		}catch(Exception e) {
		}
		done = true;
		repaint();
		media(2);
	}
	
	private void quicksort(int low, int high) throws InterruptedException {
		int i = low;
		int j = high;
		Thread.sleep(sleepTime);
		repaint();
		int pivot = arr[low + (high - low) / 2];
		redColumn = low + (high - low) / 2;
		
		while (i <= j) {
			blueColumn = i;
			yellowColumn = j;
			Thread.sleep(sleepTime);
			repaint();
			while (arr[i] < pivot) {
				i++;
				blueColumn = i;
				count++;
				countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));
				Thread.sleep(sleepTime);
				repaint();
			}
			while (arr[j] > pivot) {
				j--;
				yellowColumn = j;
				count++;
				countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));
				Thread.sleep(sleepTime);
				repaint();
			}

			if (i <= j) {
				if(i<j) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					media(1);
				}
				if(i == redColumn) {
					redColumn = j;
				}else if (j == redColumn) {
					redColumn = i;
				}
				count++;
				countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));
				repaint();
				Thread.sleep(sleepTime);
				i++;
				j--;
			}
		}

		if (low < j) {
			quicksort(low, j);
		}
		if (i < high) {
			quicksort(i, high);
		}
		if(low > greenColumn) {
			greenColumn = low;
			media(0);
			blueColumn = -1;
			yellowColumn = -1;
		}
		Thread.sleep(sleepTime);
		repaint();
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
			g.fillRect(columnWidth * blueColumn + x, getHeight() - arr[blueColumn] * columnHeight, columnWidth, arr[blueColumn] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * blueColumn + x, getHeight() - arr[blueColumn] * columnHeight, columnWidth, arr[blueColumn] * columnHeight-1);
		}
		if(yellowColumn != -1) {
			g.setColor(Color.YELLOW);
			g.fillRect(columnWidth * yellowColumn + x, getHeight() - arr[yellowColumn] * columnHeight, columnWidth, arr[yellowColumn] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * yellowColumn + x, getHeight() - arr[yellowColumn] * columnHeight, columnWidth, arr[yellowColumn] * columnHeight-1);
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
