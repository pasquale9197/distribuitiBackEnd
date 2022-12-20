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

    @GetMapping("/filePerTipo")
    public ResponseEntity<List<File>> getFilePerTitolo(@RequestParam String string)
    {   try
        {
            return new ResponseEntity(fileService.trovaFilePerTitolo(string), HttpStatus.OK);
        }catch (RuntimeException e)
        {
            return new ResponseEntity("FIle non esistente", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/aggiungiFile")
    public ResponseEntity<String> aggiungiFile(@RequestBody File file)
    {   try
        {   fileService.aggiungiFile(file);
            return new ResponseEntity("File aggiunto", HttpStatus.OK);
        }catch(RuntimeException e)
        {
            return  new ResponseEntity("Qualcosa è andato storto", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/aggiungiPreferito")
    public ResponseEntity<String> aggiungiPreferito(@RequestBody File file, @RequestBody User user)
    {   try
        {   fileService.aggiungiPreferito(user, file);
            return new ResponseEntity<String>("File aggiunto con successo ai preferiti", HttpStatus.OK);
        }catch(RuntimeException e)
        {   return new ResponseEntity<String>("File non aggiunto", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rimuoviPreferito")
    public ResponseEntity<String> rimuoviPreferito(@RequestBody File file, @RequestBody User user)
    {   try
        {   fileService.rimuoviPreferito(user, file);
            return new ResponseEntity<String>("FIle rimosso con successo", HttpStatus.OK);
        }catch(RuntimeException e)
        {
            return new ResponseEntity<String>("Qualcosa è andato storto", HttpStatus.BAD_REQUEST);
        }
    }
}
