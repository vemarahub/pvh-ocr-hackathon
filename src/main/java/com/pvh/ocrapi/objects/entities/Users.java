package com.pvh.ocrapi.objects.entities;

import lombok.*;


@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    String userId;
    String userName;
    String reviewSource;
    int loyaltyPoints;
}
