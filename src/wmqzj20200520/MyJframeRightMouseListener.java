package wmqzj20200520;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JFrame������Ҽ��������˵� MyJFrameRightMouseListener�����Ҽ���ļ�����
 * 
 * @author WmqZj
 */
public class MyJframeRightMouseListener implements ActionListener {
	String selectAddFile = "����ļ�";
	String selectAddWebSite = "�����վ";
	String selectAddOther = "�������";
	String selectAddMySelf = "�ֶ����";

	@Override
	public void actionPerformed(ActionEvent e) {
		int x = (MyJframe.jFrame.getWidth() - 210) / 2;
		int y = (MyJframe.jFrame.getHeight() - 190) / 2;
		Point topLeft = MyJframe.jFrame.getLocationOnScreen();
		// ����ļ�
		if (e.getActionCommand().equals(selectAddFile)) {
			new MyMenuSelectFile();
		}
		// �����վ
		else if (e.getActionCommand().equals(selectAddWebSite)) {
			// ���һ������Ϊfalse����Ϊ��վ����,ΪtrueΪ�Զ�������
			MyDialogAddMyself dialog = new MyDialogAddMyself(MyJframe.jFrame, "����д��Ϣ~", false);
			// ����Ϊ������ʾ
			dialog.setBounds(x + topLeft.x, y + topLeft.y, 210, 190);
			dialog.setVisible(true);
		}
		// �������
		else if (e.getActionCommand().equals(selectAddOther)) {
			new MyJframeRightAddOther();
		}
		// �ֶ����
		else if (e.getActionCommand().equals(selectAddMySelf)) {
			// ���һ������Ϊfalse����Ϊ��վ����,ΪtrueΪ�Զ�������
			MyDialogAddMyself dialog = new MyDialogAddMyself(MyJframe.jFrame, "����д��Ϣ~", true);
			// ����Ϊ������ʾ
			dialog.setBounds(x + topLeft.x, y + topLeft.y, 210, 190);
			dialog.setVisible(true);
		}

	}

}
