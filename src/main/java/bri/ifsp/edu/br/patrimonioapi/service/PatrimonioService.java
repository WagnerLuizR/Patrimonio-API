package bri.ifsp.edu.br.patrimonioapi.service;

import bri.ifsp.edu.br.patrimonioapi.DAO.PatrimonioDAO;
import bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO;
import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.message.Response;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;
import bri.ifsp.edu.br.patrimonioapi.service.errors.TestarCampoRequerido;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            dao.consultarPorId(patrimonio.getCodigo());
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

    public String exportReport() throws FileNotFoundException, JRException {
        String path = "C:\\Users\\wagner\\Documents\\Patrimonio-API-main\\Patrimonio-API-main\\src\\main\\resources\\reports";
        List<PatrimonioDTO> patrimonio = listar().stream().map(PatrimonioDTO::new).collect(Collectors.toList());
        //load file and compile it
        File file = new File( "classpath:PatrimonioReports.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(patrimonio);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Patrimonio-API");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\Patrimonio.pdf");

        return "report generated in path : " + path;
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
    public Page<AreaDTO> dtoPaginado(int paginAtual, int tamanhoPagina) {
        return null;
    }

    @Override
    public Page<AreaDTO> dtoPaginado(int paginAtual, int tamanhoPagina, String text) {
        return null;
    }

    public String exportData() throws JRException, FileNotFoundException {
        return exportReport();
    }

    @Override
    public Page<PatrimonioDTO> patrimonioDtoPaginado(int paginAtual, int tamanhoPagina){
        return dao.patrimonioPaginadoDTO(paginAtual,tamanhoPagina);
    }
    @Override
    public Page<PatrimonioDTO> patrimonioDtoPaginado(int paginAtual, int tamanhoPagina, String text){
        return dao.patrimonioPaginadoDTO(paginAtual,tamanhoPagina,text);
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
