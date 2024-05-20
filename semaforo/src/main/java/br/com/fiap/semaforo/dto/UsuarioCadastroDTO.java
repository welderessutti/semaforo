package br.com.fiap.semaforo.dto;

import br.com.fiap.semaforo.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(

        Long id,

        @NotBlank(message = "O nome é obrigatório!")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "O e-mail do usuário não é válido!")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6, max = 16, message = "A senha deve conter entre 6 e 16 caracteres!")
        String senha,

        UsuarioRole role
) {
}
