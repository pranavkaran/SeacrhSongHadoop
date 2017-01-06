package org;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Utilities.Constant;


public class Server{
	
	/*This is call creates the registry*/
	public static void main(String[] args) throws RemoteException {
        try{
        	//System.setProperty("java.security.policy","org/Server.policy");
        	//System.setSecurityManager(new SecurityManager());
        	ServerImpl server = new ServerImpl();
        	Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
        	registry.bind(Constant.RMI_ID , server);
        	System.out.println("Server started...");
        }
        catch(Exception e){
        	System.out.println("Error: "+e);
        	
        }
    }
}
