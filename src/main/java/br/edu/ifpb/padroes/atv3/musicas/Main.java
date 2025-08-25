package br.edu.ifpb.padroes.atv3.musicas;

import br.edu.ifpb.padroes.atv3.musicas.facade.SistemaStreamMusicaFacade;
import br.edu.ifpb.padroes.atv3.musicas.model.MusicaPadrao;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎵 SISTEMA DE STREAM DE MÚSICA - PADRÕES ESTRUTURAIS 🎵");
        System.out.println("=====================================================\n");
        
        try {
            SistemaStreamMusicaFacade stream = new SistemaStreamMusicaFacade();

            // Lista todas as músicas unificadas dos dois serviços
            System.out.println("📋 LISTA DE MÚSICAS DISPONÍVEIS:");
            System.out.println("--------------------------------");
            List<MusicaPadrao> todas = stream.listarTodasMusicas();
            todas.forEach(System.out::println);

            // Toca algumas músicas
            System.out.println("\n🎵 TOCANDO MÚSICAS:");
            System.out.println("-------------------");
            if (!todas.isEmpty()) {
                stream.tocarMusica(todas.get(0));
                stream.tocarMusica(todas.get(1));
                stream.tocarMusica(todas.get(2));
                stream.tocarMusica(todas.get(0)); // toca repetida para ver estatísticas
            }

            // Exibe estatísticas geradas pelo decorator
            System.out.println("\n📊 ESTATÍSTICAS DE USO:");
            System.out.println("----------------------");
            stream.mostrarEstatisticas();
            
            System.out.println("\n✅ TODOS OS PADRÕES TESTADOS COM SUCESSO!");
            System.out.println("   • Adapter: Integração de serviços diferentes");
            System.out.println("   • Proxy: Cache para melhor performance");
            System.out.println("   • Decorator: Funcionalidades adicionais");
            System.out.println("   • Facade: Interface unificada");
            
        } catch (Exception e) {
            System.out.println("❌ Erro durante a execução: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
