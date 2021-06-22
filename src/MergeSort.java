import java.awt.*;

public class MergeSort extends Sort{
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumnStart = -1;
	private int greenColumnFinish = -1;
	
	public MergeSort() {
		super();
	}

	@Override
	public void reset() {
		redColumn = -1;
		blueColumn = -1;
		greenColumnStart = -1;
		greenColumnFinish = -1;
	}

	@Override
	public void run() {
		try {
			mergeSort(0, size - 1);
			greenColumnStart = 0;
			greenColumnFinish = size - 1;
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

	public void mergeSort(int start, int fin) throws InterruptedException {
		if ((fin - start) > 0) {
			mergeSort(start, start + (fin - start) / 2);
			mergeSort(start + (fin - start) / 2 + 1, fin);
			merge(start, start + (fin - start) / 2, start + (fin - start) / 2 + 1, fin);
		}
	}

	public void merge(int start1, int fin1, int start2, int fin2) throws InterruptedException {
		int[] arr1 = new int[fin1 - start1 + 1];
		int[] arr2 = new int[fin2 - start2 + 1];
		int[] tmp = new int[arr1.length + arr2.length];
		System.arraycopy(arr, start1, arr1, 0, arr1.length);
		System.arraycopy(arr, start2, arr2, 0, arr2.length);
		Thread.sleep(sleepTime);
		repaint();
	    int current1 = 0;
	    redColumn = start1 + current1;
	    int current2 = 0;
	    blueColumn = start2 + current2;
	    int current3 = 0;

		while (current1 < arr1.length && current2 < arr2.length) {
			Thread.sleep(sleepTime);
			repaint();
			if (arr1[current1] < arr2[current2]) {
				tmp[current3++] = arr1[current1++];
				redColumn = start1 + current1 - 1;
			}else {
				tmp[current3++] = arr2[current2++];
				blueColumn = start2 + current2 - 1;
			}
			count++;
			countLabel.setText("ºñ±³È½¼ö : " + String.valueOf(count));
			Thread.sleep(sleepTime);
			repaint();
		}

		while (current1 < arr1.length) {
			tmp[current3++] = arr1[current1++];
			redColumn = start1 + current1 - 1;
			Thread.sleep(sleepTime);
			repaint();
		}

		while (current2 < arr2.length) {
			tmp[current3++] = arr2[current2++];
			blueColumn = start2 + current2 - 1;
			Thread.sleep(sleepTime);
			repaint();
		}

		redColumn = -1;
		blueColumn = -1;
		greenColumnStart = start1;
		media(0);
		for (int i = 0; i < tmp.length; i++) {
			greenColumnFinish = start1 + i;
			arr[start1 + i] =  tmp[i];
			Thread.sleep(sleepTime);
			repaint();
		}
		greenColumnStart = -1;
		greenColumnFinish = -1;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < arr.length; i++) {
			g.setColor(Color.pink);
			g.fillRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * i + x, getHeight() - arr[i] * columnHeight, columnWidth, arr[i] * columnHeight-1);			
		}
		if((greenColumnStart != -1)&&(greenColumnFinish != -1)) {
			for (int i = greenColumnStart; i <= greenColumnFinish; i++) {
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
		if(blueColumn != -1) {
			g.setColor(Color.BLUE);
			g.fillRect(columnWidth * blueColumn + x, getHeight() - arr[blueColumn] * columnHeight, columnWidth, arr[blueColumn] * columnHeight-1);
			g.setColor(Color.BLACK);
			g.drawRect(columnWidth * blueColumn + x, getHeight() - arr[blueColumn] * columnHeight, columnWidth, arr[blueColumn] * columnHeight-1);
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

