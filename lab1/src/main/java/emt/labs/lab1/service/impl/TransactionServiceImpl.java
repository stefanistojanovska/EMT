package emt.labs.lab1.service.impl;

import emt.labs.lab1.models.Transaction;
import emt.labs.lab1.repository.db.PersistentTransactionRepository;
import emt.labs.lab1.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private PersistentTransactionRepository repo;
    public TransactionServiceImpl(PersistentTransactionRepository repo)
    {
        this.repo=repo;
    }
    @Override
    public Transaction addNewTrasnsaction(Transaction transaction) {
        return repo.save(transaction);
    }
}
