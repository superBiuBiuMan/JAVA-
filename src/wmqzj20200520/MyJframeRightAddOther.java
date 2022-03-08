package wmqzj20200520;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 添加其他
 * 
 * @author WmqZj
 */
public class MyJframeRightAddOther extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	/*** 按钮【关机】 */
	JButton buttonClose;
	/** 按钮【重启 */
	JButton buttonReboot;
	/*** 按钮【睡眠】 */
	JButton buttonSleep;
	/** 按钮【注册表】 */
	JButton buttonRegedit;
	/** 按钮【计算器】 */
	JButton buttonCalc;
	/*** 按钮【我的电脑】 */
	JButton buttonComputer;
	/** 按钮【写字板】 */
	JButton buttonWrite;
	/** 按钮【画图板】 */
	JButton buttonDraw;
	/** 按钮【控制面板】 */
	JButton buttonControl;
	/** 计算居中的位置的干活 */
	int x = (MyJframe.jFrame.getWidth() - 210) / 2;
	int y = (MyJframe.jFrame.getHeight() - 190) / 2;
	Point topLeft = MyJframe.jFrame.getLocationOnScreen();

	public MyJframeRightAddOther() {
		// 设置居中
		this.setBounds(x + topLeft.x - 50, y + topLeft.y - 20, 380, 320);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("请单击你想添加的功能吧~");
		// 设置不可调节窗口大小
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		buttonClose = new JButton("关机", new ImageIcon("images/shutdown.png"));
		buttonReboot = new JButton("重启", new ImageIcon("images/reboot.png"));
		buttonSleep = new JButton("睡眠", new ImageIcon("images/sleep.png"));
		buttonCalc = new JButton("计算器", new ImageIcon("images/calc.png"));
		buttonRegedit = new JButton("注册表", new ImageIcon("images/regedit.png"));
		buttonComputer = new JButton("我的电脑", new ImageIcon("images/computer.png"));
		buttonWrite = new JButton("写字板", new ImageIcon("images/write.png"));
		buttonDraw = new JButton("画图板", new ImageIcon("images/draw.png"));
		buttonControl = new JButton("控制面板", new ImageIcon("images/control.png"));

		setButton(buttonClose);
		setButton(buttonReboot);
		setButton(buttonSleep);
		setButton(buttonCalc);
		setButton(buttonRegedit);
		setButton(buttonComputer);
		setButton(buttonWrite);
		setButton(buttonDraw);
		setButton(buttonControl);

		this.add(buttonClose);
		this.add(buttonReboot);
		this.add(buttonSleep);
		this.add(buttonCalc);
		this.add(buttonRegedit);
		this.add(buttonComputer);
		this.add(buttonWrite);
		this.add(buttonControl);
		this.add(buttonDraw);

	}

	/**
	 * 设置按钮
	 * 
	 * @param button -要设置的按钮
	 */
	public void setButton(JButton button) {
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		// 设置是否绘制边框
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
//		button.setFocusPainted(false);
		// 设置按钮的宽
		button.setPreferredSize(new Dimension(86, 86));
		button.addActionListener(this);
	}

	/**
	 * 克隆按钮
	 * 
	 * @param name   -按钮名称
	 * @param tip    -提示信息
	 * @param button -要设置的按钮
	 * @param width  -要设置的宽
	 * @param height -高
	 * @param cmd    -dos命令
	 */
	public void clone(String name, String tip, JButton button, int width, int height, String cmd) {
		// 垂直文字居中
		button.setVerticalTextPosition(JButton.BOTTOM);
		// 水平文字居中
		button.setHorizontalTextPosition(JButton.CENTER);
//		// 设置是否绘制边框
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
//		button.setFocusPainted(false);
		// 设置按钮的宽
		button.setPreferredSize(new Dimension(width, height));
		// 添加监视
		button.addActionListener(new ActionListener() {
			String shutDownNow = "关机(立马关机哦~)";
			String rebooNow = "重启(立马重启哦~)";
			String sleepNow = "睡眠(休息会吧~)";

			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// 分割后temp[0]为null,temp[1]文件的名称,,temp[2]为路径
				String[] splitAfter = filePath.split("(<[^\\>]+> *)+");
				if (splitAfter[1].equals(shutDownNow) || splitAfter[1].equals(rebooNow)
						|| splitAfter[1].equals(sleepNow)) {
					Object[] options = { "确认", "取消" };
					int select = JOptionPane.showOptionDialog(MyJframe.jFrame, "你确定执行【 " + splitAfter[1] + "】吗？", "提示：",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							new ImageIcon("images/cancel.png"), options, options[1]);
					// 确认按下
					if (select == 0) {
						// 执行打开文件
						String cmd = "rundll32 url.dll,FileProtocolHandler " + splitAfter[2];
						try {
							Runtime.getRuntime().exec(cmd);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(MyJframe.jFrame, "出错了，请重试！");
							e1.printStackTrace();
						}
					}
				} else {
					// 执行打开文件
					String cmd = splitAfter[2];
					try {
						Runtime.getRuntime().exec(cmd);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MyJframe.jFrame, "出错了，请重试！");
						e1.printStackTrace();
					}
				}

			}
		});
		// 添加监视
		button.addMouseMotionListener(MyCreateMySelfButton.mousedrag);
		button.addMouseListener(new MyButtonRightAndDragFile());

		String webStyle = "<html><strong><font size=4>" + name + "(" + tip + ")" + "</font> </strong><br>" + cmd
				+ "<br></html>";
		button.setToolTipText(webStyle);
		// 添加到窗体
		MyJframe.panelArray.get(MyJframe.panelIndex).add(button);
		// 重新设置当前panel的数量，数量为当前的加上1
		int temp1 = MyJframe.panelComAmount.get(MyJframe.panelIndex) + 1;
		MyJframe.panelComAmount.set(MyJframe.panelIndex, temp1);
		// 写入文件 绝对路径+分割符号+文件名+特殊命名标记
		String writeTemp = cmd + "###" + name + "(" + tip + ")" + "###" + "Special";
		new MyDataIniReadAndWrite().write(MyJframe.panelComAmount.get(MyJframe.panelIndex), writeTemp);
		new MyIniReadAndWrite().changAmount(MyJframe.panelComAmount.get(MyJframe.panelIndex));
		MyJframe.jFrame.validate();

	}

	/**
	 * 当单击按钮的时候,"克隆一个按钮到当前panel"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button;
		switch (((JButton) e.getSource()).getText()) {
		case "关机":
			button = new JButton("关机", new ImageIcon("images/shutdown.png"));
			// 关机
			clone("关机", "立马关机哦~", button, 70, 60, "shutdown /p");
			break;
		case "重启":
			button = new JButton("重启", new ImageIcon("images/reboot.png"));
			// 重启
			clone("重启", "立马重启哦~", button, 70, 60, "shutdown /r");
			break;
		case "睡眠":
			button = new JButton("睡眠", new ImageIcon("images/sleep.png"));
			// 睡眠
			clone("睡眠", "休息会吧~", button, 70, 60, "shutdown /h");
			break;
		case "注册表":
			button = new JButton("注册表", new ImageIcon("images/regedit.png"));
			// 打开注册表
			clone("注册表", "编辑注册表", button, 70, 60, "regedit");
			break;
		case "计算器":
			button = new JButton("计算器", new ImageIcon("images/calc.png"));
			// 计算器
			clone("计算器", "计算器", button, 70, 60, "calc");
			break;
		case "我的电脑":
			button = new JButton("我的电脑", new ImageIcon("images/computer.png"));
			// 我的电脑
			clone("我的电脑", "打开我的电脑", button, 70, 60, "explorer.exe");
			break;
		case "写字板":
			button = new JButton("写字板", new ImageIcon("images/write.png"));
			// 写字板
			clone("写字板", "记记笔记吧~", button, 70, 60, "write");
			break;
		case "画图板":
			button = new JButton("画图板", new ImageIcon("images/draw.png"));
			// 画图板
			clone("画图板", "放松以下画下画吧~", button, 70, 60, "mspaint");
			break;
		case "控制面板":
			button = new JButton("控制面板", new ImageIcon("images/control.png"));
			// 控制面板
			clone("控制面板", "管理你的电脑", button, 70, 60, "control");
			break;
		default:
			break;
		}
		// 重置滚动条
		new MyScrollPane().reserScroll(MyJframe.panelIndex);

	}

}