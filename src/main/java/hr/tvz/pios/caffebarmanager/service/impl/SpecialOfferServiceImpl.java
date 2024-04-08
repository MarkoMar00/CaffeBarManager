package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.SpecialOffer;
import hr.tvz.pios.caffebarmanager.dto.SpecialOfferDto;
import hr.tvz.pios.caffebarmanager.repository.SpecialOfferRepository;
import hr.tvz.pios.caffebarmanager.service.SpecialOfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecialOfferServiceImpl implements SpecialOfferService {
    SpecialOfferRepository specialOfferRepository;


    @Override
    public List<SpecialOfferDto> getAllSpecialOffers() {
        return specialOfferRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<SpecialOfferDto> findByName(String name) {
        return specialOfferRepository.findByName(name).map(this::mapToDto);
    }

    @Override
    public Optional<SpecialOfferDto> saveOffer(SpecialOfferDto specialOfferDto) {
        return Optional.of(mapToDto(specialOfferRepository.save(mapToSpecialOffer(specialOfferDto))));
    }

    private SpecialOfferDto mapToDto(SpecialOffer specialOffer) {
        SpecialOfferDto specialOfferDto = new SpecialOfferDto();

        specialOfferDto.setId(specialOffer.getId());
        specialOfferDto.setName(specialOffer.getName());
        specialOfferDto.setDescription(specialOffer.getDescription());
        specialOfferDto.setPrice(specialOffer.getPrice());
        specialOfferDto.setValidFrom(specialOffer.getValidFrom());
        specialOfferDto.setValidTo(specialOffer.getValidTo());

        return specialOfferDto;
    }

    private SpecialOffer mapToSpecialOffer(SpecialOfferDto specialOfferDto) {
        SpecialOffer specialOffer = new SpecialOffer();

        specialOffer.setId(specialOfferDto.getId());
        specialOffer.setName(specialOfferDto.getName());
        specialOffer.setDescription(specialOfferDto.getDescription());
        specialOffer.setPrice(specialOfferDto.getPrice());
        specialOffer.setValidFrom(specialOfferDto.getValidFrom());
        specialOffer.setValidTo(specialOfferDto.getValidTo());

        return specialOffer;
    }
}
