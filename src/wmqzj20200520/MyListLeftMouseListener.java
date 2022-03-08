package wmqzj20200520;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * ��߰�ɫ���������MouseListener������ MyList��߰�ɫ���������MouseListener������
 * 
 * @author WmqZj
 */

public class MyListLeftMouseListener implements MouseListener {
	/** �˵��� */
	JPopupMenu popupMenu;
	/** �˵���ɾ�� */
	JMenuItem itemDelete;
	/** �˵������ */
	JMenuItem itemAdd;
	/** �˵��������� */
	JMenuItem itemReName;

	/**
	 * ��ʼ���Ҽ���Ŀ
	 */
	public MyListLeftMouseListener() {
		// �����ڴ�
		popupMenu = new JPopupMenu();
		itemDelete = new JMenuItem("ɾ��", new ImageIcon("images/delete.png"));
		itemAdd = new JMenuItem("���", new ImageIcon("images/add.png"));
		itemReName = new JMenuItem("������", new ImageIcon("images/rename.png"));
		// ��Ӽ�����
		itemDelete.addActionListener(new MyListRightActionListener());
		itemAdd.addActionListener(new MyListRightActionListener());
		itemReName.addActionListener(new MyListRightActionListener());
		// ������
		popupMenu.add(itemDelete);
		popupMenu.add(itemAdd);
		popupMenu.add(itemReName);
	}

	/**
	 * չʾ�Ҽ��˵���������ʾλ��
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// ��������Ҽ�
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			// չʾ�Ҽ��˵���������ʾλ��
			popupMenu.show(MyJframe.list, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
