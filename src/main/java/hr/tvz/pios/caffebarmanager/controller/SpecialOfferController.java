package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.SpecialOfferDto;
import hr.tvz.pios.caffebarmanager.service.SpecialOfferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("specialOffer")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SpecialOfferController {
    SpecialOfferService specialOfferService;

    @GetMapping("/all")
    public List<SpecialOfferDto> getAllSpecialOffers() {
        return specialOfferService.getAllSpecialOffers();
    }

    @GetMapping("/")
    public ResponseEntity<SpecialOfferDto> findByName(@RequestParam String name){
        return specialOfferService.findByName(name)
                .map(
                        specialOffer -> ResponseEntity.status(HttpStatus.OK).body(specialOffer)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    @PostMapping("/create")
    public ResponseEntity<SpecialOfferDto> saveSpecialOffer(@RequestBody SpecialOfferDto specialOfferDto) {
        return specialOfferService.saveOffer(specialOfferDto)
                .map(
                        specialOffer -> ResponseEntity.status(HttpStatus.CREATED).body(specialOffer)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }
}
