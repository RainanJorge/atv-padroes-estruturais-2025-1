package br.edu.ifpb.padroes.atv3.musicas.servico;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;
import java.util.List;

public interface ServicoMusica {
    List<MusicaPadrao> listarMusicas();
    List<MusicaPadrao> buscarPorArtista(String artista);
    List<MusicaPadrao> buscarPorGenero(String genero);
    MusicaPadrao buscarPorTitulo(String titulo);
}
