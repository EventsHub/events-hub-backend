package com.saxakiil.eventshubbackend.service;

import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.model.User;
import com.saxakiil.eventshubbackend.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardService {

    private final CardRepository cardRepository;

    public Page<Card> getCardsOnPage(int pageNumber, int pageSize, boolean isPublished) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return cardRepository.findByPublished(isPublished, paging);
    }

    public boolean deleteById(Long id) {
        if (cardRepository.findById(id).isPresent()) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    public boolean publishByID(Long id) {
//        if (cardRepository.findById(id).isPresent()) {
//            Card changedElem = cardRepository.findById(id).get();
//            changedElem.setPublished(true);
//            cardRepository.save(changedElem);
//            return true;
//        }
//        return false;
//    }

    public Card addNewCard(Card card) {
        if (card.getId() != null) {
            return new Card();
        }
        return cardRepository.save(card);
    }

    public Optional<Card> getById(Long id) {
        return cardRepository.findById(id);
    }

//    public boolean addUserInLikedList(User user, Long id) {
//        if (cardRepository.findById(id).isPresent()) {
//            Card card = cardRepository.findById(id).get();
//            if (card.getLikedUsers().contains(user)) {
//                return false;
//            }
//            card.getLikedUsers().add(user);
//            cardRepository.save(card);
//            return true;
//        }
//        return false;
//    }
}
