package br.edu.ifpb.padroes.atv3.musicas.proxy;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusica;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ServicoMusicaProxy implements ServicoMusica {
    private final ServicoMusica servicoReal;
    private final Map<String, List<MusicaPadrao>> cacheMusicas = new ConcurrentHashMap<>();
    private final Map<String, List<MusicaPadrao>> cacheArtistas = new ConcurrentHashMap<>();
    private final Map<String, List<MusicaPadrao>> cacheGeneros = new ConcurrentHashMap<>();
    private final Map<String, MusicaPadrao> cacheTitulos = new ConcurrentHashMap<>();

    public ServicoMusicaProxy(ServicoMusica servicoReal) {
        this.servicoReal = servicoReal;
    }

    @Override
    public List<MusicaPadrao> listarMusicas() {
        String chave = "todas_musicas";
        if (cacheMusicas.containsKey(chave)) {
            System.out.println("Retornando músicas do cache...");
            return cacheMusicas.get(chave);
        }

        System.out.println("Buscando músicas do serviço real...");
        List<MusicaPadrao> musicas = servicoReal.listarMusicas();
        cacheMusicas.put(chave, musicas);
        return musicas;
    }

    @Override
    public List<MusicaPadrao> buscarPorArtista(String artista) {
        if (cacheArtistas.containsKey(artista)) {
            System.out.println("Retornando busca por artista do cache: " + artista);
            return cacheArtistas.get(artista);
        }

        System.out.println("Buscando por artista no serviço real: " + artista);
        List<MusicaPadrao> musicas = servicoReal.buscarPorArtista(artista);
        cacheArtistas.put(artista, musicas);
        return musicas;
    }

    @Override
    public List<MusicaPadrao> buscarPorGenero(String genero) {
        if (cacheGeneros.containsKey(genero)) {
            System.out.println("Retornando busca por gênero do cache: " + genero);
            return cacheGeneros.get(genero);
        }

        System.out.println("Buscando por gênero no serviço real: " + genero);
        List<MusicaPadrao> musicas = servicoReal.buscarPorGenero(genero);
        cacheGeneros.put(genero, musicas);
        return musicas;
    }

    @Override
    public MusicaPadrao buscarPorTitulo(String titulo) {
        if (cacheTitulos.containsKey(titulo)) {
            System.out.println("Retornando busca por título do cache: " + titulo);
            return cacheTitulos.get(titulo);
        }

        System.out.println("Buscando por título no serviço real: " + titulo);
        MusicaPadrao musica = servicoReal.buscarPorTitulo(titulo);
        if (musica != null) {
            cacheTitulos.put(titulo, musica);
        }
        return musica;
    }

    public void limparCache() {
        cacheMusicas.clear();
        cacheArtistas.clear();
        cacheGeneros.clear();
        cacheTitulos.clear();
        System.out.println("Cache limpo!");
    }
}
