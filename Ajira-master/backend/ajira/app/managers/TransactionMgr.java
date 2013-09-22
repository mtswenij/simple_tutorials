package managers;

import java.util.Date;
import java.util.List;

import interfaces.TransactionManager;
import models.PaymentType;
import models.Transaction;

public class TransactionMgr implements TransactionManager {

	@Override
	public List<Transaction> GetTransactionByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CreateTransaction(String microJobID, Transaction t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction GetTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean DestroyTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Transaction> GetTransactionsByMicroWorkerID(String microWorkerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> GetTransactionsByMicroEmployerID(
			String microEmployerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> GetTransactionsByPaymentType(PaymentType payType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean CloseTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean CompleteTransaction(String microJobID) {
		// TODO Auto-generated method stub
		return false;
	}

}
