package bri.ifsp.edu.br.patrimonioapi.service;

import bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO;
import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.message.MessageResponse;
import bri.ifsp.edu.br.patrimonioapi.message.ModelResponse;
import bri.ifsp.edu.br.patrimonioapi.message.Response;
import bri.ifsp.edu.br.patrimonioapi.persistencia.ConexaoBancoDados;
import bri.ifsp.edu.br.patrimonioapi.service.errors.ErrorsData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

public abstract class DataBaseTransactionService<T, ID> {

    @PersistenceContext(unitName = "patrimonio")
    private EntityManager entityManager;

    protected List<ErrorsData> errorsData;
    protected Response response;
    protected MessageResponse<ErrorsData> errorData;
    protected MessageResponse<T> messageResponse;
    protected ModelResponse<T> modelResponse;

    public EntityManager openEntityManager() {

        if (Objects.isNull(entityManager)) {
            entityManager = ConexaoBancoDados.getConexao().getEntityManager();
        }
        return entityManager;
    }

    public void getTransaction() {
        entityManager.getTransaction().begin();
    }

    public void getCommit() {
        entityManager.getTransaction().commit();
    }

    public boolean getAtivo() {
        return entityManager.getTransaction().isActive();
    }

    public void getRollback() {
        entityManager.getTransaction().rollback();
    }

    public void closeEntityManager() {
        entityManager.close();
    }

    public abstract Response incluir(T entity);

    public abstract Response alterar(T entity);

    public abstract Response excluir(T entity);

    public abstract Response consultaPorId(ID id);

    public abstract Page<T> listaPaginada(int paginaAtual, int tamanhoPagina);

    public abstract Page<T> listaPaginada(int paginaAtual, int tamanhoPagina, String text);

    public abstract Page<AreaDTO> dtoPaginado(int paginAtual, int tamanhoPagina);

    public abstract Page<AreaDTO> dtoPaginado(int paginAtual, int tamanhoPagina, String text);

    public abstract Page<PatrimonioDTO> patrimonioDtoPaginado(int paginAtual, int tamanhoPagina);

    public abstract Page<PatrimonioDTO> patrimonioDtoPaginado(int paginAtual, int tamanhoPagina, String text);

    public abstract List<T> listar();

    public abstract Response validarDadosFromView(T objeto);

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public MessageResponse<T> getMessageResponse() {
        return new MessageResponse<T>();
    }

    public void setMessageResponse(MessageResponse<T> messageResponse) {
        this.messageResponse = messageResponse;
    }

    public ModelResponse<T> getModelResponse() {
        return new ModelResponse<T>();
    }

    public void setModelResponse(ModelResponse<T> modelResponse) {
        this.modelResponse = modelResponse;
    }

    public Response returnErrorOrNot() {
        if (errorsData.size() > 0) {
            response = getErrorData().message(errorsData, "", true);
        } else {
            response = getErrorData().message(errorsData, "", false);
        }
        return response;
    }

    public MessageResponse<ErrorsData> getErrorData() {
        return new MessageResponse<ErrorsData>();
    }

    public void setErrorData(MessageResponse<ErrorsData> errorData) {
        this.errorData = errorData;
    }
}
