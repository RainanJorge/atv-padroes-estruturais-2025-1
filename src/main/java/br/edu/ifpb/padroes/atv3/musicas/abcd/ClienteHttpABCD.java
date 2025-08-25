package br.edu.ifpb.padroes.atv3.musicas.abcd;

import java.util.Arrays;
import java.util.List;

public class ClienteHttpABCD {

    public List<Musica> listarMusicas() {
        // Dados fictícios do serviço brasileiro
        return Arrays.asList(
            new Musica("1", "Garota de Ipanema", "Tom Jobim", 1962L, "Bossa Nova"),
            new Musica("2", "Águas de Março", "Elis Regina", 1974L, "MPB"),
            new Musica("3", "Chega de Saudade", "João Gilberto", 1959L, "Bossa Nova"),
            new Musica("4", "Construção", "Chico Buarque", 1971L, "MPB"),
            new Musica("5", "País Tropical", "Jorge Ben Jor", 1969L, "MPB"),
            new Musica("6", "Aquarela do Brasil", "Ary Barroso", 1939L, "Samba"),
            new Musica("7", "O Que é Que a Baiana Tem", "Carmen Miranda", 1939L, "Samba")
        );
    }
}
