package wmqzj20200520;

import java.awt.Dimension;

/**
 * ʹ�ô��ڻ������������Ӷ����ֹ����� MyScrollPane������
 * 
 * @author WmqZj
 */

public class MyScrollPane {

	/**
	 * ����������ʱ���ʼ��������
	 */
	public void iniAll() {
		for (int i = 0; i < MyJframe.panelComAmount.size(); i++) {
			// ��ȡ��ǰ���ڵ�button����;
			int temp = MyJframe.panelComAmount.get(i);
			if (temp > MyJframe.allAmount) {
				if (temp % MyJframe.eachLine == 0) {
					// ����ڼ���
					temp = temp / MyJframe.eachLine;
					temp = temp - MyJframe.allLine;
				} else {
					// ����ڼ���
					temp = (temp / MyJframe.eachLine) + 1;
					temp = temp - MyJframe.allLine;
				}
				// ���¶��崰�ڵ�Dimension,�Ա��й�������ʾ
				MyJframe.panelArray.get(i).setPreferredSize(new Dimension(351, (800 + 69 * temp)));
			}

		}
	}

	/**
	 * �����ڷ����ı������������������ʱ�����ù�����
	 * 
	 * @param indexP -Ҫ���ù�������panel
	 */
	public void reserScroll(int indexP) {
		// ��ȡ��ǰ���ڵ�button����
		int tempbb = MyJframe.panelComAmount.get(indexP) + 1;
		// ��ȡ����
		if (tempbb > MyJframe.allAmount) {
			if (tempbb % MyJframe.eachLine == 0) {
				// ����ڼ���
				tempbb = tempbb / MyJframe.eachLine;
				tempbb = tempbb - MyJframe.allLine;
			} else {
				// ����ڼ���
				tempbb = (tempbb / MyJframe.eachLine) + 1;
				tempbb = tempbb - MyJframe.allLine;
			}
			// ���¶��崰�ڵ�Dimension,�Ա��й�������ʾ
			MyJframe.panelArray.get(indexP).setPreferredSize(new Dimension(351, (800 + 69 * tempbb)));
		}
	}
}
