package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * 这个是创建那个左边的白色的东东的 MyListRightListener左边的白色的东东的监视器
 * 
 * @author WmqZj
 */
public class MyListRightActionListener implements ActionListener {
	private static final Pattern PATTERN = Pattern.compile("[,，]");
	String selectDelete = "删除";
	String selectAdd = "添加";
	String selectReName = "重命名";

	@Override
	public void actionPerformed(ActionEvent e) {
		// 选择了删除
		if (e.getActionCommand().equals(selectDelete)) {
			if (MyJframe.panelArray.size() - 1 <= 0) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "最后一个了哦,不能删除");
			} else {
				// 定义弹出框的样式
				Object[] options = { "确认", "取消" };
				int select = JOptionPane.showOptionDialog(MyJframe.jFrame, "你确定删除吗？", "提示：",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						new ImageIcon("images/cancel.png"), options, options[1]);
				// 用户选择了确认按钮
				if (select == 0) {
					// 移除左边文本记录
					MyJframe.listJl.remove(MyJframe.panelIndex);
					// 移除文本对应的panel
					MyJframe.panelComAmount.remove(MyJframe.panelIndex);
					// panelArray移除数据
					MyJframe.panelArray.remove(MyJframe.panelIndex);
					// 重新设置左边的文本数据
					MyJframe.list.setListData(MyJframe.listJl);
					// 重新写入数据
					new MyIniReadAndWrite().changeMyItem(true);
					new MyIniReadAndWrite().changeSelected(true);
					new MyDataIniReadAndWrite().remove(MyJframe.panelIndex);
					new MyDataIniReadAndWrite().changNumber(MyJframe.panelIndex);
					MyJframe.panelIndex = MyJframe.panelIndex - 1;
					MyJframe.scrollPane.setViewportView(MyJframe.panelArray.get(MyJframe.panelIndex));
				}

			}
		}
		// 选择了添加项目
		else if (e.getActionCommand().equals(selectAdd)) {
			addJlist();
		}
		// 选择重命名
		else if (e.getActionCommand().equals(selectReName)) {
			String newName = JOptionPane.showInputDialog(null, "请输入新名称");
			if (newName != null && newName.equals("") != true) {
				// Pattern pattern = Pattern.compile("[,，]");
				// Matcher matcher = pattern.matcher(name);
				// 移除所用的中英文逗号,删所有空格,因为如果不这样子做文件会读取错误
				String afterRemovName = PATTERN.matcher(newName).replaceAll("");
				MyJframe.listJl.set(MyJframe.panelIndex, afterRemovName);
				new MyIniReadAndWrite().changeMyItem(false);
			}
		}

	}

	/**
	 * 左边添加项目
	 */
	public void addJlist() {
		String name = JOptionPane.showInputDialog(MyJframe.jFrame, "请输入名称");
		if (name != null && name.equals("") != true) {
			// 是否重复，默认为false
			boolean isRepeat = false;
			// 移除所用的中英文逗号,因为如果不这样子做文件会读取错误
			// Pattern pattern = Pattern.compile("[,，]");
			// Matcher matcher = pattern.matcher(name);
			String temp = PATTERN.matcher(name).replaceAll("");
			// 删所有空格
			for (int i = 0; i < MyJframe.listJl.size(); i++) {
				if (temp.equals(MyJframe.listJl.get(i))) {
					JOptionPane.showMessageDialog(MyJframe.jFrame, "重复了哦~请输入一个新的名称吧");
					// 重新调用
					addJlist();
					isRepeat = true;
					break;
				}
			}
			// 如果没有重复则添加
			if (!isRepeat) {
				MyJframe.listJl.add(temp);
				// 重新设置左边的数据
				MyJframe.list.setListData(MyJframe.listJl);
				// 创建面板
//				new MyCreatePanel创建panel添加进数组().create();
				new MyJframe().createPanel();
				new MyIniReadAndWrite().changeMyItem(name);

			}

		}
	}
}
