/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author RA21551055
 */
public class InserirCLiente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Transaction tx = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String nomeClient = request.getParameter("Nome");
            int cpf_form = Integer.parseInt(request.getParameter("cpf"));
            String endereco = request.getParameter("end");
            int telefone_form = Integer.parseInt(request.getParameter("tel"));
            
            Cliente cliente = new Cliente();
            
            cliente.setNome(nomeClient);
            cliente.setEndereco(endereco);
            cliente.setCpf(cpf_form);
            cliente.setTelefone(telefone_form);
            
            //conectar com o banco
            Session sessao = HibernateUtil.getSessionFactory().openSession();
            //criar ponto de restauraçao, aguarda os dados na memoria sem persistir no banco
            tx = sessao.beginTransaction();
            
            sessao.save(cliente);
            sessao.flush();
            
            tx.commit();
            sessao.close();
                       out.println("Registro inserido com sucesso!");
        }catch (Exception ex) {
                System.out.println("Erro ao inserir Cliente: " + ex.getMessage());
                tx.rollback();
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
