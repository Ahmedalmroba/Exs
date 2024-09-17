package com.example.exame.Controller;

import com.example.exame.DTO.CustomerDTO;
import com.example.exame.Model.User;
import com.example.exame.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getCustomer() {
        return ResponseEntity.status(200).body(customerService.GetAllCustomer());
    }
    @PostMapping("/add")
    public ResponseEntity addCustomer(@Valid @RequestBody CustomerDTO CustomerDTO) {
        customerService.registerCustomer(CustomerDTO);
        return ResponseEntity.status(200).body("add customer success");
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@Valid @RequestBody CustomerDTO CustomerDTO,@AuthenticationPrincipal User user) {
        customerService.UpdateCustomer(CustomerDTO,user.getId());
        return ResponseEntity.status(200).body("update customer success");

    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user , Integer customerDTOId) {
        customerService.DeleteCustomer(customerDTOId,user.getId());
        return ResponseEntity.status(200).body("delete customer success");
    }
}
