package com.training.shop.service;


import com.training.shop.entity.User;
import com.training.shop.repository.UserRepo;
import com.training.shop.service.exception.NotFoundEntityException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
public class UserService implements  Service<User>, UserDetailsService {


    private final UserRepo userRepo;

    private static final String NOT_EXIST_ID = "Not exist user  with id = %s";

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public User create(User entity) {
        return userRepo.save(entity);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(()
                -> new NotFoundEntityException(String.format(NOT_EXIST_ID, id)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    @Transactional
    public User update(User entity) {
        return userRepo.save(entity);
    }

    public boolean existsById(Long id){
        return userRepo.existsById(id);
    }

    public User findByName(String name){
        return userRepo.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepo.findByName(userName);
    }
}
