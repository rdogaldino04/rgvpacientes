    package com.galdino.rgvpacientes.dto.product;

    import lombok.*;

    import javax.validation.constraints.NotNull;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProductBatchUpdateDTO {

        @NotNull
        private Long id;
        private String name;

    }
