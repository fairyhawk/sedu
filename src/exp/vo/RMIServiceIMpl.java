package exp.vo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServiceIMpl extends UnicastRemoteObject implements IRMIService {

	protected RMIServiceIMpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String saysmttosmone(String smone) throws RemoteException {
		System.out.println("====被调用");
		return smone;
	}

	@Override
	public String getWords(String words) throws RemoteException {
		// TODO Auto-generated method stub
		return words;
	}

}
