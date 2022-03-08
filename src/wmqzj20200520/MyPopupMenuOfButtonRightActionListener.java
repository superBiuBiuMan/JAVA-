package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 在按钮上面按下鼠标右键出现的选项 MyPopupMenuActionListener按钮上面按下鼠标右键出现的选项的监视器
 * 
 * @author WmqZj
 */
public class MyPopupMenuOfButtonRightActionListener implements ActionListener {
	String delectIcon = "删除图标";
	String openFileLocation = "打开所在位置";

	@Override
	public void actionPerformed(ActionEvent e) {
		// 删除按钮
		if (e.getActionCommand().equals(delectIcon)) {
			new MyDeleteComponent().deleteComponent();

		}
		// 打开所在位置
		else if (e.getActionCommand().equals(openFileLocation)) {
			MyButtonRightOfOpenFilePath.openFileLocation();
		}
	}

}
