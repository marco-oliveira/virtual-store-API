package com.marco.virtualstore.services.validations;

import com.marco.virtualstore.domains.enums.TipoCliente;
import com.marco.virtualstore.dtos.NewClienteDto;
import com.marco.virtualstore.resources.exception.FieldMessage;
import com.marco.virtualstore.services.validations.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NewClienteDto> {
    @Override
    public void initialize(ClienteInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(NewClienteDto newClienteDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (newClienteDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) &&
                !BR.isValidCPF(newClienteDto.getCpfOuCnpj())) list.add(new FieldMessage("cpfOuCnpj", "CPF inválido."));
        if (newClienteDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) &&
                !BR.isValidCNPJ(newClienteDto.getCpfOuCnpj())) list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido."));

        for (FieldMessage e: list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
