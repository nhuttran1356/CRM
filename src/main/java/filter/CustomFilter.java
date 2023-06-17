package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//Khi người dùng gọi link thì filter được kích hoạt
@WebFilter(urlPatterns = {"/groupwork", "/user", "/task", "/role", "/index",})
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // noi quy dinh rule

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        //Cho phép đi vào link mà người dùng request
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        String role = (String) session.getAttribute("role");
//        System.out.println(email + " filter " + password + " role " + role);
//        System.out.println("Filter kích hoạt 1");
        if (email != null && password != null) {
            String requestURI = req.getRequestURI();
            switch (role) {
                //Admin = 1
                case "1":
                    chain.doFilter(request, response);
                    break;
                // Leader = 3
                case "3":
                    if (requestURI.contains("/groupwork") || requestURI.contains("/user")) {
                        chain.doFilter(request, response);
                    }
                    break;
                //User = 4
                case "4":
                    if (requestURI.contains("/task")) {
                        chain.doFilter(request, response);
                    }
                    break;

                default:
                    resp.sendRedirect(req.getContextPath() + "/login");
                    break;
            }

        }
    }


    @Override
    public void destroy() {
//        Filter.super.destroy();
    }

}



