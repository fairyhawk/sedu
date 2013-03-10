package exp.vo;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IRMIService extends Remote{

		public String  saysmttosmone(String smone) throws RemoteException ;
		public String  getWords(String words)  throws RemoteException;
}
