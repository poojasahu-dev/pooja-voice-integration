/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.40
 * Generated at: 2020-06-01 06:20:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.responsive.pages.account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class accountOrderDetailHeadline_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("/WEB-INF/tags/responsive/common/implicit.tld", Long.valueOf(1558679600000L));
    _jspx_dependants.put("file:/C:/hybris1905/hybris/bin/platform/ext/core/lib/spring-webmvc-5.1.6.RELEASE.jar", Long.valueOf(1558680496000L));
    _jspx_dependants.put("/WEB-INF/tags/responsive/common/headline.tag", Long.valueOf(1558679600000L));
    _jspx_dependants.put("jar:file:/C:/hybris1905/hybris/bin/modules/base-accelerator/yacceleratorstorefront/web/webroot/WEB-INF/lib/jstl-impl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1308107170000L));
    _jspx_dependants.put("jar:file:/C:/hybris1905/hybris/bin/platform/ext/core/lib/spring-webmvc-5.1.6.RELEASE.jar!/META-INF/spring.tld", Long.valueOf(1554000908000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-impl-1.2.jar", Long.valueOf(1590990527093L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005fhtmlEscape_0026_005fdefaultHtmlEscape_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fhtmlEscape_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fspring_005fhtmlEscape_0026_005fdefaultHtmlEscape_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fhtmlEscape_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fspring_005fhtmlEscape_0026_005fdefaultHtmlEscape_005fnobody.release();
    _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fhtmlEscape_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      if (_jspx_meth_spring_005fhtmlEscape_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_spring_005furl_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_common_005fheadline_005f0(_jspx_page_context))
        return;
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_spring_005fhtmlEscape_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:htmlEscape
    org.springframework.web.servlet.tags.HtmlEscapeTag _jspx_th_spring_005fhtmlEscape_005f0 = (org.springframework.web.servlet.tags.HtmlEscapeTag) _005fjspx_005ftagPool_005fspring_005fhtmlEscape_0026_005fdefaultHtmlEscape_005fnobody.get(org.springframework.web.servlet.tags.HtmlEscapeTag.class);
    boolean _jspx_th_spring_005fhtmlEscape_005f0_reused = false;
    try {
      _jspx_th_spring_005fhtmlEscape_005f0.setPageContext(_jspx_page_context);
      _jspx_th_spring_005fhtmlEscape_005f0.setParent(null);
      // /WEB-INF/views/responsive/pages/account/accountOrderDetailHeadline.jsp(5,0) name = defaultHtmlEscape type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005fhtmlEscape_005f0.setDefaultHtmlEscape(true);
      int[] _jspx_push_body_count_spring_005fhtmlEscape_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_spring_005fhtmlEscape_005f0 = _jspx_th_spring_005fhtmlEscape_005f0.doStartTag();
        if (_jspx_th_spring_005fhtmlEscape_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_spring_005fhtmlEscape_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_spring_005fhtmlEscape_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_spring_005fhtmlEscape_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fspring_005fhtmlEscape_0026_005fdefaultHtmlEscape_005fnobody.reuse(_jspx_th_spring_005fhtmlEscape_005f0);
      _jspx_th_spring_005fhtmlEscape_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_spring_005fhtmlEscape_005f0, _jsp_getInstanceManager(), _jspx_th_spring_005fhtmlEscape_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_spring_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:url
    org.springframework.web.servlet.tags.UrlTag _jspx_th_spring_005furl_005f0 = (org.springframework.web.servlet.tags.UrlTag) _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fhtmlEscape_005fnobody.get(org.springframework.web.servlet.tags.UrlTag.class);
    boolean _jspx_th_spring_005furl_005f0_reused = false;
    try {
      _jspx_th_spring_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_spring_005furl_005f0.setParent(null);
      // /WEB-INF/views/responsive/pages/account/accountOrderDetailHeadline.jsp(7,0) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f0.setValue("/my-account/orders");
      // /WEB-INF/views/responsive/pages/account/accountOrderDetailHeadline.jsp(7,0) name = var type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f0.setVar("orderHistoryUrl");
      // /WEB-INF/views/responsive/pages/account/accountOrderDetailHeadline.jsp(7,0) name = htmlEscape type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_spring_005furl_005f0.setHtmlEscape(false);
      int[] _jspx_push_body_count_spring_005furl_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_spring_005furl_005f0 = _jspx_th_spring_005furl_005f0.doStartTag();
        if (_jspx_th_spring_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return true;
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_spring_005furl_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_spring_005furl_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_spring_005furl_005f0.doFinally();
      }
      _005fjspx_005ftagPool_005fspring_005furl_0026_005fvar_005fvalue_005fhtmlEscape_005fnobody.reuse(_jspx_th_spring_005furl_005f0);
      _jspx_th_spring_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_spring_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_spring_005furl_005f0_reused);
    }
    return false;
  }

  private boolean _jspx_meth_common_005fheadline_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  common:headline
    org.apache.jsp.tag.web.responsive.common.headline_tag _jspx_th_common_005fheadline_005f0 = new org.apache.jsp.tag.web.responsive.common.headline_tag();
    _jsp_getInstanceManager().newInstance(_jspx_th_common_005fheadline_005f0);
    try {
      _jspx_th_common_005fheadline_005f0.setJspContext(_jspx_page_context);
      // /WEB-INF/views/responsive/pages/account/accountOrderDetailHeadline.jsp(8,0) name = url type = java.lang.String reqTime = true required = true fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_common_005fheadline_005f0.setUrl((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${orderHistoryUrl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      // /WEB-INF/views/responsive/pages/account/accountOrderDetailHeadline.jsp(8,0) name = labelKey type = java.lang.String reqTime = true required = true fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
      _jspx_th_common_005fheadline_005f0.setLabelKey("text.account.order.title.details");
      _jspx_th_common_005fheadline_005f0.doTag();
    } finally {
      _jsp_getInstanceManager().destroyInstance(_jspx_th_common_005fheadline_005f0);
    }
    return false;
  }
}
