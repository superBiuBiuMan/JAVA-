package wmqzj20200520;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 左边白色东东的鼠标MouseListener监视器 MyList左边白色东东的鼠标MouseListener监视器
 * 
 * @author WmqZj
 */

public class MyListLeftMouseListener implements MouseListener {
	/** 菜单栏 */
	JPopupMenu popupMenu;
	/** 菜单栏删除 */
	JMenuItem itemDelete;
	/** 菜单栏添加 */
	JMenuItem itemAdd;
	/** 菜单栏重命名 */
	JMenuItem itemReName;

	/**
	 * 初始化右键项目
	 */
	public MyListLeftMouseListener() {
		// 分配内存
		popupMenu = new JPopupMenu();
		itemDelete = new JMenuItem("删除", new ImageIcon("images/delete.png"));
		itemAdd = new JMenuItem("添加", new ImageIcon("images/add.png"));
		itemReName = new JMenuItem("重命名", new ImageIcon("images/rename.png"));
		// 添加监视器
		itemDelete.addActionListener(new MyListRightActionListener());
		itemAdd.addActionListener(new MyListRightActionListener());
		itemReName.addActionListener(new MyListRightActionListener());
		// 添加组件
		popupMenu.add(itemDelete);
		popupMenu.add(itemAdd);
		popupMenu.add(itemReName);
	}

	/**
	 * 展示右键菜单并设置显示位置
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// 如果按下右键
		if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			// 展示右键菜单并设置显示位置
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
