package com.example.GestionSoutenances.dto;

import com.example.GestionSoutenances.enums.EnumMention;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultatDto {
    private float moyenne;
    private EnumMention mention;

}
