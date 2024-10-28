package com.bootcamp.demo.bc_yahoo_finance.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteResponseDTO {
    private List<SymbolDTO> result;
    private Object error;
}

