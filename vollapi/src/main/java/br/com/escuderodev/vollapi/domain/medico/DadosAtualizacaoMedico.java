package br.com.escuderodev.vollapi.domain.medico;

import br.com.escuderodev.vollapi.domain.endereco.DadosCadastroEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosCadastroEndereco endereco) {
}
