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

    public Account changeAccountBalanceById(Long id, Double amount) {
        var account = findById(id);
        account.setInitialBalance(BigDecimal.valueOf(account.getInitialBalance().doubleValue() + amount));
        repo.save(account);
        return account;
     }

    public Account sendPixTo(Long idS, Long idR, Double amount) {
        if (amount <= 0) {
            return null;
            //ExecptionResponse
        }

        var sAcc = changeAccountBalanceById(idS, amount * -1);

        changeAccountBalanceById(idR, amount);

        return sAcc;
    }
}
