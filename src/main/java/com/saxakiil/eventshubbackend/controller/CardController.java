package com.saxakiil.eventshubbackend.controller;

import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.service.CardService;
import com.saxakiil.eventshubbackend.service.UserDetailsImpl;
import com.saxakiil.eventshubbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.saxakiil.eventshubbackend.util.Utils.*;

@Slf4j
@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {

    private final CardService cardService;
    private final UserService userService;

    @SneakyThrows
    @PostMapping(value = "/add")
    //@PreAuthorize(value = "hasAnyRole('MODERATOR')")
    public ResponseEntity<Long> add(@NonNull @RequestBody Card card) {
//        UserDetailsImpl moderator = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal());

//        if (userService.getById(moderator.getId()).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        Card addedCard = cardService.addNewCard(card);
        if (addedCard.getId() != null) {
            log.info(String.format(CARD_IS_ADDED, addedCard.getId()));
            return new ResponseEntity<>(addedCard.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    @DeleteMapping(value = "/delete")
    //@PreAuthorize("hasAnyRole('MODERATOR')")
    public ResponseEntity<Long> delete(@NonNull @RequestParam Long id) {
        boolean isDeleted = cardService.deleteById(id);
        if (isDeleted) {
            log.info(String.format(CARD_IS_DELETED, id));
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @SneakyThrows
    @GetMapping(value = "/get")
    public ResponseEntity<Card> getElement(@NonNull @RequestParam Long id) {
        if (cardService.getById(id).isPresent()) {
            return new ResponseEntity<>(cardService.getById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
