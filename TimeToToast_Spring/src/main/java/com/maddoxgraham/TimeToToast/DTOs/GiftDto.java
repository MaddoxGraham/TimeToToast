package com.maddoxgraham.TimeToToast.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftDto {
private Long idGift;
    private String name;
    private String url;
    private String photo;
    private Long wanted;
    private Double price;

    private String categorie;
    private boolean isPaid;
    private Long event;
}
