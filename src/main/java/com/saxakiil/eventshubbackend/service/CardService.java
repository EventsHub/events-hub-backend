package com.saxakiil.eventshubbackend.service;

import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.repository.CardRepository;
import com.saxakiil.eventshubbackend.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardService {

    private final CardRepository cardRepository;

    public Card add(Card card) {
        if (card.getId() == null) {
            return cardRepository.save(card);
        }
        throw new RuntimeException();
    }

    public Page<Card> getCardsOnPage(final int pageNumber, final int pageSize, final boolean isPublished) {
        Pageable paging = PageRequest.of(pageNumber, pageSize);
        return cardRepository.findByPublished(isPublished, paging);
    }

    public Card getById(final Long id) {
        final Optional<Card> card = cardRepository.findById(id);
        if (card.isPresent()) {
            return card.get();
        }
        throw new RuntimeException();
    }

    public String getPublicId(final Long id) {
        if (cardRepository.findById(id).isPresent()) {
            final String urlImage = cardRepository.findById(id).get().getUrlImage();
            return Utils.getPublicId(urlImage);
        }
        throw new RuntimeException();
    }

    public Card update(final Card card) {
        if (card.getId() != null) {
            return cardRepository.save(card);
        }
        throw new RuntimeException();
    }

    @SneakyThrows
    public void deleteById(final Long id) {
        if (cardRepository.findById(id).isPresent()) {
            cardRepository.deleteById(id);
        } else {
            throw new RuntimeException();
        }
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
