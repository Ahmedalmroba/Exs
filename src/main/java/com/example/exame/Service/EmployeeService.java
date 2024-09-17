package com.example.exame.Service;

import com.example.exame.Api.ApiException;
import com.example.exame.DTO.EmployeeDTO;
import com.example.exame.Model.Employee;
import com.example.exame.Model.User;
import com.example.exame.Repository.AuthRepository;
import com.example.exame.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    public List<User> getAllEmployees() {
        List<User> Employees = new ArrayList<>();
        for (User user : authRepository.findAll()) {
            if (user.getRole().equals("EMPLOYEE")) {
                Employees.add(user);

            }
        }
        return Employees;


    }
    public void registerEmp(EmployeeDTO employeeDTO) {
        String hashed = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User();
        user.setEmail(employeeDTO.getEmail());
        user.setPassword(hashed);
        user.setRole("EMPLOYEE");
        user.setName(employeeDTO.getName());
        user.setUsername(employeeDTO.getUsername());

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        user.setEmployee(employee);
        employeeRepository.save(employee);
        authRepository.save(user);
    }
   public void updateEmp(EmployeeDTO employeeDTO, Integer id ) {
        String hashed = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
       User user = new User();
       user.setEmail(employeeDTO.getEmail());
       user.setPassword(hashed);
       user.setRole("EMPLOYEE");
       user.setName(employeeDTO.getName());
       user.setUsername(employeeDTO.getUsername());

       Employee employee = new Employee();
       employee.setPosition(employeeDTO.getPosition());
       employee.setSalary(employeeDTO.getSalary());
       user.setEmployee(employee);
       employeeRepository.save(employee);
       authRepository.save(user);
   }
   public void deleteEmp(Integer id,Integer userid) {
       User user = authRepository.findUsersById(userid);
       Employee employee = employeeRepository.findEmployeeById(id);

       if (employee==null){
           throw new ApiException("employee not found");

       }
       if (user.getId()!=employee.getId()){
           throw new ArithmeticException("Not match");
   }
       employee.setUser(user);
       employeeRepository.save(employee);
}}