package com.example.exame.Service;

import com.example.exame.DTO.CustomerDTO;
import com.example.exame.Model.Customer;
import com.example.exame.Model.User;
import com.example.exame.Repository.AuthRepository;
import com.example.exame.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    public List<User>GetAllCustomer(){
        List<User> customer= new  ArrayList<> ();
        for (User user:authRepository.findAll()){
            if(user.getRole().equals("customer")){
                customer.add(user);
            }
        }
        return customer;
    }
    public void registerCustomer(CustomerDTO CustomerDTO){
        String hashed = new BCryptPasswordEncoder().encode(CustomerDTO.getPassword());
        User user = new User();
        user.setEmail(CustomerDTO.getEmail());
        user.setPassword(hashed);
        user.setRole("customer");
        user.setName(CustomerDTO.getName());
        user.setUsername(CustomerDTO.getUsername());

        Customer customer = new Customer();
        customer.setPhoneNumber(CustomerDTO.getPhoneNumber());
        customer.setUser(user);
        customerRepository.save(customer);
        authRepository.save(user);

    }
    public void UpdateCustomer(CustomerDTO CustomerDTO,Integer userid){
        String hashed = new BCryptPasswordEncoder().encode(CustomerDTO.getPassword());
       User user = new User();
       user.setEmail(CustomerDTO.getEmail());
       user.setPassword(hashed);
       user.setUsername(CustomerDTO.getUsername());
        user.setName(CustomerDTO.getName());
        user.setRole("customer");

       Customer customer = new Customer();
       customer.setPhoneNumber(CustomerDTO.getPhoneNumber());
       customer.setUser(user);
       customerRepository.save(customer);
       authRepository.save(user);

    }
    public void DeleteCustomer(Integer customerDTOId,Integer userid){
        User user = authRepository.findUsersById(userid);
        Customer customer = customerRepository.findCustomerById(customerDTOId);
        if (customer==null){
            throw new ArithmeticException("Customer not found");
        }

        if (user.getId()!=customer.getId()){
            throw new ArithmeticException("Not match");


        }
        customer.setUser(user);
        customerRepository.save(customer);



    }
}
