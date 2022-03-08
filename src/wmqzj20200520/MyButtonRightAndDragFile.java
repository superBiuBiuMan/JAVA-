package wmqzj20200520;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * ��ť����ʾ�Ҽ��˵����ļ��϶�ʵ��
 * 
 * @author WmqZj
 */
public class MyButtonRightAndDragFile implements MouseListener {
	/** �ļ��϶�ʵ��֮��¼�İ�ť */
	public static JButton one;

	/**
	 * ��굥���¼�
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			// Ĭ��Ϊ�رյ�
			boolean web = false;
			JButton test = (JButton) e.getSource();
			boolean site = test.getToolTipText().split("(<[^\\\\>]+> *)+")[2].matches("[a-zA-z]+://[^\\s]*");

			if (site) {
				// ������һ����վ
				web = true;
			}
			// �����Ҽ�
			new MyCreateButtonPopupMenu(web);
			// �����Ҽ������
			MyJframe.jFrame.add(MyCreateButtonPopupMenu.popupmenu);
			// ����ɾ����������ݲ���,�����ж�����һ���������Ҽ�
			new MyDeleteComponent(((JButton) e.getSource()));
			// �������ļ�Ŀ¼�����ݲ���,�����ж��� ��һ���������Ҽ�
			new MyButtonRightOfOpenFilePath(((JButton) e.getSource()));
			// ������ʾλ��
			MyCreateButtonPopupMenu.popupmenu.show((JButton) e.getSource(), e.getX(), e.getY());
			web = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * �ļ��϶�ʵ��1
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
			// �Ƴ����еİ�ť
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
	 * �ļ��϶�ʵ��2
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (MyMouseDrag.selected == true) {
			one = ((JButton) e.getSource());
		}
//		// �����밴ť�ڣ���ɫ������
//		((JButton) e.getSource()).setBorderPainted(true);
//		((JButton) e.getSource()).setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
//		((JButton) e.getSource()).setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));

	}

	/**
	 * �ļ��϶�ʵ��3
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		one = null;
//		// ����Ƴ���ť����ɫ�����ʧ
//		((JButton) e.getSource()).setBorderPainted(false);
	}

}
