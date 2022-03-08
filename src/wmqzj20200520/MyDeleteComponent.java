package wmqzj20200520;

import java.awt.Component;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 删除按钮 
 * MyDeleteComponent删除按钮
 * @author WmqZj
 */
public class MyDeleteComponent {
	static JButton ChButton;

	/**
	 * 获取要删除的按钮
	 * 
	 * @param button -要删除的按钮
	 */
	public MyDeleteComponent(JButton button) {
		ChButton = button;
	}

	/**
	 * 删除按钮并执行一系列，比如说删除文件，重新布局等
	 */
	public void deleteComponent() {
		MyJframe.panelArray.get(MyJframe.panelIndex).remove(ChButton);
//		MyJFrame窗口.panelArray.get(MyJFrame窗口.panelIndex).repaint();
		// 删除文件记录,然后 重写数量
		deleteFile();
		// 刷新面板,重新布局
		restore();
		// 重新设置滚动条
		new MyScrollPane().reserScroll(MyJframe.panelIndex);
		;
//		new MyScrollPane滚动条().reserScroll(MyJFrame窗口.panelIndex);
//		MyJFrame窗口.jFrame.validate();
	}

	public MyDeleteComponent() {
	}

	/**
	 * 删除文件记录,然后 重写ini里面每个panel对于的数量
	 */
	public void deleteFile() {
		// 用来判断是否是路径还是目录
		File file;
		new MyDataIniReadAndWrite().remove(MyJframe.panelIndex);
		Component[] items1 = MyJframe.panelArray.get(MyJframe.panelIndex).getComponents();
		// 特殊标记的文本数据
		String[] special = { "控制面板(管理你的电脑)", "计算器(计算器)", "睡眠(休息会吧~)", "写字板(记记笔记吧~)", "我的电脑(打开我的电脑)", "重启(立马重启哦~)",
				"关机(立马关机哦~)", "注册表(编辑注册表)", "画图板(放松以下画下画吧~)" };
		for (int i = 0; i < items1.length; i++) {
			// 指定要求分割,0为空,1为名称,2位路径名
			String[] splitAfter = ((JButton) items1[i]).getToolTipText().split("(<[^\\>]+> *)+");
			file = new File(splitAfter[2]);
			// 当为目录或者文件数据的时候，而不是特殊图标的时候
			if (file.isFile() || file.isDirectory()) {
				new MyDataIniReadAndWrite().write((i + 1), (splitAfter[2] + "###" + splitAfter[1]));
			} else {
				// 判断是否是Special图标
				if (isHave(special, splitAfter[1])) {
					new MyDataIniReadAndWrite().write((i + 1),
							(splitAfter[2] + "###" + splitAfter[1] + "###" + "Special"));
				}
				// 判断是否是WebSite图标
				else if (splitAfter[2].matches("[a-zA-z]+://[^\\s]*")) {
					new MyDataIniReadAndWrite().write((i + 1),
							(splitAfter[2] + "###" + splitAfter[1] + "###" + "Website"));
				}
				// 如果都不是，那么就是不存在的文件路径或者文件，才会返回false，所以我们重新写入数据
				else {
					// 设置不存在的图标为null
					((JButton) items1[i]).setIcon(new ImageIcon("images/null.png"));
					new MyDataIniReadAndWrite().write((i + 1), (splitAfter[2] + "###" + splitAfter[1]));
				}
			}
		}
		// 向文件写入新的窗口数据
		new MyIniReadAndWrite().changAmount(items1.length);
		// 重新写入每一个panel的button数量
		MyJframe.panelComAmount.set(MyJframe.panelIndex, items1.length);

	}

	/**
	 * 刷新面板,重新布局
	 */
	public void restore() {
		// 获取当前panel的所有组件
		Component[] items1 = MyJframe.panelArray.get(MyJframe.panelIndex).getComponents();
		// 移除
		MyJframe.panelArray.get(MyJframe.panelIndex).removeAll();
		for (int i = 0; i < items1.length; i++) {

			MyJframe.panelArray.get(MyJframe.panelIndex).add(((JButton) items1[i]));
		}
		// 刷新
		MyJframe.scrollPane.setViewportView(MyJframe.panelArray.get(MyJframe.panelIndex));
	}

	/**
	 * 计算字符串s是否包含在special当中
	 * 
	 * @param special -检索的字符串数组
	 * @param checkStr       -要检查的字符串
	 * @return -真则是包含 假则是不包括
	 */
	public boolean isHave(String[] special, String checkStr) {
		for (int i = 0; i < special.length; i++) {
			if (special[i].equals(checkStr)) {
				return true;
			}
		}
		return false;

	}
}
