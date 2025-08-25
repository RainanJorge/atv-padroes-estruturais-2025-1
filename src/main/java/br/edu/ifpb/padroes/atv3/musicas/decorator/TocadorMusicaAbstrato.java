package br.edu.ifpb.padroes.atv3.musicas.decorator;

import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

public abstract class TocadorMusicaAbstrato implements TocadorMusicaDecorator {
    protected final TocadorMusicaDecorator tocador;

    public TocadorMusicaAbstrato(TocadorMusicaDecorator tocador) {
        this.tocador = tocador;
    }

    @Override
    public void tocarMusica(MusicaPadrao musica) {
        tocador.tocarMusica(musica);
    }
}
