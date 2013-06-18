package dados;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;

/**
 *
 * @author Rafael
 */
public class SMSDao {
    /*
     * Inserts nas Tabelas
     */

    private static final String insertRegistraSMS = "insert into MENSAGENS_ENVIADAS(Numero_Enviado,Pessoa_Enviou,Status_Retorno_Msg,Data_Envio,Hora_Envio) values(?,?,?,?,?)";
    private static final String insertModelosSMS = "insert into MODELO_MENSAGEM(Nome_Modelo,Msg) values(?,?)";
    private static final String insertGruposSMS = "insert into GRUPOS(Nome) values(?)";
    private static final String insertContatosSMS = "insert into CONTATOS(Numero,Nome) values(?,?)";
    private static final String insertContatosGruposSMS = "insert into GRUPOS_CONTATOS(ID_Grupo,ID_Contato) values(?,?)";
    /*
     *   Updates nas Tabelas
     */
    private static final String updateModeloSMS = "update CONTATOS set Msg = ?, Nome_Modelo = ? where Nome_Modelo like(?)";
    private static final String updateContatoPorNome = "update CONTATOS set Numero = ?, Nome = ? where ID = ?";
    private static final String updateGrupoSMS = "update GRUPOS set Nome = ? where Nome = ?";

    /*
     *   Deletes nas Tabelas
     */
    
    
    	private Connection connect() throws IOException, FileNotFoundException,SQLException {
		Connection con;
		Properties p = new Properties();
		p.load(new FileInputStream("src/conf/CarrinhoDAO.properties"));
		String url = p.getProperty("url");
 
		con = DriverManager.getConnection(url,p);
		return con;
	}
    
    
    public void addContatos(Contatos cont) {
        if (cont == null) {
            throw new IllegalArgumentException("O Contato não pode ser null!");
        }

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/SMS", "postgres",
                    "senacrs");

            PreparedStatement stmt = con.prepareStatement(insertContatosSMS);
            stmt.setString(1, cont.getNumero());
            stmt.setString(2, cont.getNome());
            int r = stmt.executeUpdate();

            if (r != 1) {
                throw new RuntimeException("Erro ao inserir operação");
            } else {
                JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");
            }
            if (stmt != null) {
                stmt.close();
            }

            if (r != 0) {
                stmt.close();
            }

            if (con != null) {
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o contato");
        }

    }

    public void addModelos(ModeloSMS sms) {
        if (sms == null) {
            throw new IllegalArgumentException("O Contato não pode ser null!");
        }

        try {
            Connection con = connect();

            PreparedStatement stmt = con.prepareStatement(insertModelosSMS);
            stmt.setString(1, sms.getNome_Modelo());
            stmt.setString(2, sms.getMensagem());
            int r = stmt.executeUpdate();

            if (r != 1) {
                throw new RuntimeException("Erro ao inserir operação");
            } else {
                JOptionPane.showMessageDialog(null, "Modelo cadastrado com sucesso!");
            }
            if (stmt != null) {
                stmt.close();
            }

            if (r != 0) {
                stmt.close();
            }

            if (con != null) {
                stmt.close();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Modelo de Mensagem");
            throw new RuntimeException("Erro ao inserir modelo de SMS");
        }

    }

    public void addGrupos(Grupos grp) {
        if (grp == null) {
            throw new IllegalArgumentException("O Contato não pode ser null!");
        }

        try {
            Connection con = connect();

            PreparedStatement stmt = con.prepareStatement(insertGruposSMS);
            stmt.setString(1, grp.getNome());
            int r = stmt.executeUpdate();

            if (r != 1) {
                throw new RuntimeException("Erro ao inserir operação");
            } else {
                JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");
            }
            if (stmt != null) {
                stmt.close();
            }

            if (r != 0) {
                stmt.close();
            }

            if (con != null) {
                stmt.close();
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Modelo de Mensagem");
           throw new RuntimeException("Erro ao inserir um Grupo");
        }
    }
    
    public void setPatchIcons(String patch)
    {
    }
}