package com.rmi;

import java.rmi.RemoteException;

public class BusinessImpl implements Business {

	@Override
	public String echo(String message) throws RemoteException {
		if("quit".equalsIgnoreCase(message.toString())){
			System.out.println("sever will be shutdown!");
			System.exit(0);
		}
		System.out.println("message from client:"+message);
		return "server response:"+message;
	}

}
