package br.edu.ifpb.padroes.atv3.musicas.facade;

import br.edu.ifpb.padroes.atv3.musicas.adapter.ServicoABCDAdapter;
import br.edu.ifpb.padroes.atv3.musicas.adapter.ServicoXPTOAdapter;
import br.edu.ifpb.padroes.atv3.musicas.decorator.*;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;
import br.edu.ifpb.padroes.atv3.musicas.proxy.ServicoMusicaProxy;
import br.edu.ifpb.padroes.atv3.musicas.servico.ServicoMusica;

import java.util.List;
import java.util.Map;

public class SistemaStreamMusicaFacade {
    private final ServicoMusica servicoABCD;
    private final ServicoMusica servicoXPTO;
    private final TocadorMusicaDecorator tocador;

    public SistemaStreamMusicaFacade() {
        // Configurando os serviços com proxy para cache
        this.servicoABCD = new ServicoMusicaProxy(new ServicoABCDAdapter());
        this.servicoXPTO = new ServicoMusicaProxy(new ServicoXPTOAdapter());
        
        // Configurando o tocador com decorators para funcionalidades adicionais
        TocadorMusicaDecorator tocadorBase = new TocadorMusicaBase();
        TocadorMusicaDecorator tocadorComContador = new ContadorReproducoesDecorator(tocadorBase);
        TocadorMusicaDecorator tocadorComArtistas = new EstatisticasArtistaDecorator(tocadorComContador);
        this.tocador = new EstatisticasGeneroDecorator(tocadorComArtistas);
    }

    // Métodos para buscar músicas
    public List<MusicaPadrao> listarMusicasBrasileiras() {
        return servicoABCD.listarMusicas();
    }

    public List<MusicaPadrao> listarMusicasInternacionais() {
        return servicoXPTO.listarMusicas();
    }

    public List<MusicaPadrao> listarTodasMusicas() {
        List<MusicaPadrao> musicas = servicoABCD.listarMusicas();
        musicas.addAll(servicoXPTO.listarMusicas());
        return musicas;
    }

    public List<MusicaPadrao> buscarPorArtista(String artista) {
        List<MusicaPadrao> musicas = servicoABCD.buscarPorArtista(artista);
        musicas.addAll(servicoXPTO.buscarPorArtista(artista));
        return musicas;
    }

    public List<MusicaPadrao> buscarPorGenero(String genero) {
        List<MusicaPadrao> musicas = servicoABCD.buscarPorGenero(genero);
        musicas.addAll(servicoXPTO.buscarPorGenero(genero));
        return musicas;
    }

    public MusicaPadrao buscarPorTitulo(String titulo) {
        MusicaPadrao musica = servicoABCD.buscarPorTitulo(titulo);
        if (musica == null) {
            musica = servicoXPTO.buscarPorTitulo(titulo);
        }
        return musica;
    }

    // Método para tocar música
    public void tocarMusica(MusicaPadrao musica) {
        tocador.tocarMusica(musica);
    }

    public void tocarMusicaPorTitulo(String titulo) {
        MusicaPadrao musica = buscarPorTitulo(titulo);
        if (musica != null) {
            tocarMusica(musica);
        } else {
            System.out.println("Música não encontrada: " + titulo);
        }
    }

    // Métodos para estatísticas
    public Map<String, Integer> getEstatisticasReproducoes() {
        return ContadorReproducoesDecorator.getEstatisticasReproducoes();
    }

    public String getArtistaMaisTocado() {
        return EstatisticasArtistaDecorator.getArtistaMaisTocado();
    }

    public Map<String, Integer> getEstatisticasArtistas() {
        return EstatisticasArtistaDecorator.getEstatisticasArtistas();
    }

    public String getGeneroMaisTocado() {
        return EstatisticasGeneroDecorator.getGeneroMaisTocado();
    }

    public Map<String, Integer> getEstatisticasGeneros() {
        return EstatisticasGeneroDecorator.getEstatisticasGeneros();
    }

    // Método para mostrar estatísticas
    public void mostrarEstatisticas() {
        System.out.println("Reproduções por música:");
        Map<String, Integer> estatisticas = getEstatisticasReproducoes();
        estatisticas.forEach((titulo, reproducoes) -> 
            System.out.println("- " + titulo + ": " + reproducoes + " reproduções"));
        
        System.out.println("\nArtista mais tocado: " + getArtistaMaisTocado());
        System.out.println("Gênero mais tocado: " + getGeneroMaisTocado());
    }

    // Método para limpar cache
    public void limparCache() {
        if (servicoABCD instanceof ServicoMusicaProxy) {
            ((ServicoMusicaProxy) servicoABCD).limparCache();
        }
        if (servicoXPTO instanceof ServicoMusicaProxy) {
            ((ServicoMusicaProxy) servicoXPTO).limparCache();
        }
    }
}
