    package com.galdino.rgvpacientes.model;

    import lombok.*;
    import org.hibernate.annotations.CreationTimestamp;

    import javax.persistence.*;
    import java.time.LocalDate;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Entity
    @Table(name = "product", schema = "dbapatient")
    public class Product {

        @EqualsAndHashCode.Include
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")
        private Long id;

        @Column(name = "product_name")
        private String name;

        @CreationTimestamp
        @Column(name = "created_at")
        private LocalDate createdAt;

        public Product() {
        }

        public Product(Long id) {
            this.id = id;
        }
    }
