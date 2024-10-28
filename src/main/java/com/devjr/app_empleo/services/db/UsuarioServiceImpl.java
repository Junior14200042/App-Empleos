package com.devjr.app_empleo.services.db;

import com.devjr.app_empleo.model.Usuario;
import com.devjr.app_empleo.repository.UsuariosRepository;
import com.devjr.app_empleo.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Override
    public List<Usuario> buscarTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public void guardar(Usuario usuario) {
        usuariosRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        usuariosRepository.deleteById(id);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        return usuariosRepository.findByUsername(username);

    }
}
