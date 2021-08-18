package mx.edu.utez.model.cartas;

import mx.edu.utez.model.type.BeanType;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCartas {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final Logger CONSOLE= LoggerFactory.getLogger(DaoCartas.class);

    public List<BeanCartas> findAll(){
        List<BeanCartas> listCartas = new ArrayList<>();
        try{
            con= ConnectionMySQL.getConnection() ;
            cstm= con.prepareCall("SELECT C.*,T.idType,T.type,T.status as 'status_type' FROM cartas C INNER  JOIN  types T WHERE C.idType=T.idType ");
            rs= cstm.executeQuery();


            while(rs.next()){
                BeanType beanType = new BeanType();
                BeanCartas beanCartas = new BeanCartas();

                beanType.setIdType(rs.getInt("idType"));
                beanType.setType(rs.getString("type"));
                beanType.setStatus(rs.getInt("status_type"));

                beanCartas.setIdCartas(rs.getInt("idCartas"));
                beanCartas.setNombre(rs.getString("nombre"));
                beanCartas.setFecha(rs.getString("fecha"));
                beanCartas.setStatus(rs.getInt("status"));
                beanCartas.setIdType(beanType);

                listCartas.add(beanCartas);
            }

        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());

        }finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listCartas;
    }

    public BeanCartas findById (int idCartas){
        BeanCartas cartas = null;
        try {

            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM cartas AS C INNER JOIN types AS T ON C.idType = T.idType WHERE C.idCartas = ?");
            cstm.setInt(1, idCartas);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanType type = new BeanType();
                cartas = new BeanCartas();

                type.setIdType(rs.getInt("idType"));
                type.setType(rs.getString("type"));
                type.setStatus(rs.getInt("status"));

                cartas.setIdCartas(rs.getInt("idCartas"));
                cartas.setNombre(rs.getString("nombre"));
                cartas.setFecha(rs.getString("fecha"));
                cartas.setStatus(rs.getInt("status"));

                cartas.setIdType(type);

            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return cartas;
    }

    public boolean create(BeanCartas cartas){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?)}");
            cstm.setString(1, cartas.getNombre());
            cstm.setString(2, cartas.getFecha());
            cstm.setInt(3, cartas.getIdType().getIdType());
            cstm.execute();
            flag = true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanCartas cartas){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?,?}");
            cstm.setString(1, cartas.getNombre());
            cstm.setString(2, cartas.getFecha());
            cstm.setInt(3, cartas.getIdType().getIdType());
            cstm.setInt(4,cartas.getIdCartas());

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(int idCartas){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete(?)}");
            cstm.setInt(1, idCartas);

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
}
