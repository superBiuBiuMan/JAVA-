package wmqzj20200520;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * ������ť�Ҽ��˵� MyCreatePopupMenu�����Ҽ��˵�
 * 
 * @author WmqZj
 */
public class MyCreateButtonPopupMenu {
	/** ��ť�Ҽ��˵� */
	public static JPopupMenu popupmenu;
	/** ɾ��ͼ�� */
	JMenuItem poItemDelete;
	/** ������λ�� */
	JMenuItem poItemOpenPath;

	/**
	 * �����Ҽ��˵����ж��Ƿ�Ϊ��վ
	 * 
	 * @param web -�Ƿ�Ϊ�����ǵİ�ť
	 */
	public MyCreateButtonPopupMenu(boolean web) {
		popupmenu = new JPopupMenu();
		poItemDelete = new JMenuItem("ɾ��ͼ��", new ImageIcon("images/deleteicon.png"));
		poItemOpenPath = new JMenuItem("������λ��", new ImageIcon("images/position.png"));
		if (web) {
			// ���Ϊ��,����ô�����λ��
			poItemOpenPath.setEnabled(false);
		}
		poItemDelete.addActionListener(new MyPopupMenuOfButtonRightActionListener());
		poItemOpenPath.addActionListener(new MyPopupMenuOfButtonRightActionListener());
		popupmenu.add(poItemDelete);
		popupmenu.add(poItemOpenPath);
	}
}
