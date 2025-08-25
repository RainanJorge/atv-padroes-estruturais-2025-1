package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ContadorReproducoesDecorator extends TocadorMusicaAbstrato {
    private static final Map<String, AtomicInteger> contadorReproducoes = new ConcurrentHashMap<>();

    public ContadorReproducoesDecorator(TocadorMusicaDecorator tocador) {
        super(tocador);
    }

    @Override
    public void tocarMusica(MusicaPadrao musica) {
        super.tocarMusica(musica);
        
        String titulo = musica.getTitulo();
        contadorReproducoes.computeIfAbsent(titulo, k -> new AtomicInteger(0)).incrementAndGet();
        
        System.out.println("Reprodução #" + contadorReproducoes.get(titulo).get() + " da música: " + titulo);
    }

    public static int getReproducoes(String titulo) {
        return contadorReproducoes.getOrDefault(titulo, new AtomicInteger(0)).get();
    }

    public static Map<String, Integer> getEstatisticasReproducoes() {
        Map<String, Integer> estatisticas = new ConcurrentHashMap<>();
        contadorReproducoes.forEach((titulo, contador) -> estatisticas.put(titulo, contador.get()));
        return estatisticas;
    }
}
