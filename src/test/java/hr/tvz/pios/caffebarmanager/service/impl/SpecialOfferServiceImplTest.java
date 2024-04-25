package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.SpecialOffer;
import hr.tvz.pios.caffebarmanager.dto.SpecialOfferDto;
import hr.tvz.pios.caffebarmanager.repository.SpecialOfferRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SpecialOfferServiceImplTest {

    @Mock
    SpecialOfferRepository specialOfferRepository;

    SpecialOfferServiceImpl specialOfferService;

    AutoCloseable autoCloseable;

    SpecialOffer specialOffer;

    SpecialOfferDto specialOfferDto;
    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        specialOfferService=new SpecialOfferServiceImpl(specialOfferRepository);

        specialOffer=new SpecialOffer();
        specialOffer.setId(1L);
        specialOffer.setName("Coca-cola uz kavu");
        specialOffer.setDescription("Uz narudžbu kave dobije se Coca-cola");
        specialOffer.setPrice(BigDecimal.valueOf(12));
        specialOffer.setValidFrom(LocalDate.now());
        specialOffer.setValidTo(LocalDate.now().plusWeeks(1));

        specialOfferDto= mapToDto(specialOffer);
    }



    @Test
    void getAllSpecialOffers() {
        SpecialOffer specialOffer2= specialOffer;
        specialOffer2.setId(2L);
        specialOffer2.setName("Orangina uz kavu");
        specialOffer2.setDescription("Uz narudzbu ide orangina");

        ArrayList<SpecialOffer> offers= new ArrayList<>();
        offers.add(specialOffer);
        offers.add(specialOffer2);

        when(specialOfferRepository.findAll()).thenReturn(offers);

        List<SpecialOfferDto> result= specialOfferService.getAllSpecialOffers();

        assertThat(result.get(0).getName()).isEqualTo(specialOffer.getName());
        assertThat(result.get(0).getDescription()).isEqualTo(specialOffer.getDescription());
        assertThat(result.get(1).getName()).isEqualTo(specialOffer2.getName());
        assertThat(result.get(1).getDescription()).isEqualTo(specialOffer2.getDescription());
    }


    @Test
    void findByName() {
        when(specialOfferRepository.findByName(any())).thenReturn(Optional.ofNullable(specialOffer));

        Optional<SpecialOfferDto> result= specialOfferService.findByName(specialOffer.getName());

        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(specialOffer.getId());
        assertThat(result.get().getName()).isEqualTo(specialOffer.getName());
        assertThat(result.get().getDescription()).isEqualTo(specialOffer.getDescription());
        assertThat(result.get().getPrice()).isEqualTo(specialOffer.getPrice());
        assertThat(result.get().getValidFrom()).isEqualTo(specialOffer.getValidFrom());
        assertThat(result.get().getValidTo()).isEqualTo(specialOffer.getValidTo());
    }

    @Test
    void saveOffer() {
        when(specialOfferRepository.save(any(SpecialOffer.class))).thenReturn(specialOffer);

        Optional<SpecialOfferDto> result = specialOfferService.saveOffer(specialOfferDto);

        assertThat(result).isNotNull();
        assertThat(result.get().getId()).isEqualTo(specialOffer.getId());
        assertThat(result.get().getName()).isEqualTo(specialOffer.getName());
        assertThat(result.get().getDescription()).isEqualTo(specialOffer.getDescription());
        assertThat(result.get().getPrice()).isEqualTo(specialOffer.getPrice());
        assertThat(result.get().getValidFrom()).isEqualTo(specialOffer.getValidFrom());
        assertThat(result.get().getValidTo()).isEqualTo(specialOffer.getValidTo());
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

}