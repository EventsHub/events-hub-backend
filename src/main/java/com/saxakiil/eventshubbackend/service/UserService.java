package com.saxakiil.eventshubbackend.service;

import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.model.User;
import com.saxakiil.eventshubbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getById(final Long id) {
        return userRepository.findById(id);
    }
//
//    public Optional<User> getByUsername(String userName) {
//        return userRepository.findByUsername(userName);
//    }
//
//    public boolean addOrganizeInfo(Long organizeId, Card card) {
//        if (userRepository.findById(organizeId).isPresent()) {
//            User organize = userRepository.findById(organizeId).get();
//            organize.getOrganizeCard().add(card);
//            userRepository.save(organize);
//            return true;
//        }
//        return false;
//    }

    public boolean addFavoriteCard(Long userId, Card card) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            if (user.getFavoriteCards().contains(card)) {
                return false;
            }
            user.getFavoriteCards().add(card);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
