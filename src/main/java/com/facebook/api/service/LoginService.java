package com.facebook.api.service;

import com.facebook.api.model.LoginForm;
import com.facebook.api.repository.MemberRepository;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class LoginService {
    @Autowired
    private MemberRepository memberRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String login(LoginForm loginForm) {
        if(validateUser(loginForm.getUsername(), loginForm.getPassword())) {
            String key = generateApiKey();
            memberRepository.saveApiKey(loginForm.getUsername(), key);
            return key;
        }
        else
            return null;
    }

    private boolean validateUser(String username, String password) {
        Integer cnt = memberRepository.validate(username, password);
        return cnt > 0;
    }

    private String generateApiKey() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678";
        int index = 0;
        String key = "";
        while(key.length() < 10) {
            index = (int) (str.length() * Math.random());
            key = key + str.charAt(index);
        }

        return key;
    }
}
