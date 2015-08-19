package br.edu.utfpr.pb.questionarioacademico.seguranca;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.edu.utfpr.pb.questionarioacademico.seguranca.model.Login;

/**
 * 
 * @author trgp
 *
 * Interceptador que contém a lógica de segurança da aplicação
 *
 */
@Intercepts
@RequestScoped
public class SecurityInterceptor implements Interceptor {

	private Login login;
	
	private Result result;
	
	@Inject
	public SecurityInterceptor(Result result, Login login) {
		this.result = result;
		this.login = login;
	}

	/*CDI only*/
	protected SecurityInterceptor() {
		this(null, null);
	}
	
	/*@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		boolean authenticated = false;
		String[] rolesArr = null;
		try {
			rolesArr = method.getMethod().getAnnotation(SecRole.class).roles();
		} catch (Exception e) {
			rolesArr = method.getResource().getType().getAnnotation(SecRole.class).roles();
		}
		List<String> roles = new ArrayList<String>();
		for (String role : rolesArr) roles.add(role);
		if (roles != null && roles.size() > 0) {
			Usuario u = login.getUsuario();
			if (u != null) {
				for (Perfil perfil : u.getPerfis()) {
					if (roles.contains(perfil.getAuthority())) {
						authenticated = true;
					} else {
						authenticated = false;
					}
				}
			} else {
				authenticated = false;
			}
		}
		if (authenticated) {
			stack.next(method, instance);
		} else {
			result.use(Results.json()).withoutRoot().from(new SecurityResponse(authenticated, "")).recursive().serialize();
		}
	}*/

	/**
	 * Método que define que somente os recursos anotados com @SecRole serão interceptados
	 */
	/*@Override
	public boolean accepts(ResourceMethod method) {
		return method.getMethod().isAnnotationPresent(SecRole.class)
			|| method.getResource().getType().isAnnotationPresent(SecRole.class);
	}*/

	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object controllerInstance) 
			throws InterceptionException {
		stack.next(method, controllerInstance);
	}

	@Override
	public boolean accepts(ControllerMethod method) {
		// TODO Auto-generated method stub
		return true;
	}

}