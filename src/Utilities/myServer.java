package Utilities;

import java.rmi.RemoteException;

/*RMi interface*/
public interface myServer extends java.rmi.Remote {
	String findLocation(String string) throws RemoteException;
	String getSongs(String string) throws RemoteException;
}
