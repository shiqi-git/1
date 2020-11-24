package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class RankFrame extends JFrame{
	public static JTextArea chartsarea ;
	JLabel chartsjl ;
	JPanel panel ;
	JButton imp;
	BufferedImage rankp;
	JLabel backgroung;
	
	static ArrayList list = new ArrayList();
	public static List<String> readFromFile(String filePath){
		//声明输入流对象
		BufferedReader br = null; 
		try {
			//建立输入流和源文件之间的联系  
			br = new BufferedReader(new FileReader(filePath));
			//声明字符串，用来存储读取的内容
			String msg = br.readLine();

			
			while(msg!=null){
				msg = br.readLine();
				if(msg!=null){
					list.add(msg);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//关闭资源
			if(null!=br){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public void addComponents() throws IOException{
		//获取屏幕的宽和高
		panel = new JPanel();
		chartsarea = new JTextArea();
		chartsjl = new JLabel("历史记录");
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension di = tk.getScreenSize();
		int width = di.width;
		int height= di.height;
		setBounds(width/2-250, height/2-300, 700, 600);
		//显示可见
		setVisible(true);
		//设置关闭窗口操作
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("历史记录");
		panel.setLayout(null);
		add(panel);

		List<String> list = readFromFile("D:\\历史记录.txt");
		for(String temp:list){

			chartsarea.append(temp+"\n");

			System.out.println(temp);
		}
		
		chartsjl.setFont(new Font("宋体",Font.BOLD,30));
		chartsjl.setBounds(width/2-600, height/2-500, 200, 200);
		panel.add(chartsjl);

		chartsarea.setFont(new Font("宋体",Font.BOLD,30));
		chartsarea.setBackground(null);;
		chartsarea.setBounds(width/2-650, height/2-280, 300, 400);
		panel.add(chartsarea);


		if(this.isShowing())this.getLocationOnScreen();
	}

	public static void main(String[] args) throws Exception {

		new RankFrame().addComponents();
		//把文件中的内容传到list中
		/*List<String> list = readFromFile("D:\\排行榜.txt");
		for(String temp:list){
			
			chartsarea.append(temp+"\n");
			
			System.out.println(temp);
		}*/
	}

	
}
