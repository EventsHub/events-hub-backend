package com.saxakiil.eventshubbackend.controller;

import com.saxakiil.eventshubbackend.dto.auth.MessageResponse;
import com.saxakiil.eventshubbackend.dto.card.CardRequest;
import com.saxakiil.eventshubbackend.manager.CdnManager;
import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.service.CardService;
import com.saxakiil.eventshubbackend.service.UserService;
import com.saxakiil.eventshubbackend.transformer.CardTransformer;
import com.saxakiil.eventshubbackend.util.Utils;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import static com.saxakiil.eventshubbackend.util.Constants.*;

@Slf4j
@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {

    private final CardService cardService;
    private final UserService userService;
    private final CdnManager cdnManager;
    private final CardTransformer cardTransformer;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PreAuthorize(value = "hasAnyRole('MODERATOR')")
    public ResponseEntity<MessageResponse> add(@ModelAttribute CardRequest cardRequest) {
        final String url = cdnManager.uploadFile(cardRequest.getImageFile());
        try {
            final Card addedCard = cardService.add(cardTransformer.transform(cardRequest, url));
            log.info(String.format(CARD_IS_ADDED, addedCard.getId()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            cdnManager.deleteFile(Utils.getPublicId(url));
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


//        UserDetailsImpl moderator = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal());

//        if (userService.getById(moderator.getId()).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    @DeleteMapping(value = "/delete")
    //@PreAuthorize("hasAnyRole('MODERATOR')")
    public ResponseEntity<Long> delete(@NonNull @RequestParam Long id) {
        try {
            final String publicId = cardService.getPublicId(id);
            cardService.deleteById(id);
            cdnManager.deleteFile(publicId);
            log.info(String.format(CARD_IS_DELETED, id));
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<Card> getElement(@NonNull @RequestParam Long id) {
        try {
            return new ResponseEntity<>(cardService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@PreAuthorize(value = "hasAnyRole('MODERATOR')")
    public ResponseEntity<MessageResponse> update(@NotNull @RequestParam Long id,
                                                  @ModelAttribute CardRequest cardRequest) {
        try {
            final String currentUrl = cardService.getById(id).getUrlImage();
            final String newUrl = cdnManager.uploadFile(cardRequest.getImageFile());
            final Card updatedCard = cardService.update(cardTransformer.transform(cardRequest, newUrl, id));
            if (!currentUrl.equals(newUrl)) {
                cdnManager.deleteFile(Utils.getPublicId(currentUrl));
            }
            log.info(String.format(CARD_IS_UPDATED, updatedCard.getId()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

//        UserDetailsImpl moderator = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal());

//        if (userService.getById(moderator.getId()).isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }
}
