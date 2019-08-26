package br.com.alura.jenkinsdemoproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RestTestController {

    @GetMapping
    public List<String> getAll() {
        return Arrays.asList("String 1", "String 2", "String 3");
    }

    @GetMapping(path = "/teste")
    public List<String> getAll1() {
        return Arrays.asList("String 1", "String 2", "String 3");
    }

}
