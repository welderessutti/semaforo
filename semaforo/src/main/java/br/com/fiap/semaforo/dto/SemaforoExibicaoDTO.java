package br.com.fiap.semaforo.dto;

import br.com.fiap.semaforo.model.Semaforo;

public record SemaforoExibicaoDTO(
        Long id,
        String localizacaoInstalacao,
        Integer tempoVerde,
        Integer tempoAmarelo,
        Integer tempoVermelho
) {
    public SemaforoExibicaoDTO(Semaforo semaforo) {
        this(
                semaforo.getId(),
                semaforo.getLocalizacaoInstalacao(),
                semaforo.getTempoVerde(),
                semaforo.getTempoAmarelo(),
                semaforo.getTempoVermelho()
        );
    }
}
