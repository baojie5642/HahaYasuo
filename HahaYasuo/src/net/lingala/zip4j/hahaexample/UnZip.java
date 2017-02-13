package net.lingala.zip4j.hahaexample;

import java.io.File;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;


public class UnZip {

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
	private static void unZipAll0(String sourceZipFile,String dirFile){
		if(sourceZipFile!=null){
			sourceZipFile=MakeRightFileDir.bulidRightDir(sourceZipFile);
			dirFile=MakeRightFileDir.bulidRightDir(dirFile);
		}
		try {  
		    // Initiate ZipFile object with the path/name of the zip file.  
		    ZipFile zipFile = new ZipFile(sourceZipFile);  
		    // Extracts all files to the path specified  
		    zipFile.extractAll(dirFile);        
		} catch (ZipException e) { 
		    e.printStackTrace();  
		    return;
		}  
	}
	private static void unZipAll1(String sourceZipFile,String dirFile){
		ZipFile zipFile2 = null;
		try {
			zipFile2 = new ZipFile("c:\\ZipTest\\test2.zip");
		} catch (ZipException e) {
			e.printStackTrace();
		}        
		
		List fileHeaderList = null;
		try {
			fileHeaderList = zipFile2.getFileHeaders();
		} catch (ZipException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < fileHeaderList.size(); i++) {
		    FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);
		    try {
				zipFile2.extractFile(fileHeader, "c:\\ZipTest2\\");
			} catch (ZipException e) {
				e.printStackTrace();
			}
		}
	}
	public static void unzip(String sourceZipFileDir, String desFileDir) {
		if (sourceZipFileDir != null) {
			sourceZipFileDir = bulidRightDir(sourceZipFileDir);
			desFileDir = bulidRightDir(desFileDir);
		} else {
		
			return;
		}
		try {
			ZipFile zipFile = new ZipFile(sourceZipFileDir);
			zipFile.extractAll(desFileDir);
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}
	}
	private static void chackFileInZip(String sourceZipFile){
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(sourceZipFile);
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}

		List fileHeaderList = null;
		try {
			fileHeaderList = zipFile.getFileHeaders();
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}
		for (int i = 0; i < fileHeaderList.size(); i++) {
		    FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);
		    System.out.println("****File Details for: " + fileHeader.getFileName() + "*****");
		    System.out.println("Name: " + fileHeader.getFileName());
		    System.out.println("Compressed Size: " + fileHeader.getCompressedSize());
		    System.out.println("Uncompressed Size: " + fileHeader.getUncompressedSize());
		    System.out.println("CRC: " + fileHeader.getCrc32());
		    System.out.println("************************************************************");
		}
	}
		
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		//chackFileInZip("D:\\CuteZipTest\\lyk1.zip");
		UnZip unZip=new UnZip();
		unZip.chackFileInZip("D:\\LXDes\\windows764.zip");
		//unZip.unZipAll0("D:\\LXDes\\windows764.zip", "D:\\LXDesUnZip");
	}
}
