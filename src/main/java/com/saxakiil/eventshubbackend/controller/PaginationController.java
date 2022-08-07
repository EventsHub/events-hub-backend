package com.saxakiil.eventshubbackend.controller;

import com.saxakiil.eventshubbackend.model.Card;
import com.saxakiil.eventshubbackend.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashMap;
import java.util.Map;

import static com.saxakiil.eventshubbackend.util.Constants.PAGE_SIZE;

@Slf4j
@RestController
@RequestMapping("/api/pagination")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaginationController {

    private final CardService cardService;

    @GetMapping("/getPage")
    public ResponseEntity<Map<String, Object>> getPage(
            @PositiveOrZero @RequestParam Integer pageNumber,
            @RequestParam(defaultValue = "true") Boolean published,
            @Positive @RequestParam(defaultValue = PAGE_SIZE) Integer pageSize) {
        try {
            Page<Card> paginationList = cardService.getCardsOnPage(pageNumber,
                    pageSize, published);

            Map<String, Object> response = new HashMap<>();
            response.put("cards", paginationList.getContent());
            response.put("currentPage", paginationList.getNumber());
            response.put("totalItems", paginationList.getTotalElements());
            response.put("totalPages", paginationList.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
