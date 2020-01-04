package cj.service;

import java.util.Date;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cj.encapsulation.ChengJi;
import cj.tool.DB;

public class ChengJiService {
    private DB db;
    private static ChengJiService service;
    private ChengJiService(){
        db = DB.getInstance();
    }

    public static ChengJiService getInstance(){
        if(service == null){
            service = new ChengJiService();
        }
        return service;
    }
    // 查询所有成绩单信息 
    public List<ChengJi> queryChengJi(){
        String sql = " SELECT c.id ,c.name ,c.total, c.average ,c.time "
        		   + " FROM   t_ChengJi c order by c.time ";
        ResultSet rs = db.executeQueryNoParam(sql);
        List<ChengJi> ChengJi = null;
        try {
        	
        	ChengJi = this.getList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return ChengJi;
    }
    
    // 查询name是否已经存在
    public List<ChengJi> queryChengJiByName(String name){
        String sql = " select * from t_ChengJi where name = ? ";
        ResultSet rs = db.executeQueryByParam(sql, name );
        List<ChengJi> cj=null;
        try {
            cj = this.getList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return cj;
    }
    
    // 根据id查询一个成绩单的信息 
    public ChengJi queryChengJiById(int id){
        String sql = " SELECT c.id ,c.name ,c.total, c.average ,c.time "
        		   + " FROM   t_ChengJi c where id = ? ";
        ResultSet rs = db.executeQueryByParam(sql, id );
        ChengJi chengji = null;
        try {
        	while(rs.next()) {
        		chengji = new ChengJi();
                chengji.setId(rs.getInt("id"));
                chengji.setName(rs.getString("name"));
                chengji.setTotal(rs.getDouble("total"));
                chengji.setAverage(rs.getDouble("average"));
                chengji.setTime(rs.getDate("time"));
        	}
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return chengji;
    }
    
    //得到成绩单信息
    private List<ChengJi> getList(ResultSet rs) throws SQLException{
    	List<ChengJi> ChengJiList = new ArrayList<ChengJi>();
        ChengJi chengji;
        while(rs.next()){
                    chengji = new ChengJi();
                    chengji.setId(rs.getInt("id"));
                    chengji.setName(rs.getString("name"));
                    chengji.setTotal(rs.getDouble("total"));
                    chengji.setAverage(rs.getDouble("average"));
                    chengji.setTime(rs.getDate("time"));
                    ChengJiList.add(chengji);
        }
        return ChengJiList;
    }
    
    //根据name插入一条成绩单
    public boolean insertChengJi(ChengJi cj){
        String sql = " insert into t_ChengJi(name,time) values(?,?) ";
        int count = db.executeUpdate(sql,cj.getName(),new Date());
        db.close();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    
    //根据成绩单id删除成绩单
    public boolean deleteChengJiById(int id){
        String sql = "delete from t_ChengJi where id = ?";
        int count = db.executeUpdate(sql,id);
        db.close();
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
    
    // 根据成绩单id修改
    public boolean updateChengJi(ChengJi cj){
    	String sql = "update t_ChengJi set name=? where id = ?";
        int count = db.executeUpdate(sql,cj.getName(),cj.getId());
        db.close();
        if(count > 0){
            return true;
        }else {
            return false;
        }
    }
    
    //在insert和update总成绩和平均分
    public void updateChengJiTotalAndAvg(){
    	
        String sql = "UPDATE t_chengji LEFT JOIN  " + 
        		"      (SELECT d.chengji_id,SUM(d.score) AS s_sum,AVG(d.score) AS s_avg  " + 
        		"       FROM t_ChengJi_detail d  " + 
        		"       GROUP BY d.chengji_id ) temp   " + 
        		"ON temp.chengji_id = t_chengji.id  " + 
        		"SET t_chengji.total=temp.s_sum ,t_chengji.average=temp.s_avg; ";
        
        int count = db.executeUpdate(sql);
        db.close();
    }
}
