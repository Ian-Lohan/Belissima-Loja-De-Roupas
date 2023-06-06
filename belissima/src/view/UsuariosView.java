package view;

import dal.UsuarioDAL;
import dao.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connections.Conexao;

public class UsuariosView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private UsuarioDAL usuarioDAL;

    public UsuariosView() {
        setTitle("Tabela de Usuários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Login");
        tableModel.addColumn("Senha");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Endereço");
        tableModel.addColumn("Número");
        tableModel.addColumn("Acesso");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Adicionar");
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Editar");
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Deletar");
        buttonPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddUsuarioView addUsuarioView = new AddUsuarioView();
                addUsuarioView.setVisible(true);
                addUsuarioView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        refreshTable();
                    }
                });
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) table.getValueAt(selectedRow, 0);
                    
                    try {
                        Connection conn = Conexao.ConectarBanco();
                        usuarioDAL = new UsuarioDAL(conn);
                        Usuario usuario = usuarioDAL.obterUsuario(userId);
                        conn.close();
                        
                        if (usuario != null) {
                            EdtUsuarioView edtUsuarioView = new EdtUsuarioView(usuario);
                            edtUsuarioView.setVisible(true);
                            edtUsuarioView.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    refreshTable();
                                }
                            });
                        } else {
                            JOptionPane.showMessageDialog(UsuariosView.this, "Usuário não encontrado!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(UsuariosView.this, "Selecione um usuário para editar!");
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int userId = (int) table.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(UsuariosView.this, "Deseja realmente deletar o usuário?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deletarUsuario(userId);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(UsuariosView.this, "Selecione um usuário para deletar!");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        try {
            Connection conn = Conexao.ConectarBanco();
            usuarioDAL = new UsuarioDAL(conn);
            List<Usuario> usuarios = usuarioDAL.obterUsuarios();

            tableModel.setRowCount(0);

            for (Usuario usuario : usuarios) {
                tableModel.addRow(new Object[]{
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getLogin(),
                        usuario.getSenha(),
                        usuario.getCpf(),
                        usuario.getEndereco(),
                        usuario.getNumero(),
                        usuario.getAcesso()
                });
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletarUsuario(int userId) {
        try {
            Connection conn = Conexao.ConectarBanco();
            usuarioDAL = new UsuarioDAL(conn);
            usuarioDAL.deletarUsuario(userId);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuariosView frame = new UsuariosView();
            frame.setVisible(true);
        });
    }
}
