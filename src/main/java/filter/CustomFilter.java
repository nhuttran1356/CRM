//package filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//
////Khi người dùng gọi link thì filter được kích hoạt
//@WebFilter(urlPatterns = {"/groupwork", "/user", "/task", "/role"})
//public class CustomFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
////        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // noi quy dinh rule
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//
//        //Cho phép đi vào link mà người dùng request
//        String email = (String) session.getAttribute("email");
//        String password = (String) session.getAttribute("password");
////        System.out.println(email + " filter " + password);
////        System.out.println("Filter kích hoạt 1");
//        if (email != null && password != null) {
//            chain.doFilter(request, response);
////            System.out.println("Filter kích hoạt 2");
//        } else {
//            resp.sendRedirect(req.getContextPath() + "/login");
//        }
////
//    }
//    @Override
//    public void destroy() {
////        Filter.super.destroy();
//    }
//}
//
