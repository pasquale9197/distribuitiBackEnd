package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControl;
import org.example.entity.File;
import org.example.entity.User;
import org.example.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
@Tag(name = "file", description = "API auth file")
public class FileController
{
    @Autowired
    private FileService fileService;

    @GetMapping("/tuttiFile")
    public ResponseEntity<List<File>> getAllFile()
    {   try
        {   return new ResponseEntity(fileService.listaFile(), HttpStatus.OK);
        }catch (RuntimeException e)
        {
            return new ResponseEntity("Empty list", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filePreferiti")
    public ResponseEntity<List<File>> getAllPreferiti()
    {   try
        {
            return new ResponseEntity(fileService.listaPreferiti(), HttpStatus.OK);
        }catch (RuntimeException e)
        {   return new ResponseEntity("Empty List", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filePerTipo")
    public ResponseEntity<List<File>> getFilePerTipo(@RequestParam String string)
    {   try
        {
            return new ResponseEntity(fileService.trovaFilePerTipo(string), HttpStatus.OK);
        }catch (RuntimeException e)
        {
            return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
        }
    }
}
