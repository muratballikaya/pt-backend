package com.softtech.personalitytest.ptbackend.controller;

import com.softtech.personalitytest.ptbackend.dto.setup.SetupDto;
import com.softtech.personalitytest.ptbackend.service.SetupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/setup")
@Api(value = "Personal Test Setup Api documentation")
public class SetupController {

    private final String SUCCESS = "Test has been created";

    @Autowired
    SetupService setupService;

    @PostMapping
    @ApiOperation(value = "Generates new test")
    public ResponseEntity<String> saveTest(@RequestBody @Valid SetupDto setupDto) {
        return new ResponseEntity<String>(setupService.setup(setupDto), HttpStatus.CREATED);
    }

}
