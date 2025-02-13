package bsu.rfe.java.group6.lab2.Ivleva.var1;

import javax.swing.JFrame;
// Главный класс приложения, он же класс фрейма
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class MainFrame extends JFrame {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldZ;
	
	private JTextField textFieldResult;
	
	private ButtonGroup radioButtons = new ButtonGroup();
	private ButtonGroup radioButtons_mem = new ButtonGroup();
	
	private Box hboxFormulaType = Box.createHorizontalBox();
	private Box hboxMemoryType = Box.createHorizontalBox();
	
	private int formulaId = 1;
	private int memoryid = 1;
	
	public Double calculate1(Double x, Double y, Double z) {
		return ( Math.sin(x) + y*y + Math.exp(Math.sin(y) )*Math.pow( Math.log(z)+Math.sin(Math.PI*x), 0.25));
	}
	
	public Double calculate2(Double x, Double y, Double z) {
		return ( Math.pow(y+x*x*x,1/z) )/Math.log(z);
	}
	
	private void addRadioButton(String buttonName, final int formulaId) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			private JComponent imagePane;
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.formulaId = formulaId;
				imagePane.updateUI();
			}
		});
		radioButtons.add(button);
		hboxFormulaType.add(button);
	}
	
	private void addRadioButton_mem(String buttonName, final int memoryid) {
		JRadioButton button = new JRadioButton(buttonName);
		button.addActionListener(new ActionListener() {
			private JComponent imagePane;
			public void actionPerformed(ActionEvent ev) {
				MainFrame.this.memoryid = memoryid;
				imagePane.updateUI();
			}
		});
		radioButtons_mem.add(button);
		hboxMemoryType.add(button);
	}
	
	// Конструктор класса
	public MainFrame() {
		
		super("Вычисление формулы");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		// Отцентрировать окно приложения на экране
		setLocation((kit.getScreenSize().width - WIDTH)/2,
				(kit.getScreenSize().height - HEIGHT)/2);
		
		hboxFormulaType.add(Box.createHorizontalGlue());
		addRadioButton("Формула 1", 1);
		addRadioButton("Формула 2", 2);
		
		radioButtons.setSelected(
				radioButtons.getElements().nextElement().getModel(), true);
		
		hboxFormulaType.add(Box.createHorizontalGlue());
		hboxFormulaType.setBorder(
				BorderFactory.createLineBorder(Color.YELLOW));  
		
		hboxMemoryType.add(Box.createHorizontalGlue());
		hboxMemoryType.setBorder(
				BorderFactory.createLineBorder(Color.PINK));
		
		addRadioButton_mem("переменная 1", 1);
		addRadioButton_mem("переменная 2", 2);
		addRadioButton_mem("переменная 3", 3);
		radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
		hboxMemoryType.add(Box.createHorizontalGlue());
		
		// Создать область с полями ввода для X и Y
		JLabel labelForX = new JLabel("X:");
		textFieldX = new JTextField("0", 10);
		textFieldX.setMaximumSize(textFieldX.getPreferredSize());
		
		JLabel labelForY = new JLabel("Y:");
		textFieldY = new JTextField("0", 10);
		textFieldY.setMaximumSize(textFieldY.getPreferredSize());
		
		JLabel labelForZ=new JLabel("Z:");
		textFieldZ=new JTextField("0",10);
		textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
		
		Box hboxVariables = Box.createHorizontalBox();
		hboxVariables.setBorder(
				BorderFactory.createLineBorder(Color.RED));
		hboxVariables.add(Box.createHorizontalGlue());
		hboxVariables.add(labelForX);
		
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldX);
		
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(labelForY);
		
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldY);
		
		hboxVariables.add(Box.createHorizontalStrut(100));
		hboxVariables.add(labelForZ);
		
		hboxVariables.add(Box.createHorizontalStrut(10));
		hboxVariables.add(textFieldZ);
		
		
		hboxVariables.add(Box.createHorizontalGlue());

		
		// Создать область для вывода результата
		JLabel labelForResult = new JLabel("Результат:");
		//labelResult = new JLabel("0");
		
		textFieldResult = new JTextField("0", 10);
		textFieldResult.setMaximumSize(
				textFieldResult.getPreferredSize());
		
		Box hboxResult = Box.createHorizontalBox();
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.add(labelForResult);
		hboxResult.add(Box.createHorizontalStrut(10));
		hboxResult.add(textFieldResult);
		hboxResult.add(Box.createHorizontalGlue());
		hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		// Создать область для кнопок
		JButton buttonCalc = new JButton("Вычислить");
		buttonCalc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				
				try {Double x = Double.parseDouble(textFieldX.getText());
				Double y = Double.parseDouble(textFieldY.getText());
				Double z = Double.parseDouble(textFieldZ.getText());
				Double result;
				if (formulaId==1)
					result = calculate1(x, y, z);
				else
					result = calculate2(x, y, z);
				textFieldResult.setText(result.toString());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(MainFrame.this,
							"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		
		JButton buttonMC = new JButton("MC");
		buttonMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				memoryid = 0;
				radioButtons_mem.clearSelection();
			}
		});
		
		JButton buttonMPlus = new JButton("M+");
		buttonMPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Double mem1 = Double.parseDouble(textFieldX.getText());
				Double mem2 = Double.parseDouble(textFieldY.getText());
				Double mem3 = Double.parseDouble(textFieldZ.getText());

				Double result = Double.parseDouble(textFieldResult.getText());
				if(memoryid == 1) {
					result+=mem1;
				}

				if(memoryid == 2) {
					result+=mem2;
				}

				if(memoryid == 3) {
					result+=mem3;
				}
				
				textFieldResult.setText(result.toString());
			}
		});

		JButton buttonReset = new JButton("Очистить поля");
		buttonReset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				textFieldX.setText("0");
				textFieldY.setText("0");
				textFieldResult.setText("0");
			}
		});
		
		Box hboxButtons = Box.createHorizontalBox();
		hboxButtons.add(Box.createHorizontalGlue());
		hboxButtons.add(buttonCalc);
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonReset);
		hboxButtons.add(Box.createHorizontalGlue());
		
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonMC);
		hboxButtons.add(Box.createHorizontalGlue());
		
		hboxButtons.add(Box.createHorizontalStrut(30));
		hboxButtons.add(buttonMPlus);
		hboxButtons.add(Box.createHorizontalGlue());
		
		hboxButtons.setBorder(
				BorderFactory.createLineBorder(Color.GREEN));
		
		// Связать области воедино в компоновке BoxLayout
		Box contentBox = Box.createVerticalBox();
		contentBox.add(Box.createVerticalGlue());
		contentBox.add(hboxFormulaType);
		contentBox.add(Box.createHorizontalStrut(10));
		contentBox.add(hboxMemoryType);
		contentBox.add(Box.createHorizontalStrut(10));
		contentBox.add(hboxVariables);
		contentBox.add(hboxResult);
		contentBox.add(hboxButtons);
		
		contentBox.add(Box.createVerticalGlue());
		getContentPane().add(contentBox, BorderLayout.CENTER);
	}
	
	// Главный метод класса
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
