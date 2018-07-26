package com.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	/** 
	 * �����÷����Ĺ��ܼ��㷨����
	 *
	 * @autor: tanzhen  2014-2-24 ����10:38:19
	 * @param args    
	 * @return void 
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 */
	public static void main(String[] args) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		Registry registry=LocateRegistry.getRegistry("localhost");
		String name="BusinessDemo";
		Business business=(Business)registry.lookup(name);
		String result=business.echo("hello");
		System.out.println(result);
		business.echo("quit");
	}

}
