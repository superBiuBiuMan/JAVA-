package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * ����Ǵ����Ǹ���ߵİ�ɫ�Ķ����� MyListRightListener��ߵİ�ɫ�Ķ����ļ�����
 * 
 * @author WmqZj
 */
public class MyListRightActionListener implements ActionListener {
	private static final Pattern PATTERN = Pattern.compile("[,��]");
	String selectDelete = "ɾ��";
	String selectAdd = "���";
	String selectReName = "������";

	@Override
	public void actionPerformed(ActionEvent e) {
		// ѡ����ɾ��
		if (e.getActionCommand().equals(selectDelete)) {
			if (MyJframe.panelArray.size() - 1 <= 0) {
				JOptionPane.showMessageDialog(MyJframe.jFrame, "���һ����Ŷ,����ɾ��");
			} else {
				// ���嵯�������ʽ
				Object[] options = { "ȷ��", "ȡ��" };
				int select = JOptionPane.showOptionDialog(MyJframe.jFrame, "��ȷ��ɾ����", "��ʾ��",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						new ImageIcon("images/cancel.png"), options, options[1]);
				// �û�ѡ����ȷ�ϰ�ť
				if (select == 0) {
					// �Ƴ�����ı���¼
					MyJframe.listJl.remove(MyJframe.panelIndex);
					// �Ƴ��ı���Ӧ��panel
					MyJframe.panelComAmount.remove(MyJframe.panelIndex);
					// panelArray�Ƴ�����
					MyJframe.panelArray.remove(MyJframe.panelIndex);
					// ����������ߵ��ı�����
					MyJframe.list.setListData(MyJframe.listJl);
					// ����д������
					new MyIniReadAndWrite().changeMyItem(true);
					new MyIniReadAndWrite().changeSelected(true);
					new MyDataIniReadAndWrite().remove(MyJframe.panelIndex);
					new MyDataIniReadAndWrite().changNumber(MyJframe.panelIndex);
					MyJframe.panelIndex = MyJframe.panelIndex - 1;
					MyJframe.scrollPane.setViewportView(MyJframe.panelArray.get(MyJframe.panelIndex));
				}

			}
		}
		// ѡ���������Ŀ
		else if (e.getActionCommand().equals(selectAdd)) {
			addJlist();
		}
		// ѡ��������
		else if (e.getActionCommand().equals(selectReName)) {
			String newName = JOptionPane.showInputDialog(null, "������������");
			if (newName != null && newName.equals("") != true) {
				// Pattern pattern = Pattern.compile("[,��]");
				// Matcher matcher = pattern.matcher(name);
				// �Ƴ����õ���Ӣ�Ķ���,ɾ���пո�,��Ϊ��������������ļ����ȡ����
				String afterRemovName = PATTERN.matcher(newName).replaceAll("");
				MyJframe.listJl.set(MyJframe.panelIndex, afterRemovName);
				new MyIniReadAndWrite().changeMyItem(false);
			}
		}

	}

	/**
	 * ��������Ŀ
	 */
	public void addJlist() {
		String name = JOptionPane.showInputDialog(MyJframe.jFrame, "����������");
		if (name != null && name.equals("") != true) {
			// �Ƿ��ظ���Ĭ��Ϊfalse
			boolean isRepeat = false;
			// �Ƴ����õ���Ӣ�Ķ���,��Ϊ��������������ļ����ȡ����
			// Pattern pattern = Pattern.compile("[,��]");
			// Matcher matcher = pattern.matcher(name);
			String temp = PATTERN.matcher(name).replaceAll("");
			// ɾ���пո�
			for (int i = 0; i < MyJframe.listJl.size(); i++) {
				if (temp.equals(MyJframe.listJl.get(i))) {
					JOptionPane.showMessageDialog(MyJframe.jFrame, "�ظ���Ŷ~������һ���µ����ư�");
					// ���µ���
					addJlist();
					isRepeat = true;
					break;
				}
			}
			// ���û���ظ������
			if (!isRepeat) {
				MyJframe.listJl.add(temp);
				// ����������ߵ�����
				MyJframe.list.setListData(MyJframe.listJl);
				// �������
//				new MyCreatePanel����panel��ӽ�����().create();
				new MyJframe().createPanel();
				new MyIniReadAndWrite().changeMyItem(name);

			}

		}
	}
}
