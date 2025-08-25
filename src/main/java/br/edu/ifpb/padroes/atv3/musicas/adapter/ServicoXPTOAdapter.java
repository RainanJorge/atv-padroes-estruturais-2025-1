package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.xpto.ClientHttpXPTO;
import br.edu.ifpb.padroes.atv3.musicas.xpto.Song;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaImpl;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusica;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoXPTOAdapter implements ServicoMusica {
    private final ClientHttpXPTO clienteXPTO;

    public ServicoXPTOAdapter() {
        this.clienteXPTO = new ClientHttpXPTO();
    }

    @Override
    public List<MusicaPadrao> listarMusicas() {
        List<Song> songs = clienteXPTO.findAll();
        return songs.stream()
                .map(this::adaptarMusica)
                .collect(Collectors.toList());
    }

    @Override
    public List<MusicaPadrao> buscarPorArtista(String artista) {
        return listarMusicas().stream()
                .filter(musica -> musica.getArtista().toLowerCase().contains(artista.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MusicaPadrao> buscarPorGenero(String genero) {
        return listarMusicas().stream()
                .filter(musica -> musica.getGenero().toLowerCase().contains(genero.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public MusicaPadrao buscarPorTitulo(String titulo) {
        return listarMusicas().stream()
                .filter(musica -> musica.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    private MusicaPadrao adaptarMusica(Song song) {
        return new MusicaImpl(
                song.id(),
                song.title(),
                song.artist(),
                song.year(),
                song.genre()
        );
    }
}
