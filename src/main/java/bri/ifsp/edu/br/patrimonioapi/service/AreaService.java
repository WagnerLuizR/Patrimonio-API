package bri.ifsp.edu.br.patrimonioapi.service;

import bri.ifsp.edu.br.patrimonioapi.DAO.AreaDAO;
import bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO;
import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.message.Response;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;
import bri.ifsp.edu.br.patrimonioapi.service.errors.TestarCampoRequerido;

import java.util.ArrayList;
import java.util.List;

public class AreaService extends DataBaseTransactionService<Area, Long> {

    private AreaDAO dao;

    public AreaService() {
        dao = new AreaDAO(openEntityManager());
    }

    @Override
    public Response incluir(Area area) {
        try {
            getTransaction();
            dao.incluir(area);
            getCommit();
            response = getMessageResponse().message(area, "Registro cadastrado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(area, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;

    }

    @Override
    public Response alterar(Area area) {
        try {
            getTransaction();
            area = dao.alterar(area);
            getCommit();
            response = getMessageResponse().message(area, "Registro cadastrado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(area, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Response excluir(Area area) {
        try {
            getTransaction();
            Area areaCadastrado = dao.consultarPorId(area.getId());
            dao.excluir(areaCadastrado);
            getCommit();
            response = getMessageResponse().message(area, "Registro excluído com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            if (getAtivo()) {
                getRollback();
            }
            response = getMessageResponse().message(area, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Response consultaPorId(Long id) {
        Area area = null;
        try {
            area = dao.consultarPorId(id);
            response = getMessageResponse().message(area, "Registro localizado com sucesso!", false);
        } catch (Exception e) {
            e.printStackTrace();
            response = getMessageResponse().message(area, e.getMessage(), true);
        } finally {
            closeEntityManager();
        }
        return response;
    }

    @Override
    public Page<Area> listaPaginada(int paginaAtual, int tamanhoPagina) {
        return dao.listaPaginada(paginaAtual, tamanhoPagina);
    }
    @Override
    public Page<Area> listaPaginada(int paginaAtual, int tamanhoPagina, String text) {
        return dao.listaPaginada(paginaAtual, tamanhoPagina, text);
    }
    @Override
    public Page<AreaDTO> dtoPaginado(int paginAtual, int tamanhoPagina){
        return dao.paginadoDTO(paginAtual,tamanhoPagina);
    }
    @Override
    public Page<AreaDTO> dtoPaginado(int paginAtual, int tamanhoPagina, String text){
        return dao.paginadoDTO(paginAtual,tamanhoPagina,text);
    }

    @Override
    public Page<PatrimonioDTO> patrimonioDtoPaginado(int paginAtual, int tamanhoPagina) {
        return null;
    }

    @Override
    public Page<PatrimonioDTO> patrimonioDtoPaginado(int paginAtual, int tamanhoPagina, String text) {
        return null;
    }

    @Override
    public List<Area> listar(){
    	return dao.listarArea();
    }

    @Override
    public Response validarDadosFromView(Area area) {
        errorsData = new ArrayList<ErrorsData>();
        errorsData = TestarCampoRequerido.validarCampoRequerido(area);
        return returnErrorOrNot();
    }
}
