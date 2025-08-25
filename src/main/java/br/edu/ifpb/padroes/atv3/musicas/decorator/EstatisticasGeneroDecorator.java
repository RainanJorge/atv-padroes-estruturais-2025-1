package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EstatisticasGeneroDecorator extends TocadorMusicaAbstrato {
    private static final Map<String, AtomicInteger> contadorGeneros = new ConcurrentHashMap<>();

    public EstatisticasGeneroDecorator(TocadorMusicaDecorator tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaPadrao musica) {
        super.tocarMusica(musica);
        
        String genero = musica.getGenero();
        contadorGeneros.computeIfAbsent(genero, k -> new AtomicInteger(0)).incrementAndGet();
        
        System.out.println("Gênero '" + genero + "' agora tem " + contadorGeneros.get(genero).get() + " reproduções");
    }

    public static String getGeneroMaisTocado() {
        return contadorGeneros.entrySet().stream()
                .max((e1, e2) -> Integer.compare(e1.getValue().get(), e2.getValue().get()))
                .map(Map.Entry::getKey)
                .orElse("Nenhum gênero");
    }

    public static Map<String, Integer> getEstatisticasGeneros() {
        Map<String, Integer> estatisticas = new ConcurrentHashMap<>();
        contadorGeneros.forEach((genero, contador) -> estatisticas.put(genero, contador.get()));
        return estatisticas;
    }
}
