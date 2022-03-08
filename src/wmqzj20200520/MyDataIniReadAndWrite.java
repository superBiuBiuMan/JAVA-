package wmqzj20200520;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

/**
 * 配置文件 MyDataIni数据文件写入与读取
 * 
 * @author WmqZj
 */
public class MyDataIniReadAndWrite {
	File filePath;
	Wini ini;

	/**
	 * 初始化配置
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
	 * 获取当前panel的数量
	 * 
	 * @param index -panel编号
	 * @param jl    -对应的数量
	 * @return
	 */
	public String getAll(int index, int jl) {
		String temp = ini.get(String.valueOf(index), String.valueOf(jl));
		return temp;
	}

	/**
	 * 写入数据在对应的panel编号下面
	 * 
	 * @param amount -为panel编号
	 * @param temp   -当前panelButton的数量
	 */
	public void write(int amount, String temp) {
		// 头文件,数量,文件名
		ini.put(String.valueOf(MyJframe.panelIndex), String.valueOf(amount), temp);
		try {
			ini.store();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 移除整个panel对应的记录,因为删除了左边白色的
	 * 
	 * @param panelIndex -当前panel
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
	 * 改变按钮在文件中的位置,因为拖动了按钮交换了位置
	 * 
	 * @param panelIndex -要改变的panel编号
	 * @param a          -按钮a号位置
	 * @param b          -按钮b位置
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
	 * 重新写入MyDataIni的值 Section部分(重写写入最顶层panel编号)
	 * 
	 * @param panelIndex -重新写入的panel编号
	 */
	public void changNumber(int panelIndex) {
		for (String sectionName : ini.keySet()) {
			// 从Section中获取节名称
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
	 * 按钮互换位置
	 * 
	 * @param a -按钮a的位置
	 * @param b -按钮b的位置
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
