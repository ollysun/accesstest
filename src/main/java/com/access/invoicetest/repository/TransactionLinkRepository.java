package com.access.invoicetest.repository;


import com.access.invoicetest.entity.Invoice;
import com.access.invoicetest.entity.TransactionLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLinkRepository extends JpaRepository<TransactionLink, Long>  {

    TransactionLink findByUniqueLink(String uniqueLink);

}
