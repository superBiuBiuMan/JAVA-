package wmqzj20200520;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 * �����ļ� MyDataIni�����ļ�д�����ȡ
 * 
 * @author WmqZj
 */
public class MyDataIniReadAndWrite {
	File filePath;
	Wini ini;

	/**
	 * ��ʼ������
	 */
	public MyDataIniReadAndWrite() {
		filePath = new File("C:\\MyData.ini");
		if (filePath.exists() == false) {
			try {
				filePath.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	 * ��ȡ��ǰpanel������
	 * 
	 * @param index -panel���
	 * @param jl    -��Ӧ������
	 * @return
	 */
	public String getAll(int index, int jl) {
		String temp = ini.get(String.valueOf(index), String.valueOf(jl));
		return temp;
	}

	/**
	 * д�������ڶ�Ӧ��panel�������
	 * 
	 * @param amount -Ϊpanel���
	 * @param temp   -��ǰpanelButton������
	 */
	public void write(int amount, String temp) {
		// ͷ�ļ�,����,�ļ���
		ini.put(String.valueOf(MyJframe.panelIndex), String.valueOf(amount), temp);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * �Ƴ�����panel��Ӧ�ļ�¼,��Ϊɾ������߰�ɫ��
	 * 
	 * @param panelIndex -��ǰpanel
	 */
	public void remove(int panelIndex) {
		ini.remove(String.valueOf(panelIndex));
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * �ı䰴ť���ļ��е�λ��,��Ϊ�϶��˰�ť������λ��
	 * 
	 * @param panelIndex -Ҫ�ı��panel���
	 * @param a          -��ťa��λ��
	 * @param b          -��ťbλ��
	 */
	public void changPositon(int panelIndex, int a, int b) {
		String aPath = ini.get(String.valueOf(panelIndex), String.valueOf(a));
		String bPath = ini.get(String.valueOf(panelIndex), String.valueOf(b));
		ini.put(String.valueOf(panelIndex), String.valueOf(a), bPath);
		ini.put(String.valueOf(panelIndex), String.valueOf(b), aPath);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * ����д��MyDataIni��ֵ Section����(��дд�����panel���)
	 * 
	 * @param panelIndex -����д���panel���
	 */
	public void changNumber(int panelIndex) {
		for (String sectionName : ini.keySet()) {
			// ��Section�л�ȡ������
			int number = Integer.parseInt(sectionName);
			if (number > panelIndex) {
				Section section = ini.get(sectionName);
				ArrayList<String> temp = new ArrayList<String>();
				for (String optionKey : section.keySet()) {
					temp.add(section.get(optionKey));
				}
				ini.remove(String.valueOf(number));
				for (int i = 0; i < temp.size(); i++) {
					ini.put(String.valueOf(number - 1), String.valueOf((i + 1)), temp.get(i));
				}
				try {
					ini.store();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

		}
	}

	/**
	 * ��ť����λ��
	 * 
	 * @param a -��ťa��λ��
	 * @param b -��ťb��λ��
	 */
	public void changNumber(String a, String b) {
		String index = String.valueOf(MyJframe.panelIndex);
		String temp1 = ini.get(index, a);
		String temp2 = ini.get(index, b);
		ini.put(index, a, temp2);
		ini.put(index, b, temp1);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
