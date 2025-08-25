package br.edu.ifpb.padroes.atv3.musicas.xpto;

import java.util.Arrays;
import java.util.List;

public class ClientHttpXPTO {

    public List<Song> findAll() {
        // Dados fictícios do serviço internacional
        return Arrays.asList(
            new Song("1", "Bohemian Rhapsody", "Queen", 1975L, "Rock"),
            new Song("2", "Stairway to Heaven", "Led Zeppelin", 1971L, "Rock"),
            new Song("3", "Thriller", "Michael Jackson", 1982L, "Pop"),
            new Song("4", "Billie Jean", "Michael Jackson", 1983L, "Pop"),
            new Song("5", "Lose Yourself", "Eminem", 2002L, "Hip Hop"),
            new Song("6", "Sweet Child O' Mine", "Guns N' Roses", 1987L, "Rock"),
            new Song("7", "Dear Mama", "2Pac", 1995L, "Hip Hop"),
            new Song("8", "November Rain", "Guns N' Roses", 1991L, "Rock")
        );
    }
}
