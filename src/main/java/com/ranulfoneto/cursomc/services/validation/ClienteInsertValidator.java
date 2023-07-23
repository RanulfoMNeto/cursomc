package com.ranulfoneto.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.ranulfoneto.cursomc.domain.enums.TipoCliente;
import com.ranulfoneto.cursomc.dto.ClienteNewDTO;
import com.ranulfoneto.cursomc.resources.exception.FieldMessage;
import com.ranulfoneto.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}