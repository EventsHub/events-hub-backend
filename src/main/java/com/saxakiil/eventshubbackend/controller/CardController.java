package com.saxakiil.eventshubbackend.controller;

import com.saxakiil.eventshubbackend.dto.auth.MessageResponse;
import com.saxakiil.eventshubbackend.dto.card.CardRequest;
import com.saxakiil.eventshubbackend.manager.CdnManager;
import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.service.CardService;
import com.saxakiil.eventshubbackend.transformer.CardTransformer;
import com.saxakiil.eventshubbackend.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.saxakiil.eventshubbackend.util.Constants.*;

@Slf4j
@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CardController {

    private final CardService cardService;
    private final CdnManager cdnManager;
    private final CardTransformer cardTransformer;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> add(@Valid CardRequest cardRequest,
                                               @ModelAttribute MultipartFile imageFile) {
        final String url = cdnManager.uploadFile(imageFile);
        try {
            final Card addedCard = cardService.add(cardTransformer.transform(cardRequest, url));
            log.info(String.format(CARD_IS_ADDED, addedCard.getId()));
            return new ResponseEntity<>(new MessageResponse("Card is created"), HttpStatus.OK);
        } catch (Exception e) {
            cdnManager.deleteFile(Utils.getPublicId(url));
            log.error(WRONG_OPERATION_EXCEPTION, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> delete(@NotNull @PositiveOrZero @RequestParam Long id) {
        try {
            final String publicId = cardService.getPublicId(id);
            cardService.deleteById(id);
            cdnManager.deleteFile(publicId);
            log.info(String.format(CARD_IS_DELETED, id));
            return new ResponseEntity<>(new MessageResponse("Card is deleted"), HttpStatus.OK);
        } catch (Exception e) {
            log.error(WRONG_OPERATION_EXCEPTION, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<Card> getElement(@NotNull @PositiveOrZero @RequestParam Long id) {
        try {
            return new ResponseEntity<>(cardService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(WRONG_OPERATION_EXCEPTION, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> update(@NotNull @PositiveOrZero @RequestParam Long id,
                                                  @Valid CardRequest cardRequest,
                                                  @NotNull @RequestParam MultipartFile file) {
        if (cardService.getById(id) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            final String currentUrl = cardService.getById(id).getUrlImage();
            final String newUrl = cdnManager.uploadFile(file);
            final Card updatedCard = cardService.update(cardTransformer.transform(cardRequest, newUrl, id));
            if (!currentUrl.equals(newUrl)) {
                cdnManager.deleteFile(Utils.getPublicId(currentUrl));
            }
            log.info(String.format(CARD_IS_UPDATED, updatedCard.getId()));
            return new ResponseEntity<>(new MessageResponse("Card is updated"), HttpStatus.OK);
        } catch (Exception e) {
            log.error(WRONG_OPERATION_EXCEPTION, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/publishById")
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> publishById(@NotNull @PositiveOrZero @RequestParam Long id) {
        try {
            return cardService.publishById(id) ? new ResponseEntity<>(new MessageResponse("Card is published"),
                    HttpStatus.OK) : new ResponseEntity<>(new MessageResponse("Card already is published"),
                    HttpStatus.BAD_REQUEST);
        } catch (UnsupportedOperationException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(WRONG_OPERATION_EXCEPTION, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
