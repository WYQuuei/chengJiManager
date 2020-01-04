package cj.service;

import java.util.Date;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.tool.DB;

public class ChengJiDetailService {
    private DB db;
    private static ChengJiDetailService service;
    
    private ChengJiDetailService(){
        db = DB.getInstance();
    }

    public static ChengJiDetailService getInstance(){
        if(service == null){
            service = new ChengJiDetailService();
        }
        return service;
    }
    // ����id��ѯ�ɼ���������Ϣ 
    public List<ChengJiDetail> queryChengJiDetailByID(int id){
        String sql =" select d.id ,d.kemu,d.score ,c.total, c.average ,c.name,d.time  " + 
        			" from   t_ChengJi_detail d " + 
        			" inner join t_ChengJi c " + 
        			" on c.id = d.chengji_id " + 
        			" where c.id = ? order by d.time ";
        ResultSet rs = db.executeQueryByParam(sql,id);
        List<ChengJiDetail> ciDetailList = null;
        try {
        	
        	ciDetailList = this.getList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return ciDetailList;
    }
    
    // ����kemu��ѯ�ɼ���������Ϣ
    public List<ChengJiDetail> queryChengJiDetailByKemu(ChengJiDetail cjd){
    	String sql =" select d.id ,d.kemu,d.score ,c.total, c.average ,c.name,d.time  " + 
    			" from   t_ChengJi_detail d " + 
    			" inner join t_ChengJi c " + 
    			" on c.id = d.chengji_id " + 
    			" where d.kemu = ? and d.chengji_id = ? order by d.time ";
        ResultSet rs = db.executeQueryByParam(sql, cjd.getKemu(),cjd.getChengJi().getId());
        List<ChengJiDetail> cj=null;
        try {
            cj = this.getList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return cj;
    }
    
 // ����kemu��ѯ�ɼ���������Ϣ
    public List<ChengJiDetail> queryChengJiDetailByKemu(String kemu){
    	String sql =" select d.id ,d.kemu,d.score ,c.total, c.average ,c.name,d.time  " + 
    			" from   t_ChengJi_detail d " + 
    			" inner join t_ChengJi c " + 
    			" on c.id = d.chengji_id " + 
    			" where d.kemu = ?  order by d.time ";
        ResultSet rs = db.executeQueryByParam(sql,kemu);
        List<ChengJiDetail> cj=null;
        try {
            cj = this.getList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return cj;
    }
    
    //�õ��ɼ���������Ϣ
    private List<ChengJiDetail> getList(ResultSet rs) throws SQLException{
    	List<ChengJiDetail> cjDetailList = new ArrayList<ChengJiDetail>();
        while(rs.next()){
        	ChengJiDetail cjd= new ChengJiDetail();
        	cjd.setId(rs.getInt("id"));
        	cjd.setKemu(rs.getString("kemu"));
        	cjd.setScore(rs.getInt("score"));
        	cjd.setTime(rs.getDate("time"));
            ChengJi cj = new ChengJi();
            cj.setName(rs.getString("name"));
            cj.setTotal(rs.getDouble("total"));
            cj.setAverage(rs.getDouble("average"));
            cjd.setChengJi(cj);
            cjDetailList.add(cjd);
        }
        return cjDetailList;
    }
    
    //���ݳɼ�������Ϣ����һ���ɼ�����ϸ
    public boolean insertChengJiDetail(ChengJiDetail cjd){
        String sql = " insert into t_ChengJi_detail(kemu,score,chengji_id,time) values(?,?,?,?) ";
        int count = db.executeUpdate(sql,cjd.getKemu(),cjd.getScore(),cjd.getChengJi().getId(),new Date());
        db.close();
        if(count>0){
            return true;
        }else{
            return false;
        }
    }
    
    // ���ݳɼ���detail_idɾ���ɼ�����ϸ
    public boolean deleteALLChengJiDetail(int detail_id){
        String sql = "delete from t_ChengJi_detail where ChengJi_id = ?";
        int count = db.executeUpdate(sql,detail_id);
        db.close();
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
    
    // ���ݳɼ���idɾ���ɼ�����ϸ
    public boolean deleteChengJiDetailById(int id){
        String sql = "delete from t_ChengJi_detail where id = ?";
        int count = db.executeUpdate(sql,id);
        db.close();
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }
    
    // ���ݳɼ�����ϸid�޸ĳɼ�����ϸ
    public boolean updateChengJiDetail(ChengJiDetail cjd){
    	String sql = "update t_ChengJi_detail set kemu = ? ,score=? where id = ?";
        int count = db.executeUpdate(sql,cjd.getKemu(),cjd.getScore(),cjd.getId());
        db.close();
        if(count > 0){
            return true;
        }else {
            return false;
        }
    }

    
}
