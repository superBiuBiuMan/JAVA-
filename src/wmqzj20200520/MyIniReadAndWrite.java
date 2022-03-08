package wmqzj20200520;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

/**
 * ��ȡд�������� MyIni��ȡд��������
 * 
 * @author WmqZj
 */
public class MyIniReadAndWrite {
	File filePath;
	Wini ini;
	/** 0Ϊ���״�����,1Ϊ�״����� */
	int i = 0;

	/**
	 * ��ʼ������
	 */
	public MyIniReadAndWrite() {
		filePath = new File("C:\\Myini.ini");
		if (filePath.exists() == false) {
			try {
				filePath.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			i = 1;
		}
		try {
			ini = new Wini(filePath);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ������
	 */
	public void iniIni() {
		// �״�����,д�벢��������
		if (i == 1) {
			ini.put("MyMainOption", "�Ƿ񿪻�����", 0);
			ini.put("MyMainOption", "��������", 2);
			// ��������ΪҪд��Ľ�����,����,ֵ
			ini.put("MyMainOption", "����ѡ�д���", 0);
			ini.put("Other", "MyItem", "[�������, ϵͳ����]");
			// ��ǰ����0��button����Ϊ0 ��ʼֵ
			ini.put("Amount", "0", "0");
			// ��ǰ����1��button����Ϊ0 ��ʼֵ
			ini.put("Amount", "1", "0");
			// ÿһ��ռ�ݵ�button�ĸ��� ��ʼֵ
			ini.put("JFrame", "eachLine", 4);
			// ��ʵ��allLine+1 ���ڿ����ɵĿ����ɵ����� ��ʼֵ
			ini.put("JFrame", "allLine", 6);
			// ���ڿ����ɵ�button������ ��ʼֵ
			ini.put("JFrame", "allAmount", 28);
			// JFrame���ڵĳ�ʼX���� ��ʼֵ
			ini.put("JFrame", "X_coordinate", 400);
			// JFrame���ڵĳ�ʼY���� ��ʼֵ
			ini.put("JFrame", "Y_coordinate", 250);
			// JFrame���ڵĳ�ʼ��� ��ʼֵ
			ini.put("JFrame", "Width", 465);
			// JFrame���ڵĳ�ʼ�߶� ��ʼֵ
			ini.put("JFrame", "Height", 525);
			getIni();
			try {
				ini.store();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// ���״�����,��������
		else {
			getIni();
		}
	}

	/**
	 * ��ȡMyini������
	 */
	public void getIni() {
		String name = ini.get("Other", "MyItem");
		// ɾ����������
		name = name.replace("[", "");
		// ɾ����������
		name = name.replace("]", "");
		String[] temp = name.split(",");
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].trim();
			// ��ӵ���߰�ɫ����
			MyJframe.listJl.add(temp[i]);
		}
		// ��ȡ����ѡ�д���
		MyJframe.panelIndex = Integer.parseInt(ini.get("MyMainOption", "����ѡ�д���"));
		/* ��ȡ */
		// ÿһ��ռ�ݵ�button�ĸ���
		MyJframe.eachLine = Integer.parseInt(ini.get("JFrame", "eachLine"));
		// ��ʵ��allLine+1 �����ɵ�����
		MyJframe.allLine = Integer.parseInt(ini.get("JFrame", "allLine"));
		// ���ڿ����ɵ�button������
		MyJframe.allAmount = Integer.parseInt(ini.get("JFrame", "allAmount"));
		// JFrame���ڵ�X����
		MyJframe.X_coordinate = Integer.parseInt(ini.get("JFrame", "X_coordinate"));
		// JFrame���ڵ�Y����
		MyJframe.Y_coordinate = Integer.parseInt(ini.get("JFrame", "Y_coordinate"));
		// JFrame���ڵĿ��
		MyJframe.Width = Integer.parseInt(ini.get("JFrame", "Width"));
		// JFrame���ڸ߶�;
		MyJframe.Height = Integer.parseInt(ini.get("JFrame", "Height"));

	}

	/**
	 * ��߱�ɫ���ѡ�д�������ļ�
	 * 
	 * @param temp -����ӵ��ļ���
	 */
	public void changeMyItem(String temp) {
		List<String> name = new ArrayList<String>();
		for (int i = 0; i < MyJframe.listJl.size(); i++) {
			name.add(MyJframe.listJl.get(i));
		}
		ini.put("Other", "MyItem", name);
		ini.put("Amount", String.valueOf((MyJframe.panelArray.size() - 1)), 0);
		ini.put("MyMainOption", "��������", MyJframe.panelArray.size());

		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * ɾ����������ѡ����޸��ļ�����
	 * 
	 * @param choice -�Ƿ�ɾ�������б� ,Ϊ��,��ɾ����������б���ѡ����Ǹ��б�
	 */
	public void changeMyItem(boolean choice) {
		List<String> name = new ArrayList<String>();
		for (int i = 0; i < MyJframe.listJl.size(); i++) {
			name.add(MyJframe.listJl.get(i));
		}
		ini.put("Other", "MyItem", name);
		ini.put("MyMainOption", "��������", MyJframe.panelArray.size());
		// choiceΪ��,ɾ����������б�
		if (choice == true) {
			ini.remove("Amount");
			// ����д��Amount��ֵ
			for (int i = 0; i < MyJframe.panelComAmount.size(); i++) {
				ini.put("Amount", String.valueOf(i), String.valueOf(MyJframe.panelComAmount.get(i)));
			}
		}

		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * �ı䵱ǰѡ���panel���
	 * 
	 * @param choice
	 */
	public void changeSelected(boolean choice) {
		if (choice == true) {
			// Ҫд��Ľ�����,����,ֵ
			ini.put("MyMainOption", "����ѡ�д���", (MyJframe.panelIndex - 1));
		} else {
			// Ҫд��Ľ�����,����,ֵ
			ini.put("MyMainOption", "����ѡ�д���", MyJframe.panelIndex);
		}
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�������������������MyJFrame.panelComAmount
	 */
	public void getInit() {
		for (int i = 0; i < MyJframe.panelArray.size(); i++) {
			String sl = ini.get("Amount", String.valueOf(i));
			MyJframe.panelComAmount.set(i, Integer.parseInt(sl));
		}

	}

	/**
	 * �����ڵİ�ť���������仯,����д��
	 * 
	 * @param amount -����д���������ֵ
	 */
	public void changAmount(int amount) {

		ini.put("Amount", String.valueOf(MyJframe.panelIndex), String.valueOf(amount));
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * �����ڵ�λ��,��С�����仯,��дд���ļ�
	 */
	public void changeJframe() {
		// ÿһ��ռ�ݵ�button�ĸ��� ��ʼֵ
		ini.put("JFrame", "eachLine", MyJframe.eachLine);
		// ��ʵ��allLine+1 �����ɵ����� ��ʼֵ
		ini.put("JFrame", "allLine", MyJframe.allLine);
		// ���ڿ����ɵ�button������ ��ʼֵ
		ini.put("JFrame", "allAmount", MyJframe.allAmount);
		// JFrame���ڵĳ�ʼ��� ��ʼֵ
		ini.put("JFrame", "Width", MyJframe.Width);
		// JFrame���ڵĳ�ʼ�߶� ��ʼֵ
		ini.put("JFrame", "Height", MyJframe.Height);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/**
	 * ���ı䴰�ڵ�����ʱ������д���ļ�
	 */
	public void changeJframePosition() {
		// JFrame���ڵĳ�ʼX���� ��ʼֵ
		ini.put("JFrame", "X_coordinate", MyJframe.X_coordinate);
		// JFrame���ڵĳ�ʼY���� ��ʼֵ
		ini.put("JFrame", "Y_coordinate", MyJframe.Y_coordinate);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
