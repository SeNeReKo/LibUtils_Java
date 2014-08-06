package de.general.xml;


import java.io.*;
import java.net.*;
import java.util.*;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;
import org.jdom2.xpath.*;

import de.general.util.*;
import de.general.io.*;


/**
 *
 * @author knauth
 */
public class XMLUtils
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

    // private static final SAXBuilder builder;
    private static final XMLOutputter outputterPretty;
    private static final XMLOutputter outputterCompact;

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

    static
    {
		// builder = new SAXBuilder();
		outputterPretty = new XMLOutputter(Format.getPrettyFormat());
		outputterCompact = new XMLOutputter(Format.getCompactFormat());
    }

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

	public static Document loadXMLFile(File file) throws JDOMException, IOException
	{
		return loadXMLFile(file, true);
	}

	public static Document loadXMLFile(File file, boolean bExpandEntities) throws JDOMException, IOException
	{
		// builder.setExpandEntities(bExpandEntities);
		// if (!bExpandEntities)
		SAXBuilder builder = new SAXBuilder();
		builder.setExpandEntities(bExpandEntities);
		// builder.setEntityResolver(new NullEntityResolver());
		StringBuilder sb = new StringBuilder("file:");
		for (String s2 : file.toString().split("\\\\")) {
			for (String s : s2.split("/")) {
				sb.append('/');
				s = URLEncoder.encode(s, "UTF-8");
				s = s.replace("+", "%20");
				sb.append(s);
			}
		}
		URL url = new URL(sb.toString());
		Document doc = builder.build(url);
		return doc;
	}

	public static Document loadXMLFile(InputStream reader) throws JDOMException, IOException
	{
		return loadXMLFile(reader, true);
	}

	public static Document loadXMLFile(Reader reader) throws JDOMException, IOException
	{
		return loadXMLFile(reader, true);
	}

	public static Document loadXMLFile(InputStream reader, boolean bExpandEntities) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		builder.setExpandEntities(bExpandEntities);
		Document doc = builder.build(reader);
		return doc;
	}

	public static Document loadXMLFile(Reader reader, boolean bExpandEntities) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();
		builder.setExpandEntities(bExpandEntities);
		Document doc = builder.build(reader);
		return doc;
	}

	public static Document loadXMLFile(String filePath) throws JDOMException, IOException
	{
		return loadXMLFile(new File(filePath), true);
	}

	public static Document loadXMLFile(String filePath, boolean bExpandEntities) throws JDOMException, IOException
	{
		return loadXMLFile(new File(filePath), bExpandEntities);
	}

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

	public static void saveXMLFilePretty(Element xmlRoot, File file) throws JDOMException, IOException
	{
		saveXMLFilePretty(xmlRoot, null, file);
	}

	public static void saveXMLFilePretty(Document xml, File file) throws JDOMException, IOException
	{
		saveXMLFilePretty(xml, null, file);
	}

	public static void saveXMLFilePretty(Document xml, String filePath) throws JDOMException, IOException
	{
		saveXMLFilePretty(xml, null, new File(filePath));
	}

	public static void saveXMLFilePretty(Element xmlRoot, DocType docType, File file) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, docType, file);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Document doc, DocType docType, File file) throws JDOMException, IOException
	{
		if (docType != null) doc.setDocType(docType);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			/*
			Format format = outputter.getFormat().cloneDocument();
			format.setTextMode(Format.TextMode.TRIM);
			format.setEscapeStrategy(new EscapeStrategy() {
				@Override
				public boolean shouldEscape(char ch)
				{
					return true;
				}
			});
			outputter.setFormat(format);
			*/
			outputterPretty.output(doc, fw);
			// doc.detachRootElement();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static void saveXMLFilePretty(Document doc, DocType docType, File file, boolean bResolveEntities) throws JDOMException, IOException
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			saveXMLFilePretty(doc, docType, fw, bResolveEntities, true);
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static void saveXMLFilePretty(Element xmlRoot, DocType docType, File file, boolean bResolveEntities) throws JDOMException, IOException
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			saveXMLFilePretty(xmlRoot, docType, fw, bResolveEntities, true);
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static void saveXMLFilePretty(Element xmlRoot, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, null, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Element xmlRoot, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, null, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Element xmlRoot, DocType docType, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, docType, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Element xmlRoot, DocType docType, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, docType, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Document doc, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		saveXMLFilePretty(doc, null, w, bCloseWriter);
	}

	public static void saveXMLFilePretty(Document doc, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		saveXMLFilePretty(doc, null, w, bCloseWriter);
	}

	public static void saveXMLFilePretty(Document doc, DocType docType, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		saveXMLFilePretty(doc, docType, w, false, bCloseWriter);
	}

	public static void saveXMLFilePretty(Document doc, DocType docType, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		saveXMLFilePretty(doc, docType, w, false, bCloseWriter);
	}

	public static void saveXMLFilePretty(Element xmlRoot, DocType docType, Writer w, boolean bResolveEntities, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, docType, w, bResolveEntities, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Element xmlRoot, DocType docType, OutputStream w, boolean bResolveEntities, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFilePretty(doc, docType, w, bResolveEntities, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFilePretty(Document doc, DocType docType, Writer w, boolean bResolveEntities, boolean bCloseWriter) throws JDOMException, IOException
	{
		// TODO: Passing the docType here is not good. Remove that feature.

		if (bResolveEntities) {
			StringWriter sw = new StringWriter();

			if (docType != null) doc.setDocType(docType);
			try {
				outputterPretty.output(doc, sw);
			} finally {
				sw.close();
			}

			doc = XMLUtils.loadXMLFile(new StringReader(sw.toString()), true);
			doc.setDocType(null);
		} else {
			if (docType != null) doc.setDocType(docType);
		}

		try {
			outputterPretty.output(doc, w);
		} finally {
			if (bCloseWriter) {
				w.close();
			}
		}
	}

	public static void saveXMLFilePretty(Document doc, DocType docType, OutputStream w, boolean bResolveEntities, boolean bCloseWriter) throws JDOMException, IOException
	{
		// TODO: Passing the docType here is not good. Remove that feature.

		if (bResolveEntities) {
			StringWriter sw = new StringWriter();

			if (docType != null) doc.setDocType(docType);
			try {
				outputterPretty.output(doc, sw);
			} finally {
				sw.close();
			}

			doc = XMLUtils.loadXMLFile(new StringReader(sw.toString()), true);
			doc.setDocType(null);
		} else {
			if (docType != null) doc.setDocType(docType);
		}

		if (docType != null) doc.setDocType(docType);
		try {
			outputterPretty.output(doc, w);
		} finally {
			if (bCloseWriter) {
				w.close();
			}
		}
	}

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

	public static void saveXMLFileCompact(Element xmlRoot, File file) throws JDOMException, IOException
	{
		saveXMLFileCompact(xmlRoot, null, file);
	}

	public static void saveXMLFileCompact(Document xml, File file) throws JDOMException, IOException
	{
		saveXMLFileCompact(xml, null, file);
	}

	public static void saveXMLFileCompact(Document xml, String filePath) throws JDOMException, IOException
	{
		saveXMLFileCompact(xml, null, new File(filePath));
	}

	public static void saveXMLFileCompact(Element xmlRoot, DocType docType, File file) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFileCompact(doc, docType, file);
		doc.detachRootElement();
	}

	public static void saveXMLFileCompact(Document doc, DocType docType, File file) throws JDOMException, IOException
	{
		if (docType != null) doc.setDocType(docType);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			/*
			Format format = outputter.getFormat().cloneDocument();
			format.setTextMode(Format.TextMode.TRIM);
			format.setEscapeStrategy(new EscapeStrategy() {
				@Override
				public boolean shouldEscape(char ch)
				{
					return true;
				}
			});
			outputter.setFormat(format);
			*/
			outputterCompact.output(doc, fw);
			// doc.detachRootElement();
		} finally {
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static void saveXMLFileCompact(Element xmlRoot, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFileCompact(doc, null, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFileCompact(Element xmlRoot, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFileCompact(doc, null, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFileCompact(Element xmlRoot, DocType docType, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFileCompact(doc, docType, w, bCloseWriter);
		doc.detachRootElement();
	}

	public static void saveXMLFileCompact(Element xmlRoot, DocType docType, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		Document doc = new Document(xmlRoot);
		saveXMLFileCompact(doc, docType, w, bCloseWriter);
		doc.detachRootElement();
	}


	public static void saveXMLFileCompact(Document doc, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		saveXMLFileCompact(doc, null, w, bCloseWriter);
	}

	public static void saveXMLFileCompact(Document doc, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		saveXMLFileCompact(doc, null, w, bCloseWriter);
	}

	public static void saveXMLFileCompact(Document doc, DocType docType, Writer w, boolean bCloseWriter) throws JDOMException, IOException
	{
		if (docType != null) doc.setDocType(docType);
		try {
			outputterCompact.output(doc, w);
		} finally {
			if (bCloseWriter) {
				w.close();
			}
		}
	}

	public static void saveXMLFileCompact(Document doc, DocType docType, OutputStream w, boolean bCloseWriter) throws JDOMException, IOException
	{
		if (docType != null) doc.setDocType(docType);
		try {
			outputterCompact.output(doc, w);
		} finally {
			if (bCloseWriter) {
				w.close();
			}
		}
	}

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

	public static Element createChild(Element xmlRoot, String name)
	{
		Element e = new Element(name);
		xmlRoot.addContent(e);
		return e;
	}

	private static String __htmlTextToPlainText(String text)
	{
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		return text;
	}

	private static void __htmlTextToPlainText(String text, Writer writer) throws IOException
	{
		StringReader sr = new StringReader(text);
		char[] chunk = new char[1024*64];

		while (true) {
			int charsRead = sr.read(chunk, 0, chunk.length);
			if (charsRead <= 0) break;

			int lastPos = 0;
			for (int i = 0; i < chunk.length; i++) {
				if (chunk[i] == '<') {
					writer.write(chunk, lastPos, i - lastPos);
					writer.write("&lt;");
					lastPos = i + 1;
				} else
				if (chunk[i] == '>') {
					writer.write(chunk, lastPos, i - lastPos);
					writer.write("&gt;");
					lastPos = i + 1;
				}
			}
			if (lastPos < chunk.length) {
				writer.write(chunk, lastPos, chunk.length - lastPos);
			}
		}
	}

	public static void convertXmlToHtml5(String text, String title, HashMap<String, String> tagColorScheme, Writer writer) throws IOException
	{
		if (tagColorScheme != null) {
			writer.write("<!DOCTYPE html><html lang=\"en\"><head>"
				+ "<title>" + title + "</title>"
				+ "<meta charset=\"utf-8\"></meta>"
				+ "</head><body>");
			__toColoredHtml(text, tagColorScheme, writer);
			writer.write("</body></html>");
		} else {
			text = text.replace("<", "&lt;").replace(">", "&gt;");
			writer.write("<!DOCTYPE html><html lang=\"en\"><head>"
				+ "<title>" + title + "</title>"
				+ "<meta charset=\"utf-8\"></meta>"
				+ "</head><body><pre>");
			// __htmlTextToPlainText(text, writer);
			writer.write(text);
			writer.write("</pre></body></html>");
		}
	}

	private static String __getNextWord(String s, int pos)
	{
		int i = 0;
		StringBuilder sb = new StringBuilder();
		while (pos + i < s.length()) {
			char c = s.charAt(pos + i);
			if (Character.isLetter(c)) {
				sb.append(c);
			} else {
				break;
			}
			i++;
		}
		if (sb.length() == 0) return null;
		return sb.toString();
	}

	/*
	private static String __toColoredHtml(String xmlText, HashMap<String, String> tagColorScheme)
	{
		String[] lines = xmlText.split("\r\n");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.trim().length() == 0) continue;

			line = line.replace("\t", "    ");
			line = line.replace("<", "|<|");
			line = line.replace(">", "|>|");
			while (true) {
				int pos = line.indexOf("|<|");
				if (pos < 0) break;
				String tagName = __getNextWord(line, pos + 3);
				String color = null;
				if (tagName != null) {
					color = tagColorScheme.get(tagName);
				}
				if (color != null) {
					line = line.substring(0, pos) + "<span style=\"color: " + color + "; font-weight: bold;\">&lt;" + line.substring(pos + 3);
				} else {
					line = line.substring(0, pos) + "<span style=\"color: #808080;\">&lt;" + line.substring(pos + 3);
				}
			}
			line = line.replace("|>|", "&gt;</span>");
			int n = line.length();
			line = line.trim();
			n -= line.length();
			for (int j = 0; j < n; j++) {
				line = "&nbsp;&nbsp;" + line;
			}
			sb.append(line);
			sb.append("<br />\r\n");
		}
		return sb.toString();
	}
	*/

	private static void __toColoredHtml(String xmlText, HashMap<String, String> tagColorScheme, Writer writer) throws IOException
	{
		BufferedReader br = new BufferedReader(new StringReader(xmlText));

		String line;
		// String[] lines = xmlText.split("\r\n");
		while ((line = br.readLine()) != null) {
			__toColoredHtmlLine(line, tagColorScheme, writer);
		}
	}

	private static void __toColoredHtmlLine(String line, HashMap<String, String> tagColorScheme, Writer writer) throws IOException
	{
		if (line.trim().length() == 0) return;

		line = line.replace("\t", "    ");
		line = line.replace("<", "|<|");
		line = line.replace(">", "|>|");
		while (true) {
			int pos = line.indexOf("|<|");
			if (pos < 0) break;
			String tagName = __getNextWord(line, pos + 3);
			String color = null;
			if (tagName != null) {
				color = tagColorScheme.get(tagName);
			}
			if (color != null) {
				line = line.substring(0, pos) + "<span style=\"color: " + color + "; font-weight: bold;\">&lt;" + line.substring(pos + 3);
			} else {
				line = line.substring(0, pos) + "<span style=\"color: #808080;\">&lt;" + line.substring(pos + 3);
			}
		}
		line = line.replace("|>|", "&gt;</span>");
		int n = line.length();
		line = line.trim();
		n -= line.length();
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < n; j++) {
			sb.append("&nbsp;&nbsp;");
		}
		writer.append(sb.toString());
		writer.append(line);
		writer.append("<br />\r\n");
	}

	public static void convertXmlToHtml5(String text, String title, File outputFile, HashMap<String, String> tagColorScheme) throws IOException
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			convertXmlToHtml5(text, title, tagColorScheme, bw);
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

	public static String convertXmlToHtml5(Document xmlDoc, String title, HashMap<String, String> tagColorScheme) throws JDOMException, IOException
	{
		StringWriter sw = new StringWriter();
		saveXMLFilePretty(xmlDoc, sw, true);
		xmlDoc = null;
		String inText = sw.toString();
		sw = null;
		StringWriter outSW = new StringWriter();
		convertXmlToHtml5(inText, title, tagColorScheme, outSW);
		inText = null;
		return outSW.toString();
	}

	/*
	public static String convertXmlToHtml5(Element xmlRoot, String title, HashMap<String, String> tagColorScheme) throws JDOMException, IOException
	{
		StringWriter sw = new StringWriter();
		saveXMLFilePretty(xmlRoot, sw, true);
		xmlRoot = null;
		return convertXmlToHtml5(sw.toString(), title, tagColorScheme);
	}
	*/

	public static void convertXmlToHtml5(Document xmlDoc, String title, File outputFile, HashMap<String, String> tagColorScheme) throws JDOMException, IOException
	{
		StringWriter sw = new StringWriter();
		saveXMLFilePretty(xmlDoc, sw, true);
		xmlDoc = null;
		String s = sw.toString();
		sw = null;

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			convertXmlToHtml5(s, title, tagColorScheme, bw);
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

	public static void convertXmlToHtml5(Element xmlRoot, String title, File outputFile, HashMap<String, String> tagColorScheme) throws JDOMException, IOException
	{
		StringWriter sw = new StringWriter();
		saveXMLFilePretty(xmlRoot, sw, true);
		xmlRoot = null;
		String s = sw.toString();
		sw = null;

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			convertXmlToHtml5(s, title, tagColorScheme, bw);
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

	/*
	public static String convertXmlToHtml5(File xmlFile, String title, HashMap<String, String> tagColorScheme) throws JDOMException, IOException
	{
		return convertXmlToHtml5(IOUtils.loadFileAsText(xmlFile), title, tagColorScheme);
	}
	*/

	public static void convertXmlToHtml5(File xmlFile, String title, File outputFile, HashMap<String, String> tagColorScheme) throws JDOMException, IOException
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			convertXmlToHtml5(IOUtils.loadFileAsText(xmlFile), title, tagColorScheme, bw);
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

	public static Document cloneDocument(Document xmlDoc) throws JDOMException, IOException
	{
		StringWriter sw = new StringWriter();
		saveXMLFilePretty(xmlDoc, sw, true);
		xmlDoc = null;
		return loadXMLFile(new StringReader(sw.toString()));
	}

	/**
	 * Retrieve all content objects from this element. After this process <code>e</code> won't have
	 * content objects any more.
	 */
	public static ArrayList<Content> extractContentAndDetach(Element xml)
	{
		List<Content> contentList = xml.getContent();
		ArrayList<Content> ret = new ArrayList<Content>();
		for (Content c : contentList) {
			ret.add(c);
		}
		for (Content c : ret) {
			c.detach();
		}
		return ret;

	}

	/**
	 * Retrieve all content objects from this element. After this process <code>e</code> won't have
	 * content objects any more.
	 */
	public static ArrayList<Element> extractChildrenAndDetach(Element xml)
	{
		List<Element> contentList = xml.getChildren();
		ArrayList<Element> ret = new ArrayList<Element>();
		for (Element e : contentList) {
			ret.add(e);
		}
		for (Element e : ret) {
			e.detach();
		}
		return ret;

	}

	public static Element createPath(Element xmlRoot, String[] pathElements)
	{
		Element e = xmlRoot;
		for (int i = 0; i < pathElements.length; i++) {
			Element eParent = e;
			e = e.getChild(pathElements[i]);
			if (e == null) {
				e = new Element(pathElements[i]);
				eParent.addContent(e);
			}
		}
		return e;
	}

	/**
	 * Bring the child elements in the specified XML element in the correct order by first removing
	 * them all and then adding them in the correct order.
	 */
	public static void orderChildElements(Element xml, String[] names)
	{
		ArrayList<Element> list = new ArrayList<Element>();
		for (int i = 0; i < names.length; i++) {
			List<Element> eList = xml.getChildren(names[i]);
			if (eList != null) list.addAll(eList);
		}
		for (int i = 0; i < list.size(); i++) {
			Element e = list.get(i);
			xml.removeContent(e);
		}
		for (int i = 0; i < list.size(); i++) {
			Element e = list.get(i);
			xml.addContent(e);
		}
	}

	public static Element cloneTreeWithoutNameSpaces(Element xmlSrc, StringSet stringSet)
	{
		if (stringSet == null) stringSet = new StringSet();

		Element xmlDest = new Element(stringSet.s(xmlSrc.getName()));
		cloneTreeWithoutNameSpaces(xmlSrc, xmlDest, stringSet);
		return xmlDest;
	}

	public static Content cloneTreeWithoutNameSpaces(Content c, StringSet stringSet)
	{
		if (stringSet == null) stringSet = new StringSet();

		if (c instanceof Element) {
			Element ce = (Element)c;
			Element e = new Element(stringSet.s(ce.getName()));
			for (Attribute a : ce.getAttributes()) {
				e.setAttribute(stringSet.s(a.getName()), stringSet.s(a.getValue()));
			}
			cloneTreeWithoutNameSpaces(ce, e, stringSet);
			return e;
		} else
		if (c instanceof Comment) {
			Element ce = (Element)c;
			Element e = new Element(stringSet.s(ce.getText()));
			return e;
		} else
		if (c instanceof DocType) {
			DocType ce = (DocType)c;
			DocType e = new DocType(stringSet.s(ce.getElementName()));
			return e;
		} else
		if (c instanceof EntityRef) {
			EntityRef ce = (EntityRef)c;
			EntityRef e = new EntityRef(stringSet.s(ce.getName()));
			return e;
		} else
		if (c instanceof Text) {
			Text ce = (Text)c;
			Text e = new Text(stringSet.s(ce.getText()));
			return e;
		} else {
			throw new ImplementationErrorException();
		}
	}

	public static void cloneTreeWithoutNameSpaces(Element xmlSrc, Element xmlDest, StringSet stringSet)
	{
		if (stringSet == null) stringSet = new StringSet();

		for (Attribute a : xmlSrc.getAttributes()) {
			xmlDest.setAttribute(stringSet.s(a.getName()), stringSet.s(a.getValue()));
		}

		for (Content c : xmlSrc.getContent()) {
			if (c instanceof Element) {
				Element ce = (Element)c;
				Element e = new Element(stringSet.s(ce.getName()));
				cloneTreeWithoutNameSpaces(ce, e, stringSet);
				xmlDest.addContent(cloneTreeWithoutNameSpaces((Element)e, stringSet));
			} else
			if (c instanceof Comment) {
				Element ce = (Element)c;
				Element e = new Element(stringSet.s(ce.getText()));
				xmlDest.addContent(e);
			} else
			if (c instanceof DocType) {
				DocType ce = (DocType)c;
				DocType e = new DocType(stringSet.s(ce.getElementName()));
				xmlDest.addContent(e);
			} else
			if (c instanceof EntityRef) {
				EntityRef ce = (EntityRef)c;
				EntityRef e = new EntityRef(stringSet.s(ce.getName()));
				xmlDest.addContent(e);
			} else
			if (c instanceof Text) {
				Text ce = (Text)c;
				Text e = new Text(stringSet.s(ce.getText()));
				xmlDest.addContent(e);
			} else {
				throw new ImplementationErrorException();
			}
		}
	}

	public static Element getByPath(Document doc, String ... nodeNames)
	{
		return getByPath(doc.getRootElement(), nodeNames);
	}

	public static Element getByPath(Element xmlDocRoot, String ... nodeNames)
	{
		Element x = xmlDocRoot;
		if (!x.getName().equals(nodeNames[0])) return null;

		for (int i = 1; i < nodeNames.length; i++) {
			x = x.getChild(nodeNames[i]);
			if (x == null) return null;
		}

		return x;
	}

	/*
	public static Element getByPath(Element xmlRoot, String[] pathElements)
	{
		Element e = xmlRoot;
		for (int i = 0; i < pathElements.length; i++) {
			e = e.getChild(pathElements[i]);
			if (e == null) return null;
		}
		return e;
	}
	*/

}

