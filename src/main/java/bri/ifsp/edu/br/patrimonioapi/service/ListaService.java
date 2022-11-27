package bri.ifsp.edu.br.patrimonioapi.service;

import bri.ifsp.edu.br.patrimonioapi.DAO.ListaDAO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.message.Response;
import bri.ifsp.edu.br.patrimonioapi.model.Lista;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;
import bri.ifsp.edu.br.patrimonioapi.service.errors.TestarCampoRequerido;

import java.util.ArrayList;
import java.util.List;

public class ListaService extends DataBaseTransactionService<Lista, Long> {

    private ListaDAO dao;

    public ListaService() {
        dao = new ListaDAO(openEntityManager());
    }

    @Override
    public Response incluir(Lista lista) {
        try {
            getTransaction();
            dao.incluir(lista);
            getCommit();
            response = getMessageResponse().message(lista, "Registro cadastrado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(lista, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;

    }

    @Override
    public Response alterar(Lista lista) {
        try {
            getTransaction();
            lista = dao.alterar(lista);
            getCommit();
            response = getMessageResponse().message(lista, "Registro cadastrado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(lista, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Response excluir(Lista lista) {
        try {
            getTransaction();
            Lista listaCadastrado = dao.consultarPorId(lista.getId());
            dao.excluir(listaCadastrado);
            getCommit();
            response = getMessageResponse().message(lista, "Registro excluído com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(lista, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Response consultaPorId(Long id) {
        Lista lista = null;
        try {
            lista = dao.consultarPorId(id);
            response = getMessageResponse().message(lista, "Registro localizado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            response = getMessageResponse().message(lista, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Page<Lista> listaPaginada(int paginaAtual, int tamanhoPagina) {
        return dao.listaPaginada(paginaAtual, tamanhoPagina);
    }

    @Override
    public Page<Lista> listaPaginada(int paginaAtual, int tamanhoPagina, String text) {
        return dao.listaPaginada(paginaAtual, tamanhoPagina, text);
    }
    @Override
    public List<Lista> listar(){
    	return dao.listar();
    }
    @Override
    public Response validarDadosFromView(Lista lista) {
        errorsData = new ArrayList<ErrorsData>();
        errorsData = TestarCampoRequerido.validarCampoRequerido(lista);
        return returnErrorOrNot();
    }
}
