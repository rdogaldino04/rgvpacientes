    package com.galdino.rgvpacientes.domain.batch.dto;

    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class BatchFilter {

        private Long id;
        private String batchNumber;
        private Long productId;

    }
