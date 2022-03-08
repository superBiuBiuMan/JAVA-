package wmqzj20200520;

/**
 * �����ַ������Դ�Сд
 * 
 * @author Administration
 *
 */
public class MyFindStringIgnoreCase {

	/**
	 * ����ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ�������ִ�С��
	 * 
	 * @param subject   �������ַ�����
	 * @param search    Ҫ���ҵ����ַ�����
	 * @param fromIndex ��ʼ���ҵ�����λ�á���ֵû�����ƣ������Ϊ����������Ϊ 0 ��Ч��ͬ���������������ַ�����
	 *                  ��������ڴ��ַ����ĳ��ȣ����������ڴ��ַ������ȵ�Ч����ͬ������ -1��
	 * @return ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ��
	 */
	public int ignoreCaseIndexOf(String subject, String search, int fromIndex) {

		// ���������ַ�����������ַ���Ϊ��ʱ���׳���ָ���쳣��
		if (subject == null || search == null) {
			throw new NullPointerException("����Ĳ���Ϊ��");
		}

		fromIndex = fromIndex < 0 ? 0 : fromIndex;
		String nullString = "";
		if (search.equals(nullString)) {
			return fromIndex >= subject.length() ? subject.length() : fromIndex;
		}

		int index1 = fromIndex;
		int index2 = 0;

		char c1;
		char c2;

		loop1: while (true) {

			if (index1 < subject.length()) {
				c1 = subject.charAt(index1);
				c2 = search.charAt(index2);

			} else {
				break loop1;
			}

			while (true) {
				if (isEqual(c1, c2)) {

					if (index1 < subject.length() - 1 && index2 < search.length() - 1) {

						c1 = subject.charAt(++index1);
						c2 = search.charAt(++index2);
					} else if (index2 == search.length() - 1) {

						return fromIndex;
					} else {

						break loop1;
					}

				} else {

					index2 = 0;
					break;
				}
			}
			// ���²������ַ�����λ��
			index1 = ++fromIndex;
		}

		return -1;
	}

	/**
	 * �ж������ַ��Ƿ���ȡ�
	 * 
	 * @param c1 �ַ�1
	 * @param c2 �ַ�2
	 * @return ����Ӣ����ĸ�������ִ�Сд�����true�����ȷ���false�� �����������֣���ȷ���true�����ȷ���false��
	 */
	private boolean isEqual(char c1, char c2) {
		// ��ĸСд ��ĸ��д
		Boolean result = ((97 <= c1 && c1 <= 122) || (65 <= c1 && c1 <= 90))
				&& ((97 <= c2 && c2 <= 122) || (65 <= c2 && c2 <= 90)) && ((c1 - c2 == 32) || (c2 - c1 == 32));
		if (result) {
			return true;
		} else if (c1 == c2) {
			return true;
		}

		return false;
	}
}
