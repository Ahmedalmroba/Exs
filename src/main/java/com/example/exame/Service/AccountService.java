package com.example.exame.Service;

import com.example.exame.Api.ApiException;
import com.example.exame.Model.Account;
import com.example.exame.Model.Customer;
import com.example.exame.Model.User;
import com.example.exame.Repository.AccountRepository;
import com.example.exame.Repository.AuthRepository;
import com.example.exame.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private  final
    public List getAllAccounts() {
        return accountRepository.findAll();
    }
    public void addAccount(Account account ,Integer userid) {
        Account user = accountRepository.findAccountById(userid);
        if(!user.getrule().equalsIgnoreCase("CUSTOMER")){
        throw new ApiException(" not a customer");
        }
        Customer customer = customerRepository.findCustomerById(user.getId());
       account.setCustomer(customer);
        accountRepository.save(account);
    }
    public void updateAccount(int userId, Account newAccount , int accountId ){
       Account oldAccount = accountRepository.findAccountById(accountId);
       if(oldAccount==null){
            throw new ApiException("account not found");}
      if(oldAccount.getCustomer().getId() != userId){
           throw new ApiException("invalid account");
        }
        oldAccount.setAccountNumber(newAccount.getAccountNumber());
        oldAccount.setActive(newAccount.isActive());
        oldAccount.setBalance(newAccount.getBalance());
        accountRepository.save(oldAccount);
    }
    public void depositAccount(int userId , int accountId, int amount){
       Account account = accountRepository.findAccountById(accountId);
        if(account==null){
            throw new ApiException("account not found");
        }
        if(account.isActive()==false ){
            throw new ApiException("account not Active");
       }
        if(account.getCustomer().getId() != userId){
           throw new ApiException("account id mismatch");
        }
       account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }
    public void withdrawAccount(int userId , int accountId, int amount){
       Account account = accountRepository.findAccountById(accountId);
        if(account==null){
          throw new ApiException("account not found");
        }
      if(account.isActive()==false ){
           throw new ApiException("account not Active");
        }
        if(account.getCustomer().getId() != userId){
            throw new ApiException("account id mismatch");
        }
      if(account.getBalance()<amount){/            throw new ApiException("insufficient balance");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    }
    
