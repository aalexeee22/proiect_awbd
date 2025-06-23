package com.awbd.musicshop.dtos;


import com.awbd.musicshop.domain.Category;
import com.awbd.musicshop.domain.Currency;

import com.awbd.musicshop.domain.Info;
import com.awbd.musicshop.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    @NotBlank(message = "Numele este obligatoriu")
    private String name;
    @NotBlank(message = "Codul este obligatoriu")
    private String code;
    @NotNull(message = "Prețul de rezervă este obligatoriu")
    @Positive(message = "Prețul de rezervă trebuie să fie mai mare decât 0")
    private Double reservePrice;
    private Boolean restored;
    private Info info=new Info();
    private Participant seller;
    private List<Category> categories;
    private Currency currency;

}
