package managers.mobile;


import java.util.Date;
import java.util.List;

import play.mvc.Controller;

import interfaces.TransactionManager;
import models.PaymentType;
import models.Transaction;

public class TransactionMgrMobile /*implements TransactionManager*/ extends Controller {

	
	public List<Transaction> GetTransactionByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean CreateTransaction(String microJobID, Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Transaction GetTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean DestroyTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Transaction> GetTransactionsByMicroWorkerID(String microWorkerID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Transaction> GetTransactionsByMicroEmployerID(
			String microEmployerID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Transaction> GetTransactionsByPaymentType(PaymentType payType) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean CloseTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean CompleteTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

}
