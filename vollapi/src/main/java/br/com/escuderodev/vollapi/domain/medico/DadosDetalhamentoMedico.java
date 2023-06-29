package br.com.escuderodev.vollapi.domain.medico;

import br.com.escuderodev.vollapi.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String telefone, String email, String crm, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico (Medico medico){
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
