package br.com.escuderodev.vollapi.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
        @NotBlank(message = "Logradouto é obrigatório")
        String logradouro,
        String numero,
        String complemento,
        @NotBlank(message = "Bairro é obrigatório")
        String bairro,
        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank(message = "Cidade é obrigatória")
        String cidade,
        @NotBlank(message = "Estado é obrigatório")
        String uf) {
}
