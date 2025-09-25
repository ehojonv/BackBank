package br.com.fiap.BackBank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    private final Map<String, List<String>> project = new HashMap<>();

    @GetMapping
    public Map<String,List<String>> getProjectMap() {
        project.put("nameProject", List.of("BackBank"));
        project.put("students", List.of("João Vinicius Alves", "Matheus Mariotto"));
        project.put("rm", List.of("rm559369", "rm560376"));

        return project;
    }
    
}
