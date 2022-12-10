package com.prehitting.security.controller;

import com.prehitting.common.model.CommonResult;
import com.prehitting.db.model.UmUser;
import com.prehitting.db.service.UmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author 24809
 * @Date 2022/12/5 19:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UmUserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/hello")
    public String hello() {
        System.out.println();
        return "hello , admin ";
    }

//    @PreAuthorize("hasAuthority('user:all')")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/all")
    public CommonResult<List<UmUser>> getAllUsers() {
        List<UmUser> list = userService.list();
        return CommonResult.success(list);
    }

    @GetMapping("/bye")
    public CommonResult<String> bye() {
        return CommonResult.success("bye");
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<String> deleteUser(@PathVariable String id) {
        return CommonResult.success("delete "+id);
    }
}
