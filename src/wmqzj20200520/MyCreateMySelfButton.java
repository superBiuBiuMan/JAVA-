package wmqzj20200520;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 创建指定要求按钮
 * 
 */
public class MyCreateMySelfButton {
	/** 当遇到特殊情况,重新命名 */
	String newName = null;
	/** 空字符 */
	String nullName = "";
	/** 特殊图标的长度 */
	int specialLength = 3;
	public static MyMouseDrag mousedrag = new MyMouseDrag();

	/**
	 * 创建相应的图标
	 * 
	 * @param imageIcon -图标
	 * 
	 * @param name      -名称
	 * @param filePath  -文件路径
	 * 
	 * @param indexP    -对应的panel
	 * 
	 * @param writeFile -是否写入数据到文件
	 * @param writeTemp -写入数据到文件的字符串
	 */
	public void createMyButton(ImageIcon imageIcon, String name, String filePath, int indexP, boolean writeFile,
			String writeTemp) {
		JButton myButton = new JButton(name, imageIcon);

		// 设置按钮属性
		myButton.setVerticalTextPosition(JButton.BOTTOM);
		myButton.setHorizontalTextPosition(JButton.CENTER);
		// 显示文字
		myButton.setMargin(new Insets(0, 0, 0, 0));
		// 设置是否绘制边框

//		myButton.setBorderPainted(false);
		// 除去默认的背景填充
//		myButton.setContentAreaFilled(false);
		// 除去焦点的框
//		myButton.setFocusPainted(false);
		// 设置按钮的宽度
		myButton.setPreferredSize(new Dimension(70, 60));

		// 以HTML的新式对tooltiptext进行样式修改
		String temp = "<html><strong><font size=4>" + name + "</font> </strong><br>" + filePath + "<br></html>";
		// 如果不为硬盘符号
		if (filePath.length() != specialLength) {
			// (a为名字,b为路径) 设置提示
			myButton.setToolTipText(temp);
		}
		// 如果为硬盘符号
		else if (filePath.length() == specialLength) {
			myButton.setToolTipText(temp);
		}

		// 添加鼠标单击的监视器
		myButton.addActionListener(new ActionListener() {

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
		// 添加拖动监视器
		myButton.addMouseMotionListener(mousedrag);
		// 添加右键监视器
		myButton.addMouseListener(new MyButtonRightAndDragFile());
		MyJframe.panelArray.get(indexP).add(myButton);
		// 重置滚动条
		new MyScrollPane().reserScroll(indexP);
		// 添加按钮进去指定面板
		MyJframe.panelArray.get(indexP).add(myButton);
		MyJframe.panelArray.get(indexP).validate();
		// 真 写入数据,假 不写入数据
		if (writeFile) {
			int temp1 = MyJframe.panelComAmount.get(MyJframe.panelIndex) + 1;
			MyJframe.panelComAmount.set(MyJframe.panelIndex, temp1);
			// 写入文件 路径+分割符号#+文件名+分割+编号 // 路径+分割符号#+文件名
			new MyDataIniReadAndWrite().write(MyJframe.panelComAmount.get(MyJframe.panelIndex), writeTemp);
			new MyIniReadAndWrite().changAmount(MyJframe.panelComAmount.get(MyJframe.panelIndex));
		}
		MyJframe.jFrame.validate();
	}

}
