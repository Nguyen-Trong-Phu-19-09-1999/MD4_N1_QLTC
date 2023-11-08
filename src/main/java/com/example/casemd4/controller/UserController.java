package com.example.casemd4.controller;

import com.example.casemd4.model.JwtResponse;
import com.example.casemd4.model.Role;
import com.example.casemd4.model.Transactions;
import com.example.casemd4.model.User;
import com.example.casemd4.service.Impl.JwtService;
import com.example.casemd4.service.Impl.RoleService;
import com.example.casemd4.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class UserController {
    //    @Autowired
//    IUserRepository iUserRepository;
    @Autowired
    private Environment env;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

//    @GetMapping
//    List<User> lisUsers(){
//        return iUserRepository.findAll();
//    }
//    @PostMapping("users/register")
//    public ResponseEntity addUser(@RequestBody User user){
//        List<User> foundUser = iUserRepository.findOneByUsername(user.getUsername().trim());
//        if(foundUser.size() > 0){
//            return new ResponseEntity("User already exists", HttpStatus.NOT_IMPLEMENTED);
//        }
//        else {
//            iUserRepository.save(user);
//            return new ResponseEntity("Add user successfully", HttpStatus.OK);
//        }
//
//    }
//    @PostMapping("users/login")
//    public ResponseEntity login(@RequestBody User user){
//        List<User> users = iUserRepository.findAllByUsernameAndPassword(user.getUsername(), user.getPassword());
//        if(users.size()==1){
//            return new ResponseEntity(users,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity("User name or password not correct",HttpStatus.NOT_IMPLEMENTED);
//        }
//    }
//    @PutMapping("users/{id}")
//    public ResponseEntity<String> update(@RequestBody User user, @PathVariable Long id){
//        boolean exists = iUserRepository.existsById(id);
//        if (exists){
//            user.setId(id);
//            iUserRepository.save(user);
//            return new ResponseEntity("Update user successfully", HttpStatus.OK);
//        }else {
//            return new ResponseEntity("cannot find this id",HttpStatus.NOT_FOUND);
//        }
//    }
//    @DeleteMapping("users/{id}")
//    public ResponseEntity deleteUser(@PathVariable Long id){
//        boolean exists = iUserRepository.existsById(id);
//        if (exists){
//            iUserRepository.deleteById(id);
//            return new ResponseEntity("Delete user successfully", HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity("Cannot find this id", HttpStatus.NOT_FOUND);
//        }
//
//    }
//    @GetMapping("users/{id}")
//    public ResponseEntity findById(@PathVariable Long id){
//        Optional<User> foundProduct = iUserRepository.findById(id);
//        return new ResponseEntity(foundProduct,HttpStatus.OK);
//    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> showAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<Iterable<User>> showAllUserByAdmin() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Đăng ký không thành công. Vui lòng kiểm tra lại thông tin.");
        }

        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
            }
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xác nhận mật khẩu không khớp. Vui lòng nhập lại.");
        }
        Role role1 = roleService.findByName("ROLE_USER");
        Set<Role> roles1 = new HashSet<>();
        roles1.add(role1);
        user.setRoles(roles1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(passwordEncoder.encode(user.getConfirmPassword()));
        userService.save(user);

        // Registration successful
        return ResponseEntity.status(HttpStatus.CREATED).body("Đăng ký thành công.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), userDetails.getAuthorities()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thong tin dang nhap");
        }
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity("Hello World", HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getProfile(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = this.userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(userOptional.get().getId());
        user.setUsername(userOptional.get().getUsername());
        user.setEnabled(userOptional.get().isEnabled());
        user.setPassword(userOptional.get().getPassword());
        user.setRoles(userOptional.get().getRoles());
        user.setConfirmPassword(userOptional.get().getConfirmPassword());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        Optional<User> transactions1 = userService.findById(id);
        if (transactions1.isPresent()){
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
