package org.example.service;

import org.example.entity.File;
import org.example.entity.User;
import org.example.repositories.FileRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    @Transactional(readOnly = true)
    public List<File> listaFile()
    {   return fileRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(File file)
    {   fileRepository.saveAndFlush(file);

    }

    @Transactional(readOnly = false)
    public void aggiungiFile(User user, String typefile, String file, String titolo, String descrizione)
    {   File nuovofile = new File(user, typefile, file, titolo, descrizione);
        fileRepository.saveAndFlush(nuovofile);
    }

    @Transactional(readOnly = false)
    public void aggiungiFile(User user, String typefile, String file, String titolo)
    {   File nuovofile = new File(user, typefile, file, titolo);
        fileRepository.saveAndFlush(nuovofile);
    }

    @Transactional(readOnly = true)
    public List<File> trovaFilePerTipo(String tipo)
    {   List listaRet = new ArrayList();
        try
        {   if(!fileRepository.existsByTypefile(tipo))
                throw new RuntimeException("File doesn't exists!");
            else
                fileRepository.findByTypefile(tipo);
        }catch(RuntimeException e)
        {   List<File>listaFile = fileRepository.findAll();
            for(File f : listaFile)
            {   if(f.getTypefile().contains(tipo))
                    listaFile.add(f);
            }
        }
        return listaRet;
    }

    @Transactional(readOnly = true)
    public List<File> trovaFilePerTitolo(String titolo)
    {   List listaRet = new ArrayList();
        try
        {   if(!fileRepository.existsByTitolo(titolo))
                throw new RuntimeException("File doesn't exists!");
            else
                fileRepository.findByTitolo(titolo);
        }catch (RuntimeException e)
        {   List<File>listaFile = fileRepository.findAll();
            for(File f : listaFile)
            {   if(f.getTitolo().contains(titolo))
                    listaFile.add(f);
            }
        }
        return listaRet;
    }


    @Transactional(readOnly = true)
    public List<User> listaPreferiti()
    {   try
        {   if(fileRepository.findByListaUser().isEmpty())
                throw new RuntimeException("Empty List");
        }catch (RuntimeException e)
        {
            return fileRepository.findByListaUser();
        }
        return null;
    }

    @Transactional(readOnly = false)
    public void aggiungiPreferito(User u, File f)
    {   List<User> list = fileRepository.findById(f.getId()).getListaUser();
        list.add(u);
        fileRepository.findById(f.getId()).setListaUser(list);
        fileRepository.saveAndFlush(f);
    }

    @Transactional(readOnly = false)
    public void rimuoviPreferito(User u, File f)
    {   List<User> list = fileRepository.findById(f.getId()).getListaUser();
        list.remove(u);
        fileRepository.findById(f.getId()).setListaUser(list);
        fileRepository.saveAndFlush(f);
    }
}
