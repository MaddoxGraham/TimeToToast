package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenRequestDto {

    private String refreshToken;
}

