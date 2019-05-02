package br.com.clima.rest.exception;


public class ServicoRestException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private Object object;

    public ServicoRestException() {}
    
    public ServicoRestException(String mensagem) {
        super(mensagem);
    }
    
    public ServicoRestException(String mensagem, Throwable e) {
        super(mensagem, e);
    }
    
    public ServicoRestException(String message, Object object) {
        super(message);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
    
    public void setObject(Object object) {
        this.object = object;
    }
}