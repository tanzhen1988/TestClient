package com.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Business extends Remote {
	public String echo(String message) throws RemoteException;
}
