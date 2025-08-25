package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EstatisticasArtistaDecorator extends TocadorMusicaAbstrato {
    private static final Map<String, AtomicInteger> contadorArtistas = new ConcurrentHashMap<>();

    public EstatisticasArtistaDecorator(TocadorMusicaDecorator tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaPadrao musica) {
        super.tocarMusica(musica);
        
        String artista = musica.getArtista();
        contadorArtistas.computeIfAbsent(artista, k -> new AtomicInteger(0)).incrementAndGet();
        
        System.out.println("Artista '" + artista + "' agora tem " + contadorArtistas.get(artista).get() + " reproduções");
    }

    public static String getArtistaMaisTocado() {
        return contadorArtistas.entrySet().stream()
                .max((e1, e2) -> Integer.compare(e1.getValue().get(), e2.getValue().get()))
                .map(Map.Entry::getKey)
                .orElse("Nenhum artista");
    }

    public static Map<String, Integer> getEstatisticasArtistas() {
        Map<String, Integer> estatisticas = new ConcurrentHashMap<>();
        contadorArtistas.forEach((artista, contador) -> estatisticas.put(artista, contador.get()));
        return estatisticas;
    }
}
