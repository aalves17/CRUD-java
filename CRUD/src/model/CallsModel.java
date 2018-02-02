package model;

import java.util.*;

import bancoDados.ConexaoBD;
import entidade.*;
import java.sql.*;

public class CallsModel {

	public List<Calls> listarTudo(){
		try{
			List<Calls> listCalls = new ArrayList<>();
			PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("select * from ingrid_calls");		
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Calls c = new Calls();
				c.setId(rs.getInt("id"));
				c.setCall_label(rs.getString("call_label"));
				c.setCall_critic(rs.getString("call_criticity"));
				c.setCall_create_date(rs.getString("call_create_date"));
				c.setCall_expire_date(rs.getString("call_expire_date"));
				listCalls.add(c);
			}
			return listCalls;
		}catch (Exception e) {
			return null;
		}
	}
	
	
	public Calls listar(int id){
		try{
			Calls c = new Calls();
			PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("select * from ingrid_calls where id=?");
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setId(rs.getInt("id"));
				c.setCall_label(rs.getString("call_label"));
				c.setCall_critic(rs.getString("call_criticity"));
				c.setCall_create_date(rs.getString("call_create_date"));
				c.setCall_expire_date(rs.getString("call_expire_date"));
			}
			return c;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean adicionar(Calls c){
		try{
			PreparedStatement ps = ConexaoBD.getConnection().prepareStatement("insert into ingrid_calls (id, call_label, call_criticity, call_create_date, call_expire_date) values (?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setString(2,  c.getCall_label());
			ps.setString(3,  c.getCall_critic());
			ps.setString(4,  c.getCall_create_date());
			ps.setString(5,  c.getCall_expire_date());
			return ps.executeUpdate() > 0;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean editar(Calls c){
		try{
			PreparedStatement ps = ConexaoBD.getConnection().prepareStatement(
					"update ingrid_calls set call_label=?, call_criticity=?, call_create_date=?, call_expire_date=? where id=?");			
			ps.setString(1,  c.getCall_label());
			ps.setString(2, c.getCall_critic());
			ps.setString(3,  c.getCall_create_date());
			ps.setString(4,  c.getCall_expire_date());
			ps.setInt(5,  c.getId());
			return ps.executeUpdate() > 0;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean deletar(Calls c){
		try{
			PreparedStatement ps = ConexaoBD.getConnection().prepareStatement(
					"delete from ingrid_calls where id=?");
			ps.setInt(1, c.getId());
			return ps.executeUpdate() > 0;
		}catch (Exception e) {
			return false;
		}
	}
}