package wmqzj20200520;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * MyJFrameRight窗口的右键项
 * 
 * @author WmqZj
 */
public class MyJframeRightMenu implements MouseListener {
	JPopupMenu popupmenu;
	JMenuItem menuItemAddFile;
	JMenuItem menuItemAddOther;
	JMenuItem menuItemAddMyself;
	JMenuItem poItemAddWebsite;
	MyJframeRightMouseListener listener;

	/**
	 * 初始化右键项
	 *
	 */
	public MyJframeRightMenu() {
		listener = new MyJframeRightMouseListener();
		popupmenu = new JPopupMenu();
		menuItemAddFile = new JMenuItem("添加文件", new ImageIcon("images/addFile.png"));
		poItemAddWebsite = new JMenuItem("添加网站", new ImageIcon("images/addWebsite.png"));
		menuItemAddOther = new JMenuItem("添加其他", new ImageIcon("images/addFolders.png"));
		menuItemAddMyself = new JMenuItem("手动添加", new ImageIcon("images/addLink.png"));

		menuItemAddFile.addActionListener(listener);
		menuItemAddOther.addActionListener(listener);
		menuItemAddMyself.addActionListener(listener);
		poItemAddWebsite.addActionListener(listener);

		popupmenu.add(menuItemAddFile);
		popupmenu.add(menuItemAddMyself);
		popupmenu.add(menuItemAddOther);
		popupmenu.add(poItemAddWebsite);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 如果按下右键
		if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
			popupmenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
