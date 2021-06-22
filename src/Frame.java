import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Frame extends JFrame implements ActionListener{
	
	JPanel mainPanel,settingPanel,methodPanel,inputPanel,outputPanel,sortPanel,myPanel;
	JLabel methodLabel,inputLabel,outputLabel,myLabel;
	JComboBox<String> cb;
	JButton startButton,resetButton,inputButton,outputButton;
	JFileChooser chooser; 
	JOptionPane popup;
	
	Sort s;
	
	Thread t;
	
	FileOutputStream file;
	
	FileNameExtensionFilter filter;
	
	String name;
	
	Boolean isUsableFile = true;
	
	public Frame() {
		this.setSize(1200,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sorting Visualization");
		this.getContentPane().setBackground(Color.white);
		this.setLayout(new BorderLayout());
		makeSortSelection();
		this.add(mainPanel,BorderLayout.NORTH);
		makeSortPanel();
		this.add(sortPanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void makeSortSelection() {
		mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,10));
		mainPanel.setBackground(Color.white);
		
		settingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
		settingPanel.setBackground(Color.white);
		
		methodPanel = new JPanel(new FlowLayout());
		methodPanel.setBackground(Color.white);
		methodLabel = new JLabel("정렬 방식");
		String method[] = {"선택정렬","버블정렬","삽입정렬","병합정렬","퀵정렬"};
		cb = new JComboBox<>(method);
		cb.setBackground(Color.lightGray);
		cb.addActionListener(this);
		methodPanel.add(methodLabel);
		methodPanel.add(cb);
		
		inputPanel = new JPanel(new FlowLayout());
		inputPanel.setBackground(Color.white);
		inputLabel = new JLabel("입력 파일");
		inputButton = new JButton("Input.txt");
		inputButton.setBackground(Color.lightGray);
		inputButton.addActionListener(this);
		inputPanel.add(inputLabel);
		inputPanel.add(inputButton);
		
		outputPanel = new JPanel(new FlowLayout());
		outputPanel.setBackground(Color.white);
		outputLabel = new JLabel("출력 파일");
		outputButton = new JButton("Output.txt");
		outputButton.setBackground(Color.lightGray);
		outputButton.addActionListener(this);
		outputPanel.add(outputLabel);
		outputPanel.add(outputButton);
		
		startButton = new JButton("Start");
		startButton.setBackground(Color.pink);
		startButton.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.setBackground(Color.cyan);
		resetButton.addActionListener(this);
		
		myPanel = new JPanel();
		myPanel.setBackground(Color.white);
		myLabel = new JLabel("201819186 조예은");
		myLabel.setFont(new Font(myLabel.getFont().getName(),myLabel.getFont().getStyle(),20));
		myPanel.add(myLabel);
		
		settingPanel.add(inputPanel);
		settingPanel.add(outputPanel);
		settingPanel.add(methodPanel);
		settingPanel.add(startButton);
		settingPanel.add(resetButton);
		
		mainPanel.add(myPanel);
		mainPanel.add(settingPanel);
	
	}
	
	public void makeSortPanel() {
		sortPanel = new JPanel();
		sortPanel.setBackground(Color.white);
		s = new SelectionSort();
		t = new Thread(s);
		cb.setSelectedIndex(0);
		sortPanel.add(s);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cb) {
			selectMethod();
		}else if(e.getSource() == startButton) {
			if(s.done) {
				selectMethod();
			}
			beginSort();
		}else if(e.getSource() == resetButton){
			selectMethod();
		}else if(e.getSource() == inputButton) {
			inputTxt();
			try {
				selectMethod();
			}catch(Exception e1) {
				isUsableFile = false;
				while(!isUsableFile) {
					popup.showMessageDialog(this, "사용할 수 없는 파일입니다.");
					inputButton.doClick();
				}
			}
			isUsableFile = true;
		}else if(e.getSource() == outputButton) {
			outputTxt();
		}
		
	}
	
	public void selectMethod() {
		sortPanel.remove(s);
		t.interrupt();
		name = cb.getItemAt(cb.getSelectedIndex());
		switch(name) {
		case "선택정렬":
			this.s = new SelectionSort();
			break;
		case "버블정렬":
			this.s = new BubbleSort();
			break;
		case "삽입정렬":
			this.s = new InsertionSort();
			break;
		case "병합정렬":
			this.s = new MergeSort();
			break;
		case "퀵정렬":
			this.s = new QuickSort();
			break;
		}
		t = new Thread(s);
		sortPanel.add(s,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	public void beginSort() {
		t.start();
	}
	
	public void inputTxt() {
		chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		chooser.setCurrentDirectory(new File("./"));
		chooser.setDialogTitle("Input 파일 열기");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		filter = new FileNameExtensionFilter("txt","txt");
		chooser.setFileFilter(filter);
		
		int open = chooser.showOpenDialog(null);
		
		if(open == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			Sort.f = f;
			inputButton.setText(f.getName());
		}

	}
	
	public void outputTxt()	{
		if(!s.done) {
			popup.showMessageDialog(this, "정렬 전입니다.");
		}else {
			try {
				file = new FileOutputStream("./Output.txt");
				String str = name + " " + Arrays.toString(s.arr);
				file.write(str.getBytes());
				file.close();
			}catch(Exception e) {
			}
			popup.showMessageDialog(this, "정렬 결과가 저장되었습니다!");
		}
	}

}
