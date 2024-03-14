package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/validation/api/item")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("Call API Controller={}");

        if (bindingResult.hasErrors()) {
            log.info("validation error occurs={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("Run Success Logic");
        return form;
    }
}
