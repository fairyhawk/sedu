<%@ page language="java" pageEncoding="GBK"%>
<%@page import="java.io.*" %>
<%@page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper" %>
<%@page import="java.util.concurrent.locks.*" %>
<%
	//С��ģ����kindeditor�༭�����ϴ�ʱ����ʹ�õ����м�洢Ŀ¼
	String kindeditorSavePath = "upload/" + (String)session.getAttribute("kindeditorSavePath");
	//Struts2  ���� ��װ������
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	// ����ϴ����ļ��� 
	String fileName = wrapper.getFileNames("imgFile")[0];
	//���δ�������� 
	File file = wrapper.getFiles("imgFile")[0];
	//----------- ���¹����ϴ��ļ���----------------------
	final Lock lock = new ReentrantLock();
	String newName = null;
	lock.lock();
	try {
		//����Ϊ��ֹ�ļ����ظ� 
		newName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."),fileName.length());
	}finally {
		lock.unlock();
	}
	//------------ ������ -------------
	
	String fileDirectory = request.getSession().getServletContext().getRealPath("/") + kindeditorSavePath;
	File isD = new File(fileDirectory);
	//У�����Ŀ¼�����ڣ��򴴽�Ŀ¼
	if(!isD.isDirectory()){
		isD.mkdirs();
	}
	//��ȡ�ļ������ 
	FileOutputStream fos = new FileOutputStream(fileDirectory);
	//���� KE �е�ͼƬ�ļ���ַ 
	String newFileName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + kindeditorSavePath +"/" + newName;
	byte[] buffer = new byte[1024];
	//��ȡ�ڴ��е�ǰ�ļ������� 
	InputStream in = new FileInputStream(file);
	try {
		int num = 0;
		while ((num = in.read(buffer)) > 0) {
			fos.write(buffer, 0, num);
		}
	} catch (Exception e) {
		e.printStackTrace(System.err);
	} finally {
		in.close();
		fos.close();
	}
	//���͸�KE 
	out.println("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=gbk'/></head><body>");
	out.println("<script type='text/javascript'>");
	out.println("parent.parent.KE.plugin['image'].insert('"
			+ wrapper.getParameter("id") + "','" + newFileName + "','"
			+ wrapper.getParameter("imgTitle") + "','"
			+ wrapper.getParameter("imgWidth") + "','"
			+ wrapper.getParameter("imgHeight") + "','"
			+ wrapper.getParameter("imgBorder") + "','"
			+ wrapper.getParameter("align") + "');</script>");
	out.println("</body></html>");
%>