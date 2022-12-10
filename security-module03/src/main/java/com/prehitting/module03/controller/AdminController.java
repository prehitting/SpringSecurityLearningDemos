package com.prehitting.module03.controller;

import com.prehitting.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminController
 * @Description api of admin
 * @Author 24809
 * @Date 2022-12-10 11:07
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @GetMapping("/hello")
    public CommonResult<String> hello() {
        log.info("hello request");
        return CommonResult.success("hello");
    }
}
