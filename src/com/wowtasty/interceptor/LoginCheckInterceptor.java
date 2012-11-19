package com.wowtasty.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wowtasty.util.Constants;

/**
 * @author Hak C.
 *
 */
public class LoginCheckInterceptor extends AbstractInterceptor {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Logger */
	private static Logger logger = Logger.getLogger(LoginCheckInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.debug("<---intercept start --->");
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		
        if (session.get(Constants.KEY_SESSION_USER) == null) {
            addActionError(invocation, "You must be authenticated to access this page. Please login first.");
            return Action.LOGIN;
        }			
	
        logger.debug("<---intercept end --->");
        return invocation.invoke();
	}
	
	private void addActionError(ActionInvocation invocation, String message) {
        Object action = invocation.getAction();
        if (action instanceof ValidationAware) {
            ((ValidationAware) action).addActionError(message);
        }
    }
}
