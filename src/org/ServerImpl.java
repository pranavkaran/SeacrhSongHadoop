package org;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Predicates.Findlocation;
import Predicates.GetSongs;
import Utilities.dataRead;
import Utilities.myServer;

/*server class implements the interface*/
public class ServerImpl extends UnicastRemoteObject implements myServer{
	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	dataRead objDataRead = new dataRead();
	ArrayList<String> retArrayList;
	
	/*findlocation gives location*/
	public String findLocation(String givenArtistName) {
		retArrayList = objDataRead.dataPull(givenArtistName);
		Findlocation objFindlocation = new Findlocation();
		return objFindlocation.getLocation(retArrayList);
	}
	
	/*getSongs gives songs*/
	public String getSongs(String givenArtistName) {
		retArrayList = objDataRead.dataPull(givenArtistName);
		GetSongs objGetSongs = new GetSongs();
		return objGetSongs.getTracks(retArrayList);
	}
}
