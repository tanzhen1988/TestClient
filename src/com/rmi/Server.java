package com.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	/** 
	 * 描述该方法的功能及算法流程
	 *
	 * @autor: tanzhen  2014-2-24 上午10:35:20
	 * @param args    
	 * @return void 
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		int port=9527;
		String name="BusinessDemo";
		Business business=new BusinessImpl();
		UnicastRemoteObject.exportObject(business, port);
		Registry registry=LocateRegistry.createRegistry(1099);
		registry.rebind(name, business);
	}

}
