package wmqzj20200520;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.ArrayUtils;

import sun.awt.shell.ShellFolder;

/**
 * 查找文件
 * 
 * @author WmqZj
 *
 */
public class MyFindFile {
	/** JFrame */
	JFrame jFrame = new JFrame("【请输入要查找的内容】");
	/** 上 JPanel */
	JPanel panelTop = new JPanel();
	/** 下JPanel */
	JPanel panelBottom = new JPanel();
	/** 底层容器 */
	Container floor;
	/** 图片标签 */
	JLabel labe1 = new JLabel("名称:", new ImageIcon("images/title.png"), JLabel.LEFT);
	/** 输入框 */
	JTextField inputName = new JTextField(30);
	/** 滚动条 */
	JScrollPane scrollPane = new JScrollPane(panelBottom);
	/** 输入框获取的内容 */
	String getName;
	/** 窗口的位置,使其居中 */
	int a = (MyJframe.jFrame.getWidth() - 210) / 2;
	int b = (MyJframe.jFrame.getHeight() - 190) / 2;
	Point topLeft = MyJframe.jFrame.getLocationOnScreen();
	/** 防误点击 */
	public boolean isTf = false;
	public int count = 0;
	/** 记录所有panel中的button的数组 */
	Component[] buttonAll = null;

	public MyFindFile() {
		setJframe();
	}

	/**
	 * 设置属性
	 */
	public void setJframe() {
		// 设置无边框
		scrollPane.setBorder(null);
		// 设置滚动条滚动的速度
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		// 设置水平滚动条不可见
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		// 把滚动条添加到窗体当中，同时也设定了宽高 重点和panel
		scrollPane.setBounds(0, 35, 350, 200);
		// 设置ScrollPane的大小
		scrollPane.setPreferredSize(new Dimension(365, 200));
		// 设置默认显示panel
		scrollPane.setViewportView(panelBottom);
		// 通过调节panelBottom来设置滚动条
		panelBottom.setPreferredSize(new Dimension(365, 200));
		jFrame.setBounds(a + topLeft.x - 50, b + topLeft.y, 365, 270);
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// 设置为空布局,方便放下2个panel
		jFrame.setLayout(null);
		// 大小不可改变
		jFrame.setResizable(false);
		// 设置panel的位置
		panelTop.setBounds(0, 0, 350, 35);
		jFrame.add(panelTop);
		jFrame.add(scrollPane);
		jFrame.setVisible(true);
		// 添加组件
		panelTop.add(labe1);
		panelTop.add(inputName);
		jFrame.setVisible(true);
		// 添加监视器,当用户改变输入框的内容的时候调用相应的方法
		inputName.addKeyListener(new KeyListener() {

			/** 键盘按下，然后释放调用此方法 */
			@Override
			public void keyTyped(KeyEvent e) {

			}

			/** 释放某个键时调用此方法 */
			@Override
			public void keyReleased(KeyEvent e) {
				// 移除之前的搜索结果
				panelBottom.removeAll();
				// 重写设置默认显示panel 防止出现按钮不消失的情况
				scrollPane.setViewportView(panelBottom);
				jFrame.validate();
				// 获取输入的内容
				getName = inputName.getText();
				// 当文本框内容为空,并且用户按下了二次删除键的时候,隐藏
				String nullText = "";
				if (getName.equals(nullText)) {
					// 防误点设计,当进行过搜索并且在为空的时候,按下过二次count,,才会看不见这个窗口
					if (e.getKeyCode() == 8) {
						count++;
						if (isTf && count >= 2) {
							jFrame.setVisible(false);
							panelBottom.removeAll();
							// 初始化值
							isTf = false;
							count = 0;
						}
					}
				}
				// 当内容不为空的时候,查找内容(getName != null || getName.equals("")==true)
				else {
					// 每次调用都初始化
					count = 0;
					buttonAll = null;
					// 获取所有panel上面的所有button
					for (int i = 0; i < MyJframe.panelArray.size(); i++) {
						buttonAll = ArrayUtils.addAll(buttonAll, MyJframe.panelArray.get(i).getComponents());
					}
					// 遍历查找
					for (int i = 0; i < buttonAll.length; i++) {
						String findName = ((JButton) buttonAll[i]).getText();
						// 自定义方法查找是否包含,如果包含,返回所在的位置,如果不,则返回-1
						int result1 = new MyFindStringIgnoreCase().ignoreCaseIndexOf(findName, getName, 0);
						// 如果查找到位真
						if (result1 != -1) {
							// 先克隆当前按钮
							JButton button = cloneButton(((JButton) buttonAll[i]));
							// 再添加进去panel
							panelBottom.add(button);
							// 为了避免有多个图标，所以继续查找，直到循环结束
							// break;
						}
					}
					isTf = true;
				}
				panelBottom.revalidate();
				// 重置滚动条
				reserScroll();
			}

			/** 按下某个键未释放时调用此方法 */
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

	}

	/**
	 * 克隆查找到的按钮
	 * 
	 */
	public JButton cloneButton(JButton button) {
		String text = button.getToolTipText();
		// 分割后temp[0]为null,temp[1]文件的名称,,temp[2]为路径或者网站或者特殊命令
		String[] temp = text.split("(<[^\\>]+> *)+");
		switch (temp[1]) {
		case "关机(立马关机哦~)":
			button = new JButton("关机", new ImageIcon("images/shutdown.png"));
			break;
		case "重启(立马重启哦~)":
			button = new JButton("重启", new ImageIcon("images/reboot.png"));
			break;
		case "睡眠(休息会吧~)":
			button = new JButton("睡眠", new ImageIcon("images/sleep.png"));
			break;
		case "注册表(编辑注册表)":
			button = new JButton("注册表", new ImageIcon("images/regedit.png"));
			break;
		case "计算器(计算器)":
			button = new JButton("计算器", new ImageIcon("images/calc.png"));
			break;
		case "我的电脑(打开我的电脑)":
			button = new JButton("我的电脑", new ImageIcon("images/computer.png"));
			break;
		case "写字板(记记笔记吧~)":
			button = new JButton("写字板", new ImageIcon("images/write.png"));
			break;
		case "画图板(放松以下画下画吧~)":
			button = new JButton("画图板", new ImageIcon("images/draw.png"));
			break;
		case "控制面板(管理你的电脑)":
			button = new JButton("控制面板", new ImageIcon("images/control.png"));
			break;
		default:
			// 如果是网站
			File file = new File(temp[2]);
			// 网站开头样式1
			String webBeginOne = "http://";
			// 网站开头样式2
			String webBeginTwo = "https://";
			if (temp[2].startsWith(webBeginOne) || temp[2].startsWith(webBeginTwo)) {
				button = new JButton(temp[1], new ImageIcon("images/websiteT.png"));
			}
			// 其他的
			else {
				try {
					button = new JButton(temp[1], new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)));
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
			}
			break;
		}
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setPreferredSize(new Dimension(70, 60));
		// 设置toolTip
		button.setToolTipText(text);
		// 添加单击事件
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// 分割后temp[0]为null,temp[1]文件的名称,,temp[2]为路径
				String[] temp = filePath.split("(<[^\\>]+> *)+");
				// 执行打开文件
				String cmd = "rundll32 url.dll FileProtocolHandler file://" + temp[2];
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return button;
	}

	/**
	 * 当窗口按钮数量超出滚动的时候重置滚动条
	 * 
	 */
	public void reserScroll() {
		// 总共可以容纳的数量
		int allAmount = 12;
		// 每一行课可占据的button的数量
		int eachLine = 4;
		// 可以容纳的行数 3还是4不知道
		int allLine = 3;
		// 获取当前窗口的button数量
		int tempbb = panelBottom.getComponents().length;
		// 获取行数
		if (tempbb > allAmount) {
			if (tempbb % eachLine == 0) {
				// 计算第几行
				tempbb = tempbb / eachLine;
				tempbb = tempbb - allLine;
			} else {
				// 计算第几行
				tempbb = (tempbb / eachLine) + 1;
				tempbb = tempbb - allLine;
			}
			// 重新定义窗口的Dimension,以便有滚动条显示
			panelBottom.setPreferredSize(new Dimension(351, (200 + 69 * tempbb)));
		}
	}

}
