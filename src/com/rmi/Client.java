package com.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: tanzhen  2014-2-24 上午10:38:19
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
