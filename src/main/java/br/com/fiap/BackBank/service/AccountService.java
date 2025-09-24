package br.com.fiap.BackBank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.BackBank.model.Account;
import br.com.fiap.BackBank.repositories.AccountRepo;
import lombok.experimental.var;

@Service
public class AccountService {

    @Autowired
    private AccountRepo repo;

    public Account save(Account account) {
        return repo.save(account);
    }

    public List<Account> findAll() {
        return repo.findAll();
    }

    public Account findById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada pelo id: " + id));
    }

    public void deleteById(Long id) {
        var account = findById(id);
        account.setActive(false);
        repo.save(account);
    }

    public Account changeAccountBalanceById(Long id, BigDecimal amount) {
        var account = findById(id);
        account.setInitalBalance(account.getInitalBalance().add(amount));
        return account;
     }

    public Account sendPixTo(Long id, BigDecimal amount) {
        if (amount.doubleValue() <= 0) {
            return null;
        }

        return null;
    }
}
