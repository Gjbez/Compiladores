/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class Controle {

    private final Tela t;
    private final JTextArea editor;
    private final JTextArea mensagem;
    private final JLabel status;
    private File arquivoAtual = null;

    JFileChooser fcSalvarArquivo;
    JFileChooser fcAbrirArquivo;

    public Controle(Tela t, JTextArea editor, JTextArea mensagem, JLabel status) {
        this.t = t;
        this.editor = editor;
        this.mensagem = mensagem;
        this.status = status;
    }

    public void buttonNovo() {
        editor.setText("");
        mensagem.setText("");
        status.setText("");
        arquivoAtual = null;
    }

    public void buttonAbrir() {
        fcAbrirArquivo = new JFileChooser();
        fcAbrirArquivo.setFileFilter(new FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));
        int res = fcAbrirArquivo.showOpenDialog(this.t);
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = fcAbrirArquivo.getSelectedFile();
            try {
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                editor.setText(content);
                mensagem.setText("");
                arquivoAtual = file;
                status.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.t, "Erro ao abrir: " + ex.getMessage());
            }
        }
    }

    public void buttonSalvar(boolean salvarComo) {
        try {
            if (arquivoAtual == null || salvarComo) {
                fcSalvarArquivo = new JFileChooser();
                fcSalvarArquivo.setFileFilter(new FileNameExtensionFilter("Arquivos de texto (*.txt)", "txt"));
                int res = fcSalvarArquivo.showSaveDialog(this.t);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = fcSalvarArquivo.getSelectedFile();
                    if (!file.getName().endsWith(".txt")) {
                        file = new File(file.getAbsolutePath() + ".txt");
                    }
                    java.nio.file.Files.write(file.toPath(), editor.getText().getBytes(StandardCharsets.UTF_8));
                    arquivoAtual = file;
                    mensagem.setText("");
                    status.setText(file.getAbsolutePath());
                }
            } else {
                java.nio.file.Files.write(arquivoAtual.toPath(), editor.getText().getBytes(StandardCharsets.UTF_8));
                mensagem.setText("");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this.t, "Erro ao salvar: " + ex.getMessage());

        }
    }

    public void buttonCopiar() {
        editor.copy();
    }

    public void buttonColar() {
        editor.paste();
    }

    public void buttonRecortar() {
        editor.cut();
    }

    public void buttonCompilar() {
        mensagem.setText("Compilação de programas ainda não foi implementada");
    }

    public void buttonEquipe() {
        mensagem.setText("");
        mensagem.setText("Equipe: Guilherme Bez e Gustavo Baum.");
    }
}
