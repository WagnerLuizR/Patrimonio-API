package bri.ifsp.edu.br.patrimonioapi.service;

import bri.ifsp.edu.br.patrimonioapi.DAO.PatrimonioDAO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.message.Response;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;
import bri.ifsp.edu.br.patrimonioapi.service.errors.TestarCampoRequerido;

import java.util.ArrayList;
import java.util.List;

public class PatrimonioService extends DataBaseTransactionService<Patrimonio, Long> {
    private PatrimonioDAO dao;

    public PatrimonioService() {
        dao = new PatrimonioDAO(openEntityManager());
    }

    @Override
    public Response incluir(Patrimonio patrimonio) {
        try {
            getTransaction();
            dao.incluir(patrimonio);
            getCommit();
            response = getMessageResponse().message(patrimonio, "Registro cadastrado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(patrimonio, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;

    }

    @Override
    public Response alterar(Patrimonio patrimonio) {
        try {
            getTransaction();
            patrimonio = dao.alterar(patrimonio);
            getCommit();
            response = getMessageResponse().message(patrimonio, "Registro cadastrado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(patrimonio, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Response excluir(Patrimonio patrimonio) {
        try {
            getTransaction();
            Patrimonio PatrimonioCadastrado = dao.consultarPorId(patrimonio.getCodigo());
            dao.excluir(PatrimonioCadastrado);
            getCommit();
            response = getMessageResponse().message(patrimonio, "Registro excluído com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(patrimonio, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Response consultaPorId(Long id) {
        Patrimonio patrimonio = null;
        try {
            patrimonio = dao.consultarPorId(id);
            response = getMessageResponse().message(patrimonio, "Registro localizado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            response = getMessageResponse().message(patrimonio, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Page<Patrimonio> listaPaginada(int paginaAtual, int tamanhoPagina) {
        return dao.listaPaginada(paginaAtual, tamanhoPagina);
    }

    @Override
    public Page<Patrimonio> listaPaginada(int paginaAtual, int tamanhoPagina, String text) {
        return dao.listaPaginada(paginaAtual, tamanhoPagina, text);
    }
    
    @Override
    public List<Patrimonio> listar(){
    	return dao.listaPatrimonio();
    }

    @Override
    public Response validarDadosFromView(Patrimonio Patrimonio) {
        errorsData = new ArrayList<ErrorsData>();
        errorsData = TestarCampoRequerido.validarCampoRequerido(Patrimonio);
        return returnErrorOrNot();
    }
}
