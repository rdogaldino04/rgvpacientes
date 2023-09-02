package com.galdino.rgvpacientes;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class AgendaTest {

    @Test
    void test() {
        List<Dia> dias = new ArrayList<>(Arrays.asList(
                new Dia(DayOfWeek.MONDAY, "08:00", "18:00"),
                new Dia(DayOfWeek.TUESDAY, "08:00", "18:00")));

        LocalDate dataInicioForm = LocalDate.now();
        LocalDate dataFinalForm = dataInicioForm.plusDays(17);

        if (dataFinalForm.isBefore(dataInicioForm)) {
            throw new IllegalArgumentException("A data é anterior à data atual");
        }

        long totalDias = DAYS.between(dataInicioForm, dataFinalForm);

        // Percorre todas as datas entre as datas de início e fim
        for (LocalDate data = dataInicioForm; !data.isAfter(dataFinalForm); data = data.plusDays(1)) {
            // Obtém o dia da semana correspondente a cada data
            DayOfWeek diaDaSemana = data.getDayOfWeek();

            Optional<Dia> findFirst = dias.stream().filter(a -> a.diaSemana.equals(diaDaSemana))
                    .findFirst();

            // Imprime o dia da semana e a data correspondente
            if (findFirst.isPresent()) {
                System.out.println(diaDaSemana + " - " + data);
                Dia dia = findFirst.get();
                dia.setDataDia(data);
                System.out.println(dia);
                System.out.println();
            }
        }

        assertEquals(17, totalDias, "total de dias");
    }

    public class Agenda {

        public Long id;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public class Dia {

        private DayOfWeek diaSemana;
        private String horaInicio;
        private String horaFim;
        private LocalDate dataDia;

        public Dia(DayOfWeek diaSemana, String horaInicio, String horaFim) {
            super();
            this.diaSemana = diaSemana;
            this.horaInicio = horaInicio;
            this.horaFim = horaFim;
        }

    }

}
