package br.edu.ifpb.padroes.atv3.musicas.adapter;

import br.edu.ifpb.padroes.atv3.musicas.abcd.ClienteHttpABCD;
import br.edu.ifpb.padroes.atv3.musicas.abcd.Musica;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaImpl;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusica;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoABCDAdapter implements ServicoMusica {
    private final ClienteHttpABCD clienteABCD;

    public ServicoABCDAdapter() {
        this.clienteABCD = new ClienteHttpABCD();
    }

    @Override
    public List<MusicaPadrao> listarMusicas() {
        List<Musica> musicasABCD = clienteABCD.listarMusicas();
        return musicasABCD.stream()
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

    private MusicaPadrao adaptarMusica(Musica musicaABCD) {
        return new MusicaImpl(
                musicaABCD.id(),
                musicaABCD.titulo(),
                musicaABCD.artista(),
                musicaABCD.ano(),
                musicaABCD.genero()
        );
    }
}
