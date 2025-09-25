package br.com.fiap.BackBank.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.BackBank.model.Account;
import br.com.fiap.BackBank.service.AccountService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public List<Account> getAll() {
        log.info("recuperando todas as contas");
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccount(@RequestBody Account account) {
        log.info("criando uma nova conta {}", account);
        return service.save(account);
    }

    @GetMapping("{id}")
    public Account getById(@PathVariable Long id) {
        log.info("recuperando conta pelo id: {}", id);
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void deactivateAccount(@PathVariable Long id) {
        log.info("desativando conta pelo id: {}", id);
        service.deleteById(id);
    }

    @PostMapping("{id}/change-balance")
    public Account changeAccountBalanceById(@PathVariable Long id,
            @RequestParam(defaultValue = "0", required = true) Double amount) {
        log.info("mudando o saldo da conta: {}", id);
        return service.changeAccountBalanceById(id, amount);
    }

    @GetMapping("/send-pix")
    public Account sendPixTo(
            @RequestParam(required = true, name = "from") Long idSender,
            @RequestParam(required = true, name = "to") Long idReceiver,
            @RequestParam(defaultValue = "0", required = true) Double amount) {
        log.info("Enviando pix da conta {} para a conta {}", idSender, idReceiver);
        return service.sendPixTo(idSender, idReceiver, amount);
    }
}
