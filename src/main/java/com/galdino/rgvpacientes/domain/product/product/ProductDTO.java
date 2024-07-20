    package com.galdino.rgvpacientes.domain.product.product;

    import lombok.*;

    import java.time.LocalDate;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProductDTO {

        private Long id;
        private String name;
        private LocalDate createdAt;

    }
