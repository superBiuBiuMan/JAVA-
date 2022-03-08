package wmqzj20200520;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * 按钮上显示右键菜单和文件拖动实现
 * 
 * @author WmqZj
 */
public class MyButtonRightAndDragFile implements MouseListener {
	/** 文件拖动实现之记录的按钮 */
	public static JButton one;

	/**
	 * 鼠标单击事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			// 默认为关闭的
			boolean web = false;
			JButton test = (JButton) e.getSource();
			boolean site = test.getToolTipText().split("(<[^\\\\>]+> *)+")[2].matches("[a-zA-z]+://[^\\s]*");

			if (site) {
				// 代表是一个网站
				web = true;
			}
			// 创建右键
			new MyCreateButtonPopupMenu(web);
			// 创建右键并添加
			MyJframe.jFrame.add(MyCreateButtonPopupMenu.popupmenu);
			// 创建删除组件并传递参数,方便判断是哪一个单击了右键
			new MyDeleteComponent(((JButton) e.getSource()));
			// 创建打开文件目录并传递参数,方便判断是 哪一个单击了右键
			new MyButtonRightOfOpenFilePath(((JButton) e.getSource()));
			// 设置显示位置
			MyCreateButtonPopupMenu.popupmenu.show((JButton) e.getSource(), e.getX(), e.getY());
			web = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * 文件拖动实现1
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (one != null && one != MyMouseDrag.two) {
			int aa = 0;
			int bb = 0;
			boolean t = false, j = false;
			Component[] items1 = MyJframe.panelArray.get(MyJframe.panelIndex).getComponents();
			for (int i = 0; i < items1.length; i++) {
				if (one == ((JButton) items1[i])) {
					aa = i;
					t = true;
					if (t && j) {
						break;
					}
				} else if (MyMouseDrag.two == ((JButton) items1[i])) {
					bb = i;
					j = true;
					if (t && j) {
						break;
					}
				}

			}
			new MyDataIniReadAndWrite().changNumber(String.valueOf(aa + 1), String.valueOf(bb + 1));
			// 移除所有的按钮
			MyJframe.panelArray.get(MyJframe.panelIndex).removeAll();

			items1[aa] = MyMouseDrag.two;
			items1[bb] = one;

			for (int i = 0; i < items1.length; i++) {
				MyJframe.panelArray.get(MyJframe.panelIndex).add(((JButton) items1[i]));
			}
			MyJframe.panelArray.get(MyJframe.panelIndex).validate();

			MyMouseDrag.selected = false;
			MyMouseDrag.two = null;
			one = new JButton();
		}

		else {
			MyMouseDrag.selected = false;
		}

	}

	/**
	 * 文件拖动实现2
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (MyMouseDrag.selected == true) {
			one = ((JButton) e.getSource());
		}
//		// 鼠标进入按钮内，黑色框框出现
//		((JButton) e.getSource()).setBorderPainted(true);
//		((JButton) e.getSource()).setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
//		((JButton) e.getSource()).setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));

	}

	/**
	 * 文件拖动实现3
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		one = null;
//		// 鼠标移除按钮，黑色框框消失
//		((JButton) e.getSource()).setBorderPainted(false);
	}

}
