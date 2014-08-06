/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.general.io;


import java.io.*;
import java.util.*;
import java.nio.file.*;

import de.general.util.*;


/**
 *
 * @author knauth
 */
public class IOUtils
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	private static final String ILLEGAL_FILE_NAME_CHARACTERS = new String(
		new char[] { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' }
		);

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	public static void deleteDirectoryRecursivelyE(File dir)
	{
		if (!dir.isDirectory()) throw new RuntimeException("Not a directory: " + dir.getAbsolutePath());

		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				deleteDirectoryRecursivelyE(f);
			} else {
				if (!f.delete()) throw new RuntimeException("Failed to delete file: " + f.getAbsolutePath());
			}
		}
		if (!dir.delete()) throw new RuntimeException("Failed to delete directory: " + dir.getAbsolutePath());
	}

	public static Writer close(Writer w)
	{
		if (w != null) {
			try {
				w.close();
			} catch (Exception ee) {
			}
		}
		return null;
	}

	public static Reader close(Reader w)
	{
		if (w != null) {
			try {
				w.close();
			} catch (Exception ee) {
			}
		}
		return null;
	}

	public static OutputStream close(OutputStream w)
	{
		if (w != null) {
			try {
				w.close();
			} catch (Exception ee) {
			}
		}
		return null;
	}

	public static InputStream close(InputStream w)
	{
		if (w != null) {
			try {
				w.close();
			} catch (Exception ee) {
			}
		}
		return null;
	}

	public static ArrayList<String> loadFileAsList(String filePath)
		throws IOException
	{
		return loadFileAsList(new File(filePath));
	}

	public static ArrayList<String> loadFileAsList(File file)
		throws IOException
	{
		FileReader fr = null;
		try {
			ArrayList<String> lineList = new ArrayList<String>();

			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				lineList.add(line);
			}

			return lineList;
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static String loadFileAsText(String filePath)
		throws IOException
	{
		return loadFileAsText(new File(filePath));
	}

	public static String detectEncoding(File file)
		throws IOException
	{
		InputStream fin = null;
		try {
			fin = new FileInputStream(file);

			byte[] buffer = new byte[3];
			int bytesRead = fin.read(buffer, 0, 3);
			if (bytesRead >= 2) {
				if ((buffer[0] == -2) && (buffer[1] == -1)) return "UTF-16BE";
				if ((buffer[0] == -1) && (buffer[1] == -2)) return "UTF-16LE";
				if (bytesRead == 3) {
					if ((buffer[0] == (byte)239) && (buffer[1] == (byte)187) && (buffer[1] == (byte)191)) return "UTF-8";
				}
				XUtils.noop();
				return null;
			} else {
				return null;
			}

		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static String loadFileAsText(File file)
		throws IOException
	{
		InputStream fin = null;
		try {
			String encoding = detectEncoding(file);

			StringBuilder sb = new StringBuilder();
			fin = new FileInputStream(file);
			InputStreamReader isr;
			if (encoding == null) {
				isr = new InputStreamReader(fin);
			} else {
				isr = new InputStreamReader(fin, encoding);
			}

			char[] cbuf = new char[65536];
			while (true) {
				int charsRead = isr.read(cbuf);
				if (charsRead < 0) break;
				sb.append(cbuf, 0, charsRead);
			}

			return sb.toString();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static byte[] loadFileAsBinary(String filePath)
		throws IOException
	{
		return loadFileAsBinary(new File(filePath));
	}

	public static byte[] loadFileAsBinary(File file)
		throws IOException
	{
		FileInputStream fr = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			fr = new FileInputStream(file);
			long len = file.length();
			byte[] buffer = new byte[1024 * 256];
			while (len > 0) {
				int bytesRead = fr.read(buffer);
				if (bytesRead < 0) throw new IOException("Failed to read data!");
				if (bytesRead > 0) {
					baos.write(buffer, 0, bytesRead);
					len -= bytesRead;
				}
			}

			return baos.toByteArray();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static void saveFile(String textData, String filePath)
		throws IOException
	{
		saveFile(textData, new File(filePath));
	}

	public static void saveFile(String textData, File file)
		throws IOException
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(textData);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static void saveFile(ArrayList<String> lineList, String filePath)
		throws IOException
	{
		saveFile(lineList, new File(filePath));
	}

	public static void saveFile(String[] lineList, String filePath)
		throws IOException
	{
		saveFile(lineList, new File(filePath));
	}

	public static void saveFile(ArrayList<String> lineList, File file)
		throws IOException
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (String line : lineList) {
				bw.write(line);
				bw.write(TextUtils.CRLF);
			}

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception ee) {
				}
				fw = null;
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static void saveFile(String[] lineList, File file)
		throws IOException
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for (String line : lineList) {
				bw.write(line);
				bw.write(TextUtils.CRLF);
			}

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (Exception ee) {
				}
				fw = null;
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static String removeInvalidFileNameChars(String fileName)
	{
		StringBuilder sb = new StringBuilder();

		for (char c : fileName.toCharArray()) {
			if (ILLEGAL_FILE_NAME_CHARACTERS.indexOf(c) < 0) {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static void saveFile(byte[] data, File file) throws IOException
	{
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file, false);
			fout.write(data);
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (Exception ee) {
				}
			}
		}
	}

	public static void ensureDirExists(String dirPath) throws IOException
	{
		File dir = new File(dirPath);
		ensureDirExists(dir);
	}

	public static void ensureDirExists(File dir) throws IOException
	{
		if (dir.exists()) {
			if (!dir.isDirectory())
				throw new IOException("This is not a directory: " + dir);
			return;
		}
		try {
			dir.mkdirs();
		} catch (Exception ee) {
			throw new IOException("Failed to create directory: " + dir.getAbsolutePath(), ee);
		}
	}

	public static String addSeparatorChar(String path)
	{
		if (path.endsWith("" + File.separatorChar)) return path;
		return path + File.separatorChar;
	}

	public static String removeFileExtension(String path)
	{
		String dirPath;
		String fileName;
		int pos = XUtils.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
		if (pos >= 0) {
			dirPath = path.substring(0, pos + 1);
			fileName = path.substring(pos + 1);
		} else {
			fileName = path;
			dirPath = "";
		}

		String fileNameWithoutExt;
		String ext;
		pos = fileName.lastIndexOf('.');
		if (pos < 0) {
			fileNameWithoutExt = fileName;
			ext = null;
		} else {
			fileNameWithoutExt = fileName.substring(0, pos);
			ext = fileName.substring(pos);
		}

		return dirPath + fileNameWithoutExt;
	}

	public static String extractFileNameWithoutExtension(String path)
	{
		String fileName;
		int pos = XUtils.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
		if (pos >= 0) {
			fileName = path.substring(pos + 1);
		} else {
			fileName = path;
		}

		String fileNameWithoutExt;
		pos = fileName.lastIndexOf('.');
		if (pos < 0) {
			fileNameWithoutExt = fileName;
		} else {
			fileNameWithoutExt = fileName.substring(0, pos);
		}

		return fileNameWithoutExt;
	}

	public static String getFileExtension(String path)
	{
		String fileName;
		int pos = XUtils.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
		if (pos >= 0) {
			fileName = path.substring(pos + 1);
		} else {
			fileName = path;
		}

		pos = fileName.lastIndexOf('.');
		if (pos < 0) {
			return null;
		} else {
			return fileName.substring(pos + 1);
		}
	}

	public static String makeFileName(String fileName)
	{
		if (fileName == null) return null;

		StringBuilder sb = new StringBuilder();
		for (char c : fileName.toCharArray()) {
			if ("\\/:;<>|\"?".indexOf(c) >= 0) {
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static void copyFiles(File sourceDir, File destDir) throws IOException
	{
		for (File f : sourceDir.listFiles()) {
			copyFile(f, new File(destDir, f.getName()));
		}
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException
	{
		byte[] data = loadFileAsBinary(sourceFile);
		saveFile(data, destFile);
	}

	public static File copyFileToDirEx(File sourceFile, File destDir) throws IOException
	{
		if (!sourceFile.isFile()) throw new IOException("Source file is not a file!");
		if (!destDir.isDirectory()) throw new IOException("Destination is not a directory!");

		File destFile = new File(destDir, sourceFile.getName());
		if (destFile.exists()) {
			if ((destFile.length() == sourceFile.length())
				&& (destFile.lastModified() == sourceFile.lastModified())) return destFile;
		}

		IOUtils.copyFile(sourceFile, destFile);
		destFile.setLastModified(sourceFile.lastModified());

		return destFile;
	}

	public static File copyFileEx(File sourceFile, File destFile) throws IOException
	{
		if (!sourceFile.isFile()) throw new IOException("Source file is not a file!");
		if (destFile.isDirectory()) throw new IOException("Destination is a directory!");

		if (destFile.exists()) {
			if ((destFile.length() == sourceFile.length())
				&& (destFile.lastModified() == sourceFile.lastModified())) return destFile;
		}

		IOUtils.copyFile(sourceFile, destFile);
		destFile.setLastModified(sourceFile.lastModified());
		return destFile;
	}

	public static String makeAbsolutePath(String basePath, String path)
	{
		if (basePath == null) {
			Path myPath = FileSystems.getDefault().getPath(path);
			return myPath.normalize().toAbsolutePath().toString();
		} else {
			Path base = FileSystems.getDefault().getPath(basePath);
			Path resolved = base.resolve(path);
			return resolved.normalize().toAbsolutePath().toString();
		}
	}

}
