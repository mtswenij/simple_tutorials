package interfaces;

import java.util.*;

import models.PaymentType;
import models.Transaction;

public interface TransactionManager {
	
	/* CRUD TRANSACTION */

	public boolean CreateTransaction(String microJobID, Transaction t);

	public Transaction GetTransaction(String microJobID);

	public boolean DestroyTransaction(String microJobID);

	public List<Transaction> GetTransactionsByMicroWorkerID(String microWorkerID);

	public List<Transaction> GetTransactionsByMicroEmployerID(String microEmployerID);

	public List<Transaction> GetTransactionByDate(Date date);

	public List<Transaction> GetTransactionsByPaymentType(PaymentType payType);

	/* MANAGE TRANSACTIONS */

	public boolean CloseTransaction(String microJobID);

	public boolean CompleteTransaction(String microJobID);
}
