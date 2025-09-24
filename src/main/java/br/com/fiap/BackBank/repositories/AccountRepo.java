package br.com.fiap.BackBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.BackBank.model.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
    
}
