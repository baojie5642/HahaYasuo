package net.lingala.zip4j.hahaexample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;


public class DoZip {
	
	@SuppressWarnings("unused")
	private static void zipAll0(String sourceFile, String dirZipFile) {
		File file = new File(sourceFile);
		if (!file.exists()) {
			try {
				throw new IOException("文件不存在: " + sourceFile);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		if (sourceFile != null) {
			sourceFile = MakeRightFileDir.bulidRightDir(sourceFile);
			dirZipFile = MakeRightFileDir.bulidRightDir(dirZipFile);
		}
		try {
			ZipFile zipFile = new ZipFile(dirZipFile);
			String folderToAdd = sourceFile;
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zipFile.addFolder(folderToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void zipAll1(String sourceFile, String desZipFile) {
		try {
			ZipFile zipFile = new ZipFile(desZipFile);
			ArrayList filesToAdd = new ArrayList();
			for (int i = 0; i < 5; i++) {
				File file = new File(sourceFile + File.separator + "CENTER" + i);
				if (file.exists()) {
					filesToAdd.add(file);
				}
			}
			filesToAdd.add(sourceFile + File.separator + "context_info.properties");
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	public static void zip(String sourceFileDir, String desZipFileDir) {
		File file = new File(sourceFileDir);
		if (!file.exists()) {
			try {
				throw new IOException("文件不存在: " + sourceFileDir);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		if (sourceFileDir != null) {
			sourceFileDir = bulidRightDir(sourceFileDir);
			desZipFileDir = bulidRightDir(desZipFileDir);
		} else {
			return;
		}
		for (int i = 0; i < 5; i++) {
			file = new File(sourceFileDir + File.separator + "CENTER" + i);
			if (file.exists() && file.isDirectory()) {
				AddFolderToZip(sourceFileDir + File.separator + "CENTER" + i, desZipFileDir);
			}
		}
		file = new File(sourceFileDir + File.separator + "context_info.properties");
		if (file.exists()) {
			AddFileToZip(sourceFileDir + File.separator + "context_info.properties", desZipFileDir);
		}
	}
	
	public static void AddFolderToZip(String folderDir, String desZipDir) {
		try {
			ZipFile zipFile = new ZipFile(desZipDir);
			String folderToAdd = folderDir;
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zipFile.addFolder(folderToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void AddFileToZip(String fileDir, String desZipDir) {
		try {
			ZipFile zipFile = new ZipFile(desZipDir);
			ArrayList filesToAdd = new ArrayList();
			filesToAdd.add(new File(fileDir));
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}
	}

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
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		DoZip zip = new DoZip();
		// zip.zipAll("D:\\CuteZipTest\\17914__AD71E297-1CDA-EF15-BC2B-FEB3B17A2983_test11g\\",
		// "D:\\CuteZipTest\\lyk1.zip");
		zip.AddFolderToZip("D:\\CuteZipTest\\lyk", "D:\\CuteZipTest\\lyk2.zip");

	}
}
