package br.com.tresb.resources;

import java.util.Map;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

/**
 * Responsavel por fornecer metodos referentes ao FacesContext.
 * 
 * @author Hylston Natann
 * 
 * @version 1.0
 */
public class FacesResources extends FacesContextFactory {

	private static FacesContextFactory delegate;

	public FacesResources(FacesContextFactory facesContextFactory) {

		delegate = facesContextFactory;
	}

	@Override
	public FacesContext getFacesContext(Object context, Object request, Object response, Lifecycle lifecycle) throws FacesException {

		return delegate.getFacesContext(context, request, response, lifecycle);
	}

	public static FacesContext facesContext() {

		return FacesContext.getCurrentInstance();
	}
	
	public static ELContext elContext() {

		return facesContext().getELContext();
	}

	public static ExternalContext externalContext() {

		return facesContext().getExternalContext();
	}

	public static String getRealPath() {

		return servletContext().getRealPath("");
	}

	public static HttpSession session() {

		return (HttpSession) externalContext().getSession(true);
	}

	public static HttpServletRequest servletRequest() {

		return (HttpServletRequest) externalContext().getRequest();
	}

	public static ServletContext servletContext() {

		return (ServletContext) externalContext().getContext();
	}

	public static HttpServletResponse servletResponse() {

		return (HttpServletResponse) externalContext().getResponse();
	}

	public static Map<String, Object> sessionMap() {

		return externalContext().getSessionMap();
	}

	@SuppressWarnings("rawtypes")
	public static Map requestMap() {

		return externalContext().getRequestParameterMap();
	}

	public static String requestMapParam(String name) {

		return (String) requestMap().get(name);
	}

	public static void setAttBean(String beanAlias, String attribute, Object newValue) {

		facesContext().getApplication().getELResolver().setValue(elContext(), beanAlias, attribute, newValue);
	}

	public static void setAttSession(String alias, Object object) {

		session().setAttribute(alias, object);
	}

	public static Object getAttSession(String alias) {

		return session().getAttribute(alias);
	}

	public static void removeAttSession(String alias) {

		session().removeAttribute(alias);
	}

	public static void setAttRequest(String alias, Object object) {

		servletRequest().setAttribute(alias, object);
	}

	public static Object getAttRequest(String key) {

		return servletRequest().getAttribute(key);
	}
	

	public static RequestContext getRequestContext() {

		return RequestContext.getCurrentInstance();
	}

	public static MethodExpression createMethodExpression(String expression, Class<?> returnType, Class<?>... parameterTypes) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		return facesContext.getApplication().getExpressionFactory().createMethodExpression(facesContext.getELContext(), expression, returnType, parameterTypes);
	}
	
	public static ValueExpression createValueExpression(String expression, Class<?> returnType) {
		
		return facesContext().getApplication().getExpressionFactory().createValueExpression(facesContext().getELContext(), expression, returnType);		
	}

}