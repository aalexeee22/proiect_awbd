package com.awbd.musicshop.dtos;


import com.awbd.musicshop.domain.Category;
import com.awbd.musicshop.domain.Currency;

import com.awbd.musicshop.domain.Info;
import com.awbd.musicshop.domain.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String code;
    private Double reservePrice;
    private Boolean restored;
    private Info info=new Info();
    private Participant seller;
    private List<Category> categories;
    private Currency currency;

}
