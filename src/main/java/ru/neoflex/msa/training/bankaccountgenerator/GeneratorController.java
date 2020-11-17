package ru.neoflex.msa.training.bankaccountgenerator;

import org.springframework.web.bind.annotation.*;

@RestController
public class GeneratorController {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 200;

    @GetMapping
    public BaseResponse generateBankAccount() {
        return new BaseResponse(SUCCESS_STATUS, CODE_SUCCESS);
    }
}
