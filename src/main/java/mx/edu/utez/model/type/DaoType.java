package mx.edu.utez.model.type;

import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoType {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final Logger CONSOLE= LoggerFactory.getLogger(DaoType.class);

    public List<BeanType> findAll(){
        List<BeanType> listTypes = new ArrayList<>();
        try{
            con= ConnectionMySQL.getConnection() ;
            cstm= con.prepareCall("SELECT * FROM types ");
            rs= cstm.executeQuery();

            while(rs.next()){
                BeanType beanType = new BeanType();

                beanType.setIdType(rs.getInt("idType"));
                beanType.setType(rs.getString("type"));
                beanType.setStatus(rs.getInt("status"));
                listTypes.add(beanType);
            }

        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());

        }finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listTypes;
    }
}
