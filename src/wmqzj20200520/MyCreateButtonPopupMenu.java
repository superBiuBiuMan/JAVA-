package wmqzj20200520;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 创建按钮右键菜单 MyCreatePopupMenu创建右键菜单
 * 
 * @author WmqZj
 */
public class MyCreateButtonPopupMenu {
	/** 按钮右键菜单 */
	public static JPopupMenu popupmenu;
	/** 删除图标 */
	JMenuItem poItemDelete;
	/** 打开所在位置 */
	JMenuItem poItemOpenPath;

	/**
	 * 创建右键菜单并判断是否为网站
	 * 
	 * @param web -是否为特殊标记的按钮
	 */
	public MyCreateButtonPopupMenu(boolean web) {
		popupmenu = new JPopupMenu();
		poItemDelete = new JMenuItem("删除图标", new ImageIcon("images/deleteicon.png"));
		poItemOpenPath = new JMenuItem("打开所在位置", new ImageIcon("images/position.png"));
		if (web) {
			// 如果为真,则禁用打开所在位置
			poItemOpenPath.setEnabled(false);
		}
		poItemDelete.addActionListener(new MyPopupMenuOfButtonRightActionListener());
		poItemOpenPath.addActionListener(new MyPopupMenuOfButtonRightActionListener());
		popupmenu.add(poItemDelete);
		popupmenu.add(poItemOpenPath);
	}
}
