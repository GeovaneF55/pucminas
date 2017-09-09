/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootup_v2;


import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author luigi
 */
public class ControleAluno extends javax.swing.JFrame {  
    private final ArquivoIndexado<Aluno> ARQUIVO;
    private final String OPCAO;
    private Aluno ALUNO_ALTERADO = null;
    
    private class MyDocumentListener implements DocumentListener {
        
        private void validaBotao() {
            String nome = textFieldNome.getText();
            String email = textFieldEmail.getText();
            String senha = Arrays.toString(passwordFieldSenha.getPassword());
            String telefone = textFieldTelefone.getText();
            String matricula = textFieldMatricula.getText();
            String curso = textFieldCurso.getText();
            
            if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty() &&
                    !telefone.isEmpty() && !matricula.isEmpty() && !curso.isEmpty()) {
                buttonConfirmar.setEnabled(true);
            } else {
                buttonConfirmar.setEnabled(false);
            }
        }
        
        @Override
        public void insertUpdate(DocumentEvent e) {
            validaBotao();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validaBotao();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validaBotao();
        }
    }
       
    /**
     * Creates new form CadastroAluno
     * @param arquivo
     * @param opcao
     */
    public ControleAluno(ArquivoIndexado<Aluno> arquivo, String opcao) {
        initComponents();
        ARQUIVO = arquivo; 
        OPCAO = opcao;
        
        labelTitulo.setText(OPCAO + " de Aluno");
        
        buttonConfirmar.setEnabled(false);
        
        MyDocumentListener document = new MyDocumentListener();
        textFieldNome.getDocument().addDocumentListener(document);
        textFieldEmail.getDocument().addDocumentListener(document);
        passwordFieldSenha.getDocument().addDocumentListener(document);
        textFieldTelefone.getDocument().addDocumentListener(document);
        textFieldMatricula.getDocument().addDocumentListener(document);
        textFieldCurso.getDocument().addDocumentListener(document);
        
    }
    
    public ControleAluno(ArquivoIndexado<Aluno> arquivo, Aluno alunoAlterado, String opcao) {
        this(arquivo, opcao);
      
        ALUNO_ALTERADO = alunoAlterado;
        textFieldNome.setText(alunoAlterado.nome);
        textFieldEmail.setText(alunoAlterado.email);
        passwordFieldSenha.setText(alunoAlterado.senha);
        textFieldTelefone.setText(alunoAlterado.telefone);
        textFieldMatricula.setText(alunoAlterado.matricula);
        textFieldCurso.setText(alunoAlterado.curso);
    }

    private void inclusao() {
        try {
            // Recuperando valores inseridos.
            String nome = textFieldNome.getText();
            String email = textFieldEmail.getText();
            String senha = Arrays.toString(passwordFieldSenha.getPassword());
            String telefone = textFieldTelefone.getText();
            String matricula = textFieldMatricula.getText();
            String curso = textFieldCurso.getText();
            
            // Criando novo aluno.
            Aluno novoAluno = new Aluno(-1, nome, email, senha, telefone, matricula, curso);
            
            // Inserindo novo aluno.
            int codigo = ARQUIVO.incluir(novoAluno);
            
            JOptionPane.showMessageDialog(this, "Aluno incluído com código " + codigo + " :)\n", "Inclusão", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            
            try {
                MenuAluno menu = new MenuAluno();
                menu.setLocationRelativeTo(null);
                menu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                menu.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "ERRO!", "Menu Aluno", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao incluir aluno!", "Inclusão", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void alteracao() {
        String nome = textFieldNome.getText();
        String email = textFieldEmail.getText();
        String senha = Arrays.toString(passwordFieldSenha.getPassword());
        String telefone = textFieldTelefone.getText();
        String matricula = textFieldMatricula.getText();
        String curso = textFieldCurso.getText();

        // Realizando alterações.
        ALUNO_ALTERADO.nome = (nome.length() > 0) ? nome : ALUNO_ALTERADO.nome;
        ALUNO_ALTERADO.email = (email.length() > 0) ? email : ALUNO_ALTERADO.email;
        ALUNO_ALTERADO.senha = (senha.length() > 0) ? senha : ALUNO_ALTERADO.senha;
        ALUNO_ALTERADO.telefone = (telefone.length() > 0) ? telefone : ALUNO_ALTERADO.telefone;
        ALUNO_ALTERADO.matricula = (matricula.length() > 0) ? matricula : ALUNO_ALTERADO.matricula;
        ALUNO_ALTERADO.curso = (curso.length() > 0) ? curso : ALUNO_ALTERADO.curso;

        try {
            ARQUIVO.alterar(ALUNO_ALTERADO);
            JOptionPane.showMessageDialog(this, "Aluno alterado :)\n", "Alteração", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception eAlteracao) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar aluno!", "Alteração", JOptionPane.ERROR_MESSAGE);
        }
        
        dispose();
        
        try {
            MenuAluno menu = new MenuAluno();
            menu.setLocationRelativeTo(null);
            menu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            menu.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERRO!", "Menu Aluno", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_CadastroAluno = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        textFieldEmail = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        passwordFieldSenha = new javax.swing.JPasswordField();
        labelTelefone = new javax.swing.JLabel();
        textFieldTelefone = new javax.swing.JTextField();
        labelMatricula = new javax.swing.JLabel();
        textFieldMatricula = new javax.swing.JTextField();
        labelCurso = new javax.swing.JLabel();
        textFieldCurso = new javax.swing.JTextField();
        buttonConfirmar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Cadastro de Aluno");

        labelNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNome.setText("Nome:");

        labelEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelEmail.setText("E-mail:");

        textFieldEmail.setToolTipText("");

        labelSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSenha.setText("Senha");

        labelTelefone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTelefone.setText("Telefone");

        labelMatricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMatricula.setText("Matrícula");

        labelCurso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCurso.setText("Curso");

        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmarActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_CadastroAlunoLayout = new javax.swing.GroupLayout(jPanel_CadastroAluno);
        jPanel_CadastroAluno.setLayout(jPanel_CadastroAlunoLayout);
        jPanel_CadastroAlunoLayout.setHorizontalGroup(
            jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addComponent(labelSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordFieldSenha))
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addComponent(labelEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldNome))
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(buttonConfirmar)
                        .addGap(12, 12, 12)
                        .addComponent(buttonCancelar))
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addComponent(labelTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addComponent(labelMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                        .addComponent(labelCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jPanel_CadastroAlunoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelCurso, labelEmail, labelMatricula, labelNome, labelSenha, labelTelefone});

        jPanel_CadastroAlunoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonCancelar, buttonConfirmar});

        jPanel_CadastroAlunoLayout.setVerticalGroup(
            jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CadastroAlunoLayout.createSequentialGroup()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(passwordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefone)
                    .addComponent(textFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMatricula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCurso))
                .addGap(32, 32, 32)
                .addGroup(jPanel_CadastroAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonConfirmar)
                    .addComponent(buttonCancelar))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel_CadastroAlunoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelCurso, labelEmail, labelMatricula, labelNome, labelSenha, labelTelefone});

        jPanel_CadastroAlunoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {textFieldCurso, textFieldEmail, textFieldMatricula, textFieldNome, textFieldTelefone});

        jPanel_CadastroAlunoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonCancelar, buttonConfirmar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_CadastroAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_CadastroAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        if (OPCAO.equals("Inclusão")) {
            inclusao();
        } else {
            alteracao();
        }
    }//GEN-LAST:event_buttonConfirmarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        String message = (OPCAO.equals("Inclusão")) ? "Aluno não incluso! :(" : "Aluno não alterado! :(" ;
        JOptionPane.showMessageDialog(this, message, OPCAO, JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
        
        try {
            MenuAluno menu = new MenuAluno();
            menu.setLocationRelativeTo(null);
            menu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            menu.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERRO!", "Menu Aluno", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonCancelarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JPanel jPanel_CadastroAluno;
    private javax.swing.JLabel labelCurso;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPasswordField passwordFieldSenha;
    private javax.swing.JTextField textFieldCurso;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldMatricula;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
