package emt.labs.lab1.repository.db;

import emt.labs.lab1.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface PersistentTransactionRepository extends Repository<Transaction,Long> {
    Transaction save(Transaction t);
}
