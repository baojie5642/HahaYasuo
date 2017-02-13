package net.lingala.zip4j.hahaexample;

import java.io.File;


public class MakeRightFileDir {
	public static String bulidRightDir(String dir) {
		StringBuffer newDirStringBuffer = new StringBuffer();
		char a;
		char b;
		for (int i = 0; i < dir.length(); i++) {
			newDirStringBuffer.append(dir.charAt(i));
			if (newDirStringBuffer.length() > 1) {
				a = (char) dir.charAt(i);
				b = (char) dir.charAt(i - 1);
				if (a == b) {
					if (a == 92 && a == b) {
						newDirStringBuffer = newDirStringBuffer.deleteCharAt(newDirStringBuffer.length() - 1);
					} else if (a == 47 && a == b) {
						newDirStringBuffer = newDirStringBuffer.deleteCharAt(newDirStringBuffer.length() - 1);
					}
				} else if (a != b) {
					if (a == 47 && a != b && b != 47 && b != 92) {
						newDirStringBuffer = newDirStringBuffer.deleteCharAt(newDirStringBuffer.length() - 1);
						newDirStringBuffer.append(File.separator);//"\\"
					} else if (a == 47 && a != b && b == 92) {
						newDirStringBuffer = newDirStringBuffer.deleteCharAt(newDirStringBuffer.length() - 1);
					} else if (a == 92 && a != b && b != 47 && b != 92) {
						newDirStringBuffer = newDirStringBuffer.deleteCharAt(newDirStringBuffer.length() - 1);
						newDirStringBuffer.append(File.separator);//"\\"
					} else if (a == 92 && a != b && b == 47) {
						newDirStringBuffer = newDirStringBuffer.deleteCharAt(newDirStringBuffer.length() - 1);
					}
				}
			}
		}
		return newDirStringBuffer.toString();
	}
	public static void main(String args[]) {
		System.out.println("简单例子：如下");
		
		String badDir="D:\\\\A\\\\D\\d";
		System.out.println("没有利用replaceAll()作用之前的badDir："+badDir);
		badDir=badDir.replaceAll("\\\\", "/");
		System.out.println("利用replaceAll()作用之后的badDir:"+badDir);
		boolean signal = true;
		String dirString2 = "D:\\A\\BB\\\\CCC\\\\DDDD\\EEEEE\\\\F\\g";
		String dirString4 = "D:////////A\\\\BB//////////CCC\\\\\\\\DDDD/\\EEEEE//\\\\dfg\\///ddfgg\\\\\\\\///asdf\\\\/////////ghjk.zip";
		String dirString3 = "D:\\.cuteinfo\\space\\\\14742__1B36B73F-11CB-B3F9-05EA-2DB7921F0B3F_nbybTest";
		String dirString="./a/d\\n/m/df.zip";
		System.out.println("想要解析的字符串，dirString: "+dirString);
		StringBuffer newDirStringBuffer0 = new StringBuffer();
		StringBuffer newDirStringBuffer1 = new StringBuffer();
		String[] dirDepartStrings;
		dirDepartStrings = dirString.split("\\\\");
		/*********** 方法 （一） **********************/
		for (int i = 0; i < dirDepartStrings.length; i++) {
			if ("".equalsIgnoreCase(dirDepartStrings[i]) || " ".equalsIgnoreCase(dirDepartStrings[i]))
				continue;
			newDirStringBuffer0.append(dirDepartStrings[i]);
			newDirStringBuffer0.append("\\");
		}
		newDirStringBuffer0 = newDirStringBuffer0.deleteCharAt(newDirStringBuffer0.length() - 1);
		System.out.println("方法一输出："+newDirStringBuffer0.toString());
		/*************** 方法 （二） ********************************/
		char a;
		char b;
		for (int i = 0; i < dirString.length(); i++) {
			newDirStringBuffer1.append(dirString.charAt(i));
			if (newDirStringBuffer1.length() > 1) {
				a = (char) dirString.charAt(i);
				b = (char) dirString.charAt(i - 1);
				if (a == b) {
					if (a == 92 && a == b) {
						newDirStringBuffer1 = newDirStringBuffer1.deleteCharAt(newDirStringBuffer1.length() - 1);
					} else if (a == 47 && a == b) {
						newDirStringBuffer1 = newDirStringBuffer1.deleteCharAt(newDirStringBuffer1.length() - 1);
					}
				} else if (a != b) {
					if (a == 47 && a != b && b != 47 && b != 92) {
						newDirStringBuffer1 = newDirStringBuffer1.deleteCharAt(newDirStringBuffer1.length() - 1);
						newDirStringBuffer1.append(File.separator);//"\\"
					} else if (a == 47 && a != b && b == 92) {
						newDirStringBuffer1 = newDirStringBuffer1.deleteCharAt(newDirStringBuffer1.length() - 1);
					} else if (a == 92 && a != b && b != 47 && b != 92) {
						newDirStringBuffer1 = newDirStringBuffer1.deleteCharAt(newDirStringBuffer1.length() - 1);
						newDirStringBuffer1.append(File.separator);//"\\"
					} else if (a == 92 && a != b && b == 47) {
						newDirStringBuffer1 = newDirStringBuffer1.deleteCharAt(newDirStringBuffer1.length() - 1);
					}
				}
			}
		}
		System.out.println("方法二直接输出："+newDirStringBuffer1.toString());
		System.out.println("方法二封装成方法输出："+bulidRightDir(dirString));
	}

}
