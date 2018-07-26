package com.chengyi.cpms.dock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * <li>Title: DockExecutor.java</li> <li>Project: CPMS</li> <li>Package:
 * com.chengyi.cpms.dock</li> <li>Description: �Խ�ִ����-����url����</li> <li>Copyright:
 * Copyright (c) 2010</li> <li>Company: Crescent Studio</li> <li>Created on
 * 2010-10-27 ����02:50:39</li>
 * 
 * @author chun_chang
 * @version 1.0.0.0
 */
public class DockExecutor {
	/**
	 * ������sendUrl ����URL��post����
	 * 
	 * @param urlStr
	 * @param params
	 * @return
	 * @CreateOn 2010-10-28 ����01:39:00
	 * @author chun_chang
	 */
	public String sendUrl(String urlStr, String params) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(urlStr);
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����ͨ�õ���������
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(params);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	public static void main(String args[]) {
		final String urlStr1 = "http://www.ablesky-a.com:8080/shengPayReturnTest.do";
		final String urlStr2 = "http://www.ablesky-a.com:8080/shengPayNotifyTest.do";
		final String params = "OrderNo=120924000001193&OrderAmount=200";
		final DockExecutor executor=new DockExecutor();
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String result1=executor.sendUrl(urlStr1, params);
				System.out.println(result1);
			}
			
		}).start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String result2=executor.sendUrl(urlStr2, params);
				System.out.println(result2);
			}
			
		}).start();
		
		
		
	}
}
