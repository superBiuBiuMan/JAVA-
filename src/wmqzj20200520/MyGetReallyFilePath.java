package wmqzj20200520;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 获取快捷方式真实的文件路径
 * 
 * @author Administration
 *
 */
public class MyGetReallyFilePath {

	public MyGetReallyFilePath(File f) throws Exception {
		parse(f);
	}

	private boolean isDir;

	public boolean isDirectory() {
		return isDir;
	}

	private String realFile;

	public String getRealFilename() {
		return realFile;
	}

	public void parse(File f) throws Exception {
		// read the entire file into a byte buffer
		FileInputStream fin = new FileInputStream(f);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] buff = new byte[256];
		while (true) {
			int n = fin.read(buff);
			if (n == -1) {
				break;
			}
			bout.write(buff, 0, n);
		}
		fin.close();
		byte[] link = bout.toByteArray();

		// get the flags byte
		byte flags = link[0x14];

		// get the file attributes byte
		final int fileAttsOffset = 0x18;
		byte fileatts = link[fileAttsOffset];
		byte isDirMask = (byte) 0x10;
		if ((fileatts & isDirMask) > 0) {
			isDir = true;
		} else {
			isDir = false;
		}

		// if the shell settings are present, skip them
		final int shellOffset = 0x4c;
		int shellLen = 0;
		if ((flags & 0x1) > 0) {
			// the plus 2 accounts for the length marker itself
			shellLen = bytes2short(link, shellOffset) + 2;
		}

		// get to the file settings
		int fileStart = 0x4c + shellLen;

		// get the local volume and local system values
		int localSysOff = link[fileStart + 0x10] + fileStart;
		realFile = getNullDelimitedString(link, localSysOff);
	}

	static String getNullDelimitedString(byte[] bytes, int off) {
		int len = 0;
		// count bytes until the null character (0)
		while (true) {
			if (bytes[off + len] == 0) {
				break;
			}
			len++;
		}
		return new String(bytes, off, len);
	}

	/**
	 * convert two bytes into a short note, this is little endian because it's for
	 * an Intel only OS.
	 * 
	 * @param bytes
	 * @param off
	 * @return
	 */
	static int bytes2short(byte[] bytes, int off) {
		return (bytes[off] & 0xFF) | ((bytes[off + 1] & 0xFF) << 8);
	}

	/*
	 * static int norm(byte b) { if(b < 0) { b+=128; } return b; } static int
	 * bytes2int(byte[] arr, int off) { int b1 = norm(arr[off]); int b2 =
	 * norm(arr[off+1]); int b3 = norm(arr[off+2]); int b4 = norm(arr[off+3]); int
	 * val = ( (b1 << 0) | (b2 << 8) | (b3 << 16) | (b4 << 24) ); //p("bytes2int: "
	 * + b1 + " " + b2 + " " + b3 + " " + b4); return val; }
	 * 
	 * 
	 * static NumberFormat num_format = new DecimalFormat(" 000;-000");
	 * 
	 * public static String padd(String str, int len) { while(str.length() < len) {
	 * str = " " + str; } return str; }
	 * 
	 * public static void pw(byte[] arr, int off) { StringBuffer top = new
	 * StringBuffer(); StringBuffer mid = new StringBuffer(); StringBuffer bot = new
	 * StringBuffer(); top.append("--"); mid.append("  "); bot.append("  ");
	 * 
	 * for(int i=0; i<16; i++) { int val = arr[off+i]; String str =
	 * Integer.toHexString(off+i); top.append(padd(str,5));
	 * mid.append(padd(""+val,5)); if(val < 0) { val += 128; } if(val >= ' ' && val
	 * <= '~') { str = "" + (char)val; } else { str = Integer.toHexString(val); }
	 * str = padd(str,5); bot.append(str); if(i%4==3) { top.append("    ");
	 * mid.append("    "); bot.append("    "); } } p(top.toString());
	 * p(mid.toString()); p(bot.toString()); } public static void pbits(byte bt) {
	 * p("byte = " + bt + " " + Integer.toHexString(bt) + " " +
	 * Integer.toBinaryString(bt)); }
	 */

}