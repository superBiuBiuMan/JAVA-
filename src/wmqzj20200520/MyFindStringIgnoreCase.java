package wmqzj20200520;

/**
 * 查找字符串忽略大小写
 * 
 * @author Administration
 *
 */
public class MyFindStringIgnoreCase {

	/**
	 * 返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始，不区分大小。
	 * 
	 * @param subject   被查找字符串。
	 * @param search    要查找的子字符串。
	 * @param fromIndex 开始查找的索引位置。其值没有限制，如果它为负，则与它为 0 的效果同样：将查找整个字符串。
	 *                  如果它大于此字符串的长度，则与它等于此字符串长度的效果相同：返回 -1。
	 * @return 指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始。
	 */
	public int ignoreCaseIndexOf(String subject, String search, int fromIndex) {

		// 当被查找字符串或查找子字符串为空时，抛出空指针异常。
		if (subject == null || search == null) {
			throw new NullPointerException("输入的参数为空");
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
			// 重新查找子字符串的位置
			index1 = ++fromIndex;
		}

		return -1;
	}

	/**
	 * 判断两个字符是否相等。
	 * 
	 * @param c1 字符1
	 * @param c2 字符2
	 * @return 若是英文字母，不区分大小写，相等true，不等返回false； 若不是则区分，相等返回true，不等返回false。
	 */
	private boolean isEqual(char c1, char c2) {
		// 字母小写 字母大写
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
