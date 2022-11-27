package bri.ifsp.edu.br.patrimonioapi.message;

import java.util.List;

public class MessageResponse<T> {
	
	public ModelResponse<T> message(T object, String message, boolean error){
		ModelResponse<T> response = new ModelResponse<T>();
		response.setError(error);
		response.setMessage(message);
		response.setObject(object);
		return response;
	}
	
	public ModelResponse<T> message(List<T> object, String message, boolean error){
		ModelResponse<T> response = new ModelResponse<T>();
		response.setError(error);
		response.setMessage(message);
		response.setListObject(object);
		return response;
	}
}
