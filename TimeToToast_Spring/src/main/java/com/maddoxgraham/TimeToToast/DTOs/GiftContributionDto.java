package com.maddoxgraham.TimeToToast.DTOs;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftContributionDto {
    private Long idGiftContribution;
    private Double amount;
    private Long idGift;
    private Long idPerson;
}
