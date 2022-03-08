package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MyJframeMenuActionListener�˵�������
 * @author WmqZj
 * 
 */
public class MyJframeTopMenuActionListener implements ActionListener {
	/**
	 * ��굥��Menu���¼������ݵ���������һ��ִ����Ӧ�Ĵ���
	 */
	String selectFile = "ѡ���ļ�";
	String selectFindFile = "�����ļ�";
	String selectExit = "�˳�";

	@Override
	public void actionPerformed(ActionEvent e) {
		// ѡ���ļ��˵���
		if (e.getActionCommand().equals(selectFile)) {
			new MyMenuSelectFile();
		}
		// �����ļ��˵���
		else if (e.getActionCommand().equals(selectFindFile)) {
			new MyFindFile();

		} 
		//�˳�ѡ��
		else if (e.getActionCommand().equals(selectExit)) {
			System.exit(0);
		}
	}

}
