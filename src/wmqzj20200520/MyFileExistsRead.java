package wmqzj20200520;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import sun.awt.shell.ShellFolder;

/**
 * ��ʼ������button���� MyFileExists�����ļ���ȡ
 * 
 * @author WmqZj
 */
public class MyFileExistsRead {
	/**
	 * �����ļ����ڣ����ȡ���ݲ�д���ļ�
	 */
	String specialWebSite = "WebSite";
	String specialSpecial = "Special";

	public void fileExists() {
		System.out.print(111);
		for (int i = 0; i < MyJframe.panelArray.size(); i++) {
			for (int j = 0; j < MyJframe.panelComAmount.get(i); j++) {
				// iΪ�������,jΪÿ�������������
				String tempOne = new MyDataIniReadAndWrite().getAll(i, (j + 1));
				// 0Ϊ·�� 1Ϊ�ļ���
				String[] tempTwo = tempOne.split("###");
				File file = new File(tempTwo[0]);
				// ��������վ�����������ʶ
				if (tempTwo.length == 3) {
					// ˵������վ
					if (tempTwo[2].equals(specialWebSite)) {
//						JButton buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/add.png"));
						JButton buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/websiteT.png"));
						new MyDialogAddMyself().setButton(buttonTemp, 70, 60);
						setButton(buttonTemp, tempTwo, i);
					}
					// ˵��������
					else if (tempTwo[2].equals(specialSpecial)) {
						JButton buttonTemp = null;
						switch (tempTwo[1]) {
						case "ע���(�༭ע���)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/regedit.png"));
							break;
						case "˯��(��Ϣ���~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/sleep.png"));
							break;
						case "������(������)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/calc.png"));
							break;
						case "����(��������Ŷ~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/reboot.png"));
							break;
						case "�ػ�(����ػ�Ŷ~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/shutdown.png"));
							break;
						case "��ͼ��(�������»��»���~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/draw.png"));
							break;
						case "�ҵĵ���(���ҵĵ���)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/computer.png"));
							break;
						case "д�ְ�(�ǼǱʼǰ�~)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/write.png"));
							break;
						case "�������(������ĵ���)":
							buttonTemp = new JButton(tempTwo[1], new ImageIcon("images/control.png"));
							break;
						default:
							break;
						}
						setButton(buttonTemp, tempTwo, i);
					}

				} else {
					// �ļ�������������Ϊnull.png ����ͼ��
					if (file.exists() == false) {
						// �����ֱ�Ϊͼ��,�ļ���,·��,��Ӧ��panel
						new MyCreateMySelfButton().createMyButton(new ImageIcon("images/null.png"), tempTwo[1],
								tempTwo[0], i, false, null);
					} else {

						try {
							new MyCreateMySelfButton().createMyButton(
									new ImageIcon(ShellFolder.getShellFolder(file).getIcon(true)), tempTwo[1],
									tempTwo[0], i, false, null);
						} catch (FileNotFoundException e) {
							// �����Ӧ��ɾ��ԭ���Ļ���,����û��дȫ
							JOptionPane.showMessageDialog(MyJframe.jFrame, "����,����������!");
							e.printStackTrace();
						}

					}
				}
			}

			MyJframe.jFrame.validate();
		}
	}

	/**
	 * ����ָ��Ҫ��ť
	 * 
	 * @param button  -Ҫ������button
	 * @param tempTwo -�����ı�
	 * @param indexP  -��ǰpanel���
	 */
	public void setButton(JButton button, String[] tempTwo, int indexP) {
		String temp = "<html><strong><font size=4>" + tempTwo[1] + "</font> </strong><br>" + tempTwo[0] + "<br></html>";
		new MyDialogAddMyself().setButton(button, 70, 60);
		button.setToolTipText(temp);
		// ����϶�������
		button.addMouseMotionListener(MyCreateMySelfButton.mousedrag);
		button.addMouseListener(new MyButtonRightAndDragFile());
		button.addActionListener(new ActionListener() {
			// ��������ҳ
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = ((JButton) e.getSource()).getToolTipText();
				// �ָ��temp[0]Ϊnull,temp[1]�ļ�������,,temp[2]Ϊ·��
				String[] temp = filePath.split("(<[^\\>]+> *)+");
				// ִ�д��ļ�
				String cmd = "rundll32 url.dll,FileProtocolHandler " + temp[2];
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		MyJframe.panelArray.get(indexP).add(button);

	}

}
