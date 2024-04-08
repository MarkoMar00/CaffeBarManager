package hr.tvz.pios.caffebarmanager.service;

import hr.tvz.pios.caffebarmanager.dto.SpecialOfferDto;

import java.util.List;
import java.util.Optional;

public interface SpecialOfferService {
    List<SpecialOfferDto> getAllSpecialOffers();

    Optional<SpecialOfferDto> findByName(String name);

    Optional<SpecialOfferDto> saveOffer(SpecialOfferDto specialOfferDto);
}
