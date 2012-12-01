package com.wowtasty.interceptor;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.wowtasty.mybatis.vo.ActionAuthVO;
import com.wowtasty.mybatis.vo.CodeMasterVO;
import com.wowtasty.mybatis.vo.MemberMasterVO;
import com.wowtasty.util.Constants;
import com.wowtasty.util.SessionUtil;

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
		
        // In case of no login
		if (session.get(Constants.KEY_SESSION_USER) == null) {
			logger.info("!!!!!Unauthorized access without login --->");
            addActionError(invocation, "You must be authenticated to access this page. Please login first.");
            return Action.LOGIN;
        }			
        // In case of no authorization
		MemberMasterVO mvo = (MemberMasterVO)session.get(Constants.KEY_SESSION_USER);
		int auth = Integer.parseInt(mvo.getAuth());
		// Get namespace : "/namespace"
		StringBuilder sb = new StringBuilder();
		String namespace = invocation.getProxy().getNamespace();
		
		// Get action authorization list from single tone session.
		List<ActionAuthVO> list = (List<ActionAuthVO>)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_ACTIONAUTH_LIST);

		// Comparing member auth with action auth
		if (list != null) {
			int size = list.size();
			ActionAuthVO vo = new ActionAuthVO();
			for (int i = 0; i < size; i++) {
				vo = list.get(i);
				if(namespace.equals(vo.getActionName())) {
					if(auth > Integer.parseInt(vo.getAuth())) {
						// If memeber auth is over action auth, forward to error
						logger.info("!!!!!Unauthorized access without proper authorization --->");
						return "autherror";
					} else {
						// If memeber auth is less action auth, proceed to action
						break;
					}
				}
			}
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
