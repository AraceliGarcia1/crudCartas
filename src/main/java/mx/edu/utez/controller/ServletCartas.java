package mx.edu.utez.controller;

import com.google.gson.Gson;
import mx.edu.utez.model.cartas.BeanCartas;
import mx.edu.utez.model.cartas.DaoCartas;
import mx.edu.utez.model.type.BeanType;
import mx.edu.utez.model.type.DaoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletCartas",  urlPatterns ={"/readCartas","/createCartas", "/updateCartas","/deleteCartas"})
public class ServletCartas extends HttpServlet {
    private Map map=new HashMap();
    final Logger CONSOLE= LoggerFactory.getLogger(ServletCartas.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        map.put("listCartas", new DaoCartas().findAll());
        map.put("listTypes",new DaoType().findAll());
        write(response, map);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action= request.getParameter("action");
        BeanCartas beanCartas=new BeanCartas();
        DaoCartas daoCartas=new DaoCartas();
        BeanType beanType=new BeanType();

        switch (action){
            case "create":

                beanType.setIdType(Integer.parseInt(request.getParameter("idType")));
                beanCartas.setNombre(request.getParameter("nombre"));
                beanCartas.setFecha(request.getParameter("fecha"));
                beanCartas.setIdType(beanType);
                boolean flag=daoCartas.create(beanCartas);
                if(flag){

                    map.put("message","Se registro correctamente");
                }else{
                    map.put("message", "No se registro correctamente");
                    CONSOLE.error("");
                }
                break;
            case "update":
                beanCartas.setIdCartas(Integer.parseInt(request.getParameter("idCartas")));
                beanType.setIdType(Integer.parseInt(request.getParameter("idType")));
                beanCartas.setNombre(request.getParameter("nombre"));
                beanCartas.setFecha(request.getParameter("fecha"));
                beanCartas.setIdType(beanType);
                boolean flag1=daoCartas.update(beanCartas);
                if(flag1){
                    map.put("message","Se registro correctamente");

                }else{
                    map.put("message", "No se registro correctamente");
                }
                break;

            case "delete":
                if(new DaoCartas().delete(Integer.parseInt(request.getParameter("idCartas")))){
                    request.setAttribute("mesage","Se ha eliminado correctamente");

                }else{
                    CONSOLE.error("No se ha eliminado");
                }

                break;

            default:
                request.getRequestDispatcher("/").forward(request,response);
                break;


        }
        response.sendRedirect(request.getContextPath()+"/readCartas");


    }
    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
        response.setContentType("aplication/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));

    }
}
