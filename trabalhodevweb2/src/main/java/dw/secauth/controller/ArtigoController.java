package dw.secauth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dw.secauth.model.Artigo;
import dw.secauth.repository.ArtigoRepository;

@RestController
//@RequestMapping("/api")
public class ArtigoController {

    @Autowired
    ArtigoRepository rep;
    @PostMapping("/cadastrar")
    public ResponseEntity<Artigo> createArtigo(Artigo ar)
    {   
        try {
            Artigo _a = rep.save(new Artigo(ar.getTitulo(), ar.getResumo(), ar.isPublicado()));

            return new ResponseEntity<>(_a, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/deletar")
    public ResponseEntity<HttpStatus> deleteAllArtigo()
    {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

   
    @GetMapping("/listagem-de-artigos")
    public ModelAndView getArtigos() {
        ModelAndView mv = new ModelAndView("listar/listar");
        List<Artigo> listar = rep.findAll();
        mv.addObject("listar", listar);
        return mv; 
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirArtigos(@PathVariable(value="id") long id){
        
        Artigo art = rep.findArtigoById(id);
        rep.delete(art);
        return "Artigo Excluido!";
    }
    

}

   
    



