package com.ap.apassignment.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {

    @NotNull(message = "id는 필수 값입니다.")
    private Integer id;

    @NotBlank(message = "categoryName 값이 비어 있습니다.")
    private String categoryName;

}
