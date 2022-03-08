package wmqzj20200520;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sun.awt.shell.ShellFolder;

/**
 * 自定义对话框 对话框包括一个label、一个文本框和2个按钮。
 * @author WmqZj
 */
public class MyDialogAddMyself extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	/** 文本框，用于输入字符串 */
	JTextField fileAndWebSitePath;
	JTextField fileAndWebStieName;
	/** “确定”按钮 */
	JButton button1;
	/** 取消按钮 */
	JButton button2;
	/** 功能选择判断,真-网站输入,假-自定义输入 */
	boolean choice;

	/**
	 * 
	 * @param prentFrame -父类窗口
	 * @param title      -标题
	 * @param choice     -为真代表自定义路径输入，为假代表添加网站
	 */
	public MyDialogAddMyself(JFrame prentFrame, String title, boolean choice) {
		// 调用父类的构造函数，
		// 第三个参数用false表示允许激活其他窗体。为true表示不能够激活其他窗体
		super(prentFrame, title, true);
		this.choice = choice;
		// 容器
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		// 添加Label和输入文本框
		JLabel labe1 = new JLabel("路径:", new ImageIcon("images/path.png"), JLabel.LEFT);
		JLabel labe2 = new JLabel("标题:", new ImageIcon("images/title.png"), JLabel.LEFT);
		fileAndWebSitePath = new JTextField(30);
		fileAndWebStieName = new JTextField(30);
		// 图片为一把勾勾
		button1 = new JButton("", new ImageIcon("images/ensure_before.png"));
		// 图片为一把叉叉
		button2 = new JButton("", new ImageIcon("images/cancel_before.png"));

		// 设置按钮属性
		setButton(button1, 30, 30);
		setButton(button2, 30, 30);

		// hover图片监视器
		button1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button1.setIcon(new ImageIcon("images/ensure_before.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button1.setIcon(new ImageIcon("images/ensure_after.png"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		// 切换图片监视器
		button2.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button2.setIcon(new ImageIcon("images/cancel_before.png"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button2.setIcon(new ImageIcon("images/cancel_after.png"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		// 添加确认事件监视器
		fileAndWebSitePath.addActionListener(this);
		fileAndWebStieName.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		// 添加组件
		container.add(labe1);
		container.add(fileAndWebSitePath);
		container.add(labe2);
		container.add(fileAndWebStieName);
		container.add(button1);
		container.add(button2);

		// 调整对话框布局大小
		pack();
	}

	public MyDialogAddMyself() {
	}

	/**
	 * 事件处理
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// 如果按下了取消按钮
		if (event.getSource() == button2) {
			fileAndWebSitePath.setText("");
			fileAndWebStieName.setText("");

			setVisible(false);
		} else {
			// 真代表自定义路径输入
			if (choice) {
				// 获取用户输入的名称
				String name = fileAndWebStieName.getText();
				// 获取完整的绝对路径
				String temp1 = fileAndWebSitePath.getText() + "\\" + name;
				// 获取绝对路径
				File path = new File(temp1);
				// 判断webSiteName是否为空
				boolean isNullWebSiteName = temp1 != null && temp1.equals("") != true;
				// 判断fieldWebsite是否为空
				boolean isNullFieldWebSite = name != null && name.equals("") != true;
				// 为真时创建按钮
				if (isNullWebSiteName && isNullFieldWebSite) {
					String lnkEnd = ".lnk";
					// 单纯初始化
					String writeTemp = "";
					// 获取后缀名
					String getExtenEnd = name.substring(name.lastIndexOf("."), name.length());
					// 如果后缀名为lnk
					if (getExtenEnd.equals(lnkEnd)) {
						// 获取真实的图标路径必要的步骤
						try {
							MyGetReallyFilePath getReal = new MyGetReallyFilePath(new File(temp1));
							// 获取真实的图标路径
							String realFileName = getReal.getRealFilename();
							// 重新指定新的值
							path = new File(realFileName);
							// 重新指定新的值
							temp1 = realFileName;
						} catch (Exception e) {
							JOptionPane.showMessageDialog(this, "创建失败,请检查路径为名称是否正确!注意,名称需要添加后缀名!");
							e.printStackTrace();
						}

					}
					// 删除在图标中显示的后缀名
					String newFileName = name.substring(0, name.lastIndexOf("."));
					writeTemp = temp1 + "###" + newFileName;
					try {
						new MyCreateMySelfButton().createMyButton(
								new ImageIcon(ShellFolder.getShellFolder(path).getIcon(true)), newFileName, temp1,
								MyJframe.panelIndex, true, writeTemp);
						fileAndWebSitePath.setText("");
						fileAndWebStieName.setText("");
						setVisible(false);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(this, "创建失败,请检查路径为名称是否正确!注意,名称需要添加后缀名!");
						e.printStackTrace();
					}

				} else {
					// 捕捉到异常,弹出警告信息框
					JOptionPane.showMessageDialog(this, "请输入完整的信息!");
				}
			}
			// 为假代表网站输入
			else {
				// 获取网站名称
				String webSitePath = fileAndWebSitePath.getText();
				// 获取用户输入的网站名称
				String webName = fileAndWebStieName.getText();
				// 网站开头样式1
				String webBeginOne = "https://";
				// 网站开头样式2
				String webBeginTwo = "http://";
				// 判断webSiteName是否为空
				boolean isNullWebSiteName = webSitePath != null && webSitePath.equals("") != true;
				// 判断fieldWebsite是否为空
				boolean isNullFieldWebSite = webName != null && webName.equals("") != true;
				if (isNullWebSiteName && isNullFieldWebSite) {
					if (webSitePath.startsWith(webBeginOne) || webSitePath.startsWith(webBeginTwo)) {
						// 符合要求网站,创建
						create(webSitePath, webName);

					} else {
						// 不符合要求网站,添加http进去后在进行创建
						String newWebSitePath = "https://" + webSitePath;
						create(newWebSitePath, webName);
					}

				}
				// 捕捉到异常,弹出警告信息框
				else {
					JOptionPane.showMessageDialog(this, "请输入完整的信息!");

				}
			}
		}
	}

	//
	/**
	 * 设置button的属性
	 * 
	 * @param button -要设置的button
	 * @param width  -要设置的宽
	 * @param height -要设置的高
	 */
	public void setButton(JButton button, int width, int height) {
		button.setVerticalTextPosition(JButton.BOTTOM);
		button.setHorizontalTextPosition(JButton.CENTER);
		// 设置是否绘制边框
//		button.setBorderPainted(false);
//		button.setContentAreaFilled(false);
//		button.setFocusPainted(false);
		// 设置按钮的宽
		button.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * 创建按钮并重新计算滚动条
	 * 
	 * @param webSitePath -网站路径
	 * @param webName     -网站名称
	 */
	public void create(String webSitePath, String webName) {
		JButton buttonTemp = new JButton(webName, new ImageIcon("images/websiteT.png"));
		// 设置button属性
		setButton(buttonTemp, 70, 60);
		String temp = "<html><strong><font size=4>" + webName + "</font> </strong><br>" + webSitePath + "<br></html>";
		buttonTemp.setToolTipText(temp);
		// 重置滚动条
		new MyScrollPane().reserScroll(MyJframe.panelIndex);
		// 添加拖动监视器
		buttonTemp.addMouseMotionListener(MyCreateMySelfButton.mousedrag);
		buttonTemp.addActionListener(new ActionListener() {
			// 单击打开网页
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// 分割后temp[0]为null,temp[1]文件的名称,,temp[2]为路径
				String[] temp = filePath.split("(<[^\\>]+> *)+");
				// 执行打开文件
				String cmd = "rundll32 url.dll,FileProtocolHandler " + temp[2];
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonTemp.addMouseListener(new MyButtonRightAndDragFile());

		// 添加到窗体
		MyJframe.panelArray.get(MyJframe.panelIndex).add(buttonTemp);
		int temp1 = MyJframe.panelComAmount.get(MyJframe.panelIndex) + 1;
		MyJframe.panelComAmount.set(MyJframe.panelIndex, temp1);
		// 写入文件 路径+分割符号#+文件名+分割+编号 // 绝对路径+分割符号+文件名+特殊命名标记
		String writeTemp = webSitePath + "###" + webName + "###" + "WebSite";
		new MyDataIniReadAndWrite().write(MyJframe.panelComAmount.get(MyJframe.panelIndex), writeTemp);
		new MyIniReadAndWrite().changAmount(MyJframe.panelComAmount.get(MyJframe.panelIndex));
		MyJframe.jFrame.validate();
		setVisible(false);
		// 创建好后清空原有的内容并设置为隐藏
		fileAndWebSitePath.setText("");
		fileAndWebStieName.setText("");
		setVisible(false);
	}
}