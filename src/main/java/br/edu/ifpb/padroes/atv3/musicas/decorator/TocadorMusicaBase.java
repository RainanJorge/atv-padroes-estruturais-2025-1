package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;
import br.edu.ifpb.padroes.atv3.musicas.servico.MusicaNaoEncontradaException;

public class TocadorMusicaBase implements TocadorMusicaDecorator {

    @Override
    public void tocarMusica(MusicaPadrao musica) {
        if (musica == null) {
            throw new MusicaNaoEncontradaException();
        }

        System.out.println("Tocando música: " + musica.getTitulo());
    }
}
