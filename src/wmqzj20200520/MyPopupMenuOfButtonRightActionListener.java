package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * �ڰ�ť���水������Ҽ����ֵ�ѡ�� MyPopupMenuActionListener��ť���水������Ҽ����ֵ�ѡ��ļ�����
 * 
 * @author WmqZj
 */
public class MyPopupMenuOfButtonRightActionListener implements ActionListener {
	String delectIcon = "ɾ��ͼ��";
	String openFileLocation = "������λ��";

	@Override
	public void actionPerformed(ActionEvent e) {
		// ɾ����ť
		if (e.getActionCommand().equals(delectIcon)) {
			new MyDeleteComponent().deleteComponent();

		}
		// ������λ��
		else if (e.getActionCommand().equals(openFileLocation)) {
			MyButtonRightOfOpenFilePath.openFileLocation();
		}
	}

}
