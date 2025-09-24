package br.com.fiap.BackBank.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.fiap.BackBank.model.Account;
import br.com.fiap.BackBank.service.AccountService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AccountController {
    
    @Autowired
    private AccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccount(@RequestBody Account account) {
        log.info("criando uma nova conta {}", account);
        return service.save(account);
    }

    @GetMapping
    public List<Account> getAll() {
        log.info("recuperando todas as contas");
        return service.findAll();
    }
    
    @GetMapping("{id}")
    public Account getById(Long id) {
        log.info("recuperando conta pelo id: {}", id);
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void deactivateAccount(Long id) {
        log.info("desativando conta pelo id: {}", id);
        service.deleteById(id);
    }

    public Account changeAccountBalanceById(Long id, BigDecimal amount) {
        log.info("mudando o saldo da conta: {}", id);
        return service.changeAccountBalanceById(id, amount);
    }

    public Account sendPixTo(Long id, BigDecimal amount) {
        log.info("Enviando pix para a conta: {}", id);
        return service.sendPixTo(id, amount);
    }
}
