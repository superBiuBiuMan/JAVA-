package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import sun.awt.shell.ShellFolder;

/**
 * 初始化窗口button数据 MyFileExists数据文件读取
 * 
 * @author WmqZj
 */
public class MyFileExistsRead {
	/**
	 * 数据文件存在，则读取数据并写入文件
	 */
	String specialWebSite = "WebSite";
	String specialSpecial = "Special";

	public void fileExists() {
		System.out.print(111);
		for (int i = 0; i < MyJframe.panelArray.size(); i++) {
			for (int j = 0; j < MyJframe.panelComAmount.get(i); j++) {
				// i为面板数量,j为每个面板的组件数量
				String tempOne = new MyDataIniReadAndWrite().getAll(i, (j + 1));
				// 0为路径 1为文件名
				String[] tempTwo = tempOne.split("###");
				File file = new File(tempTwo[0]);
				// 代表是网站或者是特殊标识
				if (tempTwo.length == 3) {
					// 说明是网站
					if (tempTwo[2].equals(specialWebSite)) {
//						JButton buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/add.png"));
						JButton buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/websiteT.png"));
						new MyDialogAddMyself().setButton(buttonTemp, 70, 60);
						setButton(buttonTemp, tempTwo, i);
					}
					// 说明是特殊
					else if (tempTwo[2].equals(specialSpecial)) {
						JButton buttonTemp = null;
						switch (tempTwo[1]) {
						case "注册表(编辑注册表)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/regedit.png"));
							break;
						case "睡眠(休息会吧~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/sleep.png"));
							break;
						case "计算器(计算器)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/calc.png"));
							break;
						case "重启(立马重启哦~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/reboot.png"));
							break;
						case "关机(立马关机哦~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/shutdown.png"));
							break;
						case "画图板(放松以下画下画吧~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/draw.png"));
							break;
						case "我的电脑(打开我的电脑)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/computer.png"));
							break;
						case "写字板(记记笔记吧~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/write.png"));
							break;
						case "控制面板(管理你的电脑)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/control.png"));
							break;
						default:
							break;
						}
						setButton(buttonTemp, tempTwo, i);
					}

				} else {
					// 文件不存在则设置为null.png 即空图标
					if (file.exists() == false) {
						// 参数分别为图标,文件名,路径,对应的panel
						new MyCreateMySelfButton().createMyButton(new ImageIcon("images/null.png"), tempTwo[1],
								tempTwo[0], i, false, null);
					} else {

						try {
							new MyCreateMySelfButton().createMyButton(
									new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), tempTwo[1],
									tempTwo[0], i, false, null);
						} catch (FileNotFoundException e) {
							// 出错后应该删除原来的缓存,这里没有写全
							JOptionPane.showMessageDialog(MyJframe.jFrame, "出错,请重启程序!");
							e.printStackTrace();
						}

					}
				}
			}

			MyJframe.jFrame.validate();
		}
	}

	/**
	 * 创建指定要求按钮
	 * 
	 * @param button  -要创建的button
	 * @param tempTwo -数据文本
	 * @param indexP  -当前panel编号
	 */
	public void setButton(JButton button, String[] tempTwo, int indexP) {
		String temp = "<html><strong><font size=4>" + tempTwo[1] + "</font> </strong><br>" + tempTwo[0] + "<br></html>";
		new MyDialogAddMyself().setButton(button, 70, 60);
		button.setToolTipText(temp);
		// 添加拖动监视器
		button.addMouseMotionListener(MyCreateMySelfButton.mousedrag);
		button.addMouseListener(new MyButtonRightAndDragFile());
		button.addActionListener(new ActionListener() {
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
		MyJframe.panelArray.get(indexP).add(button);

	}

}
