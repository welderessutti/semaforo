package br.com.fiap.semaforo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SemaforoAtualizacaoDTO(

        @NotNull(message = "O ID é obrigatório!")
        Long id,

        @NotBlank(message = "O endereço de localização é obrigatório!")
        String localizacaoInstalacao,

        @NotNull(message = "O tempo verde é obrigatório!")
        Integer tempoVerde,

        @NotNull(message = "O tempo amarelo é obrigatório!")
        Integer tempoAmarelo,

        @NotNull(message = "O tempo amarelo é obrigatório!")
        Integer tempoVermelho
) {
}
